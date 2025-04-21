package com.avsoftware.integertoromannumerals.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avsoftware.integertoromannumerals.R
import com.avsoftware.integertoromannumerals.ui.theme.IntegerToRomanNumeralsTheme

@Composable
fun WoodenButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isFlippedVertically: Boolean = false
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(90.dp)
            .clip(CircleShape)
            .shadow(elevation = 16.dp, shape = CircleShape)
    ) {
        Image(
            painter = painterResource(id = R.drawable.button_arrow),
            contentDescription = "Circular Button",
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .graphicsLayer {
                    // Flip vertically by setting scaleY to -1f
                    scaleY = if (isFlippedVertically) -1f else 1f
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WoodenButtonPreview() {
    IntegerToRomanNumeralsTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            WoodenButton(
                onClick = {}
            )
            WoodenButton(
                onClick = {},
                isFlippedVertically = true
            )
        }
    }
}