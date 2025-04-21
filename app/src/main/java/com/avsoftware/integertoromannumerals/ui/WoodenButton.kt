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
        onClick = onClick, // Handle the click action
        modifier = modifier
            .size(90.dp) // Set the size of the button
            .clip(CircleShape) // Ensure the button is circular
            .shadow(elevation = 8.dp, shape = CircleShape) // Add drop shadow
    ) {
        Image(
            painter = painterResource(id = R.drawable.button_arrow), // Your button image
            contentDescription = "Circular Button",
            modifier = Modifier
                .fillMaxSize() // Ensure the image fills the button area
                .clip(CircleShape) // Clip the image to a circle (redundant but ensures fit)
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