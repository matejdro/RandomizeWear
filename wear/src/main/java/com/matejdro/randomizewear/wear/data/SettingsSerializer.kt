package com.matejdro.randomizewear.wear.data

import androidx.datastore.core.Serializer
import com.matejdro.randomizewear.models.RandomLists
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object RandomListCacheSerializer : Serializer<RandomLists> {
   override val defaultValue: RandomLists
      get() = RandomLists()

   override suspend fun readFrom(input: InputStream): RandomLists {
      return RandomLists.ADAPTER.decode(input)
   }

   override suspend fun writeTo(t: RandomLists, output: OutputStream) {
      t.adapter.encode(output, t)
   }
}
