package com.nickmax.app.workouts.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nickmax.app.workouts.domain.model.ThrowResult
import com.nickmax.app.workouts.domain.model.Workout

@Database(
    entities = [Workout::class, ThrowResult::class],
    version = 4
)
abstract class WorkoutDatabase : RoomDatabase() {

    abstract val workoutDao: WorkoutDao

    companion object {
        const val DATABASE_NAME = "workouts_db"
    }
}