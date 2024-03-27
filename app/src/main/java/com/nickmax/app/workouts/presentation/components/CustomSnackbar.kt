package com.nickmax.app.workouts.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nickmax.app.R
import com.nickmax.app.ui.theme.Grey300Text
import com.nickmax.app.ui.theme.Orange10
import com.nickmax.app.ui.theme.Orange100
import com.nickmax.app.ui.theme.Orange400
import kotlinx.coroutines.launch

@Composable
fun CustomSnackbar(snackbarHostState: SnackbarHostState) {


    SnackbarHost(snackbarHostState) { data ->
        val text = stringResource(id = R.string.advice_first) + " " + data.message + stringResource(
            id = R.string.advice_second
        )
        Snackbar(
            modifier = Modifier
                .padding(16.dp)
                .height(88.dp),
            backgroundColor = Orange10,
            contentColor = Orange400,
            action = {
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = { data.performAction() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_close_24),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = text,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    fontWeight = FontWeight.Normal,
                    color = Grey300Text,
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Preview
@Composable
fun SimplePreview() {
    val scaffoldState = rememberScaffoldState()
    var scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            CustomSnackbar(snackbarHostState = scaffoldState.snackbarHostState)
        }
    ) {
        scope.launch { scaffoldState.snackbarHostState.showSnackbar("28", duration = SnackbarDuration.Indefinite) }
    }
}