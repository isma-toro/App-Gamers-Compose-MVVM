package com.ismael.appgamermvvm.data.repository

import com.google.firebase.firestore.CollectionReference
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.domain.model.User
import com.ismael.appgamermvvm.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val usersRef : CollectionReference) : UsersRepository {
  override suspend fun create(user: User): Response<Boolean> {
      return try {
        user.password = ""
        usersRef.document(user.id).set(user).await()
        Response.Success(true)

      } catch (e : Exception) {
        e.printStackTrace()
        return Response.Failure(e)
      }

  }

  override fun getUserById(id: String): Flow<User> = callbackFlow {
    val snapshotListener = usersRef.document(id).addSnapshotListener { snapShot, e ->
      val user = snapShot?.toObject(User::class.java) ?: User()
      trySend(user)
    }

    awaitClose {
      snapshotListener.remove()
    }
  }


}