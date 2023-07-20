package com.erkindilekci.favorites.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.erkindilekci.favorites.FavoritesScreen

fun NavGraphBuilder.favoritesNavigation() {
    composable(route = "favorites_screen") {
        FavoritesScreen()
    }
}
