package com.example.journey

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.journey.screens.FirstAccessScreen
import com.example.journey.screens.LoginScreen
import com.example.journey.screens.OnBoardingScreen
import com.example.journey.ui.theme.JourneyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            val navController = rememberNavController()
            val startDestination = if (onBoardingIsFinished(this@MainActivity)) {
                "login"
            } else {
                "onboarding"
            }

            JourneyTheme {
                NavHost(navController = navController, startDestination = startDestination ) {
                    composable("onboarding") {
                        OnBoardingScreen(this@MainActivity) {
                            navController.popBackStack()
                            navController.navigate("login")
                        }
                    }

                    composable("login") {
                        LoginScreen {
                            navController.popBackStack()
                            navController.navigate("firstaccess")
                        }
                    }

                    composable("firstaccess") {
                        FirstAccessScreen {

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