package com.ismael.appgamermvvm.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ismael.appgamermvvm.presentation.ui.theme.Red500
import com.ismael.appgamermvvm.presentation.ui.theme.Red700

@Composable
fun DefaultButtom(
  modifier: Modifier,
  text : String,
  errorMessage : String = "",
  onClick : () -> Unit,
  color: Color = Red500,
  icon : ImageVector? = Icons.Default.ArrowForward,
  enable : Boolean = true,
  textIconColor : Color = Color.White
) {
  Column() {
    Button(
      modifier = modifier,
      onClick = { onClick() },
      colors = ButtonDefaults.buttonColors(color),
      enabled = enable
    ) {

      icon?.let {
        Icon(
          imageVector = icon,
          contentDescription = "",
          tint = textIconColor
        )
        Spacer(modifier = Modifier.width(10.dp))
      }

      Text(
        text = text,
        color = textIconColor
      )
    }
    
    Text(
      //odifier = Modifier.padding(top = 5.dp),
      text = errorMessage,
      fontSize = 11.sp,
      color = Red700
    )
  }
  
  
}