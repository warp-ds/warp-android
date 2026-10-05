package com.schibsted.nmp.warp

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.nmp.warp.components.SearchConfiguration
import com.schibsted.nmp.warp.components.WarpTopAppBar
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WarpTopAppBarSearchClearDescriptionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalMaterial3Api::class)
    @Test(expected = IllegalArgumentException::class)
    fun searchConfigWithBlankClearDescription_throws() {
        composeTestRule.setContent {
            WarpTopAppBar(
                titleText = "Title",
                searchConfig = SearchConfiguration(
                    state = TextFieldState(""),
                    onSearch = {},
                ),
                searchClearContentDescription = "",
            )
        }
    }
}
