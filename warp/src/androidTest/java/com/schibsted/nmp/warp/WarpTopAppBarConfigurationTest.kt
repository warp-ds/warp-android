package com.schibsted.nmp.warp

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.schibsted.nmp.warp.components.TopAppBarAction
import com.schibsted.nmp.warp.components.TopAppBarConfiguration
import com.schibsted.nmp.warp.components.TopAppBarMenuItem
import com.schibsted.nmp.warp.components.TopAppBarNavigation
import com.schibsted.nmp.warp.components.WarpBottomSheet
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTopAppBar
import com.schibsted.nmp.warp.theme.WarpResources.icons
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented tests for WarpTopAppBar built from a TopAppBarConfiguration.
 */
@OptIn(ExperimentalMaterial3Api::class)
@RunWith(AndroidJUnit4::class)
class WarpTopAppBarConfigurationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private fun contentDescriptionIs(vararg descriptions: String) =
        SemanticsMatcher.expectValue(SemanticsProperties.ContentDescription, descriptions.toList())

    @Test
    fun back_readsPurposeBeforeIconDescription() {
        var iconDescription = ""
        composeTestRule.setContent {
            iconDescription = icons.arrowLeft.description
            WarpTopAppBar(TopAppBarConfiguration(title = "Title", navigation = TopAppBarNavigation.Back {}))
        }
        val back = context.getString(R.string.top_app_bar_back)

        composeTestRule.onNodeWithContentDescription(back).assert(contentDescriptionIs(back, iconDescription))
    }

    @Test
    fun sheetWithoutNavigation_showsCloseThatReadsPurposeAndDismisses() {
        var iconDescription = ""
        var dismissed = false
        composeTestRule.setContent {
            iconDescription = icons.close.description
            var shown by remember { mutableStateOf(true) }
            if (shown) {
                WarpBottomSheet(
                    onDismissRequest = {
                        dismissed = true
                        shown = false
                    },
                    topAppBar = TopAppBarConfiguration(title = "Title"),
                ) {
                    WarpText(text = "Sheet content")
                }
            }
        }
        val close = context.getString(R.string.top_app_bar_close)

        composeTestRule.onNodeWithContentDescription(close)
            .assert(contentDescriptionIs(close, iconDescription))
            .performClick()

        composeTestRule.waitUntil(timeoutMillis = 5_000) { dismissed }
    }

    @Test
    fun toggle_readsLabelAndReportsCheckedState() {
        var iconDescription = ""
        composeTestRule.setContent {
            iconDescription = icons.heart.description
            var checked by remember { mutableStateOf(false) }
            WarpTopAppBar(
                TopAppBarConfiguration(
                    title = "Title",
                    actions = listOf(
                        TopAppBarAction.Toggle(
                            icon = icons.heart,
                            checkedIcon = icons.heartFilled,
                            checked = checked,
                            label = "Favourite",
                            onCheckedChange = { checked = it },
                        )
                    ),
                )
            )
        }
        val toggle = composeTestRule.onNodeWithContentDescription("Favourite")

        toggle.assert(contentDescriptionIs("Favourite", iconDescription)).assertIsOff()
        toggle.performClick()
        toggle.assertIsOn()
    }

    @Test
    fun menu_marksCheckedItemAndRunsItsAction() {
        var picked = ""
        composeTestRule.setContent {
            WarpTopAppBar(
                TopAppBarConfiguration(
                    title = "Title",
                    actions = listOf(
                        TopAppBarAction.Menu(
                            listOf(
                                TopAppBarMenuItem("Newest first", checked = true) { picked = "newest" },
                                TopAppBarMenuItem("Oldest first") { picked = "oldest" },
                            )
                        )
                    ),
                )
            )
        }

        composeTestRule.onNodeWithContentDescription(context.getString(R.string.top_app_bar_more_options))
            .performClick()
        composeTestRule.onNodeWithText("Newest first").assertIsSelected()
        composeTestRule.onNodeWithText("Oldest first").performClick()

        assertEquals("oldest", picked)
        composeTestRule.onNodeWithText("Oldest first").assertDoesNotExist()
    }
}
