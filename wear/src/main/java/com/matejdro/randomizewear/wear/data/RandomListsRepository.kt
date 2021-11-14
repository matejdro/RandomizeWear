package com.matejdro.randomizewear.wear.data

import android.net.Uri
import androidx.datastore.core.DataStore
import com.google.android.gms.wearable.DataClient
import com.matejdro.randomizewear.common.CommPaths
import com.matejdro.randomizewear.models.RandomLists
import com.matejdro.randomizewear.wear.util.getDataItemFlow
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class RandomListsRepository @Inject constructor(dataClient: DataClient, cacheDataStore: DataStore<RandomLists>) {
   @Suppress("BlockingMethodInNonBlockingContext")
   @OptIn(DelicateCoroutinesApi::class)
   val lists = dataClient.getDataItemFlow(Uri.parse("wear://*${CommPaths.RANDOM_LISTS}"))
      .map { dataItem -> dataItem?.let { RandomLists.ADAPTER.decode(it.data) } ?: RandomLists() }
      .onEach { newLists ->
         cacheDataStore.updateData { newLists }
      }
      .onStart {
         emit(cacheDataStore.data.first())
      }
      .distinctUntilChanged()
      .shareIn(GlobalScope, SharingStarted.WhileSubscribed(), replay = 1)
}
