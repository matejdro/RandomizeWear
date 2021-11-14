package com.matejdro.randomizewear.wear.di

import android.content.Context
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.Wearable
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
}
