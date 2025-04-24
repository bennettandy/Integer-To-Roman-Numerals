package com.avsoftware.integertoromannumerals.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.avsoftware.integertoromannumerals.RomanUiIntent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DecimalSelectorBottomSheet(
    decimalValue: Long = 0L,
    intentHandler: (RomanUiIntent) -> Unit
) {

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { intentHandler(RomanUiIntent.DismissBottomSheet) },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Enter a decimal number", fontSize = 18.sp)
            TextField(
                value = decimalValue.toString(),
                onValueChange = { intentHandler(RomanUiIntent.UpdateDecimalText(it)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
                    .testTag("decimal_entry")
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        intentHandler(RomanUiIntent.ResetDecimal)
                    }
                ) {
                    Text("Reset")
                }
                Button(
                    onClick = {
                        intentHandler(RomanUiIntent.DismissBottomSheet)
                    }
                ) {
                    Text("Done")
                }
            }
        }
    }
}