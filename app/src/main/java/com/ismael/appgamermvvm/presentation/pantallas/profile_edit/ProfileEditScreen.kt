package com.ismael.appgamermvvm.presentation.pantallas.profile_edit

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ismael.appgamermvvm.presentation.components.DefaultTopBar
import com.ismael.appgamermvvm.presentation.pantallas.profile_edit.components.ProfileEditContent
import com.ismael.appgamermvvm.presentation.pantallas.profile_edit.components.SaveImage
import com.ismael.appgamermvvm.presentation.pantallas.profile_edit.components.Update
import com.ismael.appgamermvvm.presentation.pantallas.registro.components.SingUpContent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileEditScreen(
  navController : NavHostController,
  user : String
) {
  Log.d("ProfileEditScreen", "Usuario : $user" )

  Scaffold(
    topBar = {
      DefaultTopBar(
        title = "Editar Usuario",
        upAvailable = true,
        navController = navController
      )
    },
    content = { ProfileEditContent(navController) },
    bottomBar = {}
  )

  SaveImage()
  Update()
}