package com.matejdro.randomizewear.mobile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matejdro.randomizewear.mobile.data.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val settingsRepository: SettingsRepository) : ViewModel() {
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
         settingsRepository.updateUri(folderUri)
      }
   }
}
