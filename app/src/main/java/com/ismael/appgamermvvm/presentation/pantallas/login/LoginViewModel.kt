package com.ismael.appgamermvvm.presentation.pantallas.login

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.domain.use_cases.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

  //STATE FORM
  var state by mutableStateOf(LoginState())
    private set


  //EMAIL
  var isEmailValid: Boolean by mutableStateOf(false)
    private set
  var emailErrorMessage: String by mutableStateOf("")
    private set

  //PASSWORD
  var isPasswordValid: Boolean by mutableStateOf(false)
    private set
  var passwordErrorMessage: String by mutableStateOf("")
    private set

  //BUTTON
  var isEnabledLoginButton = false

  //LOGIN RESPONSE
  var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)

  val currentUser = authUseCase.getCurrentUser()

  init {
    if (currentUser != null) {
      loginResponse = Response.Success(currentUser)
    }
  }

  fun onEmailInput(email: String) {
    state = state.copy(email = email)
  }

  fun onPasswordInput(password: String) {
    state = state.copy(password = password)
  }

  fun login() = viewModelScope.launch {
    loginResponse = Response.Loading
    val result = authUseCase.login(state.email, state.password)
    loginResponse = result
  }


  fun validateEmail() {
    if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
      isEmailValid = true
      emailErrorMessage = ""
    } else {
      isEmailValid = false
      emailErrorMessage = "El email no es válido"
    }

    enabledLoginButton()
  }

  fun validatePassword() {
    if (state.password.length >= 6) {
      isPasswordValid = true
      passwordErrorMessage = ""
    } else {
      isPasswordValid = false
      passwordErrorMessage = "Al menos 6 caracteres"
    }

    enabledLoginButton()
  }

  fun enabledLoginButton() {
    isEnabledLoginButton = isEmailValid && isPasswordValid
  }

}