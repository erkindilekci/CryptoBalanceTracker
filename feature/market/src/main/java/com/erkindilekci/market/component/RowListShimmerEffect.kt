package com.erkindilekci.market.component

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowListShimmerEffect() {
    LazyRow(modifier = Modifier.padding(start = 10.dp)) {
        items(count = 5) {
            AnimatedShimmerItem()
        }
    }
}

@Composable
fun AnimatedShimmerItem() {
    val infiniteTransition = rememberInfiniteTransition()
    val alphaAnimation = infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    RowShimmerListItem(alpha = alphaAnimation.value)
}

@Composable
fun RowShimmerListItem(alpha: Float) {
    Surface(
        modifier = Modifier
            .width(220.dp)
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.secondary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Surface(
                modifier = Modifier
                    .width(60.dp)
                    .height(15.dp)
                    .alpha(alpha),
                shape = RoundedCornerShape(99.dp),
                color = Color.Gray
            ) {}
            Spacer(modifier = Modifier.height(5.dp))
            Surface(
                modifier = Modifier
                    .width(60.dp)
                    .height(15.dp)
                    .alpha(alpha),
                shape = RoundedCornerShape(99.dp),
                color = Color.Gray
            ) {}

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
