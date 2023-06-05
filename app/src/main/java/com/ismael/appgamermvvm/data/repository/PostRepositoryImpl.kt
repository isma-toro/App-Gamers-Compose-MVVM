package com.ismael.appgamermvvm.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.storage.StorageReference
import com.ismael.appgamermvvm.core.Constants.POSTS
import com.ismael.appgamermvvm.core.Constants.USERS
import com.ismael.appgamermvvm.domain.model.Post
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.domain.model.User
import com.ismael.appgamermvvm.domain.repository.PostRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

class PostRepositoryImpl @Inject constructor(
  @Named(POSTS) private val postRef: CollectionReference,
  @Named(USERS) private val usersRef : CollectionReference,
  @Named(POSTS) private val storagePostsRef: StorageReference,
) : PostRepository {
  @OptIn(DelicateCoroutinesApi::class)
  override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow {
    val snapshotListener = postRef.addSnapshotListener { snapshot, e ->

      GlobalScope.launch(Dispatchers.IO) {
        val postsResponse = if (snapshot!= null) {
          val posts = snapshot.toObjects(Post::class.java)

          snapshot.documents.forEachIndexed { index, document ->
            posts[index].id = document.id
          }

          val idUserArray = ArrayList<String>()

          posts.forEach { post ->
            idUserArray.add(post.idUser)
          }

          val idUserList = idUserArray.toSet().toList() // ELEMENTOS SIN REPETIR

          idUserList.map { id->
            async {
              val user = usersRef.document(id).get().await().toObject(User::class.java)!!
              posts.forEach { post ->
                if (post.idUser == id) {
                  post.user = user
                }
              }
            }
          }.forEach {
            it.await()
          }
          Response.Success(posts)
        } else {
          Response.Failure(e)
        }
        trySend(postsResponse)
      }

    }
    awaitClose {
      snapshotListener.remove()
    }
  }

  override fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>> = callbackFlow{
    val snapshotListener = postRef.whereEqualTo("idUser", idUser).addSnapshotListener { snapshot, e ->

        val postsResponse = if (snapshot!= null) {
          val posts = snapshot.toObjects(Post::class.java)
          snapshot.documents.forEachIndexed { index, document ->
            posts[index].id = document.id
          }

          Response.Success(posts)
        } else {
          Response.Failure(e)
        }
        trySend(postsResponse)
      }
    awaitClose {
      snapshotListener.remove()
    }
  }

  override suspend fun create(post: Post, file: File): Response<Boolean> {
    return try {
      val fromFile = Uri.fromFile(file)
      val ref = storagePostsRef.child(file.name)
      val uploadTask = ref.putFile(fromFile).await()
      val url = ref.downloadUrl.await()
      post.image = url.toString()
      postRef.add(post).await()
      Response.Success(true)

    } catch (ex: Exception) {
      ex.printStackTrace()
      Response.Failure(ex)
    }
  }

  override suspend fun update(post: Post, file: File?): Response<Boolean> {
    return try {
      if (file != null) {
        val fromFile = Uri.fromFile(file)
        val ref = storagePostsRef.child(file.name)
        val uploadTask = ref.putFile(fromFile).await()
        val url = ref.downloadUrl.await()
        post.image = url.toString()
      }

      val map : MutableMap<String, Any> = HashMap()
      map["name"] = post.name
      map["description"] = post.description
      map["image"] = post.image
      map["category"] = post.category


      postRef.document(post.id).update(map).await()
      Response.Success(true)

    } catch (ex: Exception) {
      ex.printStackTrace()
      Response.Failure(ex)
    }
  }

  override suspend fun delete(idPost: String): Response<Boolean> {
    return try {
      postRef.document(idPost).delete().await()
      Response.Success(true)
    } catch (ex : Exception) {
      ex.printStackTrace()
      Response.Failure(ex)
    }
  }

  override suspend fun like(idPost: String, idUser: String): Response<Boolean> {
    return try {
      postRef.document(idPost).update("likes", FieldValue.arrayUnion(idUser)).await()
      Response.Success(true)
    } catch (ex : Exception) {
      ex.printStackTrace()
      Response.Failure(ex)
    }
  }

  override suspend fun deleteLike(idPost: String, idUser: String): Response<Boolean> {
    return try {
      postRef.document(idPost).update("likes", FieldValue.arrayRemove(idUser)).await()
      Response.Success(true)
    } catch (ex : Exception) {
      ex.printStackTrace()
      Response.Failure(ex)
    }
  }
}