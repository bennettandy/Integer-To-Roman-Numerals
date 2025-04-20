package com.avsoftware.integertoromannumerals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avsoftware.integertoromannumerals.roman.integerToRoman
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import javax.inject.Inject

@HiltViewModel
class RomanConverterMviViewModel @Inject constructor() : ViewModel(), ContainerHost<RomanUiState, Nothing> {

    // container exposes UI state flow and side effect flows
    override val container =
        viewModelScope.container<RomanUiState, Nothing>(RomanUiState.default)

    fun handleIntent(intent: RomanUiIntent) = intent {
        when (intent){
            is RomanUiIntent.UpdateDecimalText -> {
                val romanText: String = intent.decimalText.toIntOrNull()?.takeIf { it > 0 }?.let { integerToRoman(it) } ?: ""

                reduce {
                    state.copy(
                        decimalText = intent.decimalText,
                        romanText = romanText
                    )
                }
            }
        }
    }
}

data class RomanUiState(
    val decimalText: String,
    val romanText: String
){
    companion object {
        val default = RomanUiState(
            decimalText = "",
            romanText = ""
        )
    }
}

sealed interface RomanUiIntent {
    data class UpdateDecimalText(val decimalText: String): RomanUiIntent
}