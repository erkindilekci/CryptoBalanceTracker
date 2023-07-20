package com.erkindilekci.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomInputText(
    labelText: String = "",
    backgroundColor: Color = MaterialTheme.colors.secondary,
    keyboardType: KeyboardType = KeyboardType.Text,
    initialText: String = "",
    textColor: Color = Color.White,
    onInput: (String) -> Unit,
) {
    var textState by remember { mutableStateOf(initialText) }

    TextField(
        value = textState.trim(),
        onValueChange = {
            textState = it
            onInput(it)
        },
        label = { Text(text = labelText, color = textColor) },
        singleLine = true,
        modifier = Modifier
            .width(320.dp)
            .height(55.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Next),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            textColor = textColor
        ),
        shape = RoundedCornerShape(15.dp)
    )
}
