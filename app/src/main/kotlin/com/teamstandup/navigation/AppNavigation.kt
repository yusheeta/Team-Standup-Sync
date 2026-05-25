package com.teamstandup.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.teamstandup.ui.screens.LoginScreen
import com.teamstandup.ui.screens.ProfileScreen
import com.teamstandup.ui.screens.StandupInputScreen
import com.teamstandup.ui.screens.TeamStandupListScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object TeamList : Screen("team_list")
    object StandupInput : Screen("standup_input")
    object Profile : Screen("profile")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(Screen.TeamList.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            })
        }
        composable(Screen.TeamList.route) {
            TeamStandupListScreen(
                onAddStandup = { navController.navigate(Screen.StandupInput.route) },
                onProfile = { navController.navigate(Screen.Profile.route) }
            )
        }
        composable(Screen.StandupInput.route) {
            StandupInputScreen(onSubmit = { navController.popBackStack() })
        }
        composable(Screen.Profile.route) {
            ProfileScreen(onBack = { navController.popBackStack() })
        }
    }
}
