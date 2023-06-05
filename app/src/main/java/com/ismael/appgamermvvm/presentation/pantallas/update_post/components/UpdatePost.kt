package com.ismael.appgamermvvm.presentation.pantallas.update_post.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.presentation.components.ProgressBar
import com.ismael.appgamermvvm.presentation.pantallas.new_post.NewPostViewModel
import com.ismael.appgamermvvm.presentation.pantallas.update_post.UpdatePostViewModel

@Composable
fun UpdatePost(viewModel : UpdatePostViewModel = hiltViewModel()) {
  when (val response = viewModel.updatePostResponse) {

    // MOSTRAR QUE SE ESTA REALIZANDO LA PETICIÓN Y TODAVÍA ESTÁ EN PROCESO
    Response.Loading -> {
      ProgressBar()
    }

    is Response.Success -> {
      viewModel.clearForm()
      Toast.makeText(LocalContext.current, "La publicación se ha actualizado correctamente", Toast.LENGTH_LONG).show()
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