package com.nickmax.app.workouts.domain.use_case

import com.nickmax.app.workouts.domain.repository.WorkoutRepository

class GetWorkoutsListSize(private val repository: WorkoutRepository) {

    suspend operator fun invoke() : Int {
        return repository.getWorkoutsListSize()
    }
}