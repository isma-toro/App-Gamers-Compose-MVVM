package com.ismael.appgamermvvm.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ismael.appgamermvvm.presentation.pantallas.login.LoginScreen
import com.ismael.appgamermvvm.presentation.pantallas.registro.SingUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
  navigation(
    route = Graph.AUTHENTICATION,
    startDestination = AuthScreen.Login.route
  ) {
    composable(route = AuthScreen.Login.route) {
      LoginScreen(navController)
    }

    composable(route = AuthScreen.SinUp.route) {
      SingUpScreen(navController)
    }
  }
}


sealed class AuthScreen(val route : String) {

  object Login : AuthScreen("login")
  object SinUp : AuthScreen("singup")

}