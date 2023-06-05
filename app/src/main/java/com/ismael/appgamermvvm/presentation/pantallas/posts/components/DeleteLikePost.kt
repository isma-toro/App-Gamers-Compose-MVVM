package com.ismael.appgamermvvm.presentation.pantallas.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.presentation.components.ProgressBar
import com.ismael.appgamermvvm.presentation.pantallas.posts.PostViewModel

@Composable
fun DeleteLikePost(viewModel : PostViewModel = hiltViewModel()) {
  when (val response = viewModel.deleteLikePostResponse) {

    // MOSTRAR QUE SE ESTA REALIZANDO LA PETICIÓN Y TODAVÍA ESTÁ EN PROCESO
    Response.Loading -> {
      ProgressBar()
    }

    is Response.Success -> {
      
    }

    is Response.Failure -> {
      Toast.makeText(
        LocalContext.current,
        response.exception?.message ?: "Error desconocido",
        Toast.LENGTH_SHORT
      ).show()
    }


    else -> {}
  }


}