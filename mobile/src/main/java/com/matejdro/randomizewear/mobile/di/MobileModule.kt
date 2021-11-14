package com.matejdro.randomizewear.mobile.di

import android.content.ContentResolver
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.Wearable
import com.matejdro.randomizewear.mobile.data.SettingsSerializer
import com.matejdro.randomizewear.models.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MobileModule {
   @Provides
   fun provideSettingsDataStore(@ApplicationContext context: Context): DataStore<Settings> {
      return DataStoreFactory.create(SettingsSerializer, produceFile = { context.dataStoreFile("settings.pb") })
   }

   @Provides
   fun provideContentResolver(@ApplicationContext context: Context): ContentResolver {
      return context.contentResolver
   }

   @Provides
   fun provideDataClient(@ApplicationContext context: Context): DataClient {
      return Wearable.getDataClient(context)
   }
}
