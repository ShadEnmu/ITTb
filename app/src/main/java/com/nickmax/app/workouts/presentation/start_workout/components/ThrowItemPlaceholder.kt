package com.nickmax.app.workouts.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nickmax.app.R
import com.nickmax.app.ui.theme.Orange200

@Composable
fun ThrowItemPlaceholder(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.width(30.dp))
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_throw_success_icon),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.White)
        )
        Spacer(modifier = Modifier.width(25.dp))
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_throw_angle_icon),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.White)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "-- °",
            color = Color.White,
            fontSize = 25.sp,
        )
        Spacer(modifier = Modifier.width(30.dp))
        Text(
            text = "--",
            color = Color.White,
            fontSize = 25.sp,
        )
        Text(
            text = " %",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.width(89.dp))
        Text(
            text = "--:--",
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFE0C9)
@Composable
fun ThrowItemPlaceholderPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Orange200)
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ThrowItemPlaceholder(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Orange200, shape = RoundedCornerShape(10.dp))
                .height(75.dp)
                .border(width = 3.dp, color = Color.White, shape = RoundedCornerShape(10.dp))

        )

    }
}