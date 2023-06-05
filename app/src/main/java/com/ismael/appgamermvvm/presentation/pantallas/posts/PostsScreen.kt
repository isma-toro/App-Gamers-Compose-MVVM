package com.ismael.appgamermvvm.presentation.pantallas.posts

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ismael.appgamermvvm.presentation.pantallas.posts.components.DeleteLikePost
import com.ismael.appgamermvvm.presentation.pantallas.posts.components.GetPost
import com.ismael.appgamermvvm.presentation.pantallas.posts.components.LikePost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(navController: NavHostController, viewModel: PostViewModel = hiltViewModel()) {
  Scaffold(
    content = {
      GetPost(navController)
    }
  )

  LikePost()
  DeleteLikePost()
}