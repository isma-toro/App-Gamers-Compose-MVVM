package com.ismael.appgamermvvm.presentation.pantallas.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.domain.use_cases_auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase : AuthUseCase) : ViewModel(){

  //EMAIL
  var email : MutableState<String> = mutableStateOf("")
  var isEmailValid : MutableState<Boolean> = mutableStateOf(false)
  var emailErrorMessage : MutableState<String> = mutableStateOf("")

  //PASSWORD
  var password : MutableState<String> = mutableStateOf("")
  var isPasswordValid : MutableState<Boolean> = mutableStateOf(false)
  var passwordErrorMessage : MutableState<String> = mutableStateOf("")

  //BUTTON
  var isEnabledLoginButton = false

  private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
  val loginFlow : StateFlow<Response<FirebaseUser>?> = _loginFlow

  val currentUser = authUseCase.getCurrentUser()

  init {
    if (currentUser != null) {
      _loginFlow.value = Response.Success(currentUser)
    }
  }

  fun login() = viewModelScope.launch {
    _loginFlow.value = Response.Loading
    val result = authUseCase.login(email.value, password.value)
    _loginFlow.value = result
  }


  fun validateEmail() {
    if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
      isEmailValid.value = true
      emailErrorMessage.value = ""
    } else {
      isEmailValid.value = false
      emailErrorMessage.value = "El email no es válido"
    }

    enabledLoginButton()
  }

  fun validatePassword() {
    if (password.value.length >= 6) {
      isPasswordValid.value = true
      passwordErrorMessage.value = ""
    } else {
      isPasswordValid.value = false
      passwordErrorMessage.value = "Al menos 6 caracteres"
    }

    enabledLoginButton()
  }

  fun enabledLoginButton() {
    isEnabledLoginButton = isEmailValid.value && isPasswordValid.value
  }

}