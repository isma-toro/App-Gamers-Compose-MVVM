package com.ismael.appgamermvvm.presentation.pantallas.registro.components

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ismael.appgamermvvm.R
import com.ismael.appgamermvvm.domain.model.Response
import com.ismael.appgamermvvm.presentation.components.DefaultButtom
import com.ismael.appgamermvvm.presentation.components.DefaultTextField
import com.ismael.appgamermvvm.presentation.navigation.AppScreen
import com.ismael.appgamermvvm.presentation.pantallas.registro.SignUpViewModel
import com.ismael.appgamermvvm.presentation.ui.theme.DarkGray500
import com.ismael.appgamermvvm.presentation.ui.theme.Red500

@Composable
fun SingUpContent(navController : NavHostController, viewModel: SignUpViewModel = hiltViewModel()) {
  val signUpFlow = viewModel.signUpFlow.collectAsState()

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
        Spacer(modifier = Modifier.height(65.dp))

        Image(
          modifier = Modifier.height(100.dp),
          painter = painterResource(id = R.drawable.user),
          contentDescription = "Imagen del usuario"
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
          text = "REGISTRO",
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
          text = "Por favor, ingresa estos datos para continuar",
          fontSize = 12.sp,
          color = Color.Gray
        )

        DefaultTextField(
          modifier = Modifier.padding(top = 25.dp),
          value = viewModel.userName.value,
          onValueChange = {viewModel.userName.value = it},
          label = "Nombre de usuario",
          icon = Icons.Default.Person,
          errorMessage = viewModel.userNameErrorMessage.value,
          validateField = {
            viewModel.validateUserName()
          }
        )

        DefaultTextField(
          modifier = Modifier.padding(top = 0.dp),
          value = viewModel.email.value,
          onValueChange = {viewModel.email.value = it},
          label = "Correo electrónico",
          icon = Icons.Default.Email,
          keyboardType = KeyboardType.Email,
          errorMessage = viewModel.emailErrorMessage.value,
          validateField = {
            viewModel.validateEmail()
          }
        )

        DefaultTextField(
          modifier = Modifier.padding(top = 0.dp),
          value = viewModel.password.value,
          onValueChange = {viewModel.password.value = it},
          label = "Contraseña",
          icon = Icons.Default.Lock,
          hideText = true,
          errorMessage = viewModel.passwordErrorMessage.value,
          validateField = {
            viewModel.validatePassword()
          }
        )

        DefaultTextField(
          modifier = Modifier.padding(top = 0.dp),
          value = viewModel.confirmPassword.value,
          onValueChange = {viewModel.confirmPassword.value = it},
          label = "Confirmar Contraseña",
          icon = Icons.Outlined.Lock,
          hideText = true,
          errorMessage = viewModel.confirmPasswordErrorMessage.value,
          validateField = {
            viewModel.validateConfirmPassword()
          }
        )


        DefaultButtom(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
          text = "REGISTRARSE",
          onClick = { viewModel.onSignUp()},
          enable = viewModel.isEnabledLoginButton
        )
      }


    }

  }

  signUpFlow.value.let {
    when(it) {
      is Response.Loading -> {
        Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier.fillMaxSize()
        ) {
          CircularProgressIndicator()
        }
      }

      is Response.Success -> {
        navController.popBackStack(AppScreen.Login.route, true)
        LaunchedEffect(Unit) {
          navController.navigate(AppScreen.Profile.route)
        }
      }

      is Response.Failure -> {
        Toast.makeText(LocalContext.current, it.exception?.message?: "Error desconocido", Toast.LENGTH_SHORT).show()
      }

      else -> {}
    }
  }
}