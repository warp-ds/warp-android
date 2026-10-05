package com.schibsted.nmp.warp

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.TextRange
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.nmp.warp.components.WarpSearchBar
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WarpSearchBarCaretPositionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun caretSelection(): TextRange =
        composeTestRule
            .onNode(hasSetTextAction())
            .fetchSemanticsNode()
            .config[SemanticsProperties.TextSelectionRange]

    @Test
    fun interactiveSearchBar_placesCaretAtEndOfExistingText() {
        composeTestRule.setContent {
            WarpSearchBar(
                textFieldState = TextFieldState(EXISTING_QUERY),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = HINT,
                requestInitialFocus = true,
            )
        }

        assertEquals(TextRange(EXISTING_QUERY.length), caretSelection())
    }

    @Test
    fun interactiveSearchBar_keepsCaretAtEnd_afterReEnteringComposition() {
        val textFieldState = TextFieldState(EXISTING_QUERY)
        var searchBarVisible by mutableStateOf(true)
        composeTestRule.setContent {
            if (searchBarVisible) {
                WarpSearchBar(
                    textFieldState = textFieldState,
                    clearContentDescription = CLEAR_DESCRIPTION,
                    hint = HINT,
                    requestInitialFocus = true,
                )
            }
        }

        searchBarVisible = false
        composeTestRule.waitForIdle()
        searchBarVisible = true
        composeTestRule.waitForIdle()

        assertEquals(TextRange(EXISTING_QUERY.length), caretSelection())
    }

    @Test
    fun interactiveSearchBar_reportsEveryUserEdit_withoutFiringOnComposition() {
        val changes = mutableListOf<String>()
        composeTestRule.setContent {
            WarpSearchBar(
                textFieldState = TextFieldState(EXISTING_QUERY),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = HINT,
                onSearchChanged = { changes += it },
            )
        }

        composeTestRule.waitForIdle()
        assertEquals(emptyList<String>(), changes)

        composeTestRule.onNode(hasSetTextAction()).performTextInput("!")
        composeTestRule.waitForIdle()

        assertEquals(listOf("$EXISTING_QUERY!"), changes)
    }

    @Test
    fun interactiveSearchBar_submitsCurrentTextOnSearch() {
        val submitted = mutableListOf<String>()
        composeTestRule.setContent {
            WarpSearchBar(
                textFieldState = TextFieldState(EXISTING_QUERY),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = HINT,
                onSearch = { submitted += it },
            )
        }

        composeTestRule.onNode(hasSetTextAction()).performTextInput("!")
        composeTestRule.onNode(hasSetTextAction()).performImeAction()

        assertEquals(listOf("$EXISTING_QUERY!"), submitted)
    }
}

private const val HINT = "Search here"
private const val EXISTING_QUERY = "hello"
private const val CLEAR_DESCRIPTION = "Clear"
