package com.erkindilekci.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.erkindilekci.search.SearchScreen

fun NavGraphBuilder.searchScreen(navController: NavHostController) {
    composable("search_screen") {
        SearchScreen(navController = navController)
    }
}
