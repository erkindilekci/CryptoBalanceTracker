package com.erkindilekci.cryptobalancetracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.erkindilekci.components.BottomNavItem
import com.erkindilekci.components.BottomNavigationBar
import com.erkindilekci.cryptobalancetracker.navigation.SetupNavGraph
import com.erkindilekci.domain.usecase.onboarding.ReadOnboardingStateUseCase
import com.erkindilekci.ui.theme.CryptoBalanceTrackerTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoBalanceTrackerTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        if (currentRoute(navController) != "welcome_screen" &&
                            currentRoute(navController) != "signin_screen" &&
                            currentRoute(navController) != "signup_screen"
                        ) {

                            BottomNavigationBar(items = listOf(

                                BottomNavItem("Market", "market_screen", Icons.Default.Home),
                                BottomNavItem("Search", "search_screen", Icons.Default.Search),
                                BottomNavItem(
                                    "Favorite",
                                    "favorites_screen",
                                    Icons.Default.Favorite
                                ),
                                BottomNavItem(
                                    "Portfolio",
                                    "portfolio_screen",
                                    Icons.Default.List
                                ),
                                BottomNavItem(
                                    "Settings",
                                    "settings_screen",
                                    Icons.Default.Settings
                                )

                            ), navController = navController, onItemClick = {
                                navController.popBackStack()
                                navController.navigate(it.route)
                            })
                        }
                    }
                ) {
                    SetupNavGraph(navController)
                }
            }
        }
    }

    @Composable
    private fun currentRoute(navController: NavController): String? {
        val route = navController.currentBackStackEntryAsState().value?.destination?.route
        return route
    }
}
