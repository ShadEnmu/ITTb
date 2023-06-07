package com.nickmax.app.workouts.presentation.start_workout

import com.nickmax.app.workouts.domain.model.ThrowResult

sealed class StartWorkoutEvent {
    object StartWorkout: StartWorkoutEvent()
    object MadeThrow: StartWorkoutEvent()
    object EndWorkout: StartWorkoutEvent()
    object EndSession: StartWorkoutEvent()
}