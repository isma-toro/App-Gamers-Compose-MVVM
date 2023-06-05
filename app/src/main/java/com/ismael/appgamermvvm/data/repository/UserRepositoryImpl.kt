package com.ismael.appgamermvvm.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.ismael.appgamermvvm.core.Constants
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.domain.model.User
import com.ismael.appgamermvvm.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

class UserRepositoryImpl @Inject constructor(
  @Named(Constants.USERS) private val storageUsersRef: StorageReference,
  @Named(Constants.USERS) private val usersRef: CollectionReference
) : UsersRepository {
  override suspend fun create(user: User): Response<Boolean> {
    return try {
      user.password = ""
      usersRef.document(user.id).set(user).await()
      Response.Success(true)

    } catch (e: Exception) {
      e.printStackTrace()
      return Response.Failure(e)
    }

  }

  override suspend fun update(user: User): Response<Boolean> {
    return try {
      val map: MutableMap<String, Any> = HashMap()
      map["userName"] = user.userName
      map["image"] = user.image
      usersRef.document(user.id).update(map).await()
      Response.Success(true)

    } catch (e: Exception) {
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

  override suspend fun saveImage(file: File): Response<String> {
    return try {
      val fromFile = Uri.fromFile(file)
      val ref = storageUsersRef.child(file.name)
      val uploadTask = ref.putFile(fromFile).await()
      val url = ref.downloadUrl.await()
      return Response.Success(url.toString())
    }

    catch (ex: Exception) {
      ex.printStackTrace()
      Response.Failure(ex)
    }
  }


}