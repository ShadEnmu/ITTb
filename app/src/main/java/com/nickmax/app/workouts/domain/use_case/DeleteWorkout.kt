package com.nickmax.app.workouts.domain.use_case

import com.nickmax.app.workouts.domain.model.Workout
import com.nickmax.app.workouts.domain.repository.WorkoutRepository

class DeleteWorkout(
    private val repository: WorkoutRepository
) {

    suspend operator fun invoke(workout: Workout) {
        repository.deleteWorkout(workout)
    }
}