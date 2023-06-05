package com.ismael.appgamermvvm.presentation.pantallas.new_post

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismael.appgamermvvm.R
import com.ismael.appgamermvvm.domain.model.Post
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.domain.use_cases.auth.AuthUseCase
import com.ismael.appgamermvvm.domain.use_cases.posts.PostsUseCases
import com.ismael.appgamermvvm.presentation.utils.ComposeFileProvider
import com.ismael.appgamermvvm.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor(
  @ApplicationContext private val context : Context,
  private val postsUseCases : PostsUseCases,
  private val authUseCases: AuthUseCase
): ViewModel(){

  var state by mutableStateOf(NewPostState())

  //FILE
  var file : File? = null

  val resultingActivityHandler = ResultingActivityHandler()

  //RESPONSE
  var createPostResponse by mutableStateOf<Response<Boolean>?>(null)
    private set

  //USER SESSION
  val currentUser = authUseCases.getCurrentUser()

  val radioOptions = listOf(
    CategoryRadioButton("PC", R.drawable.icon_pc),
    CategoryRadioButton("PS4", R.drawable.icon_ps4),
    CategoryRadioButton("XBOX", R.drawable.icon_xbox),
    CategoryRadioButton("NINTENDO", R.drawable.icon_nintendo),
    CategoryRadioButton("MOBILE", R.drawable.ic_mobile),
  )

  fun onNameInput(name : String) {
    state = state.copy(name = name)
  }

  fun onCategoryinput(category: String) {
    state = state.copy(category = category)
  }

  fun onDescriptionInput(description : String) {
    state = state.copy(description = description)
  }

  fun onImageInput(image : String) {
    state = state.copy(image = image)
  }

  fun clearForm() {
    state = state.copy(
      name = "",
      category = "",
      description = "",
      image = ""
    )
    createPostResponse = null
  }

  fun createPost(post : Post) = viewModelScope.launch {
    createPostResponse = Response.Loading
    val result = postsUseCases.create(post, file!!)
    createPostResponse = result
  }

  fun onNewPost() {
    val post = Post(
      name = state.name,
      description = state.description,
      category = state.category,
      idUser = currentUser?.uid ?: ""
    )
    createPost(post)
  }

  fun pickImage() = viewModelScope.launch {
    val result = resultingActivityHandler.getContent("image/*")
    if (result!=null) {
      file = ComposeFileProvider.createFileFromUri(context, result)
      state = state.copy(image = result.toString())
    }
  }

  fun takePhoto() = viewModelScope.launch {
    val result = resultingActivityHandler.takePicturePreview()
    if (result!= null) {
      state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result!!))
      file = File(state.image)
    }
  }


}

data class CategoryRadioButton(
  var category: String,
  var image: Int
)