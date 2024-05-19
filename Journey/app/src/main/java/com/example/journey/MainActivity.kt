package com.example.journey

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import com.example.journey.data.remote.TokenManager
import com.example.journey.screens.CompleteJourneyScreen
import com.example.journey.screens.FirstAccessScreen
import com.example.journey.screens.JourneyDetailsScreen
import com.example.journey.screens.JourneysListScreen
import com.example.journey.screens.LoginScreen
import com.example.journey.screens.OnBoardingScreen
import com.example.journey.screens.PostsListScreen
import com.example.journey.screens.RegistrationScreen
import com.example.journey.screens.Routes
import com.example.journey.ui.theme.JourneyTheme
import com.example.journey.ui.theme.PrimaryBackgroundColor
import com.example.journey.viewModels.JourneyViewModel
import com.example.journey.viewModels.PostViewModel
import com.example.journey.viewModels.SuperpowerViewModel
import com.example.journey.viewModels.TagViewModel
import com.example.journey.viewModels.UserViewModel

/*
* PagerState na bottom bar
* Tela perfil, criação de post, carrossel de post
* TIRAR VERIFICAÇÃO DE TOKEN PARA REQUISIÇÃO GET NO SUPERPOWER E NA TAG
* JOURNEY NÃO TÁ RETORNANDO TAGS E SUPERPOWERS VERIFICAR A POSTS TAMBÉM
* */
class MainActivity : ComponentActivity() {
    private val userViewModel by viewModels<UserViewModel>()
    private val journeyViewModel by viewModels<JourneyViewModel>()
    private val postViewModel by viewModels<PostViewModel>()
    private val superpowerViewModel by viewModels<SuperpowerViewModel>()
    private val tagViewModel by viewModels<TagViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TokenManager.init(this@MainActivity)
        installSplashScreen()

        setContent {
            val navControllerNoAppBars = rememberNavController()
            val navControllerWithAppBars = rememberNavController()

            val startDestinationNoAppBars =
                if (onBoardingIsFinished(this@MainActivity)) {
                    if (TokenManager.getToken().isNotBlank()) {
                        userViewModel.setAuthenticatedUser()
                        Routes.WithAppBars.route
                    } else Routes.Login.route
            } else {
                Routes.OnBoarding.route
            }

            var startDestinationWithAppBars = Routes.JourneysList.route

            JourneyTheme {
                NavHost(
                    navController = navControllerNoAppBars,
                    startDestination = startDestinationNoAppBars
                ) {
                    composable(Routes.OnBoarding.route) {
                        OnBoardingScreen(this@MainActivity) {
                            navControllerNoAppBars.popBackStack()
                            navControllerNoAppBars.navigate(Routes.Login.route)
                        }
                    }

                    composable(Routes.Login.route) {
                        LoginScreen(
                            context = this@MainActivity,
                            userViewModel = userViewModel,
                            onAuthConfirm = {
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
                            context = this@MainActivity,
                            userViewModel = userViewModel,
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
                        FirstAccessScreen (
                            context = this@MainActivity,
                            userViewModel = userViewModel,
                            superpowerViewModel = superpowerViewModel,
                            tagViewModel = tagViewModel,
                            onRegistrationConfirm = {
                                navControllerNoAppBars.popBackStack()
                                navControllerNoAppBars.popBackStack()
                                navControllerNoAppBars.navigate(Routes.WithAppBars.route)
                            }
                        )
                    }

                    composable(Routes.JourneyDetails.route) {
                        JourneyDetailsScreen(
                            journey = journeyViewModel.getSelectedJourney(),
                            onBackClick = {
                                navControllerNoAppBars.popBackStack()
                            },
                            onAcceptClick = {
                                navControllerNoAppBars.navigate(Routes.CompleteJourney.route)
                            }
                        )
                    }

                    composable(Routes.CompleteJourney.route) {
                        CompleteJourneyScreen(
                            onBackClick = {
                                navControllerNoAppBars.popBackStack()
                            },
                            onSendClick = {
                                navControllerNoAppBars.popBackStack()
                                navControllerNoAppBars.popBackStack()
                            }
                        )
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
                                    startDestination = startDestinationWithAppBars
                                ) {
                                    composable(Routes.JourneysList.route) {
                                        JourneysListScreen(
                                            paddingValues = paddingValues,
                                            journeyViewModel = journeyViewModel,
                                            onJourneyDetailsClick = {
                                                startDestinationWithAppBars = Routes.JourneysList.route
                                                navControllerNoAppBars.navigate(Routes.JourneyDetails.route)
                                            }
                                        )
                                    }

                                    composable(Routes.PostsList.route) {
                                        PostsListScreen(paddingValues = paddingValues)
                                    }

                                    composable(Routes.Profile.route){}

                                    composable(Routes.Ranking.route){}

                                    composable(Routes.Creation.route){}
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