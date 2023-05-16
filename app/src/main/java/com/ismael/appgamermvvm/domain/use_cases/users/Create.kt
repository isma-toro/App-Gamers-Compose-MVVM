package com.ismael.appgamermvvm.domain.use_cases.users

import com.ismael.appgamermvvm.domain.model.User
import com.ismael.appgamermvvm.domain.repository.UsersRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository : UsersRepository) {
  suspend operator fun invoke(user : User) = repository.create(user)


}