package com.ismael.appgamermvvm.presentation.pantallas.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ismael.appgamermvvm.R
import com.ismael.appgamermvvm.presentation.components.DefaultButtom
import com.ismael.appgamermvvm.presentation.navigation.AppScreen
import com.ismael.appgamermvvm.presentation.pantallas.login.LoginScreen
import com.ismael.appgamermvvm.presentation.pantallas.profile.ProfileViewModel
import com.ismael.appgamermvvm.presentation.ui.theme.AppGamerMVVMTheme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ProfileContent(
  navController: NavHostController,
  viewModel: ProfileViewModel = hiltViewModel()
) {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Box() {
      Image(
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp),
        painter = painterResource(id = R.drawable.background),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        alpha = 0.6f
      )

      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
          text = "Bienvenido",
          fontSize = 30.sp,
          fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(55.dp))
        if (viewModel.userData.image != "") {
          AsyncImage(
            modifier = Modifier
              .size(115.dp)
              .clip(CircleShape),
            model = viewModel.userData.image,
            contentDescription = "User Image",
            contentScale = ContentScale.Crop
          )
        } else {
          Image(
            modifier = Modifier.size(115.dp),
            painter = painterResource(id = R.drawable.user),
            contentDescription = "",
          )
        }

      }
    }

    Spacer(
      modifier = Modifier.height(55.dp)
    )
    Text(
      text = viewModel.userData.userName,
      fontSize = 20.sp,
      fontWeight = FontWeight.Bold,
      fontStyle = FontStyle.Italic
    )

    Text(
      text = viewModel.userData.email,
      fontSize = 15.sp,
      fontStyle = FontStyle.Italic
    )

    Spacer(modifier = Modifier.height(20.dp))

    DefaultButtom(
      modifier = Modifier.width(250.dp),
      text = "Editar Datos",
      color = Color.White,
      icon = Icons.Default.Edit,
      onClick = {
        navController.navigate(
          AppScreen.ProfileEdit.passUser(
            viewModel.userData.toJson()
          )
        )
      },
      textIconColor = Color.Black
    )


    DefaultButtom(
      modifier = Modifier.width(250.dp),
      text = "Cerrar Sesión",
      onClick = {
        viewModel.logout()
        navController.navigate(route = AppScreen.Login.route) {
          popUpTo(AppScreen.Profile.route) {
            inclusive = true
          }
        }
      })

  }
}