package com.erkindilekci.portfolio.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.erkindilekci.domain.model.coin.CoinItem
import com.erkindilekci.domain.model.portfolio.PortfolioModel
import com.erkindilekci.portfolio.PortfolioViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PortfolioListView(
    portfolio: List<PortfolioModel>,
    coin: List<CoinItem>,
    viewModel: PortfolioViewModel
) {
    LazyColumn(
        contentPadding = PaddingValues(
            top = 10.dp,
            bottom = 60.dp,
            start = 20.dp,
            end = 20.dp
        )
    ) {
        items(portfolio.size) { index ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                LaunchedEffect(key1 = Unit) {
                    portfolio[index].symbol.let {
                        viewModel.deletePortfolio(it)
                        println("Doc Id: $it")
                        dismissState.reset()
                    }
                }
            }
            val degrees by animateFloatAsState(
                targetValue = if (dismissState.targetValue == DismissValue.Default) 0f else -45f
            )

            SwipeToDismiss(
                state = dismissState,
                directions = setOf(
                    DismissDirection.EndToStart
                ),
                dismissThresholds = { direction ->
                    FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                },
                background = { RedBackground(degrees = degrees) },
                dismissContent = {
                    if (portfolio.isNotEmpty() && coin.isNotEmpty()) {
                        PortfolioExpandableCard(portfolio[index], coin[index])
                    }
                }
            )
        }
    }
}

@Composable
fun RedBackground(degrees: Float) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxSize()
            .background(Color.Red)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier.rotate(degrees),
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.White
        )
    }
}
