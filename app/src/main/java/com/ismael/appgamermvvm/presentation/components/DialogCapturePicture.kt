package com.ismael.appgamermvvm.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DialogCapturePicture(
  status: MutableState<Boolean>,
  takePhoto: () -> Unit,
  pickImage: () -> Unit
) {

  if (status.value) {
    AlertDialog(
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
      onDismissRequest = { status.value = false },
      containerColor = Color.White,
      title = {
        Text(
          modifier = Modifier.padding(bottom = 20.dp),
          text = "Selecciona una opción",
          fontSize = 20.sp,
          color = Color.Black

        )
      },
      confirmButton = {

        Column(
          modifier = Modifier
            .fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {

          DefaultButtom(
            modifier = Modifier
              .width(200.dp)
              .fillMaxWidth(),
            text = "GALERIA",
            icon = null,
            onClick = {
              status.value = false
              pickImage() })
        }
      },

      dismissButton = {
        Column(
          modifier = Modifier
            .fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {

          DefaultButtom(
            modifier = Modifier
              .width(200.dp)
              .fillMaxWidth(),
            text = "CÁMARA",
            icon = null,
            onClick = {
              status.value = false
              takePhoto() })
        }


      }
    )
  }

}