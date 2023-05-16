package com.ismael.appgamermvvm.presentation.pantallas.registro

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ismael.appgamermvvm.presentation.components.DefaultTopBar
import com.ismael.appgamermvvm.presentation.pantallas.registro.components.SingUpContent
import com.ismael.appgamermvvm.presentation.ui.theme.AppGamerMVVMTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUpScreen(navController: NavHostController) {
  Scaffold(
    topBar = {
      DefaultTopBar(
        title = "Nuevo usuario",
        upAvailable = true,
        navController = navController
      )
    },
    content = { SingUpContent(navController)},
    bottomBar = {}
  ) 
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSingUpScreen() {
  AppGamerMVVMTheme(darkTheme = true) {
    // A surface container using the 'background' color from the theme
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colorScheme.background
    ) {
      SingUpScreen(rememberNavController())
    }
  }
}