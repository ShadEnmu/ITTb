package com.nickmax.app.workouts.presentation.navigation
import com.nickmax.app.R

sealed class BottomNavigationItems(var title: String, var icon: Int, var screen_route: String) {
    object WorkoutsScreen: BottomNavigationItems("Workouts", R.drawable.ic_workouts, "workouts")
    object HistoryScreen: BottomNavigationItems("History", R.drawable.ic_history, "history")
}
