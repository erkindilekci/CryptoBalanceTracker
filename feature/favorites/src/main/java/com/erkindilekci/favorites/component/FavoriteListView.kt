package com.erkindilekci.favorites.component

import androidx.compose.foundation.background
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erkindilekci.domain.model.FavoritesEntity
import com.erkindilekci.domain.model.coin.CoinItem
import com.erkindilekci.favorites.FavoritesViewModel
import com.erkindilekci.favorites.R
import com.erkindilekci.util.FormatCoinPrice.Companion.formatPrice

@Composable
fun FavoriteListView(
    coinList: List<CoinItem>,
    favoriteList: List<FavoritesEntity>,
    viewModel: FavoritesViewModel
) {
    LazyColumn {
        if (favoriteList.isNotEmpty() && coinList.isNotEmpty()) {
            item { 
                Spacer(modifier = Modifier.height(24.dp))
            }
            
            items(favoriteList.size) { index ->
                FavoriteListItem(
                    favoriteList[index],
                    coinList[index],
                    viewModel
                )
            }
        }
    }
}

@Composable
fun FavoriteListItem(
    favorite: FavoritesEntity,
    coin: CoinItem,
    viewModel: FavoritesViewModel,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.secondary)
        ) {
            Column(modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 15.dp)) {
                Text(text = coin.name, fontSize = 18.sp, color = Color.White)
                Text(text = coin.symbol, fontSize = 14.sp, color = Color.LightGray)
            }

            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically
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
                    modifier = Modifier.padding(end = 5.dp)
                )

                IconButton(onClick = { viewModel.deleteFavorite(favorite) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_star_24),
                        contentDescription = "delete icon",
                        tint = Color.Yellow
                    )
                }
            }
        }
    }
}
