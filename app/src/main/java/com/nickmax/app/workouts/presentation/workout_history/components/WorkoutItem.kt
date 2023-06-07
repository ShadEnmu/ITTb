package com.nickmax.app.workouts.presentation.workout_history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nickmax.app.R
import com.nickmax.app.ui.theme.Grey300Text
import com.nickmax.app.ui.theme.Orange400
import com.nickmax.app.workouts.domain.model.Workout
import java.text.SimpleDateFormat

@Composable
fun WorkoutItem(
    onClick: () -> Unit,
    workout: Workout
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(165.dp)
    ) {
        val sdf : SimpleDateFormat = SimpleDateFormat("dd.MM.yy")
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .background(Orange400, RoundedCornerShape(10.dp))
                .size(165.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = sdf.format(workout.dateEnd),
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = workout.averageSuccessRate.toString() + "%",
                fontSize = 32.sp,
                color = Color.White
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_throw_angle_icon),
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = workout.averageAngle.toString() + "°",
                        fontSize = 14.sp,
                        color = Color.White,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_throw_success_icon),
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = workout.hitsRate.toString() + "%",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
//        Text(
//            text = "Lorem ipsum dolor sit amet consectetur...",
//            textAlign = TextAlign.Center,
//            fontSize = 14.sp,
//            color = Grey300Text
//        )
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutItemPreview() {
    WorkoutItem(onClick = { /*TODO*/ }, workout = Workout(0, 0, 68, 98, 35))
}