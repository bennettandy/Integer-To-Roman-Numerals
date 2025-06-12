package com.avsoftware.integertoromannumerals.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WoodenButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTextFieldInput() {
        // Set the composable content
        composeTestRule.setContent {
            WoodenButton(
                onClick = {}
            )
        }

        // Find the TextField by its label and verify it exists
        composeTestRule
            .onNodeWithContentDescription("Circular Button")
            .assertExists()


    }
}