package com.matejdro.randomizewear.wear.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.Wearable
import com.matejdro.randomizewear.models.RandomLists
import com.matejdro.randomizewear.wear.data.RandomListCacheSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class WearModule {
   @Provides
   fun provideDataClient(@ApplicationContext context: Context): DataClient {
      return Wearable.getDataClient(context)
   }

   @Provides
   fun provideRandomListsDataStore(@ApplicationContext context: Context): DataStore<RandomLists> {
      return DataStoreFactory.create(RandomListCacheSerializer, produceFile = { context.dataStoreFile("settings.pb") })
   }
}
