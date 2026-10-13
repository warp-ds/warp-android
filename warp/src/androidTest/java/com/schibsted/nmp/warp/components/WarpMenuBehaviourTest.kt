package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.height
import androidx.compose.ui.unit.width
import android.view.KeyEvent
import androidx.test.platform.app.InstrumentationRegistry
import com.schibsted.nmp.warp.components.utils.InternalWarpApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class WarpMenuBehaviourTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun itemClickInvokesOnClick() {
        var clicks = 0
        compose.setContent {
            WarpMenuItem(text = "Edit", onClick = { clicks++ }, modifier = Modifier.testTag("item"))
        }

        compose.onNodeWithTag("item").assertIsEnabled().performClick()

        assertEquals(1, clicks)
    }

    @Test
    fun disabledItemIsNotEnabledAndIgnoresClicks() {
        var clicks = 0
        compose.setContent {
            WarpMenuItem(text = "Edit", onClick = { clicks++ }, enabled = false, modifier = Modifier.testTag("item"))
        }

        compose.onNodeWithTag("item").assertIsNotEnabled().performClick()

        assertEquals(0, clicks)
    }

    @Test
    fun itemHasButtonRole() {
        compose.setContent {
            WarpMenuItem(text = "Edit", onClick = {}, modifier = Modifier.testTag("item"))
        }

        compose.onNodeWithTag("item")
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))
            .assertIsNotSelected()
    }

    @Test
    fun selectedItemIsSelectedAndCheckHasNoDescription() {
        compose.setContent {
            WarpMenuItem(
                text = "Newest",
                supportingText = "Shown first",
                onClick = {},
                selected = true,
                modifier = Modifier.testTag("item")
            )
        }

        compose.onNodeWithTag("item")
            .assertIsSelected()
            .assert(
                SemanticsMatcher.expectValue(
                    SemanticsProperties.ContentDescription,
                    listOf("Newest", "Shown first")
                )
            )
    }

    @Test
    fun selectedDisabledItemIsSelectedAndNotEnabled() {
        compose.setContent {
            WarpMenuItem(
                text = "Newest",
                onClick = {},
                selected = true,
                enabled = false,
                modifier = Modifier.testTag("item")
            )
        }

        compose.onNodeWithTag("item").assertIsSelected().assertIsNotEnabled()
    }

    @Test
    fun selectedItemReplacesLeadingIcon() {
        var iconComposed = false
        compose.setContent {
            WarpMenuItem(
                text = "Newest",
                onClick = {},
                selected = true,
                icon = { iconComposed = true },
            )
        }
        compose.waitForIdle()

        assertEquals(false, iconComposed)
    }

    @Test
    fun expandedMenuShowsItemsAndClickRunsCallback() {
        var clicked = false
        compose.setContent {
            Box {
                WarpMenu(expanded = true, onDismissRequest = {}) {
                    WarpMenuItem(text = "Edit", onClick = { clicked = true }, modifier = Modifier.testTag("edit"))
                }
            }
        }

        compose.onNodeWithTag("edit").assertIsDisplayed().performClick()

        assertEquals(true, clicked)
    }

    @Test
    fun collapsedMenuShowsNothing() {
        compose.setContent {
            Box {
                WarpMenu(expanded = false, onDismissRequest = {}) {
                    WarpMenuItem(text = "Edit", onClick = {}, modifier = Modifier.testTag("edit"))
                }
            }
        }

        compose.onNodeWithTag("edit").assertDoesNotExist()
    }

    @Test
    fun backPressRequestsDismiss() {
        var dismissed = false
        compose.setContent {
            Box {
                WarpMenu(expanded = true, onDismissRequest = { dismissed = true }) {
                    WarpMenuItem(text = "Edit", onClick = {})
                }
            }
        }
        compose.waitForIdle()

        InstrumentationRegistry.getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK)
        compose.waitForIdle()

        assertEquals(true, dismissed)
    }

    @Test
    fun longMenuScrollsToLastItem() {
        compose.setContent {
            Box {
                WarpMenu(expanded = true, onDismissRequest = {}) {
                    repeat(40) { i ->
                        WarpMenuItem(text = "Item $i", onClick = {}, modifier = Modifier.testTag("item$i"))
                    }
                }
            }
        }

        compose.onNodeWithTag("item39").performScrollTo().assertIsDisplayed()
    }

    @OptIn(InternalWarpApi::class)
    @Test
    fun menuContentMatchesDropdownLayout() {
        compose.setContent {
            Column {
                Box {
                    WarpMenu(expanded = true, onDismissRequest = {}) {
                        WarpMenuItem(text = "Edit", onClick = {}, modifier = Modifier.testTag("popupItem"))
                    }
                }
                WarpMenuContent {
                    WarpMenuItem(text = "Edit", onClick = {}, modifier = Modifier.testTag("staticItem"))
                }
            }
        }

        val popup = compose.onNodeWithTag("popupItem").getBoundsInRoot()
        val static = compose.onNodeWithTag("staticItem").getBoundsInRoot()
        assertEquals(static.left, popup.left)
        assertEquals(static.top, popup.top)
        assertEquals(static.width, popup.width)
        assertEquals(static.height, popup.height)
    }
}
