package com.erkindilekci.market.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import com.erkindilekci.domain.model.coin.CoinItem
import com.erkindilekci.util.FormatCoinPrice

@Composable
fun RowListView(
    coinList: List<CoinItem>,
    navController: NavHostController
) {
    LazyRow(modifier = Modifier.padding(bottom = 40.dp, start = 10.dp)) {
        items(coinList.size) { index ->
            RowListItem(coin = coinList[index], navController)
        }
    }
}

@Composable
fun RowListItem(coin: CoinItem, navController: NavHostController) {

    var expandState by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .width(220.dp)
            .padding(horizontal = 10.dp)
            .clickable {
                expandState = !expandState
            },
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        colors = if (expandState) listOf(Color(0xFFFFE082), Color(0xFFFFD54F))
                        else listOf(Color(0xFFFB8C00), Color(0xFFFF9800))
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = coin.name,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Card(
                modifier = Modifier.size(60.dp, 25.dp),
                shape = RoundedCornerShape(99.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = coin.symbol,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Black,
                        fontSize = 11.sp,
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            AnimatedVisibility(
                visible = expandState,
                enter = fadeIn() + expandVertically(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            ) {
                ExpandedSection(coin = coin, navController = navController)
            }
        }
    }
}

@Composable
fun ExpandedSection(coin: CoinItem, navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$${FormatCoinPrice.formatPrice(coin.quote.USD.price)}",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        IconButton(
            onClick = {
                navController.navigate(
                    "detail_screen/${coin.name}/${coin.quote.USD.price.toFloat()}/${coin.symbol}/${coin.quote.USD.percent_change_24h}/${coin.quote.USD.percent_change_1h}"
                )
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "next icon",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
