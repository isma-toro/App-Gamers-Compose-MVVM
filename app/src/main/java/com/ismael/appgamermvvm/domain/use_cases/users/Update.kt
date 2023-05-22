package com.ismael.appgamermvvm.domain.use_cases.users

import com.ismael.appgamermvvm.domain.model.User
import com.ismael.appgamermvvm.domain.repository.UsersRepository
import javax.inject.Inject

class Update @Inject constructor(private var repository : UsersRepository){
  suspend operator fun invoke(user: User) = repository.update(user)


}