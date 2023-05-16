package com.ismael.appgamermvvm.presentation.components

import android.view.KeyEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ismael.appgamermvvm.presentation.ui.theme.Red700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextField(
  modifier: Modifier,
  value: String,
  onValueChange: (value: String) -> Unit,
  validateField: () -> Unit = {},
  label: String,
  icon: ImageVector,
  keyboardType: KeyboardType = KeyboardType.Text,
  hideText: Boolean = false,
  errorMessage: String = ""
) {

  Column() {
    OutlinedTextField(
      modifier = modifier,
      value = value,
      onValueChange = {
        onValueChange(it)
        validateField()
      },
      keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
      label = {
        Text(label)
      },
      leadingIcon = {
        Icon(
          imageVector = icon,
          contentDescription = "",
          tint = Color.White
        )
      },
      visualTransformation =
      if (hideText) PasswordVisualTransformation()
      else VisualTransformation.None
    )

    Text(
      text = errorMessage,
      modifier = Modifier.padding(top = 3.dp),
      fontSize = 11.sp,
      color = Red700


    )


  }

}