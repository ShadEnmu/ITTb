package com.nickmax.app.workouts.domain.use_case

import com.nickmax.app.workouts.domain.model.ThrowResult
import com.nickmax.app.workouts.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetThrowResults(
    private val repository: WorkoutRepository
) {
    operator fun invoke(): Flow<List<ThrowResult>> {
        return repository.getThrowResults().map { throws ->
            throws.sortedByDescending { it.time }
        }
    }
}