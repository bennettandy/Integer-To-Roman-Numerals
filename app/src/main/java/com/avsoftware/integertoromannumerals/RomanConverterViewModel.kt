package com.avsoftware.integertoromannumerals

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avsoftware.integertoromannumerals.roman.integerToRoman
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class RomanConverterViewModel : ViewModel() {
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