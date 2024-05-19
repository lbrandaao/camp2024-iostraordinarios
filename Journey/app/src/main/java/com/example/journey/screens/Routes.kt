package com.example.journey.screens

import okhttp3.Route

sealed class Routes(val route: String) {
    data object OnBoarding: Routes("onboarding")
    data object Login: Routes("login")
    data object Registration: Routes("registration")
    data object FirstAccess: Routes("firstaccess")
    data object WithAppBars: Routes("withappbars")
    data object JourneysList: Routes("journeyslist")
    data object JourneyDetails: Routes("journeydetails")
    data object CompleteJourney: Routes("completejourney")
    data object PostsList: Routes("postslist")

    data object Profile: Routes("profile")

    data object Ranking: Routes("ranking")

    data object Creation: Routes("creation")
}