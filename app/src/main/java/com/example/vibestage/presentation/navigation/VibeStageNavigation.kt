package com.example.vibestage.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vibestage.presentation.splash.SplashScreen
import com.example.vibestage.presentation.onboarding.OnboardingScreen
import com.example.vibestage.presentation.auth.LoginScreen
import com.example.vibestage.presentation.auth.RegisterScreen
import com.example.vibestage.presentation.artist.ArtistDashboardScreen
import com.example.vibestage.presentation.promoter.PromoterDashboardScreen

/**
 * Rutas de navegación para VibeStage
 */
sealed class VibeStageRoutes(val route: String) {
    object Splash : VibeStageRoutes("splash")
    object Onboarding : VibeStageRoutes("onboarding")
    object Login : VibeStageRoutes("login")
    object Register : VibeStageRoutes("register/{userType}") {
        fun createRoute(userType: String) = "register/$userType"
    }
    object ArtistDashboard : VibeStageRoutes("artist_dashboard")
    object PromoterDashboard : VibeStageRoutes("promoter_dashboard")
}

/**
 * Navegación principal de VibeStage
 */
@Composable
fun VibeStageNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = VibeStageRoutes.Splash.route
    ) {
        composable(VibeStageRoutes.Splash.route) {
            SplashScreen(
                onNavigateToOnboarding = {
                    navController.navigate(VibeStageRoutes.Onboarding.route) {
                        popUpTo(VibeStageRoutes.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(VibeStageRoutes.Login.route) {
                        popUpTo(VibeStageRoutes.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(VibeStageRoutes.Onboarding.route) {
            OnboardingScreen(
                onNavigateToLogin = {
                    navController.navigate(VibeStageRoutes.Login.route)
                }
            )
        }

        composable(VibeStageRoutes.Login.route) {
            LoginScreen(
                onNavigateToRegister = { userType ->
                    navController.navigate(VibeStageRoutes.Register.createRoute(userType))
                },
                onLoginSuccess = { userType ->
                    when (userType) {
                        "artist" -> navController.navigate(VibeStageRoutes.ArtistDashboard.route) {
                            popUpTo(VibeStageRoutes.Login.route) { inclusive = true }
                        }
                        "promoter" -> navController.navigate(VibeStageRoutes.PromoterDashboard.route) {
                            popUpTo(VibeStageRoutes.Login.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(VibeStageRoutes.Register.route) { backStackEntry ->
            val userType = backStackEntry.arguments?.getString("userType") ?: "artist"
            RegisterScreen(
                userType = userType,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onRegisterSuccess = {
                    when (userType) {
                        "artist" -> navController.navigate(VibeStageRoutes.ArtistDashboard.route) {
                            popUpTo(VibeStageRoutes.Login.route) { inclusive = true }
                        }
                        "promoter" -> navController.navigate(VibeStageRoutes.PromoterDashboard.route) {
                            popUpTo(VibeStageRoutes.Login.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(VibeStageRoutes.ArtistDashboard.route) {
            ArtistDashboardScreen()
        }

        composable(VibeStageRoutes.PromoterDashboard.route) {
            PromoterDashboardScreen()
        }
    }
}
