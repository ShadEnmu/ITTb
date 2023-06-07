package com.nickmax.app.workouts.domain.repository

import com.nickmax.app.workouts.domain.model.ThrowResult
import com.nickmax.app.workouts.domain.model.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {

    fun getWorkouts(): Flow<List<Workout>>

    suspend fun getWorkoutById(id: Int): Workout?

    suspend fun getWorkoutsListSize() : Int


    suspend fun insertWorkout(workout: Workout)

    suspend fun deleteWorkout(workout: Workout)

    fun getThrowResults(): Flow<List<ThrowResult>>

    fun getThrowResultsByParentId(parentId: Int): Flow<List<ThrowResult>>

    suspend fun insertThrowResult(throwResult: ThrowResult)

    suspend fun deleteThrowResult(throwResult: ThrowResult)

    suspend fun updateParentId(parentId: Int, id: Int)
}