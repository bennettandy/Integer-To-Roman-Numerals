package com.avsoftware.integertoromannumerals

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RomanNumeralGeneratorTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeTestRule.enableAccessibilityChecks()
    }

    @Test
    fun mainActivityTest() {
        composeTestRule.apply {
            onNodeWithTag("decimal_display").performClick()

            // Verify the "Enter Number" button is displayed
            onNodeWithText("Enter a decimal number").assertIsDisplayed()

            // Enter "42" into the TextField
            onNodeWithTag("decimal_entry")
                .performTextReplacement("42")

            // Verify the "Done" button is displayed
            onNodeWithText("Done").assertIsDisplayed()

            // Click the "Done" button to close the BottomSheet
            onNodeWithText("Done").performClick()

            // Verify the Roman numeral output is "XLII" (for 42)
            onNodeWithText("XLII").assertIsDisplayed()
        }
    }

    @Test
    fun testInvalidInput() {
        composeTestRule.apply {
            onNodeWithTag("decimal_display").performClick()

            // Verify the "Enter Number" button is displayed
            onNodeWithText("Enter a decimal number").assertIsDisplayed()

            // Enter "42" into the TextField
            onNodeWithTag("decimal_entry")
                .performTextReplacement("abc")

            onNodeWithText("Done").performClick()
            onNodeWithTag("decimal_display").assertTextEquals("0")
            onNodeWithTag("roman_numeral").assertTextEquals("") // Empty output
        }
    }

    @Test
    fun testResetButton() {
        composeTestRule.apply {

            onNodeWithTag("decimal_display").performClick()

            // Verify the "Enter Number" button is displayed
            onNodeWithText("Enter a decimal number").assertIsDisplayed()

            // Enter "42" into the TextField
            onNodeWithTag("decimal_entry")
                .performTextReplacement("123")

            onNodeWithText("Reset").performClick()

            onNodeWithTag("roman_numeral").assertTextEquals("") // Empty output
        }
    }
}