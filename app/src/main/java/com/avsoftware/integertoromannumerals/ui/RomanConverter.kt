package com.avsoftware.integertoromannumerals.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.avsoftware.integertoromannumerals.R
import com.avsoftware.integertoromannumerals.RomanUiIntent
import com.avsoftware.integertoromannumerals.RomanUiState
import com.avsoftware.integertoromannumerals.ui.theme.IntegerToRomanNumeralsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RomanConverter(
    uiState: RomanUiState,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    intentHandler: (RomanUiIntent) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()

    Column(modifier = modifier) {

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier
                .testTag("decimal_display")
                .padding(8.dp)
                .fillMaxWidth()
                .clickable { intentHandler(RomanUiIntent.ShowBottomSheet) },
            text = uiState.decimal.toString(),
            color = textColor,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            style = MaterialTheme.typography.displayLarge,
        )

        Spacer(modifier = Modifier.height(64.dp))

        Text(
            text = uiState.romanText,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp).fillMaxWidth().testTag("roman_numeral"),
            fontFamily = FontFamily.Serif,
            style = MaterialTheme.typography.displayLarge,
        )

        if (uiState.showBottomSheet) {
            DecimalSelectorBottomSheet(
                intentHandler = intentHandler,
                decimalValue = uiState.decimal
            )
        }
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