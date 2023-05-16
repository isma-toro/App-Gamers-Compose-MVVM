package com.ismael.appgamermvvm.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ismael.appgamermvvm.presentation.ui.theme.Red500
import com.ismael.appgamermvvm.presentation.ui.theme.Red700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
  title: String,
  upAvailable: Boolean = false,
  navController: NavHostController? = null
) {
  TopAppBar(
    title = {
      Text(
        text = title,
        fontSize = 19.sp
      )
    },
    colors = TopAppBarDefaults.mediumTopAppBarColors(Red500),
    navigationIcon = {
      if (upAvailable) {
        IconButton(onClick = { navController?.popBackStack() }) {
          Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "",
            tint = Color.White
          )
        }
      }
    }
  )
}