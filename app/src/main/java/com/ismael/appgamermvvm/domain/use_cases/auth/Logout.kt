package com.ismael.appgamermvvm.domain.use_cases.auth

import com.ismael.appgamermvvm.domain.repository.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(private val repository: AuthRepository) {

  operator fun invoke() = repository.logout()
}