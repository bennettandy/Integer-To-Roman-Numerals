package com.avsoftware.integertoromannumerals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avsoftware.integertoromannumerals.roman.DecimalToRomanUseCase
import com.avsoftware.integertoromannumerals.roman.integerToRoman
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import javax.inject.Inject

@HiltViewModel
class RomanConverterMviViewModel @Inject constructor(
    private val useCase: DecimalToRomanUseCase
) : ViewModel(), ContainerHost<RomanUiState, Nothing> {

    // container exposes UI state flow and side effect flows
    override val container =
        viewModelScope.container<RomanUiState, Nothing>(RomanUiState.default)

    fun handleIntent(intent: RomanUiIntent) = intent {
        when (intent){
            is RomanUiIntent.UpdateDecimalText -> {
                reduce {
                    state.copy(
                        decimalText = intent.decimalText,
                        romanText = useCase(intent.decimalText)
                    )
                }
            }

            is RomanUiIntent.UpClicked -> intent {
                reduce {
                    val decimalText = ((state.decimalText.toIntOrNull() ?: 0) + 1).toString()
                    state.copy(
                        decimalText = decimalText,
                        romanText = useCase(decimalText)
                    )
                }
            }

            is RomanUiIntent.DownClicked -> intent {
                reduce {
                    val decimalText = ((state.decimalText.toIntOrNull() ?: 0) - 1).toString()
                    state.copy(
                        decimalText = decimalText,
                        romanText = useCase(decimalText)
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
    data object UpClicked: RomanUiIntent
    data object DownClicked: RomanUiIntent
}