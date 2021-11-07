package com.matejdro.randomizewear.wear.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme
import com.matejdro.randomizewear.common.AppColors
import com.matejdro.randomizewear.wear.util.LocalRotaryEventDispatcher
import com.matejdro.randomizewear.wear.util.RotaryEventDispatcher


@Composable
fun WearAppTheme(
   content: @Composable () -> Unit
) {
   MaterialTheme(
      colors = colorPalette,
      content = content
   )
}

private val colorPalette = Colors(
   primary = AppColors.primary,
   primaryVariant = AppColors.primaryDark,
   secondary = AppColors.secondary,
   secondaryVariant = AppColors.secondaryDark,
   error = AppColors.error,
   onPrimary = AppColors.onPrimary,
   onSecondary = AppColors.onSecondary,
   onError = AppColors.onError
)

@Composable
fun PreviewTheme(content: @Composable () -> Unit) {
   CompositionLocalProvider(LocalRotaryEventDispatcher provides RotaryEventDispatcher()) {
      WearAppTheme {
         content()
      }
   }
}
