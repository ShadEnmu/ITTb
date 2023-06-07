package com.nickmax.app.workouts.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.nickmax.app.workouts.presentation.navigation.BottomNavigationView
import com.nickmax.app.workouts.presentation.navigation.MainNavigationGraph

@Composable
fun WorkoutAppScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationView(navController = navController)
        }
    ) {
        MainNavigationGraph(navController = navController)
    }
}