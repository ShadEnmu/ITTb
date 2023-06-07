package com.nickmax.app.workouts.data.data_source

import androidx.room.*
import com.nickmax.app.workouts.domain.model.ThrowResult
import com.nickmax.app.workouts.domain.model.Workout
import kotlinx.coroutines.flow.Flow


@Dao
interface WorkoutDao {

    // Workouts dao
    @Query("SELECT * FROM workout")
    fun getWorkouts(): Flow<List<Workout>>

    @Query("SELECT * FROM workout WHERE id = :id")
    suspend fun getWorkoutById(id: Int): Workout?

    @Query("SELECT count(id) from workout")
    suspend fun getWorkoutsListSize() : Int

    @Query("UPDATE throwresult SET parentId= :parentId WHERE id= :id")
    suspend fun updateParentId(parentId: Int, id : Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: Workout)

    @Delete
    suspend fun deleteWorkout(workout: Workout)

    //Throw results dao
    @Query("SELECT * FROM throwresult")
    fun getThrowResults(): Flow<List<ThrowResult>>

    @Query("SELECT * FROM throwresult WHERE parentId = :parentId")
    fun getThrowResultsByParentId(parentId: Int): Flow<List<ThrowResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThrowResult(throwResult: ThrowResult)

    @Delete
    suspend fun deleteThrowResult(throwResult: ThrowResult)
}