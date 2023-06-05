package com.ismael.appgamermvvm.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.ismael.appgamermvvm.presentation.pantallas.detail_post.DetailPostScreen
import com.ismael.appgamermvvm.presentation.pantallas.new_post.NewPostScreen
import com.ismael.appgamermvvm.presentation.pantallas.profile_edit.ProfileEditScreen
import com.ismael.appgamermvvm.presentation.pantallas.update_post.UpdatePostScreen

fun NavGraphBuilder.detailsNavGraph(navController : NavHostController) {
  navigation(
    route = Graph.DETAILS,
    startDestination = DetailsScreen.ProfileEdit.route
  ) {
    composable(route = DetailsScreen.NewPost.route) {
      NewPostScreen(navController = navController)
    }

    composable(
      route = DetailsScreen.ProfileEdit.route,
      arguments = listOf(navArgument("user") {
        type = NavType.StringType
      })
    ) {
      it.arguments?.getString("user")?.let {
        ProfileEditScreen(navController, user = it)

      }
    }

    composable(
      route = DetailsScreen.DetailPost.route,
      arguments = listOf(navArgument("post") {
        type = NavType.StringType
      })
    ) {
      it.arguments?.getString("post")?.let {
        DetailPostScreen(navController, post = it)

      }
    }

    composable(
      route = DetailsScreen.UpdatePost.route,
      arguments = listOf(navArgument("post") {
        type = NavType.StringType
      })
    ) {
      it.arguments?.getString("post")?.let {
        UpdatePostScreen(navController, post = it)

      }
    }
  }
}

sealed class DetailsScreen(val route : String) {
  object NewPost : DetailsScreen("posts/new")
  object ProfileEdit : DetailsScreen("profile/edit/{user}") {
    fun passUser(user : String) = "profile/edit/$user"
  }

  object DetailPost : DetailsScreen("posts/detail/{post}") {
    fun passPost(post : String) = "posts/detail/$post"
  }

  object UpdatePost : DetailsScreen("posts/update/{post}") {
    fun passPost(post : String) = "posts/update/$post"
  }
}