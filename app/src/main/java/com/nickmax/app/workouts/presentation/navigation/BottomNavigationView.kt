package com.nickmax.app.workouts.presentation.navigation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nickmax.app.R
import com.nickmax.app.ui.theme.Grey300
import com.nickmax.app.ui.theme.Grey300Text
import com.nickmax.app.ui.theme.Orange400

@Composable
fun BottomNavigationView(navController: NavController) {

    BottomNavigation(backgroundColor = Color.White, contentColor = Grey300Text)
    {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavigationItem(
            selected = currentRoute == BottomNavigationItems.WorkoutsScreen.screen_route,
            onClick = {
                navController.navigate(BottomNavigationItems.WorkoutsScreen.screen_route) {
                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            selectedContentColor = Orange400,
            unselectedContentColor = Grey300Text,
            alwaysShowLabel = false,
            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationItems.WorkoutsScreen.icon),
                    contentDescription = "workouts screen"
                )
            }
        )

        IconButton(
            onClick = { /*TODO*/ },
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_middle_button),
                    contentDescription = "what u need know",
                    modifier = Modifier.fillMaxHeight().align(Alignment.CenterVertically)
                )
            })

        BottomNavigationItem(
            selected = currentRoute == BottomNavigationItems.HistoryScreen.screen_route,
            onClick = {
                navController.navigate(BottomNavigationItems.HistoryScreen.screen_route) {
                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            selectedContentColor = Orange400,
            unselectedContentColor = Grey300Text,
            alwaysShowLabel = false,
            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationItems.HistoryScreen.icon),
                    contentDescription = "workouts screen"
                )
            }
        )

    }
}
