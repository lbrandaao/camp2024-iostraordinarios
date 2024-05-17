package com.example.journey

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.journey.components.appbars.CustomBottomAppBar
import com.example.journey.components.appbars.CustomTopAppBar
import com.example.journey.screens.FirstAccessScreen
import com.example.journey.screens.JourneysListScreen
import com.example.journey.screens.LoginScreen
import com.example.journey.screens.OnBoardingScreen
import com.example.journey.screens.PostsListScreen
import com.example.journey.screens.RegistrationScreen
import com.example.journey.screens.Routes
import com.example.journey.ui.theme.JourneyTheme
import com.example.journey.ui.theme.PrimaryBackgroundColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            val navControllerNoAppBars = rememberNavController()
            val navControllerWithAppBars = rememberNavController()

            val startDestination = if (onBoardingIsFinished(this@MainActivity)) {
                Routes.Login.route
            } else {
                Routes.OnBoarding.route
            }

            JourneyTheme {
                NavHost(
                    navController = navControllerNoAppBars,
                    startDestination = Routes.WithAppBars.route
                ) {
                    composable(Routes.OnBoarding.route) {
                        OnBoardingScreen(this@MainActivity) {
                            navControllerNoAppBars.popBackStack()
                            navControllerNoAppBars.navigate(Routes.Login.route)
                        }
                    }

                    composable(Routes.Login.route) {
                        LoginScreen(
                            onConfirmButtonClick = {
                                navControllerNoAppBars.popBackStack()
                                navControllerNoAppBars.navigate(Routes.WithAppBars.route)
                            },
                            onRegistrationClick = {
                                navControllerNoAppBars.navigate(Routes.Registration.route)
                            }
                        )
                    }

                    composable(Routes.Registration.route) {
                        RegistrationScreen(
                            onBackButtonClick = {
                                navControllerNoAppBars.popBackStack()
                            },
                            onContinueButtonClick = {
                                navControllerNoAppBars.popBackStack()
                                navControllerNoAppBars.navigate(Routes.FirstAccess.route)
                            }
                        )
                    }

                    composable(Routes.FirstAccess.route) {
                        FirstAccessScreen {
                            navControllerNoAppBars.popBackStack()
                            navControllerNoAppBars.navigate(Routes.WithAppBars.route)
                        }
                    }

                    composable(Routes.WithAppBars.route) {
                        Scaffold(
                            topBar = {
                                CustomTopAppBar(
                                    navControllerNoAppBars = navControllerNoAppBars,
                                    navControllerWithAppBars = navControllerWithAppBars
                                )
                            },
                            bottomBar = {
                                CustomBottomAppBar(
                                    navController = navControllerWithAppBars
                                )
                            }
                        ) {
                            val paddingValues = it
                            Surface(
                                modifier = Modifier
                                    .fillMaxSize(),
                                color = PrimaryBackgroundColor
                            ) {
                                NavHost(
                                    navController = navControllerWithAppBars,
                                    startDestination = Routes.JourneysList.route
                                ) {
                                    composable(Routes.JourneysList.route) {
                                        JourneysListScreen(paddingValues = paddingValues)
                                    }

                                    composable(Routes.PostsList.route) {
                                        PostsListScreen(paddingValues = paddingValues)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onBoardingIsFinished(context: MainActivity): Boolean {
        val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isFinished", false)
    }
}