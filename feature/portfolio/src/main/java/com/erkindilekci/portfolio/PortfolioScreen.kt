package com.erkindilekci.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.erkindilekci.components.LoadingCircularProgress
import com.erkindilekci.portfolio.component.PortfolioListView
import com.erkindilekci.util.FormatCoinPrice.Companion.formatPrice
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun PortfolioScreen() {

    val viewModel: PortfolioViewModel = hiltViewModel()
    val list = viewModel.selectedPortfolioList.value
    val coinList = viewModel.selectedCoinList.value
    val isLoading = viewModel.loadingState.value
    val lastBalance = viewModel.totalCurrentBalanceState.value
    val buyingPrice = viewModel.totalBuyingPriceState.value
    val profit = viewModel.profitState.value

    var refreshState by remember { mutableStateOf(false) }

    LaunchedEffect(refreshState) {
        if (refreshState) {
            viewModel.refresh()
            delay(100)
            refreshState = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshState),
        onRefresh = { refreshState = true }
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.primary),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                CurrentBalanceCard(lastBalance, buyingPrice, profit)

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Your Assets",
                    color = Color.White,
                    fontSize = 22.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp),
                    fontWeight = FontWeight.Medium
                )

                PortfolioListView(list, coinList, viewModel)
            }
            if (isLoading) LoadingCircularProgress()
        }
    }
}

@Composable
fun CurrentBalanceCard(
    lastBalance: Double,
    buyingPrice: Double,
    profit: Double,
) {
    val formattedLastBalance = formatPrice(lastBalance)
    val formattedBuyingPrice = formatPrice(buyingPrice)

    Card(
        modifier = Modifier
            .size(340.dp, 180.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color(0xFFFB8C00), Color(0xFFFF9800))
                    )
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Current Balance", color = Color(0xFFEBE7E7), fontSize = 17.sp)
            Text(
                text = "$$formattedLastBalance",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Medium
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Column(modifier = Modifier.align(Alignment.BottomStart)) {
                    Text(text = "Total Buying Price", color = Color(0xFFEBE7E7), fontSize = 16.sp)
                    Text(
                        text = "$$formattedBuyingPrice",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Column(modifier = Modifier.align(Alignment.BottomEnd)) {
                    Text(text = "Profit/Loss", color = Color(0xFFEBE7E7), fontSize = 16.sp)
                    Text(
                        text = "%${String.format("%.2f", profit)}",
                        color = if (profit > 0) Color.Green else Color.Red,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
