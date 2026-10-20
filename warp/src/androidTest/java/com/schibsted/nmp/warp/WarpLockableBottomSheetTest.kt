package com.schibsted.nmp.warp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.nmp.warp.components.BottomSheetBackButton
import com.schibsted.nmp.warp.components.DiscouragedWarpApi
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpLockableBottomSheet
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpResources.icons
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented tests for WarpLockableBottomSheet's lock.
 */
@OptIn(DiscouragedWarpApi::class)
@RunWith(AndroidJUnit4::class)
class WarpLockableBottomSheetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var dismissCount = 0
    private var backButtonDescription = ""
    private var closeButtonDescription = ""

    private fun setSheet(locked: Boolean) {
        composeTestRule.setContent {
            var shown by remember { mutableStateOf(true) }
            var backClicks by remember { mutableIntStateOf(0) }
            backButtonDescription = icons.arrowLeft.description
            closeButtonDescription = icons.close.description
            if (shown) {
                WarpLockableBottomSheet(
                    onDismissRequest = {
                        dismissCount++
                        shown = false
                    },
                    locked = locked,
                    backButton = BottomSheetBackButton { backClicks++ },
                    title = "Title",
                ) { dismiss ->
                    WarpText(text = "Sheet content")
                    WarpButton(text = "Done", onClick = dismiss)
                }
            }
        }
        composeTestRule.waitForIdle()
    }

    @Test
    fun unlocked_backPress_dismisses() {
        setSheet(locked = false)

        Espresso.pressBack()

        composeTestRule.waitUntil(timeoutMillis = 5_000) { dismissCount == 1 }
        composeTestRule.onNodeWithText("Sheet content").assertDoesNotExist()
    }

    @Test
    fun lockedAfterOpening_backPress_doesNotDismiss() {
        // M3 reads shouldDismissOnBackPress only when the sheet opens, so lock after opening.
        composeTestRule.setContent {
            var shown by remember { mutableStateOf(true) }
            var locked by remember { mutableStateOf(false) }
            if (shown) {
                WarpLockableBottomSheet(
                    onDismissRequest = {
                        dismissCount++
                        shown = false
                    },
                    locked = locked,
                ) {
                    WarpText(text = "Sheet content")
                    WarpButton(text = "Lock", onClick = { locked = true })
                }
            }
        }
        composeTestRule.onNodeWithText("Lock").performClick()

        Espresso.pressBack()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Sheet content").assertExists()
        assertEquals(0, dismissCount)
    }

    @Test
    fun locked_backPress_doesNotDismiss() {
        setSheet(locked = true)

        Espresso.pressBack()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Sheet content").assertExists()
        assertEquals(0, dismissCount)
    }

    @Test
    fun unlocked_swipeDown_dismisses() {
        setSheet(locked = false)

        composeTestRule.onNodeWithText("Sheet content").performTouchInput { swipeDown() }

        composeTestRule.waitUntil(timeoutMillis = 5_000) { dismissCount == 1 }
        composeTestRule.onNodeWithText("Sheet content").assertDoesNotExist()
    }

    @Test
    fun locked_swipeDown_doesNotDismiss() {
        setSheet(locked = true)

        composeTestRule.onNodeWithText("Sheet content").performTouchInput { swipeDown() }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Sheet content").assertExists()
        assertEquals(0, dismissCount)
    }

    @Test
    fun locked_ownDismiss_closesSheet() {
        setSheet(locked = true)

        composeTestRule.onNodeWithText("Done").performClick()

        composeTestRule.waitUntil(timeoutMillis = 5_000) { dismissCount == 1 }
        composeTestRule.onNodeWithText("Sheet content").assertDoesNotExist()
    }

    @Test
    fun locked_backButton_isDisabled() {
        setSheet(locked = true)

        composeTestRule.onNodeWithContentDescription(backButtonDescription).assertIsNotEnabled()
    }

    @Test
    fun unlocked_backButton_isEnabled() {
        setSheet(locked = false)

        composeTestRule.onNodeWithContentDescription(backButtonDescription).assertIsEnabled()
    }

    private fun setSheetWithoutBackButton(locked: Boolean) {
        composeTestRule.setContent {
            var shown by remember { mutableStateOf(true) }
            closeButtonDescription = icons.close.description
            if (shown) {
                WarpLockableBottomSheet(
                    onDismissRequest = {
                        dismissCount++
                        shown = false
                    },
                    locked = locked,
                    title = "Title",
                ) {
                    WarpText(text = "Sheet content")
                }
            }
        }
        composeTestRule.waitForIdle()
    }

    @Test
    fun closeButton_dismisses() {
        setSheetWithoutBackButton(locked = false)

        composeTestRule.onNodeWithContentDescription(closeButtonDescription).performClick()

        composeTestRule.waitUntil(timeoutMillis = 5_000) { dismissCount == 1 }
        composeTestRule.onNodeWithText("Sheet content").assertDoesNotExist()
    }

    @Test
    fun locked_closeButton_isDisabled() {
        setSheetWithoutBackButton(locked = true)

        composeTestRule.onNodeWithContentDescription(closeButtonDescription).assertIsNotEnabled()
        assertEquals(0, dismissCount)
    }

    @Test
    fun backButton_replacesCloseButton() {
        setSheet(locked = false)

        composeTestRule.onNodeWithContentDescription(backButtonDescription).assertExists()
        composeTestRule.onNodeWithContentDescription(closeButtonDescription).assertDoesNotExist()
    }
}
