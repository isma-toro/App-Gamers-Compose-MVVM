package com.ismael.appgamermvvm.presentation.pantallas.profile_edit

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ismael.appgamermvvm.presentation.components.DefaultTopBar
import com.ismael.appgamermvvm.presentation.pantallas.registro.components.SingUpContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileEditScreen(navController : NavHostController) {
  Scaffold(
    topBar = {
      DefaultTopBar(
        title = "Nuevo usuario",
        upAvailable = true,
        navController = navController
      )
    },
    content = { SingUpContent(navController) },
    bottomBar = {}
  )
}