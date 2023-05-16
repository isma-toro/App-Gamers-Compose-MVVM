package com.ismael.appgamermvvm.presentation.pantallas.registro.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.presentation.components.ProgressBar
import com.ismael.appgamermvvm.presentation.navigation.AppScreen
import com.ismael.appgamermvvm.presentation.pantallas.registro.SignUpViewModel

@Composable
fun SignUp(navController : NavHostController, viewModel: SignUpViewModel = hiltViewModel()) {
  when(val signUpResponse = viewModel.signUpResponse) {
    is Response.Loading -> {
      ProgressBar()
    }

    is Response.Success -> {
      LaunchedEffect(Unit) {
        viewModel.createUser()
        navController.popBackStack(AppScreen.Login.route, true)
        navController.navigate(AppScreen.Profile.route)
      }
    }

    is Response.Failure -> {
      Toast.makeText(LocalContext.current, signUpResponse.exception?.message?: "Error desconocido", Toast.LENGTH_SHORT).show()
    }

    else -> {}
  }
}