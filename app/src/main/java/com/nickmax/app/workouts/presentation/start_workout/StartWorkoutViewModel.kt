package com.nickmax.app.workouts.presentation.start_workout

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nickmax.app.workouts.core.BluetoothServer
import com.nickmax.app.workouts.domain.model.ThrowResult
import com.nickmax.app.workouts.domain.model.Workout
import com.nickmax.app.workouts.domain.use_case.WorkoutUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StartWorkoutViewModel @Inject constructor(
    private val workoutUseCases: WorkoutUseCases,
    private val server: BluetoothServer
) : ViewModel() {
    private val _state: MutableState<StartWorkoutState> = mutableStateOf(StartWorkoutState())
    val state: MutableState<StartWorkoutState> = _state

    @SuppressLint("SimpleDateFormat")
    val sdf: SimpleDateFormat = SimpleDateFormat("hh:mm")
    private var currentAngle = 0
    private var maxAngle = 0

    fun onEvent(event: StartWorkoutEvent) {
        when (event) {
            is StartWorkoutEvent.StartWorkout -> {
                maxAngle = 0
                _state.value = state.value.copy(
                    activeWorkoutState = ActiveWorkoutState.Started,
                    listOfThrows = state.value.listOfThrows + state.value.currentListOfThrows,
                    currentListOfThrows = emptyList(),
                    currentWorkoutDateStart = Date().time,
                    workout = null
                )
                viewModelScope.launch {
                    server.connect("00:22:06:30:75:1D", object : BluetoothServer.BluetoothCallback {
                        override fun post(angle: String) {
                            try {
                                val temp = angle.toFloat().toInt()
                                currentAngle = temp
                                Log.d("DevTag", "onEvent: $currentAngle")
                            } catch (e: NumberFormatException) {

                            }
                        }
                    })
                    while (state.value.activeWorkoutState is ActiveWorkoutState.Started ||
                        state.value.activeWorkoutState is ActiveWorkoutState.Continues ||
                        state.value.activeWorkoutState is ActiveWorkoutState.CheckThrowSuccess
                    ) {
                        if (state.value.activeWorkoutState is ActiveWorkoutState.CheckThrowSuccess) {
                            delay(200)
                            continue
                        }
                        if (currentAngle in 89..179) {
                            while (true) {
                                if (currentAngle in (maxAngle + 1)..178) maxAngle = currentAngle
                                if (currentAngle < 86) {
                                    _state.value = state.value.copy(
                                        activeWorkoutState = ActiveWorkoutState.CheckThrowSuccess
                                    )
                                    break
                                }
                            }
                        }
                        delay(200)
                    }
                }
            }
            is StartWorkoutEvent.MadeThrow -> {
                viewModelScope.launch {

                    _state.value = state.value.copy(
                        activeWorkoutState = ActiveWorkoutState.Continues,
                        currentThrow = state.value.currentThrowState?.let {
                            ThrowResult(
                                getSuccessRate(maxAngle),
                                maxAngle,
                                sdf.format(Date().time),
                                it,
                            )
                        }
                    )
                    maxAngle = 0
                    state.value.currentThrow?.let { workoutUseCases.saveThrowResults(it) }
                    _state.value = state.value.copy(
                        currentListOfThrows = (state.value.currentListOfThrows + state.value.currentThrow!!)
                    )
                }

            }
            is StartWorkoutEvent.EndWorkout -> {
                viewModelScope.launch {
                    server.closeConnection()
                    val dateEnd = Date().time
                    _state.value = _state.value.copy(
                        activeWorkoutState = ActiveWorkoutState.Ended,
                        currentWorkoutDateEnd = dateEnd,
                        workout = Workout(
                            state.value.currentWorkoutDateStart!!,
                            dateEnd,
                            getAverageAngle(state.value.currentListOfThrows),
                            getAverageSuccessRate(state.value.currentListOfThrows),
                            getHitsRate(state.value.currentListOfThrows)
                        )
                    )
                    workoutUseCases.addWorkout(state.value.workout!!)
                    state.value.currentListOfThrows.forEach {
                        workoutUseCases.updateParentId(workoutUseCases.getWorkoutsListSize(), it.id)
                    }
                }
            }
            is StartWorkoutEvent.EndSession -> {

            }
        }
    }

    private fun getHitsRate(list: List<ThrowResult>): Int {
        var hitsRate = 0f
        list.forEach {
            if(it.isThrowSuccessful) hitsRate++
        }
        return ((hitsRate/list.size)*100).toInt()
    }

    private fun getAverageAngle(list: List<ThrowResult>): Int {
        var averageAngle = 0f
        list.forEach {
            averageAngle += it.throwAngle
        }
        return (averageAngle / list.size).toInt()
    }

    private fun getAverageSuccessRate(list: List<ThrowResult>): Int {
        var averageSuccessRate = 0f
        list.forEach {
            averageSuccessRate += it.successRate
        }
        return (averageSuccessRate / list.size).toInt()
    }

    private fun getSuccessRate(angle: Int): Int {
        val perfectAngle = 94f
        val deviation: Float = (perfectAngle / 100) * kotlin.math.abs(perfectAngle - angle)
        return (100 - deviation).toInt()
    }
}