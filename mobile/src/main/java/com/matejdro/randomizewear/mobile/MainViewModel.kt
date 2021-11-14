package com.matejdro.randomizewear.mobile

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
   private val _lastSelectedRandomListsFolder = MutableStateFlow<Uri?>(null)
   val lastSelectedRandomListsFolder: StateFlow<Uri?>
      get() = _lastSelectedRandomListsFolder

   fun selectRandomListsFolder(folderUri: Uri) {
      _lastSelectedRandomListsFolder.value = folderUri
   }
}
