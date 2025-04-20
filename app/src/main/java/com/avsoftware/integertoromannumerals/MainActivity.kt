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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avsoftware.integertoromannumerals.roman.integerToRoman
import com.avsoftware.integertoromannumerals.ui.theme.IntegerToRomanNumeralsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntegerToRomanNumeralsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: RomanConverterViewModel by viewModels()
                    RomanConverter(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RomanConverter(viewModel: RomanConverterViewModel, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(stringResource(R.string.enter_an_integer), modifier = Modifier.padding(8.dp))
        TextField(
            value = viewModel.inputState.value,
            onValueChange = { viewModel.inputState.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Text(stringResource(R.string.roman_numeral), modifier = Modifier.padding(8.dp))
        Text(text = viewModel.outputState.value, modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntegerToRomanNumeralsTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val viewModel = RomanConverterViewModel()
            RomanConverter(
                viewModel = viewModel,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}