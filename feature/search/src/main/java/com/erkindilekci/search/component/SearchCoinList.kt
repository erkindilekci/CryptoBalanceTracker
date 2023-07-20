package com.erkindilekci.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.erkindilekci.domain.model.coin.CoinItem
import com.erkindilekci.util.FormatCoinPrice.Companion.formatPrice

@Composable
fun SearchCoinList(navController: NavHostController, coinList: List<CoinItem>) {
    LazyColumn(modifier = Modifier.padding(bottom = 40.dp)) {
        items(coinList.size) { index ->
            ListItem(coin = coinList[index], navController = navController)
        }
    }
}

@Composable
fun ListItem(navController: NavHostController, coin: CoinItem) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            .clickable {
                navController.navigate(
                    "detail_screen/${coin.name}/${coin.quote.USD.price.toFloat()}/${coin.symbol}/${coin.quote.USD.percent_change_24h}/${coin.quote.USD.percent_change_1h}"
                )
            },
        shape = RoundedCornerShape(15.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.secondary)
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = coin.name,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = coin.symbol,
                    color = Color(0xFFEBE7E7),
                    fontSize = 15.sp,
                )
            }

            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = "$${formatPrice(coin.quote.USD.price)}",
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(5.dp),
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "%${String.format("%.2f", coin.quote.USD.percent_change_24h)}",
                    color = if (coin.quote.USD.percent_change_24h > 0) Color.Green else Color.Red,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(end = 15.dp)
                )
            }
        }
    }
}
