package com.ismael.appgamermvvm.domain.use_cases.users

import com.ismael.appgamermvvm.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserById @Inject constructor(private val repository: UsersRepository) {
  operator fun invoke(id: String) = repository.getUserById(id)
}