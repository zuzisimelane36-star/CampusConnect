package com.example.campusconnect.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.campusconnect.ui.auth.AuthViewModel
import com.example.campusconnect.ui.home.HomeScreen
import com.example.campusconnect.ui.login.LoginScreen
import com.example.campusconnect.ui.profile.ProfileScreen
import com.example.campusconnect.ui.registration.RegistrationScreen
import com.example.campusconnect.ui.settings.SettingsScreen
import com.example.campusconnect.ui.settings.SettingsViewModel
import com.example.campusconnect.ui.splash.SplashScreen
import com.example.campusconnect.ui.timetable.TimetableScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Registration : Screen("registration")
    object Home : Screen("home")
    object Timetable : Screen("timetable")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
}

@Composable
fun AppNavigation(
    settingsViewModel: SettingsViewModel = viewModel()
) {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(onSplashFinished = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Login.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Registration.route)
                }
            )
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(
                authViewModel = authViewModel,
                onRegistrationSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Registration.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onNavigateTimetable = {
                    navController.navigate(Screen.Timetable.route)
                },
                onNavigateProfile = {
                    navController.navigate(Screen.Profile.route)
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                },
                settingsViewModel = settingsViewModel
            )
        }
        composable(Screen.Timetable.route) {
            TimetableScreen(
                onNavigateHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Timetable.route) { inclusive = true }
                    }
                },
                onNavigateProfile = {
                    navController.navigate(Screen.Profile.route)
                },
                onNavigateSettings = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                userEmail = "student@campus.edu",
                onNavigateHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Profile.route) { inclusive = true }
                    }
                },
                onNavigateTimetable = {
                    navController.navigate(Screen.Timetable.route)
                },
                onNavigateSettings = {
                    navController.navigate(Screen.Settings.route)
                },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Profile.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                viewModel = settingsViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
