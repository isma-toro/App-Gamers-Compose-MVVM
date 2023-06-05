package com.ismael.appgamermvvm.presentation.pantallas.registro.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.presentation.components.ProgressBar
import com.ismael.appgamermvvm.presentation.navigation.AuthScreen
import com.ismael.appgamermvvm.presentation.navigation.DetailsScreen
import com.ismael.appgamermvvm.presentation.navigation.Graph
import com.ismael.appgamermvvm.presentation.navigation.RootScreen
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
        navController.popBackStack(Graph.AUTHENTICATION, true)
        navController.navigate(route = Graph.HOME)
      }
    }

    is Response.Failure -> {
      Toast.makeText(LocalContext.current, signUpResponse.exception?.message?: "Error desconocido", Toast.LENGTH_SHORT).show()
    }

    else -> {}
  }
}