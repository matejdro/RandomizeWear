package com.matejdro.randomizewear.wear.landing

import android.content.res.Configuration
import android.graphics.Color
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.matejdro.randomizewear.wear.R
import com.matejdro.randomizewear.wear.Screen
import com.matejdro.randomizewear.wear.theme.PreviewTheme
import com.matejdro.randomizewear.wear.util.AutoResizeText
import com.matejdro.randomizewear.wear.util.FontSizeRange
import com.matejdro.randomizewear.wear.util.HandleRotaryEvents

@Composable
private fun LandingScreenUi(
   openNumberPicker: () -> Unit,
   openRandomName: (index: Int) -> Unit,
   closePopup: () -> Unit,
   randomListNames: List<String>,
   displayedPopupText: String?
) {
   BackHandler(enabled = displayedPopupText != null) { closePopup() }

   val state = rememberLazyListState()

   HandleRotaryEvents(scrollState = state)

   LazyColumn(
      Modifier.fillMaxSize(),
      state = state,
      verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
      horizontalAlignment = Alignment.CenterHorizontally,
      contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
   ) {
      item {
         Button(onClick = openNumberPicker, Modifier.padding(bottom = 16.dp)) {
            Icon(painter = painterResource(id = R.drawable.ic_number), contentDescription = "Number")
         }
      }

      itemsIndexed(randomListNames) { index, item ->
         Button(onClick = { openRandomName(index) }, Modifier.fillMaxSize()) {
            Text(text = item)
         }
      }
   }

   var lastDisplayedText by remember { mutableStateOf("") }
   AnimatedVisibility(
      visible = displayedPopupText != null,
      enter = scaleIn(spring(stiffness = Spring.StiffnessMedium)),
      exit = scaleOut(spring(stiffness = Spring.StiffnessMedium))
   ) {
      if (displayedPopupText != null) {
         lastDisplayedText = displayedPopupText
      }

      Box(
         Modifier
            .fillMaxSize()
            .padding(32.dp)
            .background(MaterialTheme.colors.secondary, MaterialTheme.shapes.large)
            .clickable { closePopup() },
         contentAlignment = Alignment.Center
      ) {
         AutoResizeText(
            text = lastDisplayedText,
            fontSizeRange = FontSizeRange(16.sp, 32.sp),
            color = MaterialTheme.colors.onSecondary,
            textAlign = TextAlign.Center
         )
      }
   }
}

@Composable
fun LandingScreen(navController: NavController) {
   val viewModel: LandingViewModel = hiltViewModel()

   val lifecycleOwner = LocalLifecycleOwner.current
   val listsToDisplayFlow = remember {
      viewModel.listsToDisplay.flowWithLifecycle(lifecycleOwner.lifecycle)
   }

   val listsToDisplay by listsToDisplayFlow.collectAsState(initial = emptyList())

   val textToDisplayFlow = remember {
      viewModel.displayedPopupText.flowWithLifecycle(lifecycleOwner.lifecycle)
   }

   val textToDisplay by textToDisplayFlow.collectAsState(initial = null)

   LandingScreenUi(
      openNumberPicker = {
         navController.navigate(Screen.NumberPicker.route)
      },
      openRandomName = {
         viewModel.selectRandomElementFromList(it)
      },
      closePopup = {
         viewModel.closePopup()
      },
      randomListNames = listsToDisplay,
      displayedPopupText = textToDisplay
   )
}

@Composable
@Preview(
   widthDp = 200,
   heightDp = 200,
   uiMode = Configuration.UI_MODE_TYPE_WATCH,
   showBackground = true,
   backgroundColor = Color.BLACK.toLong()
)
private fun LandingScreenPreview() {
   PreviewTheme {
      LandingScreenUi(
         openNumberPicker = {},
         openRandomName = {},
         closePopup = {},
         randomListNames = listOf("Pomodoro", "Kitajska"),
         displayedPopupText = null
      )
   }
}

@Composable
@Preview(
   widthDp = 200,
   heightDp = 200,
   uiMode = Configuration.UI_MODE_TYPE_WATCH,
   showBackground = true,
   backgroundColor = Color.BLACK.toLong()
)
private fun LandingScreenPreviewWithPopupText() {
   PreviewTheme {
      LandingScreenUi(
         openNumberPicker = {},
         openRandomName = {},
         closePopup = {},
         randomListNames = listOf("Pomodoro", "Kitajska"),
         displayedPopupText = "Mushroom Pizza"
      )
   }
}
