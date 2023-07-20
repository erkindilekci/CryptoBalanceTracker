package com.erkindilekci.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
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
import androidx.compose.ui.unit.dp
import com.erkindilekci.ui.theme.Orange

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }

    Box(modifier = Modifier.padding(top = 20.dp)) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            label = { Text(text = "Search", color = Color.White) },
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            maxLines = 1,
            singleLine = true,
            modifier = modifier
                .width(350.dp)
                .height(55.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Orange,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                textColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp)
        )
    }
}
