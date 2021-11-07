package com.matejdro.randomizewear.wear.number

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class NumberPickerViewModel @Inject constructor() : ViewModel() {
   private val _fromNumber = MutableStateFlow(1)
   val fromNumber: StateFlow<Int>
      get() = _fromNumber

   private val _toNumber = MutableStateFlow(2)
   val toNumber: StateFlow<Int>
      get() = _toNumber

   private val _selectedNumber = MutableStateFlow<Int?>(null)
   val selectedNumber: StateFlow<Int?>
      get() = _selectedNumber

   fun decrementFrom() {
      _fromNumber.value--
   }

   fun incrementFrom() {
      _fromNumber.value = (_fromNumber.value + 1).coerceAtMost(_toNumber.value - 1)
   }

   fun decrementTo() {
      _toNumber.value = (_toNumber.value - 1).coerceAtLeast(_fromNumber.value + 1)
   }

   fun incrementTo() {
      _toNumber.value++
   }

   fun select() {
      _selectedNumber.value = Random.nextInt(_fromNumber.value, _toNumber.value + 1)
   }
}
