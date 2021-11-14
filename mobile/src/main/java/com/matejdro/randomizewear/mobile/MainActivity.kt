package com.matejdro.randomizewear.mobile

import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.matejdro.randomizewear.R
import com.matejdro.randomizewear.common.util.collectAsStateWithLifecycle
import com.matejdro.randomizewear.mobile.theme.MobileAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         ComposeContent()
      }
   }

   @Composable
   private fun ComposeContent() {
      val viewModel: MainViewModel by viewModels()

      val currentFolder by viewModel.lastSelectedRandomListsFolder.collectAsStateWithLifecycle()

      MobileAppTheme {
         Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            FolderSelectButton(currentFolder, viewModel::selectRandomListsFolder)
         }
      }
   }

   @Composable
   private fun FolderSelectButton(currentFolder: Uri?, newFolderListener: (Uri) -> Unit) {
      val result = rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenDocumentTree()) { folderNullable ->
         folderNullable?.let { newFolderListener(it) }
      }

      Button(onClick = { result.launch(currentFolder) }) {
         Text(stringResource(R.string.select_folder))
      }
   }
}
