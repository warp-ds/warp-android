package com.schibsted.snapshot

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.schibsted.nmp.warp.components.WarpDialog
import com.schibsted.nmp.warp.components.WarpFullScreenDialog
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextField
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.utils.WarpDialogScenario
import com.schibsted.nmp.warp.utils.WarpDialogScenarios
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpDialogTest(
    @TestParameter val flavor: Flavor,
    @TestParameter val nightMode: NightMode,
    @TestParameter(valuesProvider = FontScaleProvider::class) private val fontScale: Float,
) {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5.copy(
            nightMode = nightMode,
            fontScale = fontScale,
        ),
        theme = "android:Theme.Material.Light.NoActionBar",
        renderingMode = SessionParams.RenderingMode.NORMAL,
        snapshotHandler = if (Config.isVerifying) {
            SnapshotVerifier(
                maxPercentDifference = Config.maxPercentDifference,
                rootDirectory = flavor.dir,
            )
        } else {
            HtmlReportWriter(snapshotRootDirectory = flavor.dir)
        },
    )

    @Test
    fun warp_dialog_basic() = snapshotDialog { WarpDialogScenarios.basic }

    @Test
    fun warp_dialog_with_icon() = snapshotDialog { WarpDialogScenarios.withIcon() }

    @Test
    fun warp_dialog_with_image() = snapshotDialog { WarpDialogScenarios.withImage() }

    @Test
    fun warp_dialog_single_button() = snapshotDialog { WarpDialogScenarios.singleButton }

    @Test
    fun warp_dialog_long_text() = snapshotDialog { WarpDialogScenarios.longText }

    @Test
    fun warp_full_screen_dialog() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpFullScreenDialog(
                    title = "New event",
                    onDismiss = { },
                    actionText = "Save",
                    onActionClick = { },
                ) {
                    WarpText(
                        text = "Full-screen dialogs are used for a series of tasks such as creating a calendar entry with a title, date, location, and time.",
                        style = WarpTextStyle.Body,
                        modifier = Modifier.padding(bottom = dimensions.space3),
                    )
                    WarpTextField(
                        value = "",
                        onValueChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = dimensions.space2),
                        label = "Event title",
                        placeholderText = "Team offsite",
                    )
                    WarpTextField(
                        value = "",
                        onValueChange = { },
                        modifier = Modifier.fillMaxWidth(),
                        label = "Notes",
                        placeholderText = "Add any extra details",
                    )
                }
            }
        }
    }

    private fun snapshotDialog(scenarioProvider: @Composable () -> WarpDialogScenario) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                val scenario = scenarioProvider()
                WarpDialog(
                    title = scenario.title,
                    body = scenario.body,
                    icon = scenario.icon,
                    primaryButtonText = scenario.primaryButtonText,
                    secondaryButtonText = scenario.secondaryButtonText,
                    onDismiss = { },
                    onPrimaryButtonClick = { },
                    onSecondaryButtonClick = { },
                )
            }
        }
    }
}
