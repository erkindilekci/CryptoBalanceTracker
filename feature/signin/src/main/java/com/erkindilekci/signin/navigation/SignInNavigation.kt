package com.erkindilekci.signin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.erkindilekci.signin.SignInScreen

fun NavGraphBuilder.signInNavigation(navController: NavHostController) {
    composable("signin_screen") {
        SignInScreen(navController = navController)
    }
}
