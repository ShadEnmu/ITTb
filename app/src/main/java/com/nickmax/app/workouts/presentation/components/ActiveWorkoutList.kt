package com.nickmax.app.workouts.presentation.start_workout.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nickmax.app.R
import com.nickmax.app.ui.theme.Orange200
import com.nickmax.app.ui.theme.Orange300
import com.nickmax.app.ui.theme.Orange400
import com.nickmax.app.workouts.domain.model.ThrowResult
import com.nickmax.app.workouts.presentation.components.ThrowItem
import com.nickmax.app.workouts.presentation.components.ThrowItemPlaceholder
import com.nickmax.app.workouts.presentation.start_workout.ActiveWorkoutState
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@Composable
fun ActiveWorkoutList(
    throwResults: List<ThrowResult>,
    activeWorkoutState: ActiveWorkoutState,
    modifier: Modifier,
    onClick: () -> Unit = {},
    timeStart: Long?,
    timeEnd: Long?
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        val sdf: SimpleDateFormat = SimpleDateFormat("hh:mm")
        if (activeWorkoutState is ActiveWorkoutState.Started ||
            activeWorkoutState is ActiveWorkoutState.Continues ||
            activeWorkoutState is ActiveWorkoutState.CheckThrowSuccess
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .background(Orange200)
                    .clickable(onClick = onClick)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_end_workout_icon),
                    contentDescription = "",
                    tint = Orange400
                )
                Text(
                    text = stringResource(id = R.string.finish_workout),
                    color = Orange400,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
        if (activeWorkoutState is ActiveWorkoutState.Ended ) Text(
            text = stringResource(id = R.string.end_time) + " " + sdf.format(timeEnd),
            color = Orange400
        )
        if (activeWorkoutState is ActiveWorkoutState.Started) ThrowItemPlaceholder(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .fillMaxWidth()
                .background(color = Orange200, shape = RoundedCornerShape(10.dp))
                .height(75.dp)
                .border(
                    width = 3.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
        )
        if (activeWorkoutState is ActiveWorkoutState.Continues ||
            activeWorkoutState is ActiveWorkoutState.Ended ||
            activeWorkoutState is ActiveWorkoutState.CheckThrowSuccess
        )
            Column(
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                throwResults.forEach { result ->
                    ThrowItem(
                        throwResult = result, modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                            .height(75.dp)
                    )
                }
            }
        Text(
            text = stringResource(id = R.string.start_time) + " " + sdf.format(timeStart),
            color = Orange400
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ActiveWorkoutListPreview() {
    ActiveWorkoutList(
        throwResults = listOf(ThrowResult(21, 43, "20:00", true)),
        activeWorkoutState = ActiveWorkoutState.Ended,
        modifier = Modifier
            .padding(top = 15.dp)
            .background(Orange200, RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .border(width = 2.dp, shape = RoundedCornerShape(20.dp), color = Orange300)
            .padding(top = 12.dp, start = 15.dp, end = 15.dp, bottom = 14.dp),
        timeEnd = 0,
        timeStart = 0
    )
}