package com.ismael.appgamermvvm.presentation.pantallas.registro

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.domain.model.User
import com.ismael.appgamermvvm.domain.use_cases_auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

  //USERNAME
  var userName : MutableState<String> = mutableStateOf("")
  var isUserNameValid : MutableState<Boolean> = mutableStateOf(false)
  var userNameErrorMessage: MutableState<String> = mutableStateOf("")

  //CONFIRMAR CONTRASEÑA
  var confirmPassword : MutableState<String> = mutableStateOf("")
  var isConfirmPasswordValid : MutableState<Boolean> = mutableStateOf(false)
  var confirmPasswordErrorMessage: MutableState<String> = mutableStateOf("")

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

  private val _signUpFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
  val signUpFlow : StateFlow<Response<FirebaseUser>?> = _signUpFlow

  fun onSignUp() {
    val user = User(
      userName = userName.value,
      email = email.value,
      password = password.value,
    )

    signUp(user)
  }

  fun signUp(user: User) = viewModelScope.launch{
    _signUpFlow.value = Response.Loading

    val result = authUseCase.signUp(user)
    _signUpFlow.value = result
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
        && isUserNameValid.value && isConfirmPasswordValid.value
  }

  fun validateUserName() {
    if (userName.value.length >=5) {
      isUserNameValid.value = true
      userNameErrorMessage.value = ""
    } else {
      isUserNameValid.value = false
      userNameErrorMessage.value = "Al menos 5 caracteres"
    }

    enabledLoginButton()
  }

  fun validateConfirmPassword() {
    if (password.value == confirmPassword.value) {
      isConfirmPasswordValid.value = true
      confirmPasswordErrorMessage.value = ""
    } else {
      isConfirmPasswordValid.value = false
      confirmPasswordErrorMessage.value = "Las contraseñas no coinciden"
    }

    enabledLoginButton()
  }



}