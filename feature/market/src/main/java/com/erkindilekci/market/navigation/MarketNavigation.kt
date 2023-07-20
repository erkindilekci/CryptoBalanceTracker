package com.erkindilekci.market.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.erkindilekci.market.MarketScreen

fun NavGraphBuilder.marketScreen(navController: NavHostController) {
    composable("market_screen") {
        MarketScreen(navController = navController)
    }
}
