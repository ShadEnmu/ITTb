package com.nickmax.app.workouts.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nickmax.app.R
import com.nickmax.app.ui.theme.Orange400

@Composable
fun ButtonWithIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    cornerRadius: Dp = 10.dp,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = buttonColors,
        shape = RoundedCornerShape(cornerRadius),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus_in_circle_icon),
                contentDescription = "",
            )
            Text(
                text = stringResource(id = R.string.add_workout),
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonWithIconPreview() {
    ButtonWithIcon(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(65.dp),
        onClick = {},
        buttonColors = ButtonDefaults.buttonColors(
            backgroundColor = Orange400,
            contentColor = Color.White
        ),
    )
}