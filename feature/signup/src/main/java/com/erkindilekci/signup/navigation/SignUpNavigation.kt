package com.erkindilekci.signup.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.erkindilekci.signup.SignUpScreen

fun NavGraphBuilder.signUpScreen(navController: NavHostController) {
    composable("signup_screen") {
        SignUpScreen(navController = navController)
    }
}
