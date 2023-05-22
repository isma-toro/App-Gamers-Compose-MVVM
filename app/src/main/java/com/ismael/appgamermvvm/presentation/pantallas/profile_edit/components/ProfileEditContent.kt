package com.ismael.appgamermvvm.presentation.pantallas.profile_edit.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ismael.appgamermvvm.R
import com.ismael.appgamermvvm.presentation.components.DefaultButtom
import com.ismael.appgamermvvm.presentation.components.DefaultTextField
import com.ismael.appgamermvvm.presentation.components.DialogCapturePicture
import com.ismael.appgamermvvm.presentation.pantallas.profile_edit.ProfileEditViewModel
import com.ismael.appgamermvvm.presentation.ui.theme.DarkGray500
import com.ismael.appgamermvvm.presentation.ui.theme.Red500
import com.ismael.appgamermvvm.presentation.utils.ComposeFileProvider

@Composable
fun ProfileEditContent(
  navController: NavHostController,
  viewModel: ProfileEditViewModel = hiltViewModel()
) {
  val state = viewModel.state
  viewModel.resultingActivityHandler.handle()
  var dialogState = remember { mutableStateOf(false) }

  DialogCapturePicture(
    status = dialogState,
    takePhoto = {
      viewModel.takePhoto()
    },
    pickImage = { viewModel.pickImage() })

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
        if (viewModel.state.image != "") {
          AsyncImage(
            modifier = Modifier
              .height(100.dp)
              .width(100.dp)
              .clip(CircleShape)
              .clickable {
                dialogState.value = true
              },
            model = viewModel.state.image,
            contentDescription = "selected image",
            contentScale = ContentScale.Crop
          )
        } else {
          Image(
            modifier = Modifier
              .height(120.dp)
              .clickable {
                dialogState.value = true
              },
            painter = painterResource(id = R.drawable.user),
            contentDescription = "Imagen del usuario"
          )
        }
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
          text = "EDITAR USUARIO",
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
          value = state.userName,
          onValueChange = { viewModel.onUserNameInput(it) },
          label = "Nombre de usuario",
          icon = Icons.Default.Person,
          errorMessage = viewModel.userNameErrorMessage,
          validateField = {
            viewModel.validateUserName()
          }
        )


        DefaultButtom(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
          text = "ACTUALIZAR DATOS",
          onClick = {
            viewModel.saveImage()
          },
        )
      }


    }

  }
}