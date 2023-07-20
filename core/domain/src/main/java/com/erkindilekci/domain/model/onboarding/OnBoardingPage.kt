package com.erkindilekci.domain.model.onboarding

import androidx.annotation.DrawableRes
import com.erkindilekci.domain.R

sealed class OnBoardingPage(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
) {
    object First : OnBoardingPage(
        title = "Manage Your Portfolio",
        description = "Utilize a crypto portfolio tracker to monitor your gains in real time",
        R.drawable.ic_onboard_first
    )

    object Second : OnBoardingPage(
        title = "Discover Top Coins",
        description = "Explore the best coins available in the market",
        R.drawable.ic_onboard_second
    )

    object Third : OnBoardingPage(
        title = "Monitor Cryptocurrencies",
        description = "Track price fluctuations 24/7 and manage your investments in real time",
        R.drawable.ic_onboard_third
    )
}
