package com.ismael.appgamermvvm.presentation.pantallas.profile_edit

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.domain.model.User
import com.ismael.appgamermvvm.domain.use_cases.users.UsersUseCase
import com.ismael.appgamermvvm.presentation.utils.ComposeFileProvider
import com.ismael.appgamermvvm.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val userUseCase: UsersUseCase,
  @ApplicationContext private val context : Context
) : ViewModel() {
  // STATE FORM
  var state by mutableStateOf(ProfileEditState())
    private set

  var userNameErrorMessage by mutableStateOf("")
    private set

  val data = savedStateHandle.get<String>("user")
  val user = User.fromJson(data!!)

  var updateResponse by mutableStateOf<Response<Boolean>?>(null)
    private set


  var saveImageResponse by mutableStateOf<Response<String>?>(null)
    private set


  val resultingActivityHandler = ResultingActivityHandler()

  //FILE
  var file : File? = null


  init {
    state = state.copy(userName = user.userName, image = user.image)

  }

  fun saveImage() = viewModelScope.launch {
    if (file!=null) {
      saveImageResponse = Response.Loading
      val result = userUseCase.saveImage(file!!)
      saveImageResponse = result
    }

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

  fun onUpdate(url : String) {
    val myUser = User(
      id = user.id,
      userName = state.userName,
      image = url
    )
    update(myUser)
  }

  private fun update(user: User) = viewModelScope.launch {
      updateResponse = Response.Loading
      val result = userUseCase.update(user)
      updateResponse = result

  }


  fun onUserNameInput(userName: String) {
    state = state.copy(userName = userName)
  }

  fun validateUserName() {
    if (state.userName.length >= 5) {
      userNameErrorMessage = ""
    } else {
      userNameErrorMessage = "Al menos 5 caracteres"
    }
  }

}