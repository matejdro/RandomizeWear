package com.matejdro.randomizewear.mobile.data

import androidx.datastore.core.Serializer
import com.matejdro.randomizewear.models.Settings
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object SettingsSerializer : Serializer<Settings> {
   override val defaultValue: Settings
      get() = Settings()

   override suspend fun readFrom(input: InputStream): Settings {
      return Settings.ADAPTER.decode(input)
   }

   override suspend fun writeTo(t: Settings, output: OutputStream) {
      t.adapter.encode(output, t)
   }
}
