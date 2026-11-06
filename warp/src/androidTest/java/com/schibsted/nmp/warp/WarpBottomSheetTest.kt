package com.schibsted.nmp.warp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
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
import com.schibsted.nmp.warp.components.WarpBottomSheet
import com.schibsted.nmp.warp.components.WarpBottomSheetHeader
import com.schibsted.nmp.warp.components.WarpBottomSheetHeaderIcon
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpResources.icons
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented tests for WarpBottomSheet's header, close button and dismiss.
 */
@RunWith(AndroidJUnit4::class)
class WarpBottomSheetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val closeLabel = InstrumentationRegistry.getInstrumentation().targetContext
        .getString(R.string.bottom_sheet_close)
    private var dismissCount = 0
    private var onHiddenCount = 0
    private var closeIconDescription = ""

    private fun setSheet(
        header: WarpBottomSheetHeader = WarpBottomSheetHeader(title = "Title", showCloseButton = true),
    ) {
        composeTestRule.setContent {
            var shown by remember { mutableStateOf(true) }
            closeIconDescription = icons.close.description
            if (shown) {
                WarpBottomSheet(
                    onDismissRequest = {
                        dismissCount++
                        shown = false
                    },
                    header = header,
                ) { dismiss ->
                    WarpText(text = "Sheet content")
                    WarpButton(text = "Done", onClick = dismiss)
                    WarpButton(
                        text = "Confirm",
                        onClick = {
                            dismiss {
                                onHiddenCount++
                                shown = false
                            }
                        },
                    )
                }
            }
        }
        composeTestRule.waitForIdle()
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
    fun titleOnly_showsNoCloseButton() {
        setSheet(WarpBottomSheetHeader(title = "Title"))

        composeTestRule.onNodeWithText("Title").assert(isHeading())
        composeTestRule.onNodeWithContentDescription(closeLabel).assertDoesNotExist()
    }

    @Test
    fun closeButtonOnly_dismisses() {
        setSheet(WarpBottomSheetHeader(showCloseButton = true))

        composeTestRule.onNodeWithContentDescription(closeLabel).performClick()

        waitUntilDismissed()
    }

    @Test
    fun emptyHeader_showsNoWarpCloseButton() {
        setSheet(WarpBottomSheetHeader.empty())

        composeTestRule.onNodeWithText("Sheet content").assertExists()
        composeTestRule.onNodeWithContentDescription(closeLabel).assertDoesNotExist()
    }

    @Test
    fun headerIcon_isDecorative() {
        var iconDescription = ""
        composeTestRule.setContent {
            iconDescription = icons.sparkles.description
            WarpBottomSheet(
                onDismissRequest = {},
                header = WarpBottomSheetHeader(
                    title = "Title",
                    icon = WarpBottomSheetHeaderIcon.Icon(icons.sparkles),
                ),
            ) {
                WarpText(text = "Sheet content")
            }
        }

        composeTestRule.onNodeWithText("Title").assert(isHeading())
        composeTestRule.onNodeWithContentDescription(iconDescription).assertDoesNotExist()
    }

    @Test
    fun dismiss_callsOnDismissRequest() {
        setSheet()

        composeTestRule.onNodeWithText("Done").performClick()

        waitUntilDismissed()
    }

    @Test
    fun dismissWithOnHidden_runsItInsteadOfOnDismissRequest() {
        setSheet()

        composeTestRule.onNodeWithText("Confirm").performClick()

        composeTestRule.waitUntil(timeoutMillis = 5_000) { onHiddenCount == 1 }
        composeTestRule.onNodeWithText("Sheet content").assertDoesNotExist()
        assertEquals(0, dismissCount)
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

}
