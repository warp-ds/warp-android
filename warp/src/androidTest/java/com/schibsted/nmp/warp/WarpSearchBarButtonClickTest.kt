package com.schibsted.nmp.warp

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.schibsted.nmp.warp.components.WarpSearchBarAction
import com.schibsted.nmp.warp.components.WarpSearchBarButton
import com.schibsted.nmp.warp.theme.WarpIconResources
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WarpSearchBarButtonClickTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var leadingActionClicks = 0
    private var inputClicks = 0

    private fun setSearchBarButtonWithLeadingActionContent() {
        composeTestRule.setContent {
            WarpSearchBarButton(
                text = "",
                hint = HINT,
                onClick = { inputClicks++ },
                leadingAction = WarpSearchBarAction(
                    icon = WarpIconResources.arrowLeft,
                    contentDescription = LEADING_ACTION_DESCRIPTION,
                    onClick = { leadingActionClicks++ },
                ),
            )
        }
    }

    @Test
    fun searchBarButton_sendsLeadingIconClickToLeadingAction() {
        setSearchBarButtonWithLeadingActionContent()

        composeTestRule.onNodeWithContentDescription(LEADING_ACTION_DESCRIPTION).performClick()

        assertEquals(1, leadingActionClicks)
        assertEquals(0, inputClicks)
    }

    @Test
    fun searchBarButton_sendsClickOutsideLeadingIconToInputClick() {
        setSearchBarButtonWithLeadingActionContent()

        composeTestRule.onNodeWithText(HINT).performClick()

        assertEquals(1, inputClicks)
        assertEquals(0, leadingActionClicks)
    }

    @Test
    fun searchBarButton_sendsClickAtLeadingIconTrailingEdgeToLeadingAction() {
        setSearchBarButtonWithLeadingActionContent()

        val leadingIconTouchBounds = composeTestRule
            .onNodeWithContentDescription(LEADING_ACTION_DESCRIPTION)
            .fetchSemanticsNode()
            .touchBoundsInRoot
        val insideTrailingEdge = Offset(
            leadingIconTouchBounds.right - 1f,
            leadingIconTouchBounds.center.y,
        )

        composeTestRule.onRoot().performTouchInput { click(insideTrailingEdge) }

        assertEquals(1, leadingActionClicks)
        assertEquals(0, inputClicks)
    }

    @Test
    fun searchBarButton_showsClearButtonOnlyWhenHandlerProvided() {
        val clearHandler = mutableStateOf<(() -> Unit)?>(null)
        composeTestRule.setContent {
            WarpSearchBarButton(
                text = "something",
                hint = HINT,
                onClearClick = clearHandler.value
            )
        }

        val clearFieldDescription = InstrumentationRegistry.getInstrumentation().targetContext
            .getString(R.string.clear_field)
        composeTestRule.onNodeWithContentDescription(clearFieldDescription).assertDoesNotExist()

        composeTestRule.runOnIdle { clearHandler.value = {} }

        composeTestRule.onNodeWithContentDescription(clearFieldDescription).assertExists()
    }
}

private const val HINT = "Search here"
private const val LEADING_ACTION_DESCRIPTION = "Back"
