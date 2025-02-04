package com.jammin.myapplication.core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jammin.myapplication.core.icon.JunctionIcon
import com.jammin.myapplication.core.theme.Body3
import com.jammin.myapplication.core.theme.Error
import com.jammin.myapplication.core.theme.JunctionColor
import com.jammin.myapplication.core.theme.JunctionTypography

@Composable
fun ReportInTextField(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    value: String,
    enabledSideBtn: Boolean = false,
    round: Dp = 5.dp,
    onValueChange: (String) -> Unit,
    error: String? = null,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    onClick: (() -> Unit)? = null,
    hint: String? = null,
    description: String? = null
) {
    val interactionSource = remember { MutableInteractionSource() }

    val borderColor: Color = if (error == null) JunctionColor.Gray100 else JunctionColor.Error

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    val textFieldFraction = if (enabledSideBtn) 0.75f else 0.9f

    Column {
        Box(
            modifier = modifier
                .height(44.dp)
                .wrapContentHeight(Alignment.CenterVertically)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(round),
                )
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(round)
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    if (onClick != null) {
                        onClick()
                    }
                }
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth(textFieldFraction)
                        .padding(start = 14.dp),
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = imeAction
                    ),
                    visualTransformation = if (!passwordVisible && isPassword) PasswordVisualTransformation() else VisualTransformation.None,
                    maxLines = 1,
                    textStyle = JunctionTypography.body3,
                    decorationBox = { innerTextField ->
                        if (value.isEmpty() && hint != null) {
                            Body3(text = hint, color = JunctionColor.Gray200)
                        }

                        innerTextField()
                    },
                )

                if (isPassword) {
                    Image(
                        modifier = Modifier
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) { passwordVisible = !passwordVisible },
                        painter = painterResource(id = JunctionIcon.Password(passwordVisible)),
                        contentDescription = null,
                        alpha = if (value.isNotEmpty()) 1f else 0f
                    )
                }
            }
        }

        if (error != null) {
            Error(
                text = error,
                modifier = Modifier.padding(start = 3.dp, top = 6.dp)
            )
        }

        if (description != null) {
            Body3(
                text = description,
                modifier = Modifier.padding(start = 3.dp, top = 6.dp),
                color = JunctionColor.Gray200
            )
        }
    }
}

@Preview
@Composable
fun PreviewMoizaTextField() {
    var value by remember { mutableStateOf<String?>(null) }
    var value2 by remember { mutableStateOf<String?>(null) }
    var value3 by remember { mutableStateOf<String?>(null) }
    var value4 by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // default text field
        ReportInTextField(
            value = value ?: "",
            onValueChange = { value = it }
        )

        // password text field
        ReportInTextField(
            value = value2 ?: "",
            onValueChange = { value2 = it },
            isPassword = true
        )

        // error text field
        ReportInTextField(
            value = value3 ?: "",
            onValueChange = { value3 = it },
            error = "특수문자는 사용할 수 없습니다!"
        )

        // description text field
        ReportInTextField(
            value = value4 ?: "",
            onValueChange = { value4 = it },
            description = "비밀번호는 4자리 이상 입력해주세요.",
            hint = "비밀번호"
        )
    }
}
