package com.nickmax.app.workouts.presentation.workout_history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nickmax.app.workouts.domain.use_case.WorkoutUseCases
import com.nickmax.app.workouts.domain.util.OrderType
import com.nickmax.app.workouts.domain.util.WorkoutOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutsHistoryViewModel @Inject constructor(
    private val workoutUseCases: WorkoutUseCases
) : ViewModel() {

    private val _state = mutableStateOf(WorkoutsHistoryState())
    val state: State<WorkoutsHistoryState> = _state

    private var getWorkoutsJob: Job? = null
    private var getThrowResultsJob: Job? = null

    init {
        getWorkouts(WorkoutOrder.Date(OrderType.Descending))
        getThrowResults()
    }

    fun onEvent(event: WorkoutsHistoryEvent) {
        when (event) {
            is WorkoutsHistoryEvent.Order -> {
                if (state.value.workoutOrder::class == event.workoutOrder::class &&
                    state.value.workoutOrder.orderType == event.workoutOrder.orderType
                ) {
                    return
                }
            }
            is WorkoutsHistoryEvent.DeleteWorkout -> {
                viewModelScope.launch {
                    workoutUseCases.deleteWorkout(event.workout)
                }
            }
            is WorkoutsHistoryEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    fun getWorkouts(workoutOrder: WorkoutOrder) {
        workoutUseCases.getWorkouts(workoutOrder)
        getWorkoutsJob?.cancel()
        getWorkoutsJob =
            workoutUseCases.getWorkouts(workoutOrder).onEach { workouts ->
                _state.value = state.value.copy(
                    workouts = workouts,
                    workoutOrder = workoutOrder
                )
            }.launchIn(viewModelScope)
    }

    fun getThrowResults() {
        workoutUseCases.getThrowResults
        getThrowResultsJob?.cancel()
        getWorkoutsJob =
            workoutUseCases.getThrowResults().onEach { throwResults ->
                _state.value = state.value.copy(
                    throws = throwResults
                )
            }.launchIn(viewModelScope)
    }
}