package com.example.myuniservices.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myuniservices.ui.theme.screens.HomeScreen
import com.example.myuniservices.ui.theme.screens.LoginScreen
import com.example.myuniservices.ui.theme.screens.ProfileScreen
import com.example.myuniservices.ui.theme.screens.RegisterScreen
import com.example.myuniservices.ui.theme.screens.ServiceScreen


@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("service") { ServiceScreen() }

    }
}