package com.nickmax.app.workouts.presentation.start_workout

import com.nickmax.app.workouts.domain.model.ThrowResult
import com.nickmax.app.workouts.domain.model.Workout

data class StartWorkoutState(
    val listOfThrows: List<ThrowResult> = listOf(),
    val activeWorkoutState: ActiveWorkoutState = ActiveWorkoutState.NotActive,
    val currentListOfThrows: List<ThrowResult> = listOf(),
    val currentThrow: ThrowResult? = null,
    val workout: Workout? = null,
    var currentWorkoutDateStart: Long? = null,
    var currentWorkoutDateEnd: Long? = null,
    var currentThrowState: Boolean? = null
)