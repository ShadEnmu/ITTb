package com.nickmax.app.workouts.domain.use_case

import com.nickmax.app.workouts.domain.repository.WorkoutRepository

class UpdateParentId(private val repository: WorkoutRepository) {

    suspend operator fun invoke(parentId: Int, id: Int) {
        repository.updateParentId(parentId, id)
    }
}