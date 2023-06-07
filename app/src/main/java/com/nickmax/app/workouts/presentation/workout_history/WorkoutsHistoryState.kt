package com.nickmax.app.workouts.presentation.workout_history

import com.nickmax.app.workouts.domain.model.ThrowResult
import com.nickmax.app.workouts.domain.model.Workout
import com.nickmax.app.workouts.domain.util.OrderType
import com.nickmax.app.workouts.domain.util.WorkoutOrder

data class WorkoutsHistoryState(
    val workouts: List<Workout> = emptyList(),
    val workoutOrder: WorkoutOrder = WorkoutOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
    val throws: List<ThrowResult> = emptyList()
)