package com.ismael.appgamermvvm.domain.use_cases_auth

import com.ismael.appgamermvvm.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(private  val repository : AuthRepository){
  operator fun invoke() = repository.currentUser

}