package com.ismael.appgamermvvm.presentation.pantallas.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismael.appgamermvvm.domain.model.User
import com.ismael.appgamermvvm.domain.use_cases.auth.AuthUseCase
import com.ismael.appgamermvvm.domain.use_cases.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
  private val authUseCase: AuthUseCase,
  private val usersUseCase: UsersUseCase
) : ViewModel() {
  var userData by mutableStateOf(User())
    private set

  init {
    getUserById()
  }

  private fun getUserById() = viewModelScope.launch {
    usersUseCase.getUserById(authUseCase.getCurrentUser()!!.uid).collect() {
      userData = it
    }
  }

  fun logout() {
    authUseCase.logout()
  }


}