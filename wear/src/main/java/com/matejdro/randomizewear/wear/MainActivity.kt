package com.matejdro.randomizewear.wear

import android.os.Bundle
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.InputDeviceCompat
import androidx.core.view.MotionEventCompat
import androidx.core.view.ViewConfigurationCompat
import androidx.lifecycle.lifecycleScope
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.matejdro.randomizewear.wear.landing.LandingScreen
import com.matejdro.randomizewear.wear.number.NumberPickerScreen
import com.matejdro.randomizewear.wear.theme.WearAppTheme
import com.matejdro.randomizewear.wear.util.LocalRotaryEventDispatcher
import com.matejdro.randomizewear.wear.util.RotaryEventDispatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
   private val rotaryEventDispatcher = RotaryEventDispatcher()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      setContent {
         CompositionLocalProvider(LocalRotaryEventDispatcher provides rotaryEventDispatcher) {
            MainScreen()
         }
      }
   }

   @Composable
   private fun MainScreen() {
      WearAppTheme {
         Scaffold(timeText = {
            TimeText()
         }) {
            val navController = rememberSwipeDismissableNavController()
            BackHandler {
               navController.popBackStack()
            }

            SwipeDismissableNavHost(navController = navController, startDestination = Screen.Landing.route) {
               composable(Screen.Landing.route) {
                  LandingScreen(navController)
               }
               composable(Screen.NumberPicker.route) {
                  NumberPickerScreen()
               }
            }
         }
      }
   }

   override fun onGenericMotionEvent(event: MotionEvent?): Boolean {
      if (event?.action != MotionEvent.ACTION_SCROLL ||
         !event.isFromSource(InputDeviceCompat.SOURCE_ROTARY_ENCODER)
      ) {
         return false
      }

      val delta = -event.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
              ViewConfigurationCompat.getScaledVerticalScrollFactor(
                 ViewConfiguration.get(this), this
              )
      lifecycleScope.launch {
         rotaryEventDispatcher.onRotate(delta)
      }

      return true
   }
}

