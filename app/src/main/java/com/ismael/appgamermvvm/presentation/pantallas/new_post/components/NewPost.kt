package com.ismael.appgamermvvm.presentation.pantallas.new_post.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.presentation.components.ProgressBar
import com.ismael.appgamermvvm.presentation.navigation.AuthScreen
import com.ismael.appgamermvvm.presentation.navigation.Graph
import com.ismael.appgamermvvm.presentation.navigation.RootScreen
import com.ismael.appgamermvvm.presentation.pantallas.login.LoginViewModel
import com.ismael.appgamermvvm.presentation.pantallas.new_post.NewPostViewModel

@Composable
fun NewPost(viewModel : NewPostViewModel = hiltViewModel()) {
  when (val response = viewModel.createPostResponse) {

    // MOSTRAR QUE SE ESTA REALIZANDO LA PETICIÓN Y TODAVÍA ESTÁ EN PROCESO
    Response.Loading -> {
      ProgressBar()
    }

    is Response.Success -> {
      viewModel.clearForm()
      Toast.makeText(LocalContext.current, "La publicación se ha creado correctamente", Toast.LENGTH_LONG).show()
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