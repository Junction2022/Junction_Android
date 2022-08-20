package com.jammin.myapplication.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jammin.myapplication.core.theme.Body1

@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    enabled: Boolean = true,
    onClick: () -> Unit,
    backgroundColor: Color,
    pressedBackgroundColor: Color,
    disabledBackgroundColor: Color,
    content: @Composable (isPressed: Boolean) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    val isPressed = interactionSource.collectIsPressedAsState()

    val btnColor =
        if (!enabled) disabledBackgroundColor else if (isPressed.value) pressedBackgroundColor else backgroundColor

    Box(
        modifier = modifier
            .background(
                color = btnColor,
                shape = shape
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
                enabled = enabled
            ),
        contentAlignment = Alignment.Center
    ) {
        content(isPressed.value)
    }
}

@Composable
fun BasicBigButton(
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    enabled: Boolean = true,
    onClick: () -> Unit,
    backgroundColor: Color,
    pressedBackgroundColor: Color,
    disabledBackgroundColor: Color,
    textColor: Color,
    disabledTextColor: Color
) {
    val textColor = if (enabled) textColor else disabledTextColor

    BasicButton(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = shape,
        enabled = enabled,
        onClick = onClick,
        backgroundColor = backgroundColor,
        pressedBackgroundColor = pressedBackgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
    ) {
        Body1(text = text, color = textColor)
    }
}

@Composable
fun BasicRoundButton(
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    enabled: Boolean = true,
    onClick: () -> Unit,
    backgroundColor: Color,
    pressedBackgroundColor: Color,
    disabledBackgroundColor: Color,
    textColor: Color,
    disabledTextColor: Color,
    round: Dp
) {

    val shape = RoundedCornerShape(round)

    BasicBigButton(
        modifier = modifier,
        text = text,
        shape = shape,
        onClick = onClick,
        backgroundColor = backgroundColor,
        pressedBackgroundColor = pressedBackgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
        textColor = textColor,
        disabledTextColor = disabledTextColor,
        enabled = enabled
    )
}