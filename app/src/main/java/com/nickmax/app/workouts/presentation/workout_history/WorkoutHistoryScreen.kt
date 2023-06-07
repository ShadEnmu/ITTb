package com.nickmax.app.workouts.presentation.workout_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nickmax.app.R
import com.nickmax.app.ui.theme.Grey100
import com.nickmax.app.ui.theme.Grey300
import com.nickmax.app.ui.theme.Grey300Text
import com.nickmax.app.ui.theme.Orange400
import com.nickmax.app.workouts.domain.model.ThrowResult
import com.nickmax.app.workouts.domain.model.Workout
import com.nickmax.app.workouts.presentation.components.ThrowItem
import com.nickmax.app.workouts.presentation.workout_history.components.WorkoutItem

@Composable
fun WorkoutsHistoryScreen(
    workoutsHistoryViewModel: WorkoutsHistoryViewModel = hiltViewModel(),
) {
    LazyColumn(
        modifier = Modifier
            .background(Grey100)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.workouts),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Grey300Text
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grid_icon),
                        contentDescription = "",
                        tint = Grey300,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_horizontal_slider_icon),
                        contentDescription = "",
                        tint = Orange400,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
            LazyRow() {
                items(workoutsHistoryViewModel.state.value.workouts) { workout ->
                    WorkoutItem(onClick = { /*TODO*/ }, workout = workout)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.all_throws),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Grey300Text
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar_icon),
                        contentDescription = "",
                        tint = Grey300,
                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter_icon),
                        contentDescription = "",
                        tint = Orange400
                    )
                }
            }
        }
        items(workoutsHistoryViewModel.state.value.throws) { throwItem ->
            ThrowItem(
                throwResult = throwItem,
                modifier = Modifier
                    .padding(top = 10.dp, start = 15.dp, end = 15.dp)
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .height(75.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun WorkoutsHistoryScreenPreview() {
//    WorkoutsHistoryScreen(
//        listOfThrows = listOf(
//            ThrowResult(20, 54, "20:00", true),
//            ThrowResult(20, 54, "20:00", true),
//            ThrowResult(20, 54, "20:00", false),
//            ThrowResult(20, 54, "20:00", true),
//            ThrowResult(20, 54, "20:00", false),
//            ThrowResult(20, 54, "20:00", false),
//            ThrowResult(20, 54, "20:00", true),
//            ThrowResult(20, 54, "20:00", true),
//            ThrowResult(20, 54, "20:00", true),
//        ),
//        listOfWorkouts = listOf(
//            Workout(0, 0, 45, 32),
//            Workout(0, 0, 45, 32),
//            Workout(0, 0, 45, 32),
//            Workout(0, 0, 45, 32),
//            Workout(0, 0, 45, 32),
//            Workout(0, 0, 45, 32),
//            Workout(0, 0, 45, 32),
//            Workout(0, 0, 45, 32),
//            Workout(0, 0, 45, 32),
//            Workout(0, 0, 45, 32),
//            Workout(0, 0, 45, 32),
//            Workout(0, 0, 45, 32),
//        )
//    )
//}