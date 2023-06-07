package com.nickmax.app.workouts.domain.use_case

import com.nickmax.app.workouts.domain.model.ThrowResult
import com.nickmax.app.workouts.domain.model.Workout

class WorkoutUseCases(
    val getWorkouts: GetWorkouts,
    val deleteWorkout: DeleteWorkout,
    val addWorkout: AddWorkout,
    val saveThrowResults: SaveThrowResults,
    val getThrowResults: GetThrowResults,
    val getWorkoutsListSize: GetWorkoutsListSize,
    val updateParentId: UpdateParentId
)