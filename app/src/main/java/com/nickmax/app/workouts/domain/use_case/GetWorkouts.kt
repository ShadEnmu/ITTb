package com.nickmax.app.workouts.domain.use_case

import com.nickmax.app.workouts.domain.model.Workout
import com.nickmax.app.workouts.domain.repository.WorkoutRepository
import com.nickmax.app.workouts.domain.util.OrderType
import com.nickmax.app.workouts.domain.util.WorkoutOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetWorkouts(
    private val repository: WorkoutRepository
) {

    operator fun invoke(
        workoutOrder: WorkoutOrder = WorkoutOrder.Date(OrderType.Descending)
    ): Flow<List<Workout>> {
        return repository.getWorkouts().map { workouts ->
            when (workoutOrder.orderType) {
                is OrderType.Ascending -> {
                    when (workoutOrder) {
                        is WorkoutOrder.Date -> workouts.sortedBy { it.dateStart }
                        is WorkoutOrder.SuccessRate -> workouts.sortedBy { it.averageSuccessRate }
                    }
                }
                is OrderType.Descending -> {
                    when (workoutOrder) {
                        is WorkoutOrder.Date -> workouts.sortedByDescending { it.dateStart }
                        is WorkoutOrder.SuccessRate -> workouts.sortedByDescending { it.averageSuccessRate }
                    }
                }
            }
        }
    }
}