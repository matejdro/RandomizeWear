package com.matejdro.randomizewear.mobile.data

import android.net.Uri
import androidx.datastore.core.DataStore
import com.matejdro.randomizewear.models.Settings
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(private val dataStore: DataStore<Settings>) {
   val uri = dataStore.data.map { it.folderUri?.let { uriString -> Uri.parse(uriString) } }

   suspend fun updateUri(newUri: Uri?) {
      dataStore.updateData {
         it.copy(folderUri = newUri.toString())
      }
   }
}
