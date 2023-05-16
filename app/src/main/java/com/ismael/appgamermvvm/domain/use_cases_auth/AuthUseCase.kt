package com.ismael.appgamermvvm.domain.use_cases_auth

data class AuthUseCase(
  val getCurrentUser : GetCurrentUser,
  val login : Login,
  val logout : Logout,
  val signUp : SignUp
)