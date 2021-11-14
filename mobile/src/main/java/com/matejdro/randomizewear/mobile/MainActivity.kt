package com.matejdro.randomizewear.mobile

import android.content.Context
import android.content.Intent
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

            Button(onClick = viewModel::reloadRandomLists) {
               Text(text = stringResource(R.string.reload))
            }
         }
      }
   }

   @Composable
   private fun FolderSelectButton(currentFolder: Uri?, newFolderListener: (Uri) -> Unit) {
      val result = rememberLauncherForActivityResult(contract = PersistableOpenDocumentTree()) { folderNullable ->
         folderNullable?.let { newFolderListener(it) }
      }

      Button(onClick = { result.launch(currentFolder) }) {
         Text(stringResource(R.string.select_folder))
      }
   }

   private class PersistableOpenDocumentTree : ActivityResultContracts.OpenDocumentTree() {
      override fun createIntent(context: Context, input: Uri?): Intent {
         return super.createIntent(context, input).also {
            it.addFlags(
               Intent.FLAG_GRANT_READ_URI_PERMISSION or
                       Intent.FLAG_GRANT_WRITE_URI_PERMISSION or
                       Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION or
                       Intent.FLAG_GRANT_PREFIX_URI_PERMISSION
            )
         }
      }
   }
}
