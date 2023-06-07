package com.nickmax.app.di

import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import androidx.room.Room
import com.nickmax.app.workouts.core.BluetoothServer
import com.nickmax.app.workouts.data.data_source.WorkoutDatabase
import com.nickmax.app.workouts.data.repository.WorkoutRepositoryImpl
import com.nickmax.app.workouts.domain.repository.WorkoutRepository
import com.nickmax.app.workouts.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWorkoutDatabase(app: Application): WorkoutDatabase {
        return Room.databaseBuilder(
            app, WorkoutDatabase::class.java, WorkoutDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideWorkoutRepository(db: WorkoutDatabase): WorkoutRepository {
        return WorkoutRepositoryImpl(db.workoutDao)
    }

    @Provides
    @Singleton
    fun provideWorkoutUseCases(repository: WorkoutRepository): WorkoutUseCases {
        return WorkoutUseCases(
            getWorkouts = GetWorkouts(repository),
            deleteWorkout = DeleteWorkout(repository),
            addWorkout = AddWorkout(repository),
            saveThrowResults = SaveThrowResults(repository),
            getThrowResults = GetThrowResults(repository),
            getWorkoutsListSize = GetWorkoutsListSize(repository),
            updateParentId = UpdateParentId(repository)
        )
    }

    @Provides
    @Singleton
    fun provideBluetoothManager(@ApplicationContext context: Context) : BluetoothManager =
        context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

    @Provides
    @Singleton
    fun provideBluetoothAdapter(manager: BluetoothManager): BluetoothAdapter = manager.adapter


    @Provides
    @Singleton
    fun provideBluetoothServer(adapter: BluetoothAdapter): BluetoothServer =
        BluetoothServer(adapter)
}