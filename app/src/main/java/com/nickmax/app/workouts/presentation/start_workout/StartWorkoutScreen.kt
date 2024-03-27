package com.nickmax.app.workouts.presentation.start_workout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nickmax.app.R
import com.nickmax.app.ui.theme.*
import com.nickmax.app.workouts.presentation.components.ButtonWithIcon
import com.nickmax.app.workouts.presentation.components.ThrowItem
import com.nickmax.app.workouts.presentation.start_workout.components.ActiveWorkoutList
import com.nickmax.app.workouts.presentation.start_workout.components.CustomAlertDialog
import kotlinx.coroutines.launch

@Composable
fun StartWorkoutScreen(
    startWorkoutViewModel: StartWorkoutViewModel = hiltViewModel(),
    mainScaffoldState: ScaffoldState
) {
    val imageHeight = 320.dp
    val scaffoldState = rememberScaffoldState()
    val columnState = rememberLazyListState()
    var scrolledY = 0f
    var previousOffset = 10
    val scope = rememberCoroutineScope()
    val message_first = stringResource(id = R.string.advice_first)
    val message_second = stringResource(id = R.string.advice_second)
    val accept = stringResource(id = R.string.accept_message)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = Grey100
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = innerPadding,
                state = columnState
            ) {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.man_basketball),
                        modifier = Modifier
                            .graphicsLayer {
                                scrolledY += columnState.firstVisibleItemScrollOffset - previousOffset
                                translationY = scrolledY * 0.4f
                                previousOffset = columnState.firstVisibleItemScrollOffset
                            }
                            .height(imageHeight)
                            .fillMaxWidth()
                            .background(Grey200),
                        contentDescription = "",
                    )
                    if (startWorkoutViewModel.state.value.activeWorkoutState is ActiveWorkoutState.NotActive
                        || startWorkoutViewModel.state.value.activeWorkoutState is ActiveWorkoutState.Ended
                    ) ButtonWithIcon(
                        onClick = { startWorkoutViewModel.onEvent(StartWorkoutEvent.StartWorkout) },
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(top = 16.dp)
                            .height(65.dp),
                        buttonColors = ButtonDefaults.buttonColors(
                            backgroundColor = Orange400,
                            contentColor = Color.White
                        )
                    )

                    if (startWorkoutViewModel.state.value.activeWorkoutState is ActiveWorkoutState.NotActive) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 104.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.not_active),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = Grey300Text
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = stringResource(id = R.string.put_on_the_armband),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Grey300Text
                            )
                        }
                    }

                    if (startWorkoutViewModel.state.value.activeWorkoutState !is ActiveWorkoutState.NotActive) ActiveWorkoutList(
                        throwResults = startWorkoutViewModel.state.value.currentListOfThrows.reversed(),
                        activeWorkoutState = startWorkoutViewModel.state.value.activeWorkoutState,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .background(Orange200, RoundedCornerShape(20.dp))
                            .fillMaxWidth()
                            .border(
                                width = 2.dp,
                                shape = RoundedCornerShape(20.dp),
                                color = Orange300
                            )
                            .padding(top = 12.dp, start = 15.dp, end = 15.dp, bottom = 14.dp)
                            .weight(1f),
                        onClick = {

                                if(startWorkoutViewModel.state.value.bestAngle != 0 && startWorkoutViewModel.state.value.currentListOfThrows.isNotEmpty())
                                scope.launch {
                                    val result = mainScaffoldState.snackbarHostState.showSnackbar(
                                        startWorkoutViewModel.state.value.bestAngle.toString(),
                                        duration = SnackbarDuration.Indefinite
                                    )

                                }

                            startWorkoutViewModel.onEvent(StartWorkoutEvent.EndWorkout)

                        },
                        timeStart = startWorkoutViewModel.state.value.currentWorkoutDateStart,
                        timeEnd = startWorkoutViewModel.state.value.currentWorkoutDateEnd
                    )
                }

                items(startWorkoutViewModel.state.value.listOfThrows.reversed()) { result ->
                    ThrowItem(
                        throwResult = result,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 15.dp, end = 15.dp)
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                            .height(75.dp)
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(70.dp))
                }
            }
        }
        if (startWorkoutViewModel.state.value.activeWorkoutState is ActiveWorkoutState.CheckThrowSuccess) {
            CustomAlertDialog(
                onClickYes = {
                    startWorkoutViewModel.state.value.currentThrowState = true
                    startWorkoutViewModel.onEvent(StartWorkoutEvent.MadeThrow)
                },
                onClickNo = {
                    startWorkoutViewModel.state.value.currentThrowState = false
                    startWorkoutViewModel.onEvent(StartWorkoutEvent.MadeThrow)
                }
            )
        }


    }
}



