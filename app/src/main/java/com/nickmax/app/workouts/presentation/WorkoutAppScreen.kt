package com.nickmax.app.workouts.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.nickmax.app.workouts.presentation.components.CustomSnackbar
import com.nickmax.app.workouts.presentation.navigation.BottomNavigationView
import com.nickmax.app.workouts.presentation.navigation.MainNavigationGraph

@Composable
fun WorkoutAppScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationView(navController = navController)
        },
        snackbarHost = {
            CustomSnackbar(scaffoldState.snackbarHostState)
        }
    ) {
        MainNavigationGraph(navController = navController, scaffoldState)
    }
}