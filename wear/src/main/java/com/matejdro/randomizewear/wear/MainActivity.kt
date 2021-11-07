package com.matejdro.randomizewear.wear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import com.matejdro.randomizewear.wear.util.RotaryEventHandlerSetup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      setContent {
         val rotaryEventDispatcher = RotaryEventDispatcher()
         CompositionLocalProvider(LocalRotaryEventDispatcher provides rotaryEventDispatcher) {
            RotaryEventHandlerSetup(rotaryEventDispatcher = rotaryEventDispatcher)

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
}

