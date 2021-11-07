package com.matejdro.randomizewear.wear.landing

import android.content.res.Configuration
import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import com.matejdro.randomizewear.wear.R
import com.matejdro.randomizewear.wear.Screen
import com.matejdro.randomizewear.wear.theme.PreviewTheme

@Composable
private fun LandingScreenUi(
   openNumberPicker: () -> Unit
) {
   Column(
      Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Button(onClick = openNumberPicker) {
         Icon(painter = painterResource(id = R.drawable.ic_number), contentDescription = "Number")
      }
   }
}

@Composable
fun LandingScreen(navController: NavController) {
   LandingScreenUi(
      openNumberPicker = {
         navController.navigate(Screen.NumberPicker.route)
      }
   )
}

@Composable
@Preview(
   widthDp = 200,
   heightDp = 200,
   uiMode = Configuration.UI_MODE_TYPE_WATCH,
   showBackground = true,
   backgroundColor = Color.BLACK.toLong()
)
private fun LandingScreenPreview() {
   PreviewTheme {
      LandingScreenUi {}
   }
}
