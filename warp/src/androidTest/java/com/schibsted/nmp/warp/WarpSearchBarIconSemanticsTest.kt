package com.schibsted.nmp.warp

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.createComposeRule
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
class WarpSearchBarIconSemanticsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun mergedContentDescriptions(): List<List<String>> =
        composeTestRule
            .onAllNodes(SemanticsMatcher.keyIsDefined(SemanticsProperties.ContentDescription))
            .fetchSemanticsNodes()
            .mapNotNull { it.config.getOrNull(SemanticsProperties.ContentDescription) }

    @Test
    fun searchBarButton_iconButtonsAnnounceTheirFunctionAndTheIconDescription() {
        composeTestRule.setContent {
            WarpSearchBarButton(
                text = "something",
                onClearClick = {},
                secondaryAction = WarpSearchBarAction(
                    icon = WarpIconResources.filter,
                    contentDescription = SECONDARY_ACTION_DESCRIPTION,
                    onClick = {},
                ),
                leadingAction = WarpSearchBarAction(
                    icon = WarpIconResources.arrowLeft,
                    contentDescription = LEADING_ACTION_DESCRIPTION,
                    onClick = {},
                ),
            )
        }

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val clearFieldDescription = context.getString(R.string.clear_field)
        val closeIconDescription = context.getString(R.string.close)
        val filterIconDescription = context.getString(R.string.filter)
        val arrowLeftIconDescription = context.getString(R.string.arrowleft)
        val descriptions = mergedContentDescriptions()

        assertEquals(
            listOf(clearFieldDescription, closeIconDescription),
            descriptions.single { clearFieldDescription in it },
        )
        assertEquals(
            listOf(SECONDARY_ACTION_DESCRIPTION, filterIconDescription),
            descriptions.single { SECONDARY_ACTION_DESCRIPTION in it },
        )
        assertEquals(
            listOf(LEADING_ACTION_DESCRIPTION, arrowLeftIconDescription),
            descriptions.single { LEADING_ACTION_DESCRIPTION in it },
        )
    }
}

private const val SECONDARY_ACTION_DESCRIPTION = "Filter"
private const val LEADING_ACTION_DESCRIPTION = "Back"
