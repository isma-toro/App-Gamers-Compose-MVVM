package com.ismael.appgamermvvm.presentation.pantallas.new_post

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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewPostScreen(navController: NavHostController, viewModel : NewPostViewModel = hiltViewModel()) {
  Scaffold(
    topBar = {
      DefaultTopBar(
        title = "Nuevo Post",
        upAvailable = true,
        navController = navController
      )
    },
    content = {
      NewPostContent()
    },
    bottomBar = {
      DefaultButtom(
        modifier = Modifier.fillMaxWidth(),
        text = "PUBLICAR",
        onClick = { viewModel.onNewPost() })
    }
  )

  NewPost()
}