package com.ismael.appgamermvvm.domain.use_cases_auth

import com.ismael.appgamermvvm.data.repository.AuthRepositoryImpl
import com.ismael.appgamermvvm.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(private val repository : AuthRepository){
  suspend operator fun invoke(email : String, password : String) = repository.login(email, password)



}