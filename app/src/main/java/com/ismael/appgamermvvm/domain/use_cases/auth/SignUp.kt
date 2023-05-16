package com.ismael.appgamermvvm.domain.use_cases.auth

import com.ismael.appgamermvvm.domain.model.User
import com.ismael.appgamermvvm.domain.repository.AuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(private val repository: AuthRepository){
  suspend operator fun invoke(user : User) = repository.signUp(user)
}