package com.ismael.appgamermvvm.domain.repository

import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
  suspend fun create(user : User): Response<Boolean>
  fun getUserById(id : String) : Flow<User>


}