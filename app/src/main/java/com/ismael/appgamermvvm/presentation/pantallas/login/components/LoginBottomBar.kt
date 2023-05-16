package com.ismael.appgamermvvm.presentation.pantallas.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ismael.appgamermvvm.presentation.navigation.AppScreen

@Composable
fun LoginBottomBar(navController: NavController) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(bottom = 20.dp),
    horizontalArrangement = Arrangement.Center
  ) {
    Text(
      text = "¿No tienes cuenta?",
      fontSize = 14.sp,
      color = Color.Gray
    )

    Spacer(modifier = Modifier.width(7.dp))

    Text(
      modifier = Modifier.clickable {
        navController.navigate(AppScreen.SinUp.route)
      },
      text = "REGISTRATE AQUÍ",
      color = Color.Red,
      fontSize = 14.sp,
      fontWeight = FontWeight.Bold
    )
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  LoginBottomBar(rememberNavController())
}