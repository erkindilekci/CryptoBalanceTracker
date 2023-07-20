package com.erkindilekci.portfolio.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.erkindilekci.portfolio.PortfolioScreen

fun NavGraphBuilder.portfolioNavigation() {
    composable("portfolio_screen") {
        PortfolioScreen()
    }
}
