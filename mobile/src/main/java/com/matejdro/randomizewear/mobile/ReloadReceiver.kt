package com.matejdro.randomizewear.mobile

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.matejdro.randomizewear.mobile.data.WearPusher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ReloadReceiver : BroadcastReceiver() {
   @Inject
   lateinit var wearPusher: WearPusher

   @OptIn(DelicateCoroutinesApi::class)
   override fun onReceive(context: Context?, intent: Intent?) {
      val result = goAsync()
      GlobalScope.launch(Dispatchers.Main) {
         try {
            wearPusher.refresh()
         } finally {
            result.finish()
         }
      }
   }
}
