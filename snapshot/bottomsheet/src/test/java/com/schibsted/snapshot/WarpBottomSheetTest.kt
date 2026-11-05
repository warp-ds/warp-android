package com.schibsted.snapshot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpBottomSheet
import com.schibsted.nmp.warp.components.WarpBottomSheetDefaults
import com.schibsted.nmp.warp.components.WarpBottomSheetHeader
import com.schibsted.nmp.warp.components.WarpBottomSheetHeaderIcon
import com.schibsted.nmp.warp.components.WarpBottomSheetSnapshotTesting
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.components.utils.InternalWarpApi
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(InternalWarpApi::class)
@RunWith(TestParameterInjector::class)
class WarpBottomSheetTest(
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
    fun warp_bottom_sheet_title_and_close() = snapshotSheet(
        WarpBottomSheetHeader(title = "Sort by", showCloseButton = true),
    )

    @Test
    fun warp_bottom_sheet_title_only() = snapshotSheet(WarpBottomSheetHeader(title = "Sort by"))

    @Test
    fun warp_bottom_sheet_close_only() = snapshotSheet(WarpBottomSheetHeader(showCloseButton = true))

    @Test
    fun warp_bottom_sheet_no_header() = snapshotSheet(WarpBottomSheetHeader.empty())

    @Test
    fun warp_bottom_sheet_leading_icon() = snapshotSheet {
        WarpBottomSheetHeader(
            title = "About the short version",
            showCloseButton = true,
            icon = WarpBottomSheetHeaderIcon.Icon(icons.sparkles, color = colors.icon.primary),
        )
    }

    @Test
    fun warp_bottom_sheet_leading_image() = snapshotSheet {
        WarpBottomSheetHeader(
            title = "Verify with your bank",
            showCloseButton = true,
            icon = WarpBottomSheetHeaderIcon.Image(painterResource(R.drawable.warp_finn)),
        )
    }

    @Test
    fun warp_bottom_sheet_long_title_with_icon() = snapshotSheet {
        WarpBottomSheetHeader(
            title = "What will we use the information you share with us for?",
            showCloseButton = true,
            icon = WarpBottomSheetHeaderIcon.Icon(icons.info),
        )
    }

    private fun snapshotSheet(header: WarpBottomSheetHeader) = snapshotSheet { header }

    private fun snapshotSheet(header: @Composable () -> WarpBottomSheetHeader) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpBottomSheetSnapshotTesting {
                    WarpBottomSheet(onDismissRequest = {}, header = header()) { dismiss ->
                        Column(
                            modifier = Modifier.padding(
                                horizontal = WarpBottomSheetDefaults.contentHorizontalPadding,
                                vertical = dimensions.space2,
                            ),
                        ) {
                            WarpText(
                                text = "Sheet content lines up with the header's title.",
                                style = WarpTextStyle.Body,
                            )
                            WarpButton(
                                text = "Done",
                                onClick = dismiss,
                                style = WarpButtonStyle.Primary,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = dimensions.space2),
                            )
                        }
                    }
                }
            }
        }
    }
}
