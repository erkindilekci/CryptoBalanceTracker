package com.erkindilekci.portfolio.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erkindilekci.domain.model.coin.CoinItem
import com.erkindilekci.domain.model.portfolio.PortfolioModel
import com.erkindilekci.util.FormatCoinPrice.Companion.formatPrice

@Composable
fun PortfolioExpandableCard(portfolio: PortfolioModel, coin: CoinItem) {
    var isExpandState by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpandState = !isExpandState },
        shape = RoundedCornerShape(12.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(MaterialTheme.colors.secondary)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = portfolio.name,
                            color = Color.White,
                            fontSize = 20.sp,
                        )

                        Text(
                            text = "(${portfolio.symbol})",
                            color = Color(0xFFEBE7E7),
                            fontSize = 18.sp,
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Amount",
                        color = Color(0xFFEBE7E7),
                        fontSize = 15.sp,
                    )

                    Text(
                        text = portfolio.amount,
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                }
                Text(
                    text = "$${formatPrice(portfolio.totalPrice.toDouble())}",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterEnd)
                )
            }
            AnimatedVisibility(
                visible = isExpandState,
                enter = fadeIn() + expandVertically(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            ) {
                SectionOfExpanded(portfolio = portfolio, coin = coin)
            }
        }
    }
}

@Composable
fun SectionOfExpanded(portfolio: PortfolioModel, coin: CoinItem) {
    val lastPrice = formatPrice(coin.quote.USD.price)
    val buyingPrice = formatPrice(portfolio.buyingPrice.toDouble())
    val profit =
        (coin.quote.USD.price - portfolio.buyingPrice.toDouble()) * 100 / portfolio.buyingPrice.toDouble()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.secondary)
    ) {
        Column(modifier = Modifier
            .align(Alignment.TopStart)
            .padding(top = 5.dp)) {
            Text(text = "Buying Price", fontSize = 15.sp, color = Color(0xFFEBE7E7))
            Text(text = buyingPrice, fontSize = 16.sp, color = Color.White)
        }

        Column(modifier = Modifier.align(Alignment.TopEnd)) {
            Text(text = "Last Price", fontSize = 15.sp, color = Color(0xFFEBE7E7))
            Text(text = lastPrice, fontSize = 16.sp, color = Color.White)
        }

        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Text(text = "Profit/Loss", fontSize = 15.sp, color = Color(0xFFEBE7E7))
            Text(
                text = "%${String.format("%.2f", profit)}",
                fontSize = 16.sp,
                color = if (profit >= 0) Color.Green else Color.Red
            )
        }
    }
}
