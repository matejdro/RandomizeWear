package com.matejdro.randomizewear.wear

sealed class Screen(
    val route: String
) {
    object Landing : Screen("landing")
    object NumberPicker : Screen("numberPicker")
}
