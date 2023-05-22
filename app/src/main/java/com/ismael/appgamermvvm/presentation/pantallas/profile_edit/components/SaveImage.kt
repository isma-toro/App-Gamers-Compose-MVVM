package com.ismael.appgamermvvm.presentation.pantallas.profile_edit.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.presentation.components.ProgressBar
import com.ismael.appgamermvvm.presentation.pantallas.profile_edit.ProfileEditViewModel

@Composable
fun SaveImage(viewModel : ProfileEditViewModel = hiltViewModel()) {
  when(val response = viewModel.saveImageResponse) {
    Response.Loading -> {
      ProgressBar()
    }

    is Response.Success -> {
      viewModel.onUpdate(response.data)
    }

    is Response.Failure -> {
      Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido", Toast.LENGTH_SHORT).show()
    }

    else -> {}
  }

}