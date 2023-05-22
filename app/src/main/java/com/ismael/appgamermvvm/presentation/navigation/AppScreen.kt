package com.ismael.appgamermvvm.presentation.navigation

import com.google.firebase.firestore.auth.User

sealed class AppScreen(val route : String) {

  object Login : AppScreen("login")
  object SinUp : AppScreen("singup")
  object Profile : AppScreen("profile")
  object ProfileEdit : AppScreen("profile/edit/{user}") {
    fun passUser(user : String) = "profile/edit/$user"
  }

}
