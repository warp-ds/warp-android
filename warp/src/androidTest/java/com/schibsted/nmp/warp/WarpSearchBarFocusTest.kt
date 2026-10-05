package com.schibsted.nmp.warp

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.nmp.warp.components.WarpSearchBar
import com.schibsted.nmp.warp.components.WarpSearchBarButton
import com.schibsted.nmp.warp.components.WarpSearchBarImpl
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WarpSearchBarFocusTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun interactiveSearchBar_focusesInputField_whenInitialFocusRequested() {
        composeTestRule.setContent {
            WarpSearchBar(
                textFieldState = TextFieldState(""),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = HINT,
                requestInitialFocus = true,
            )
        }

        composeTestRule.onNode(hasSetTextAction()).assertIsFocused()
    }

    @Test
    fun interactiveSearchBar_leavesInputFieldUnfocused_byDefault() {
        composeTestRule.setContent {
            WarpSearchBar(
                textFieldState = TextFieldState(""),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = HINT,
            )
        }

        composeTestRule.onNode(hasSetTextAction()).assertIsNotFocused()
    }

    @Test
    fun searchBarButton_staysUnfocusable_whenClicked() {
        var clicks = 0
        composeTestRule.setContent {
            WarpSearchBarButton(
                text = "",
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = HINT,
                onClick = { clicks++ },
            )
        }

        composeTestRule.onAllNodes(isFocused()).assertCountEquals(0)

        composeTestRule.onNodeWithText(HINT).performClick()

        assertEquals(1, clicks)
        composeTestRule.onAllNodes(isFocused()).assertCountEquals(0)
    }

    @Test
    fun readOnlySearchBar_ignoresInitialFocusRequest() {
        composeTestRule.setContent {
            WarpSearchBarImpl(
                textFieldState = TextFieldState(""),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = HINT,
                readOnly = true,
                onInputClick = {},
                requestInitialFocus = true,
            )
        }

        composeTestRule.onAllNodes(isFocused()).assertCountEquals(0)
    }

    @Test
    fun interactiveSearchBar_reportsSingleFocusGainToCaller() {
        val focusEvents = mutableListOf<Boolean>()
        composeTestRule.setContent {
            WarpSearchBar(
                textFieldState = TextFieldState(""),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = HINT,
                onFocusChanged = { focusEvents += it },
            )
        }

        assertEquals(emptyList<Boolean>(), focusEvents)

        composeTestRule.onNode(hasSetTextAction()).performClick()

        composeTestRule.onNode(hasSetTextAction()).assertIsFocused()
        assertEquals(listOf(true), focusEvents)
    }

    @Test
    fun readOnlySearchBar_neverReportsFocusChanges() {
        val focusEvents = mutableListOf<Boolean>()
        composeTestRule.setContent {
            WarpSearchBarImpl(
                textFieldState = TextFieldState(""),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = HINT,
                readOnly = true,
                onInputClick = {},
                onFocusChanged = { focusEvents += it },
            )
        }

        composeTestRule.onNodeWithText(HINT).performClick()

        composeTestRule.onAllNodes(isFocused()).assertCountEquals(0)
        assertEquals(emptyList<Boolean>(), focusEvents)
    }

    @Test
    fun callerFocusRequester_focusesInputField() {
        val focusRequester = FocusRequester()
        val focusEvents = mutableListOf<Boolean>()
        composeTestRule.setContent {
            WarpSearchBar(
                textFieldState = TextFieldState(""),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = HINT,
                onFocusChanged = { focusEvents += it },
                focusRequester = focusRequester,
            )
        }

        composeTestRule.runOnIdle { focusRequester.requestFocus() }

        composeTestRule.onNode(hasSetTextAction()).assertIsFocused()
        assertEquals(listOf(true), focusEvents)
    }
}

private const val HINT = "Search here"
private const val CLEAR_DESCRIPTION = "Clear"
