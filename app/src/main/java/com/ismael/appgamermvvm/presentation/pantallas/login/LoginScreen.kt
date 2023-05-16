package com.ismael.appgamermvvm.presentation.pantallas.login

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
import com.ismael.appgamermvvm.presentation.pantallas.login.components.Login
import com.ismael.appgamermvvm.presentation.pantallas.login.components.LoginBottomBar
import com.ismael.appgamermvvm.presentation.pantallas.login.components.LoginContent
import com.ismael.appgamermvvm.presentation.ui.theme.AppGamerMVVMTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
  Scaffold(
    topBar = {},
    content = {
      LoginContent(navController)
    },
    bottomBar = {
      LoginBottomBar(navController)
    }
  )

  //MANEJAR EL ESTADO DE LA PETICIÓN DE LOGIN
  Login(navController = navController)


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
  AppGamerMVVMTheme(darkTheme = true) {
    // A surface container using the 'background' color from the theme
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colorScheme.background
    ) {
      LoginScreen(rememberNavController())
    }
  }
}