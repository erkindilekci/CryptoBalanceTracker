package com.erkindilekci.cryptobalancetracker.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.erkindilekci.detail.navigation.detailScreen
import com.erkindilekci.favorites.navigation.favoritesNavigation
import com.erkindilekci.market.navigation.marketScreen
import com.erkindilekci.onboarding.navigation.onBoardingScreen
import com.erkindilekci.portfolio.navigation.portfolioNavigation
import com.erkindilekci.search.navigation.searchScreen
import com.erkindilekci.settings.navigation.settingsScreen
import com.erkindilekci.signin.navigation.signInNavigation
import com.erkindilekci.signup.navigation.signUpScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome_screen") {

        onBoardingScreen(navController = navController)
        favoritesNavigation()
        signInNavigation(navController = navController)
        signUpScreen(navController = navController)
        marketScreen(navController = navController)
        searchScreen(navController = navController)
        detailScreen(navController = navController)
        settingsScreen(navController = navController)
        portfolioNavigation()
    }
}
