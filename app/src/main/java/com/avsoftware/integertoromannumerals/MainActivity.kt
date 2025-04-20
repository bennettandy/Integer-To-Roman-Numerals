package com.avsoftware.integertoromannumerals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avsoftware.integertoromannumerals.ui.theme.IntegerToRomanNumeralsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RomanConverterMviViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val uiState: State<RomanUiState> = viewModel.container.stateFlow.collectAsState()

            IntegerToRomanNumeralsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    RomanConverter(
                        uiState = uiState.value,
                        intentHandler = viewModel::handleIntent,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RomanConverter(uiState: RomanUiState,
                   intentHandler: (RomanUiIntent) -> Unit,
                   modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(stringResource(R.string.enter_an_integer), modifier = Modifier.padding(8.dp))
        TextField(
            value = uiState.decimalText,
            onValueChange = { intentHandler(RomanUiIntent.UpdateDecimalText(it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Text(stringResource(R.string.roman_numeral), modifier = Modifier.padding(8.dp))
        Text(text = uiState.romanText, modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntegerToRomanNumeralsTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            RomanConverter(
                uiState = RomanUiState.default,
                intentHandler = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}