package com.matejdro.randomizewear.mobile

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matejdro.randomizewear.mobile.data.SettingsRepository
import com.matejdro.randomizewear.mobile.data.WearPusher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
   private val settingsRepository: SettingsRepository,
   private val wearPusher: WearPusher,
   private val contentResolver: ContentResolver
) : ViewModel() {
   private val _lastSelectedRandomListsFolder = MutableStateFlow<Uri?>(null)
   val lastSelectedRandomListsFolder: StateFlow<Uri?>
      get() = _lastSelectedRandomListsFolder

   init {
      viewModelScope.launch {
         settingsRepository.uri.collect {
            _lastSelectedRandomListsFolder.value = it
         }
      }
   }

   fun selectRandomListsFolder(folderUri: Uri) {
      viewModelScope.launch {
         contentResolver.takePersistableUriPermission(
            folderUri,
            Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
         )
         settingsRepository.updateUri(folderUri)
         wearPusher.refresh()
      }
   }

   fun reloadRandomLists() {
      viewModelScope.launch {
         wearPusher.refresh()
      }
   }
}
