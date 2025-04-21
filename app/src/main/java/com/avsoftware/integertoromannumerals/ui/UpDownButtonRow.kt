package com.avsoftware.integertoromannumerals.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avsoftware.integertoromannumerals.ui.theme.IntegerToRomanNumeralsTheme

@Composable
fun UpDownButtonRow(
    modifier: Modifier = Modifier,
    upClicked: () -> Unit,
    downClicked: () -> Unit,
    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WoodenButton(
            onClick = downClicked,
            isFlippedVertically = true
        )
        Spacer(modifier = Modifier.weight(1f))
        WoodenButton(
            onClick = upClicked,
            isFlippedVertically = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UpDownButtonRowPreview() {
    IntegerToRomanNumeralsTheme {
        UpDownButtonRow(
            upClicked = {},
            downClicked = {}
        )
    }
}