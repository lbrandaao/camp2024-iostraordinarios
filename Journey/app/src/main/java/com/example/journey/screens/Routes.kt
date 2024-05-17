package com.example.journey.screens

sealed class Routes(val route: String) {
    data object OnBoarding: Routes("onboarding")
    data object Login: Routes("login")
    data object Registration: Routes("registration")
    data object FirstAccess: Routes("firstaccess")
    data object WithAppBars: Routes("withappbars")
    data object JourneysList: Routes("journeyslist")
    data object PostsList: Routes("postslist")
}