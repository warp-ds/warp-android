package com.schibsted.nmp.warp.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
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
}
