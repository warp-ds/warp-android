package com.schibsted.nmp.warp.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.pressKey
import androidx.compose.ui.test.requestFocus
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import androidx.compose.ui.unit.width
import androidx.test.platform.app.InstrumentationRegistry
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.utils.InternalWarpApi
import com.schibsted.nmp.warp.theme.WarpResources.icons
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@Suppress("DEPRECATION")
class WarpButtonBehaviourTest {
    @get:Rule
    val compose = createComposeRule()

    private fun bounds(tag: String): DpRect = compose.onNodeWithTag(tag).getBoundsInRoot()

    @Test
    fun clickInvokesOnClick() {
        var clicks = 0
        compose.setContent {
            WarpButton(text = "Save", onClick = { clicks++ }, modifier = Modifier.testTag("b"))
        }

        compose.onNodeWithTag("b").assertIsEnabled().performClick()

        assertEquals(1, clicks)
    }

    @Test
    fun disabledButtonIgnoresClicks() {
        var clicks = 0
        compose.setContent {
            WarpButton(text = "Save", onClick = { clicks++ }, enabled = false, modifier = Modifier.testTag("b"))
        }

        compose.onNodeWithTag("b").assertIsNotEnabled().performClick()

        assertEquals(0, clicks)
    }

    @Test
    fun loadingButtonIgnoresClicks() {
        var clicks = 0
        compose.setContent {
            WarpButton(text = "Save", onClick = { clicks++ }, loading = true, modifier = Modifier.testTag("b"))
        }

        compose.onNodeWithTag("b").performClick()

        assertEquals(0, clicks)
    }

    @Test
    fun loadingButtonExposesLoadingStateDescription() {
        compose.setContent {
            WarpButton(text = "Save", onClick = {}, loading = true, modifier = Modifier.testTag("b"))
        }

        compose.onNodeWithTag("b").assert(
            SemanticsMatcher.expectValue(
                SemanticsProperties.StateDescription,
                InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.loading_description)
            )
        )
    }

    @Test
    fun loadingAndDisabledButtonIsNotEnabled() {
        var clicks = 0
        compose.setContent {
            WarpButton(
                text = "Save",
                onClick = { clicks++ },
                loading = true,
                enabled = false,
                modifier = Modifier.testTag("b")
            )
        }

        compose.onNodeWithTag("b").assertIsNotEnabled().performClick()

        assertEquals(0, clicks)
    }

    @Test
    fun buttonHasButtonRole() {
        compose.setContent {
            WarpButton(text = "Save", onClick = {}, modifier = Modifier.testTag("b"))
        }

        compose.onNodeWithTag("b")
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))
    }

    @Test
    fun labelIsTheAccessibleText() {
        compose.setContent {
            WarpButton(text = "Save", onClick = {}, modifier = Modifier.testTag("b"))
        }

        compose.onNodeWithTag("b").assertTextEquals("Save")
    }

    @Test
    fun callWithoutStyleOrSizeRendersTheNewMediumButton() {
        compose.setContent {
            WarpButton(text = "Save", onClick = {}, modifier = Modifier.testTag("b"))
        }

        compose.onNodeWithTag("b").assertHeightIsEqualTo(48.dp)
    }

    @Test
    fun smallerSizesKeepA48dpTouchTarget() {
        compose.setContent {
            Column {
                Box(Modifier.testTag("sBox")) {
                    WarpButton(text = "S", onClick = {}, size = WarpButtonSize.Small)
                }
                Box(Modifier.testTag("xsBox")) {
                    WarpButton(text = "XS", onClick = {}, size = WarpButtonSize.ExtraSmall)
                }
            }
        }

        // The wrap-content Box measures the button's layout footprint, including the 48dp target.
        listOf("sBox", "xsBox").forEach { tag ->
            compose.onNodeWithTag(tag).assertHeightIsAtLeast(48.dp).assertWidthIsAtLeast(48.dp)
        }
    }

    @Test
    fun newStyleWithSmallFlagMatchesSmallSize() {
        compose.setContent {
            Column {
                WarpButton(text = "Save", onClick = {}, style = WarpButtonStyle.Primary, small = true, modifier = Modifier.testTag("flag"))
                WarpButton(text = "Save", onClick = {}, size = WarpButtonSize.Small, modifier = Modifier.testTag("small"))
                WarpButton(text = "Save", onClick = {}, size = WarpButtonSize.Medium, modifier = Modifier.testTag("medium"))
            }
        }

        assertEquals(bounds("small").width, bounds("flag").width)
        assertNotEquals(bounds("medium").width, bounds("flag").width)
    }

    @Test
    fun legacyStyleKeepsLegacyLayout() {
        compose.setContent {
            Column {
                WarpButton(text = "Save", onClick = {}, style = WarpButtonStyle.Utility, modifier = Modifier.testTag("new"))
                WarpButton(text = "Save", onClick = {}, style = WarpButtonStyle.Utility, small = false, modifier = Modifier.testTag("old"))
                WarpButton(text = "Save", onClick = {}, style = WarpButtonStyle.Overlay, modifier = Modifier.testTag("modern"))
            }
        }

        assertEquals(bounds("old").width, bounds("new").width)
        assertEquals(bounds("old").height, bounds("new").height)
        // The legacy renderer pads 16dp horizontally, the new one 24dp.
        assertNotEquals(bounds("modern").width, bounds("new").width)
    }

    @Test
    fun legacyStyleWithSmallerSizesUsesLegacySmallLayout() {
        compose.setContent {
            Column {
                WarpButton(text = "Save", onClick = {}, style = WarpButtonStyle.Utility, size = WarpButtonSize.ExtraSmall, modifier = Modifier.testTag("xs"))
                WarpButton(text = "Save", onClick = {}, style = WarpButtonStyle.Utility, size = WarpButtonSize.Small, modifier = Modifier.testTag("s"))
                WarpButton(text = "Save", onClick = {}, style = WarpButtonStyle.Utility, small = true, modifier = Modifier.testTag("old"))
            }
        }

        val old = bounds("old")
        listOf("xs", "s").forEach { tag ->
            // Dp values are derived from per-item pixel offsets, so allow float noise.
            assertEquals(tag, old.width.value, bounds(tag).width.value, 0.01f)
            assertEquals(tag, old.height.value, bounds(tag).height.value, 0.01f)
        }
    }

    @Test
    fun disabledButtonStaysFocusable() {
        compose.setContent {
            WarpButton(text = "Save", onClick = {}, enabled = false, modifier = Modifier.testTag("b"))
        }

        compose.onNodeWithTag("b").assert(SemanticsMatcher.keyIsDefined(SemanticsProperties.Focused))
    }

    @OptIn(InternalWarpApi::class)
    @Test
    fun focusRingDoesNotShiftLayout() {
        compose.setContent {
            Column {
                WarpButton(text = "Save", onClick = {}, forcedState = WarpButtonState.Default, modifier = Modifier.testTag("default"))
                WarpButton(text = "Save", onClick = {}, forcedState = WarpButtonState.Focus, modifier = Modifier.testTag("focus"))
            }
        }

        assertEquals(bounds("default").width, bounds("focus").width)
        assertEquals(bounds("default").height, bounds("focus").height)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun focusedButtonClicksOnEnter() {
        var clicks = 0
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        instrumentation.setInTouchMode(false)
        try {
            compose.setContent {
                WarpButton(text = "Save", onClick = { clicks++ }, modifier = Modifier.testTag("b"))
            }

            compose.onNodeWithTag("b")
                .requestFocus()
                .assertIsFocused()
                .performKeyInput { pressKey(Key.Enter) }

            assertEquals(1, clicks)
        } finally {
            instrumentation.setInTouchMode(true)
        }
    }

    @Test
    fun multiLineLabelGrowsTheButton() {
        compose.setContent {
            WarpButton(
                text = "A label that is long enough to need a second and then also a third line",
                onClick = {},
                maxLines = 3,
                modifier = Modifier.width(160.dp).testTag("b")
            )
        }

        // Three lines plus padding exceed 48dp even where a text line is only ~16dp high (API 24).
        val height = bounds("b").height
        assertTrue("height was $height", height > 48.dp)
    }

    @Test
    fun labelKeepsItsNaturalWidthInAnUnboundedWidthParent() {
        compose.setContent {
            Row(Modifier.horizontalScroll(rememberScrollState())) {
                WarpButton(
                    text = "Click me",
                    onClick = {},
                    style = WarpButtonStyle.Primary,
                    modifier = Modifier.testTag("b")
                )
            }
        }

        assertTrue(compose.onNodeWithText("Click me").getBoundsInRoot().width > 20.dp)
        assertTrue(bounds("b").width > bounds("b").height)
    }

    @Test
    fun iconButtonExposesContentDescription() {
        compose.setContent {
            WarpIconButton(icon = icons.share, contentDescription = "Share", onClick = {}, modifier = Modifier.testTag("b"))
        }

        compose.onNodeWithTag("b")
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.ContentDescription, listOf("Share")))
    }

    @Test
    fun iconButtonClicksAndHasButtonRole() {
        var clicks = 0
        compose.setContent {
            WarpIconButton(icon = icons.share, contentDescription = "Share", onClick = { clicks++ }, modifier = Modifier.testTag("b"))
        }

        compose.onNodeWithTag("b")
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))
            .performClick()

        assertEquals(1, clicks)
    }

    @Test
    fun mediumIconButtonIsA48dpCircle() {
        compose.setContent {
            WarpIconButton(icon = icons.share, contentDescription = "Share", onClick = {}, modifier = Modifier.testTag("b"))
        }

        compose.onNodeWithTag("b").assertWidthIsEqualTo(48.dp).assertHeightIsEqualTo(48.dp)
    }

    @Test
    fun extraSmallIconButtonKeepsA48dpTouchTarget() {
        compose.setContent {
            // The wrap-content Box measures the touch target; the tagged button itself reports the 32dp visual size.
            Box(Modifier.testTag("bBox")) {
                WarpIconButton(
                    icon = icons.share,
                    contentDescription = "Share",
                    onClick = {},
                    size = WarpButtonSize.ExtraSmall,
                )
            }
        }

        compose.onNodeWithTag("bBox").assertWidthIsAtLeast(48.dp).assertHeightIsAtLeast(48.dp)
    }
}
