package com.ismael.appgamermvvm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ismael.appgamermvvm.presentation.pantallas.login.LoginScreen
import com.ismael.appgamermvvm.presentation.pantallas.profile.ProfileScreen
import com.ismael.appgamermvvm.presentation.pantallas.registro.SingUpScreen

@Composable
fun AppNavigation(navController : NavHostController) {

  NavHost(
    navController = navController,
    startDestination = AppScreen.Login.route
  ) {
    composable(route = AppScreen.Login.route) {
      LoginScreen(navController)
    }

    composable(route = AppScreen.SinUp.route) {
      SingUpScreen(navController)
    }

    composable(route = AppScreen.Profile.route) {
      ProfileScreen(navController)
    }
  }

}