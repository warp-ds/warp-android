package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.components.WarpTextField
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpTextFieldTest(
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
        renderingMode = SessionParams.RenderingMode.FULL_EXPAND,
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
    fun warpTextField() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.surface.elevated100)
                        .padding(horizontal = dimensions.space2, vertical = dimensions.space2),
                    verticalArrangement = Arrangement.spacedBy(dimensions.space2)
                ) {
                    WarpTextField(
                        value = "With value",
                        onValueChange = { },
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "With label",
                        onValueChange = { },
                        label = "A label",
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "",
                        onValueChange = { },
                        label = "Empty",
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Label & optional",
                        onValueChange = { },
                        label = "A label",
                        optionalLabel = "Optional",
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Help text",
                        onValueChange = { },
                        helpText = "A help text",
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "",
                        onValueChange = { },
                        placeholderText = "A placeholder",
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "",
                        onValueChange = { },
                        label = "Placeholder & label",
                        placeholderText = "A placeholder",
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "",
                        onValueChange = { },
                        label = "With all",
                        optionalLabel = "Optional",
                        placeholderText = "A placeholder",
                        helpText = "A help text",
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Read only",
                        onValueChange = { },
                        readOnly = true,
                        helpText = "A meow-only text",
                        label = "Meow only",
                        optionalLabel = "Optional meow",
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Disabled",
                        onValueChange = { },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Error",
                        onValueChange = { },
                        isError = true,
                        helpText = "Error",
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Prefix",
                        onValueChange = { },
                        prefixText = "prefix",
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Suffix",
                        onValueChange = { },
                        suffixText = "suffix",
                        modifier = Modifier.fillMaxWidth()
                    )
                    val leadingIcon: @Composable () -> Unit = {
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = "Content description for the leading icon"
                        )
                    }
                    WarpTextField(
                        value = "Leading icon",
                        onValueChange = { },
                        leadingIcon = leadingIcon,
                        modifier = Modifier.fillMaxWidth()
                    )
                    val trailingIcon: @Composable () -> Unit = {
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = "Content description for the trailing icon"
                        )
                    }
                    WarpTextField(
                        value = "Trailing icon",
                        onValueChange = { },
                        trailingIcon = trailingIcon,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Custom width",
                        onValueChange = { },
                        label = "Meow",
                        optionalLabel = "Optional",
                        placeholderText = "A placeholder",
                        helpText = "A help text",
                        modifier = Modifier.width(300.dp)

                    )
                    WarpTextField(
                        value = "Display textstyle",
                        onValueChange = { },
                        textStyle = WarpTextStyle.Display,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Title1 textstyle",
                        onValueChange = { },
                        textStyle = WarpTextStyle.Title1,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Title2 textstyle",
                        onValueChange = { },
                        textStyle = WarpTextStyle.Title2,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Title3 textstyle",
                        onValueChange = { },
                        textStyle = WarpTextStyle.Title3,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Title4 textstyle",
                        onValueChange = { },
                        textStyle = WarpTextStyle.Title4,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Title5 textstyle",
                        onValueChange = { },
                        textStyle = WarpTextStyle.Title5,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Title6 textstyle",
                        onValueChange = { },
                        textStyle = WarpTextStyle.Title6,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Preamble textstyle",
                        onValueChange = { },
                        textStyle = WarpTextStyle.Preamble,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Body textstyle",
                        onValueChange = { },
                        textStyle = WarpTextStyle.Body,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "BodyStrong textstyle",
                        onValueChange = { },
                        textStyle = WarpTextStyle.BodyStrong,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Caption textstyle",
                        onValueChange = { },
                        textStyle = WarpTextStyle.Caption,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "CaptionStrong textstyle",
                        onValueChange = { },
                        textStyle = WarpTextStyle.CaptionStrong,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "Detail textstyle",
                        onValueChange = { },
                        textStyle = WarpTextStyle.Detail,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WarpTextField(
                        value = "DetailStrong textstyle",
                        onValueChange = { },
                        textStyle = WarpTextStyle.DetailStrong,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}