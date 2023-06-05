package com.ismael.appgamermvvm.presentation.pantallas.my_posts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ismael.appgamermvvm.presentation.navigation.DetailsScreen
import com.ismael.appgamermvvm.presentation.pantallas.my_posts.components.GetPostByIdUser
import com.ismael.appgamermvvm.presentation.pantallas.my_posts.components.MyPostContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPostsScreen(navController: NavHostController) {
  Scaffold(
    content = {
      GetPostByIdUser(navController = navController)
    },
    floatingActionButton = {
      FloatingActionButton(
        modifier = Modifier.padding(bottom = 80.dp),
        onClick = {
          navController.navigate(DetailsScreen.NewPost.route)
        },
        containerColor = Color.White
      ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "")
      }
    }
  )


}