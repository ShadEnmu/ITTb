package com.nickmax.app.workouts.data.repository

import com.nickmax.app.workouts.data.data_source.WorkoutDao
import com.nickmax.app.workouts.domain.model.ThrowResult
import com.nickmax.app.workouts.domain.model.Workout
import com.nickmax.app.workouts.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow

class WorkoutRepositoryImpl(
    private val dao: WorkoutDao
) : WorkoutRepository {
    override fun getWorkouts(): Flow<List<Workout>> = dao.getWorkouts()

    override suspend fun getWorkoutById(id: Int): Workout? = dao.getWorkoutById(id)

    override suspend fun insertWorkout(workout: Workout) {
        dao.insertWorkout(workout)
    }

    override suspend fun deleteWorkout(workout: Workout) {
        dao.deleteWorkout(workout)
    }

    override fun getThrowResults(): Flow<List<ThrowResult>> = dao.getThrowResults()

    override fun getThrowResultsByParentId(parentId: Int): Flow<List<ThrowResult>> =
        dao.getThrowResultsByParentId(parentId)

    override suspend fun insertThrowResult(throwResult: ThrowResult) {
        dao.insertThrowResult(throwResult)
    }

    override suspend fun deleteThrowResult(throwResult: ThrowResult) {
        dao.deleteThrowResult(throwResult)
    }

    override suspend fun getWorkoutsListSize(): Int =
        dao.getWorkoutsListSize()

    override suspend fun updateParentId(parentId: Int, id: Int) {
        dao.updateParentId(parentId, id)
    }
}