package com.nickmax.app.workouts.presentation.start_workout

sealed class ActiveWorkoutState {
    object NotActive: ActiveWorkoutState()
    object Started: ActiveWorkoutState()
    object Continues: ActiveWorkoutState()
    object Ended: ActiveWorkoutState()
    object CheckThrowSuccess: ActiveWorkoutState()
}