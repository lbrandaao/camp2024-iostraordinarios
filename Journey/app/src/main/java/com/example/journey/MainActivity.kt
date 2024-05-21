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
import com.example.journey.screens.CreationScreen
import com.example.journey.screens.FirstAccessScreen
import com.example.journey.screens.JourneyDetailsScreen
import com.example.journey.screens.JourneysListScreen
import com.example.journey.screens.LoginScreen
import com.example.journey.screens.OnBoardingScreen
import com.example.journey.screens.PostsListScreen
import com.example.journey.screens.ProfileScreen
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
* (1) TELA DE RANKING: aguardando endpoint pra puxar ranking
* (2) Tela de Completar Jornada: Aguardando rota pra conectar com API
* (3) Tela de Feed (deixar parte de pesquisa não funcional por enquanto)
* (4) Adicionar reações
* */
class MainActivity : ComponentActivity() {
    private val userViewModel by viewModels<UserViewModel>()
    private val journeyViewModel by viewModels<JourneyViewModel>()
    private val postViewModel by viewModels<PostViewModel>()
    private val superpowerViewModel by viewModels<SuperpowerViewModel>()
    private val tagViewModel by viewModels<TagViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        TokenManager.init(this@MainActivity)

        setContent {
            val navControllerNoAppBars = rememberNavController()

            val startDestinationNoAppBars =
                if (onBoardingIsFinished(this@MainActivity)) {
                    if (TokenManager.getToken().isNotBlank()) {
                        Routes.WithAppBars.route
                    } else Routes.Login.route
                } else {
                    Routes.OnBoarding.route
                }

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
                        FirstAccessScreen(
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
                            userViewModel = userViewModel,
                            journeyViewModel = journeyViewModel,
                            onBackClick = {
                                navControllerNoAppBars.popBackStack()
                            },
                            onJourneyCompleted = {
                                navControllerNoAppBars.popBackStack()
                                navControllerNoAppBars.popBackStack()
                            }
                        )
                    }

                    composable(Routes.PostsFeed.route){}

                    composable(Routes.WithAppBars.route) {
                        val navControllerWithAppBars = rememberNavController()
                        Scaffold(
                            topBar = {
                                CustomTopAppBar(
                                    navControllerWithAppBars = navControllerWithAppBars,
                                    userViewModel = userViewModel
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
                                        JourneysListScreen(
                                            paddingValues = paddingValues,
                                            journeyViewModel = journeyViewModel,
                                            onJourneyDetailsClick = {
                                                navControllerNoAppBars.navigate(Routes.JourneyDetails.route)
                                            }
                                        )
                                    }

                                    composable(Routes.PostsList.route) {
                                        PostsListScreen(
                                            paddingValues = paddingValues,
                                            postViewModel = postViewModel,
                                            onSeeMoreButtonClick = {
                                                navControllerNoAppBars.navigate(Routes.PostsFeed.route)
                                            }
                                        )
                                    }

                                    composable(Routes.Profile.route) {
                                        ProfileScreen(
                                            paddingValues = paddingValues,
                                            userViewModel = userViewModel,
                                            onLogoutConfirm = {
                                                TokenManager.setToken("")
                                                navControllerWithAppBars
                                                    .popBackStack(0, true)

                                                navControllerNoAppBars
                                                    .popBackStack(0, true)
                                                navControllerNoAppBars.navigate(Routes.Login.route)

                                            }
                                        )
                                    }

                                    composable(Routes.Ranking.route) {}

                                    composable(Routes.Creation.route) {
                                        CreationScreen(
                                            context = this@MainActivity,
                                            paddingValues = paddingValues,
                                            userViewModel = userViewModel,
                                            journeyViewModel = journeyViewModel,
                                            postViewModel = postViewModel,
                                            tagViewModel = tagViewModel,
                                            superpowerViewModel = superpowerViewModel,
                                            onBackButtonClick = {
                                                navControllerWithAppBars.popBackStack()
                                            },
                                            onCreationConfirm = {
                                                navControllerWithAppBars.popBackStack()
                                                navControllerWithAppBars.navigate(Routes.JourneysList.route)
                                            }
                                        )
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