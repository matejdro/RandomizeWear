package com.matejdro.randomizewear.common.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun <T> StateFlow<T>.collectAsStateWithLifecycle(): State<T> {
   val lifecycleOwner = LocalLifecycleOwner.current

   return remember { this.flowWithLifecycle(lifecycleOwner.lifecycle) }.collectAsState(value)
}
