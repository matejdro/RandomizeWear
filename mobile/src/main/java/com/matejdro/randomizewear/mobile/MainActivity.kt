package com.matejdro.randomizewear.mobile

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.matejdro.randomizewear.R
import com.matejdro.randomizewear.mobile.theme.MobileAppTheme

class MainActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         ComposeContent()
      }
   }

   @Composable
   private fun ComposeContent() {
      MobileAppTheme {
         Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            FolderSelectButton()
         }
      }
   }

   @Composable
   private fun FolderSelectButton() {
      Button(onClick = {}) {
         Text(stringResource(R.string.select_folder))
      }
   }
}
