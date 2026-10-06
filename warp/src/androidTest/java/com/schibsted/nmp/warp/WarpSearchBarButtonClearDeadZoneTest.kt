package com.schibsted.nmp.warp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.schibsted.nmp.warp.components.WarpSearchBarButton
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WarpSearchBarButtonClearDeadZoneTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var inputClicks = 0

    @Test
    fun searchBarButton_tapAtFormerClearIconPosition_stillRoutesToInputClick_afterTextClears() {
        var text by mutableStateOf(EXISTING_QUERY)
        composeTestRule.setContent {
            WarpSearchBarButton(
                text = text,
                hint = HINT,
                onClick = { inputClicks++ },
                onClearClick = {},
            )
        }

        val clearFieldDescription = InstrumentationRegistry.getInstrumentation().targetContext
            .getString(R.string.clear_field)
        val formerClearIconBounds = composeTestRule
            .onNodeWithContentDescription(clearFieldDescription)
            .fetchSemanticsNode()
            .touchBoundsInRoot
        val tapPosition = formerClearIconBounds.center

        text = ""
        composeTestRule.waitForIdle()

        composeTestRule.onRoot().performTouchInput { click(tapPosition) }

        assertEquals(1, inputClicks)
    }
}

private const val HINT = "Search here"
private const val EXISTING_QUERY = "hello"
