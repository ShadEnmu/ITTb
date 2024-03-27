    package com.nickmax.app.workouts.presentation.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nickmax.app.workouts.presentation.start_workout.StartWorkoutScreen
import com.nickmax.app.workouts.presentation.workout_history.WorkoutsHistoryScreen

@Composable
fun MainNavigationGraph(navController: NavHostController, mainScaffoldState: ScaffoldState) {

    NavHost(navController, startDestination = BottomNavigationItems.WorkoutsScreen.screen_route){

        composable(BottomNavigationItems.WorkoutsScreen.screen_route) {
            StartWorkoutScreen(mainScaffoldState = mainScaffoldState)
        }
        composable(BottomNavigationItems.HistoryScreen.screen_route) {
            WorkoutsHistoryScreen()
        }
    }

}