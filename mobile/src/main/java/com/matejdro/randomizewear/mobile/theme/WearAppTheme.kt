package com.matejdro.randomizewear.mobile.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import com.matejdro.randomizewear.common.AppColors


@Composable
fun MobileAppTheme(
   content: @Composable () -> Unit
) {
   MaterialTheme(
      colors = colorPalette,
      content = content
   )
}

private val colorPalette = darkColors(
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
   MobileAppTheme {
      content()
   }
}
