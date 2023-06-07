package com.nickmax.app.workouts.presentation.workout_history

import com.nickmax.app.workouts.domain.model.Workout
import com.nickmax.app.workouts.domain.util.WorkoutOrder

sealed class WorkoutsHistoryEvent {
    data class Order(val workoutOrder: WorkoutOrder): WorkoutsHistoryEvent()
    data class DeleteWorkout(val workout: Workout): WorkoutsHistoryEvent()
    object ToggleOrderSection: WorkoutsHistoryEvent()
}