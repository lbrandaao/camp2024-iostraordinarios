package com.example.journey

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
        //enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            JourneyTheme {
                NavHost(navController = navController, startDestination = "onboarding" ) {
                    composable("onboarding") {
                        OnBoardingScreen()
                    }

                    composable("login") {
                        LoginScreen()
                    }

                    composable("firstaccess") {
                        FirstAccessScreen()
                    }
                }
            }
        }
    }
}