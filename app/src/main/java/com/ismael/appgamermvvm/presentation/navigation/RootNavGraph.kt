package com.ismael.appgamermvvm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ismael.appgamermvvm.presentation.pantallas.home.HomeScreen
import com.ismael.appgamermvvm.presentation.pantallas.login.LoginScreen
import com.ismael.appgamermvvm.presentation.pantallas.profile.ProfileScreen
import com.ismael.appgamermvvm.presentation.pantallas.profile_edit.ProfileEditScreen
import com.ismael.appgamermvvm.presentation.pantallas.registro.SingUpScreen

@Composable
fun RootNavGraph(navController : NavHostController) {

  NavHost(
    navController = navController,
    route = Graph.ROOT,
    startDestination = Graph.AUTHENTICATION
  ) {

    authNavGraph(navController = navController)

    composable(route = Graph.HOME) {
      HomeScreen()
    }
  }

}

sealed class RootScreen(
  val route : String
) {
  object Home : RootScreen("home")
}