package com.erkindilekci.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.erkindilekci.components.ColumnListShimmerEffect
import com.erkindilekci.favorites.FavoritesViewModel
import com.erkindilekci.favorites.component.FavoriteListView

@Composable
fun FavoritesScreen() {

    val viewModel: FavoritesViewModel = hiltViewModel()
    val coinList = viewModel.coinList.value
    val favoriteList = viewModel.favoriteList.value
    val loadingState = viewModel.loadingState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        if (loadingState) {
            Column {
                Spacer(modifier = Modifier.height(24.dp))
                ColumnListShimmerEffect()
            }
        } else {
            FavoriteListView(coinList, favoriteList, viewModel)
        }
    }
}
