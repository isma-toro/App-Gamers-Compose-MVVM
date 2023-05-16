package com.ismael.appgamermvvm.presentation.pantallas.profile

import androidx.lifecycle.ViewModel
import com.ismael.appgamermvvm.domain.use_cases_auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUseCase: AuthUseCase): ViewModel(){

  fun logout() {
    authUseCase.logout()
  }



}