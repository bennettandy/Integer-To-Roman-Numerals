package com.avsoftware.integertoromannumerals.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.avsoftware.integertoromannumerals.RomanUiIntent
import com.avsoftware.integertoromannumerals.ui.WoodenButton
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DecimalSelectorBottomSheetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // mock intent handler for bottom sheet
    private val intentHandler: (RomanUiIntent) -> Unit = mockk(relaxed = true)

    @Test
    fun testBottomSheetDisplaysCorrectly() {
        // Set up the composable
        composeTestRule.setContent {
            DecimalSelectorBottomSheet(decimalValue = 42L, intentHandler = intentHandler)
        }

        // Verify the bottom sheet content is displayed
        composeTestRule.onNodeWithText("Enter a decimal number").assertExists()
        composeTestRule.onNodeWithTag("decimal_entry").assertExists()
        composeTestRule.onNodeWithText("42").assertExists() // Initial decimalValue
        composeTestRule.onNodeWithText("Reset").assertExists()
        composeTestRule.onNodeWithText("Done").assertExists()
    }

    @Test
    fun testTextFieldInputTriggersIntent() {
        // Set up the composable
        composeTestRule.setContent {
            DecimalSelectorBottomSheet(decimalValue = 0L, intentHandler = intentHandler)
        }

        // Perform text input
        composeTestRule.onNodeWithTag("decimal_entry").performTextInput("123")

        // Verify the intentHandler was called with UpdateDecimalText
        // todo: text is appended to existing "0" string
        verify { intentHandler(RomanUiIntent.UpdateDecimalText("1230")) }
    }

    @Test
    fun testResetButtonTriggersIntent() {
        // Set up the composable
        composeTestRule.setContent {
            DecimalSelectorBottomSheet(decimalValue = 42L, intentHandler = intentHandler)
        }

        // Click the Reset button
        composeTestRule.onNodeWithText("Reset").performClick()

        // Verify the intentHandler was called with ResetDecimal
        verify { intentHandler(RomanUiIntent.ResetDecimal) }
    }

    @Test
    fun testDoneButtonTriggersDismissIntent() {
        // Set up the composable
        composeTestRule.setContent {
            DecimalSelectorBottomSheet(decimalValue = 42L, intentHandler = intentHandler)
        }

        // Click the Done button
        composeTestRule.onNodeWithText("Done").performClick()

        // Verify the intentHandler was called with DismissBottomSheet
        verify { intentHandler(RomanUiIntent.DismissBottomSheet) }
    }
}