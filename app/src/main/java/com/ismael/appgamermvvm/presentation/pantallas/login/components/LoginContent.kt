package com.ismael.appgamermvvm.presentation.pantallas.login.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ismael.appgamermvvm.R
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.presentation.components.DefaultButtom
import com.ismael.appgamermvvm.presentation.components.DefaultTextField
import com.ismael.appgamermvvm.presentation.navigation.AppScreen
import com.ismael.appgamermvvm.presentation.pantallas.login.LoginViewModel
import com.ismael.appgamermvvm.presentation.ui.theme.AppGamerMVVMTheme
import com.ismael.appgamermvvm.presentation.ui.theme.DarkGray500
import com.ismael.appgamermvvm.presentation.ui.theme.Red500


@Composable
fun LoginContent(
  navController: NavHostController,
  viewModel: LoginViewModel = hiltViewModel()
) {
  val state = viewModel.state

  Box(
    modifier = Modifier
      .fillMaxWidth()

  ) {

    Box(
      modifier = Modifier
        .height(280.dp)
        .background(Red500)
        .fillMaxWidth()
    ) {

      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Image(
          modifier = Modifier.height(130.dp),
          painter = painterResource(id = R.drawable.control),
          contentDescription = "Mando XBOX 360"
        )

        Text(
          text = "FIREBASE MVVM"
        )
      }

    }

    Card(
      modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 200.dp),
      colors = CardDefaults.cardColors(containerColor = DarkGray500)
    ) {

      Column(
        modifier = Modifier.padding(horizontal = 20.dp),
      ) {
        Text(
          modifier = Modifier.padding(top = 40.dp),
          text = "LOGIN",
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
          text = "Por favor, inicia sesión para continuar",
          fontSize = 12.sp,
          color = Color.Gray
        )

        DefaultTextField(
          modifier = Modifier.padding(top = 25.dp),
          value = state.email,
          onValueChange = { viewModel.onEmailInput(it) },
          label = "Email",
          icon = Icons.Default.Email,
          keyboardType = KeyboardType.Email,
          errorMessage = viewModel.emailErrorMessage,
          validateField = {
            viewModel.validateEmail()
          }
        )


        Spacer(modifier = Modifier.height(10.dp))

        DefaultTextField(
          modifier = Modifier.padding(top = 5.dp),
          value = state.password,
          onValueChange = { viewModel.onPasswordInput(it) },
          label = "Contraseña",
          icon = Icons.Default.Lock,
          hideText = true,
          errorMessage = viewModel.passwordErrorMessage,
          validateField = {
            viewModel.validatePassword()
          }
        )


        DefaultButtom(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
          text = "INICIAR SESIÓN",
          onClick = {
            viewModel.login()
          },
          enable = viewModel.isEnabledLoginButton
        )
      }
    }
  }

}