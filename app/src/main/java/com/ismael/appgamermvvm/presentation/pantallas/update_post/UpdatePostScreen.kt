package com.ismael.appgamermvvm.presentation.pantallas.update_post

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ismael.appgamermvvm.presentation.components.DefaultButtom
import com.ismael.appgamermvvm.presentation.components.DefaultTopBar
import com.ismael.appgamermvvm.presentation.pantallas.new_post.components.NewPost
import com.ismael.appgamermvvm.presentation.pantallas.new_post.components.NewPostContent
import com.ismael.appgamermvvm.presentation.pantallas.update_post.components.UpdatePost
import com.ismael.appgamermvvm.presentation.pantallas.update_post.components.UpdatePostContent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UpdatePostScreen(navController: NavHostController, post : String, viewModel : UpdatePostViewModel = hiltViewModel()) {
  Scaffold(
    topBar = {
      DefaultTopBar(
        title = "Editar Post",
        upAvailable = true,
        navController = navController
      )
    },
    content = {
      UpdatePostContent()
    },
    bottomBar = {
      DefaultButtom(
        modifier = Modifier.fillMaxWidth(),
        text = "ACTUALIZAR",
        onClick = { viewModel.onUpdatePost() })
    }
  )

  UpdatePost()
}