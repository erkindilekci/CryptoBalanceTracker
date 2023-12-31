package com.erkindilekci.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erkindilekci.ui.theme.Orange

@Composable
fun InfoCard(lastChange: Double, text: String) {

    val color = if (lastChange < 0) Color.Red else Color.Green

    Card(shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier
                .size(140.dp, 80.dp)
                .background(Orange)
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color(0xFFEBE7E7),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "%${String.format("%.2f", lastChange)}",
                fontSize = 19.sp,
                color = color,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
