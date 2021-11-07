package com.matejdro.randomizewear.wear.number

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.matejdro.randomizewear.common.util.collectAsStateWithLifecycle
import com.matejdro.randomizewear.wear.R
import com.matejdro.randomizewear.wear.theme.PreviewTheme
import com.matejdro.randomizewear.wear.util.RotaryEventCallback

@Composable
private fun NumberPickerUi(
   fromNumber: Int,
   toNumber: Int,
   selectedNumber: Int?,
   decrementFrom: () -> Unit,
   incrementFrom: () -> Unit,
   decrementTo: () -> Unit,
   incrementTo: () -> Unit,
   selectNumber: () -> Unit
) {
   Column(modifier = Modifier.padding(all = 32.dp)) {
      var firstSelected by remember { mutableStateOf(false) }
      var secondSelected by remember { mutableStateOf(false) }

      RotaryEventCallback {
         if (firstSelected) {
            if (it < 0) {
               decrementFrom()
            } else {
               incrementFrom()
            }
         } else if (secondSelected) {
            if (it < 0) {
               decrementTo()
            } else {
               incrementTo()
            }
         }
      }

      Text(text = stringResource(R.string.select_number))

      Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
         NumberScrollButton(firstSelected, fromNumber) {
            firstSelected = true
            secondSelected = false
         }

         Text(text = "-")

         NumberScrollButton(secondSelected, toNumber) {
            firstSelected = false
            secondSelected = true
         }
      }

      Button(
         modifier = Modifier
            .padding(8.dp)
            .height(32.dp)
            .fillMaxWidth(),
         onClick = selectNumber
      ) {
         Text(text = selectedNumber?.toString() ?: "SELECT")
      }
   }

}

@Composable
private fun RowScope.NumberScrollButton(active: Boolean, currentValue: Int, onClick: () -> Unit) {
   Button(
      onClick = onClick,
      Modifier
         .weight(1f)
         .padding(8.dp)
         .height(32.dp)
         .width(32.dp),
      colors = if (active)
         ButtonDefaults.primaryButtonColors(backgroundColor = MaterialTheme.colors.secondary)
      else
         ButtonDefaults.secondaryButtonColors()
   ) {
      Text(
         text = currentValue.toString(),
      )
   }
}

@Composable
fun NumberPickerScreen() {
   val viewModel: NumberPickerViewModel = hiltViewModel()

   val from by viewModel.fromNumber.collectAsStateWithLifecycle()
   val to by viewModel.toNumber.collectAsStateWithLifecycle()
   val selected by viewModel.selectedNumber.collectAsStateWithLifecycle()

   NumberPickerUi(
      from,
      to,
      selected,
      viewModel::decrementFrom,
      viewModel::incrementFrom,
      viewModel::decrementTo,
      viewModel::incrementTo,
      viewModel::select
   )
}

@Composable
@Preview(widthDp = 200, heightDp = 200, uiMode = Configuration.UI_MODE_TYPE_WATCH, showBackground = true, backgroundColor = 0xFF000000)
private fun NumberPickerPreview() {
   PreviewTheme {
      NumberPickerUi(
         10,
         1337,
         null,
         {},
         {},
         {},
         {},
         {}
      )
   }
}
