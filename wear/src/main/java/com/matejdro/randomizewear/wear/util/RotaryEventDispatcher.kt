package com.matejdro.randomizewear.wear.util

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.coroutines.launch

// Adapted from https://github.com/joreilly/PeopleInSpace/blob/main/wearApp/src/main/java/com/surrus/peopleinspace/RotaryEventDispatcher.kt

val LocalRotaryEventDispatcher = staticCompositionLocalOf<RotaryEventDispatcher> {
   noLocalProvidedFor("LocalRotaryEventDispatcher")
}

/**
 * Dispatcher to link rotary event to [ScrollableState].
 * The instance should be set up by calling [RotaryEventHandlerSetup] function.
 */
class RotaryEventDispatcher() {
   private var callbacks: List<(Float) -> Unit> = emptyList()

   fun addCallback(callback: (Float) -> Unit) {
      callbacks = callbacks + callback
   }

   fun removeCallback(callback: (Float) -> Unit) {
      callbacks = callbacks - callback
   }

   fun onRotate(delta: Float) {
      println("On Rotate ${callbacks.size}")
      callbacks.forEach { it(delta) }
   }
}

@Composable
fun RotaryEventCallback(callback: (Float) -> Unit) {
   val dispatcher = LocalRotaryEventDispatcher.current

   DisposableEffect(callback) {
      dispatcher.addCallback(callback)

      this.onDispose {
         dispatcher.removeCallback(callback)
      }
   }
}

/**
 * Register a [ScrollableState] to [LocalRotaryEventDispatcher]
 */
@Composable
fun HandleRotaryEvents(scrollState: ScrollableState) {
   val scope = rememberCoroutineScope()

   RotaryEventCallback { scrollAmount: Float ->
      scope.launch {
         scrollState.scroll {
            scrollBy(scrollAmount)
         }
      }
   }
}

private fun noLocalProvidedFor(name: String): Nothing {
   error("CompositionLocal $name not present")
} 
