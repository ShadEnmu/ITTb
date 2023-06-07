package com.nickmax.app.workouts.domain.use_case

import com.nickmax.app.workouts.domain.model.ThrowResult
import com.nickmax.app.workouts.domain.repository.WorkoutRepository

class SaveThrowResults(private val repository: WorkoutRepository) {
    suspend operator fun invoke(throwsResult: ThrowResult){
        repository.insertThrowResult(throwsResult)
    }
}