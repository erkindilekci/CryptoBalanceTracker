package com.erkindilekci.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    width: Dp = 320.dp,
    height: Dp = 55.dp,
    cornerRadius: Dp = 10.dp,
    text: String = "Button",
    color: Color = Color(0xFFFDFDFD),
    textColor: Color = Color.DarkGray,
    isLoading: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        Modifier
            .defaultMinSize(
                minWidth = ButtonDefaults.MinWidth,
                minHeight = ButtonDefaults.MinHeight
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
    Button(
        modifier = Modifier.size(width, height),
        colors = ButtonDefaults.buttonColors(color),
        shape = RoundedCornerShape(cornerRadius),
        onClick = {
            if (onClick != null) {
                onClick()
            }
        }) {
        Text(text = text, color = textColor)
        if (isLoading) CustomCircularProgress()
    }
}
