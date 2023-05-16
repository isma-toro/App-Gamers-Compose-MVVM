package com.ismael.appgamermvvm.presentation.pantallas.registro

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.domain.model.User
import com.ismael.appgamermvvm.domain.use_cases.auth.AuthUseCase
import com.ismael.appgamermvvm.domain.use_cases.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
  private val authUseCase: AuthUseCase,
  private val usersUseCase: UsersUseCase
) : ViewModel() {

  // STATE FORM
  var state by mutableStateOf(SignUpState())
    private set

  //USERNAME
  var isUserNameValid by mutableStateOf(false)
    private set
  var userNameErrorMessage by mutableStateOf("")
    private set

  //CONFIRMAR CONTRASEÑA
  var isConfirmPasswordValid by mutableStateOf(false)
    private set
  var confirmPasswordErrorMessage by mutableStateOf("")
    private set

  //EMAIL
  var isEmailValid by mutableStateOf(false)
    private set
  var emailErrorMessage by mutableStateOf("")
    private set

  //PASSWORD
  var isPasswordValid by mutableStateOf(false)
    private set
  var passwordErrorMessage by mutableStateOf("")
    private set

  //BUTTON
  var isEnabledLoginButton = false

  var signUpResponse by mutableStateOf<Response<FirebaseUser>?>(null)
    private set

  var user = User()

  fun onEmailInut(email: String) {
    state = state.copy(email = email)
  }

  fun onUserNameInput(userName: String) {
    state = state.copy(userName = userName)
  }

  fun onPasswordInput(password: String) {
    state = state.copy(password = password)
  }

  fun onConfirmPasswordInput(confirmPassword: String) {
    state = state.copy(confirmPasword = confirmPassword)
  }

  fun onSignUp() {
    user.userName = state.userName
    user.email = state.email
    user.password = state.password
    signUp(user)
  }

  fun createUser() = viewModelScope.launch {
    user.id = authUseCase.getCurrentUser()!!.uid
    usersUseCase.create(user)
  }

  fun signUp(user: User) = viewModelScope.launch {
    signUpResponse = Response.Loading

    val result = authUseCase.signUp(user)
    signUpResponse = result
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
        && isUserNameValid && isConfirmPasswordValid
  }

  fun validateUserName() {
    if (state.userName.length >= 5) {
      isUserNameValid = true
      userNameErrorMessage = ""
    } else {
      isUserNameValid = false
      userNameErrorMessage = "Al menos 5 caracteres"
    }

    enabledLoginButton()
  }

  fun validateConfirmPassword() {
    if (state.password == state.confirmPasword) {
      isConfirmPasswordValid = true
      confirmPasswordErrorMessage = ""
    } else {
      isConfirmPasswordValid = false
      confirmPasswordErrorMessage = "Las contraseñas no coinciden"
    }

    enabledLoginButton()
  }


}