package com.ismael.appgamermvvm.presentation.pantallas.profile

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ismael.appgamermvvm.presentation.pantallas.profile.components.ProfileContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navHostController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
  Scaffold(
    content = {
      ProfileContent(navHostController)
    }
  )
  
}