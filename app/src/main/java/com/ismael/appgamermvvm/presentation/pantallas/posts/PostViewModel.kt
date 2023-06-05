package com.ismael.appgamermvvm.presentation.pantallas.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismael.appgamermvvm.domain.model.Post
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.domain.use_cases.auth.AuthUseCase
import com.ismael.appgamermvvm.domain.use_cases.posts.PostsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
  private val postsUseCases: PostsUseCases,
  private val authUseCase: AuthUseCase
): ViewModel() {

  var postsResponse by mutableStateOf<Response<List<Post>>?>(null)
  var likePostResponse by mutableStateOf<Response<Boolean>?>(null)
  var deleteLikePostResponse by mutableStateOf<Response<Boolean>?>(null)
  var currentUser = authUseCase.getCurrentUser()

  init {
    getPosts()
  }

  fun like(idPost : String, idUser : String) = viewModelScope.launch {
    likePostResponse = Response.Loading
    val result = postsUseCases.likePost(idPost, idUser)
    likePostResponse = result
  }

  fun deleteLike(idPost : String, idUser : String) = viewModelScope.launch {
    deleteLikePostResponse = Response.Loading
    val result = postsUseCases.deleteLikePost(idPost, idUser)
    deleteLikePostResponse = result
  }

  fun getPosts() = viewModelScope.launch {
    postsResponse = Response.Loading
    postsUseCases.getPosts().collect() { response ->
      postsResponse = response
    }

  }

}