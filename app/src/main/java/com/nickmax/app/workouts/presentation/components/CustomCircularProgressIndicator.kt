package com.nickmax.app.workouts.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.nickmax.app.R
import com.nickmax.app.ui.theme.Grey300Text
import com.nickmax.app.ui.theme.Orange400

@Composable
fun CustomCircularProgressIndicator(isEnabled: MutableState<Boolean>) {

    if (isEnabled.value) {
        Dialog(onDismissRequest = { }) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(10.dp, 5.dp, 10.dp, 10.dp)
                    .size(width = 500.dp, height = 100.dp),
                elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = Orange400
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.wait),
                        fontSize = 14.sp,
                        color = Grey300Text
                    )
                }
            }
        }
    }

}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun CustomCircularProgressIndicatorPreview() {
    CustomCircularProgressIndicator(isEnabled = mutableStateOf(true))
}