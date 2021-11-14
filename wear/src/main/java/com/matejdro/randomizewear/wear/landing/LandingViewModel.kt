package com.matejdro.randomizewear.wear.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matejdro.randomizewear.wear.data.RandomListsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(private val randomListsRepository: RandomListsRepository) : ViewModel() {
   val listsToDisplay: Flow<List<String>> = randomListsRepository.lists.map { listsObject -> listsObject.lists.map { it.name } }

   private val _displayedPopupText = MutableStateFlow<String?>(null)
   val displayedPopupText: StateFlow<String?>
      get() = _displayedPopupText

   fun selectRandomElementFromList(index: Int) {
      viewModelScope.launch {
         val list = randomListsRepository.lists.first().also { logcat { "Lists A $it" } }.lists.elementAtOrNull(index)
            .also { logcat { "Lists b $it" } } ?: return@launch

         val randomElement = list.entries.random()
         _displayedPopupText.value = randomElement
      }
   }

   fun closePopup() {
      _displayedPopupText.value = null
   }
}
