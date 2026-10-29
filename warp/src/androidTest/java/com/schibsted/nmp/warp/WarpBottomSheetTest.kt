package com.schibsted.nmp.warp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.isHeading
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.schibsted.nmp.warp.components.BottomSheetLock
import com.schibsted.nmp.warp.components.DiscouragedWarpApi
import com.schibsted.nmp.warp.components.WarpBottomSheet
import com.schibsted.nmp.warp.components.WarpBottomSheetHeader
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpResources.icons
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented tests for WarpBottomSheet's header, close button and lock.
 */
@OptIn(DiscouragedWarpApi::class)
@RunWith(AndroidJUnit4::class)
class WarpBottomSheetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val closeLabel = InstrumentationRegistry.getInstrumentation().targetContext
        .getString(R.string.bottom_sheet_close)
    private var dismissCount = 0
    private var closeIconDescription = ""

    private fun setSheet(locked: Boolean = false, lockAfterOpening: Boolean = false) {
        composeTestRule.setContent {
            var shown by remember { mutableStateOf(true) }
            var isLocked by remember { mutableStateOf(locked) }
            closeIconDescription = icons.close.description
            if (shown) {
                WarpBottomSheet(
                    onDismissRequest = {
                        dismissCount++
                        shown = false
                    },
                    header = WarpBottomSheetHeader(title = "Title"),
                    lock = BottomSheetLock(isLocked),
                ) { dismiss ->
                    WarpText(text = "Sheet content")
                    WarpButton(text = "Done", onClick = dismiss)
                    if (lockAfterOpening) WarpButton(text = "Lock", onClick = { isLocked = true })
                }
            }
        }
        composeTestRule.waitForIdle()
    }

    private fun assertStillOpen() {
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Sheet content").assertExists()
        assertEquals(0, dismissCount)
    }

    private fun waitUntilDismissed() {
        composeTestRule.waitUntil(timeoutMillis = 5_000) { dismissCount == 1 }
        composeTestRule.onNodeWithText("Sheet content").assertDoesNotExist()
    }

    @Test
    fun title_isAHeading() {
        setSheet()

        composeTestRule.onNodeWithText("Title").assert(isHeading())
    }

    @Test
    fun closeButton_readsPurposeBeforeIconDescriptionAndDismisses() {
        setSheet()

        composeTestRule.onNodeWithContentDescription(closeLabel)
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.ContentDescription, listOf(closeLabel, closeIconDescription)))
            .performClick()

        waitUntilDismissed()
    }

    @Test
    fun noHeader_showsNoWarpCloseButton() {
        composeTestRule.setContent {
            WarpBottomSheet(onDismissRequest = {}, header = null) {
                WarpText(text = "Sheet content")
            }
        }

        composeTestRule.onNodeWithText("Sheet content").assertExists()
        composeTestRule.onNodeWithContentDescription(closeLabel).assertDoesNotExist()
    }

    @Test
    fun backPress_dismisses() {
        setSheet()

        Espresso.pressBack()

        waitUntilDismissed()
    }

    @Test
    fun swipeDown_dismisses() {
        setSheet()

        composeTestRule.onNodeWithText("Sheet content").performTouchInput { swipeDown(endY = bottom + 1_000f) }

        waitUntilDismissed()
    }

    @Test
    fun locked_closeButtonIsDisabledAndBackSwipeDoNothing() {
        setSheet(locked = true)

        composeTestRule.onNodeWithContentDescription(closeLabel).assertIsNotEnabled()
        Espresso.pressBack()
        composeTestRule.onNodeWithText("Sheet content").performTouchInput { swipeDown(endY = bottom + 1_000f) }

        assertStillOpen()
    }

    @Test
    fun lockedAfterOpening_backPressDoesNothing() {
        // M3 reads shouldDismissOnBackPress only when the sheet opens, so lock after opening.
        setSheet(lockAfterOpening = true)
        composeTestRule.onNodeWithContentDescription(closeLabel).assertIsEnabled()
        composeTestRule.onNodeWithText("Lock").performClick()

        Espresso.pressBack()

        assertStillOpen()
    }

    @Test
    fun locked_ownDismissStillClosesTheSheet() {
        setSheet(locked = true)

        composeTestRule.onNodeWithText("Done").performClick()

        waitUntilDismissed()
    }
}
