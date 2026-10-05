package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.components.WarpSearchBar
import com.schibsted.nmp.warp.components.WarpSearchBarAction
import com.schibsted.nmp.warp.components.WarpSearchBarButton
import com.schibsted.nmp.warp.theme.WarpIconResources
import com.schibsted.nmp.warp.theme.WarpTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpSearchBarTest(
    @TestParameter val flavor: Flavor,
    @TestParameter val nightMode: NightMode,
    @TestParameter(valuesProvider = FontScaleProvider::class) private val fontScale: Float,
) {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5.copy(
            nightMode = nightMode,
            fontScale = fontScale
        ),
        theme = "android:Theme.Material.Light.NoActionBar",
        renderingMode = SessionParams.RenderingMode.V_SCROLL,
        snapshotHandler = if (Config.isVerifying) {
            SnapshotVerifier(
                maxPercentDifference = Config.maxPercentDifference,
                rootDirectory = flavor.dir
            )
        } else {
            HtmlReportWriter(snapshotRootDirectory = flavor.dir)
        }
    )

    @Test
    fun warp_search_bar_placeholder() {
        snapshot {
            WarpSearchBar(
                textFieldState = TextFieldState(""),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = SEARCH_HINT
            )
        }
    }

    @Test
    fun warp_search_bar_with_text_and_clear() {
        snapshot {
            WarpSearchBar(
                textFieldState = TextFieldState(QUERY),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = SEARCH_HINT
            )
        }
    }

    @Test
    fun warp_search_bar_with_secondary_action() {
        snapshot {
            WarpSearchBar(
                textFieldState = TextFieldState(""),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = SEARCH_HINT,
                secondaryAction = filterAction()
            )
        }
    }

    @Test
    fun warp_search_bar_with_leading_action() {
        snapshot {
            WarpSearchBar(
                textFieldState = TextFieldState(""),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = SEARCH_HINT,
                leadingAction = backAction()
            )
        }
    }

    @Test
    fun warp_search_bar_with_leading_and_secondary_action() {
        snapshot {
            WarpSearchBar(
                textFieldState = TextFieldState(""),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = SEARCH_HINT,
                leadingAction = backAction(),
                secondaryAction = filterAction()
            )
        }
    }

    @Test
    fun warp_search_bar_with_leading_action_and_clear() {
        snapshot {
            WarpSearchBar(
                textFieldState = TextFieldState(QUERY),
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = SEARCH_HINT,
                leadingAction = backAction()
            )
        }
    }

    @Test
    fun warp_search_bar_button_hint() {
        snapshot {
            WarpSearchBarButton(
                text = "",
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = BUTTON_HINT
            )
        }
    }

    @Test
    fun warp_search_bar_button_with_text_and_clear() {
        snapshot {
            WarpSearchBarButton(
                text = QUERY,
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = BUTTON_HINT
            )
        }
    }

    @Test
    fun warp_search_bar_button_with_secondary_action() {
        snapshot {
            WarpSearchBarButton(
                text = "",
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = BUTTON_HINT,
                secondaryAction = filterAction()
            )
        }
    }

    @Test
    fun warp_search_bar_button_with_text_and_secondary_action() {
        snapshot {
            WarpSearchBarButton(
                text = SECOND_QUERY,
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = BUTTON_HINT,
                secondaryAction = filterAction()
            )
        }
    }

    @Test
    fun warp_search_bar_button_with_leading_action() {
        snapshot {
            WarpSearchBarButton(
                text = "",
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = BUTTON_HINT,
                leadingAction = backAction()
            )
        }
    }

    @Test
    fun warp_search_bar_button_with_leading_and_secondary_action() {
        snapshot {
            WarpSearchBarButton(
                text = "",
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = BUTTON_HINT,
                leadingAction = backAction(),
                secondaryAction = filterAction()
            )
        }
    }

    @Test
    fun warp_search_bar_button_with_leading_action_and_clear() {
        snapshot {
            WarpSearchBarButton(
                text = QUERY,
                clearContentDescription = CLEAR_DESCRIPTION,
                hint = BUTTON_HINT,
                leadingAction = backAction()
            )
        }
    }

    private fun snapshot(content: @Composable () -> Unit) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(WarpTheme.colors.background.default)
                        .padding(WarpTheme.dimensions.space2)
                ) {
                    content()
                }
            }
        }
    }

    @Composable
    private fun filterAction() = WarpSearchBarAction(
        icon = WarpIconResources.filter,
        contentDescription = "Filter",
        onClick = {}
    )

    @Composable
    private fun backAction() = WarpSearchBarAction(
        icon = WarpIconResources.arrowLeft,
        contentDescription = "Back",
        onClick = {}
    )

    private companion object {
        const val CLEAR_DESCRIPTION = "Clear"
        const val SEARCH_HINT = "Search here"
        const val BUTTON_HINT = "Search in Recommerce"
        const val QUERY = "iPhone 15 Pro"
        const val SECOND_QUERY = "Samsung Galaxy S24"
    }
}
