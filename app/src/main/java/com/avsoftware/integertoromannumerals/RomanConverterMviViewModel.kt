package com.avsoftware.integertoromannumerals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avsoftware.integertoromannumerals.roman.DecimalToRomanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import timber.log.Timber
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
                try {
                    val decimalValue = intent.decimalString.toLong()
                    reduce {
                        state.copy(
                            decimal = decimalValue,
                            romanText = useCase(decimalValue)
                        )
                    }
                }
                catch (ex: NumberFormatException){
                    Timber.e("Number format exception")
                }
            }

            is RomanUiIntent.UpClicked -> intent {
                reduce {
                    val decimal = state.decimal + 1
                    state.copy(
                        decimal = decimal,
                        romanText = useCase(decimal)
                    )
                }
            }

            is RomanUiIntent.DownClicked -> intent {
                reduce {
                    val decimal = state.decimal - 1
                    state.copy(
                        decimal = decimal,
                        romanText = useCase(decimal)
                    )
                }
            }

            is RomanUiIntent.ShowBottomSheet -> intent {
                reduce {
                    state.copy(showBottomSheet = true)
                }
            }

            is RomanUiIntent.DismissBottomSheet -> intent {
                reduce {
                    state.copy(showBottomSheet = false)
                }
            }

            is RomanUiIntent.ResetDecimal -> intent {
                reduce {
                    state.copy(
                        showBottomSheet = false,
                        decimal = 0L,
                        romanText = ""
                    )
                }
            }
        }
    }
}

data class RomanUiState(
    val decimal: Long,
    val romanText: String,
    val showBottomSheet: Boolean
){
    companion object {
        val default = RomanUiState(
            decimal = 0L,
            romanText = "",
            showBottomSheet = false
        )
    }
}

sealed interface RomanUiIntent {
    data class UpdateDecimalText(val decimalString: String): RomanUiIntent
    data object UpClicked: RomanUiIntent
    data object DownClicked: RomanUiIntent
    data object DismissBottomSheet: RomanUiIntent
    data object ShowBottomSheet: RomanUiIntent
    data object ResetDecimal: RomanUiIntent
}