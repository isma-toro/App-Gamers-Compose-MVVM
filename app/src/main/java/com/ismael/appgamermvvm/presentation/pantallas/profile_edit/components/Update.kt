package com.ismael.appgamermvvm.presentation.pantallas.profile_edit.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.presentation.components.ProgressBar
import com.ismael.appgamermvvm.presentation.pantallas.profile_edit.ProfileEditViewModel

@Composable
fun Update(viewModel: ProfileEditViewModel = hiltViewModel()) {
  when(val updateResponse = viewModel.updateResponse) {
    is Response.Loading ->  {
      ProgressBar()
    }

    is Response.Success -> {
      Toast.makeText(LocalContext.current, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
    }

    is Response.Failure -> {
      Toast.makeText(LocalContext.current, updateResponse.exception?.message ?: "Error desconocido" , Toast.LENGTH_SHORT).show()

    }

    else -> {}
  }
}