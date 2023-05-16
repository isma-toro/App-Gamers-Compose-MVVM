package com.ismael.appgamermvvm.presentation.navigation

sealed class AppScreen(val route : String) {

  object Login : AppScreen("login")
  object SinUp : AppScreen("singup")
  object Profile : AppScreen("profile")

}
