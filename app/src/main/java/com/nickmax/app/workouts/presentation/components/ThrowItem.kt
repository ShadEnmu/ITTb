package com.nickmax.app.workouts.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nickmax.app.R
import com.nickmax.app.ui.theme.Grey200
import com.nickmax.app.ui.theme.Orange400
import com.nickmax.app.workouts.domain.model.ThrowResult


@Composable
fun ThrowItem(
    throwResult: ThrowResult,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.width(30.dp))
        Image(
            imageVector = ImageVector.vectorResource(id = if (throwResult.isThrowSuccessful) R.drawable.ic_throw_success_icon else R.drawable.ic_throw_failure_icon),
            contentDescription = "",
        )
        Spacer(modifier = Modifier.width(25.dp))
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_throw_angle_icon),
            contentDescription = "",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = throwResult.throwAngle.toString() + " °",
            fontSize = 25.sp,
        )
        Spacer(modifier = Modifier.width(30.dp))
        Text(
            text = throwResult.successRate.toString(),
            fontSize = 25.sp,
        )
        Text(
            text = " %",
            fontSize = 20.sp,
            color = Orange400,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = throwResult.time,
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(end = 16.dp).fillMaxWidth(),
            textAlign = TextAlign.Right
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ThrowItemPreview() {
    ThrowItem(
        throwResult = ThrowResult(21, 59, "20:00", false),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Grey200, shape = RoundedCornerShape(10.dp))
            .height(75.dp),
        onDelete = {}
    )
}