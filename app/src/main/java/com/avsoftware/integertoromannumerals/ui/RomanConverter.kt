package com.avsoftware.integertoromannumerals.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avsoftware.integertoromannumerals.R
import com.avsoftware.integertoromannumerals.RomanUiIntent
import com.avsoftware.integertoromannumerals.RomanUiState
import com.avsoftware.integertoromannumerals.ui.theme.IntegerToRomanNumeralsTheme

@Composable
fun RomanConverter(
    uiState: RomanUiState,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    intentHandler: (RomanUiIntent) -> Unit,
) {
    Column(modifier = modifier) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            value = uiState.decimalText,
            placeholder = {
                Text(
                    stringResource(R.string.enter_an_integer),
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            },
            textStyle = MaterialTheme.typography.headlineLarge,
            onValueChange = { intentHandler(RomanUiIntent.UpdateDecimalText(it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = uiState.romanText,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            fontFamily = FontFamily.Serif,
            style = MaterialTheme.typography.displayLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RomanConverterPreview() {
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