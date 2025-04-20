package com.avsoftware.integertoromannumerals

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avsoftware.integertoromannumerals.roman.integerToRoman
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class RomanConverterViewModel @Inject constructor() : ViewModel() {
    val inputState = mutableStateOf("")
    val outputState = mutableStateOf("")

    init {
        viewModelScope.launch {
            snapshotFlow { inputState.value }
                .debounce(300)
                .map { input ->
                    input.toIntOrNull()?.takeIf { it > 0 }?.let { integerToRoman(it) } ?: ""
                }
                .collect { roman ->
                    outputState.value = roman
                }
        }
    }
}