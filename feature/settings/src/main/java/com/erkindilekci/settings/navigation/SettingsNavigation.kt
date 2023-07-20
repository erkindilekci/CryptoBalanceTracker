package com.erkindilekci.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.erkindilekci.settings.SettingsScreen

fun NavGraphBuilder.settingsScreen(navController: NavHostController) {
    composable("settings_screen") {
        SettingsScreen(navController = navController)
    }
}
