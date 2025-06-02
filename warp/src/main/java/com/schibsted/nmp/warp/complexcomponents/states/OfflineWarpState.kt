package com.schibsted.nmp.warp.complexcomponents.states

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.ImageSize
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpDimensions.maximumStateWidth
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun OfflineWarpState(
    modifier: Modifier = Modifier,
    painter: Painter? = painterResource(R.drawable.wifi),
    imageSize: ImageSize = ImageSize.ICON,
    tintColor: Color? = WarpTheme.colors.icon.primary,
    imageContentDescription: String? = stringResource(R.string.wifi),
    title: String? = stringResource(R.string.offline_title),
    description: String? = stringResource(R.string.offline_message),
    primaryButtonText: String? = stringResource(R.string.try_again),
    onPrimaryButtonClicked: () -> Unit = {},
    secondaryButtonText: String? = null,
    onSecondaryButtonClicked: () -> Unit = {}
) {
    WarpState(
        modifier = modifier
            .padding(WarpDimensions.space4)
            .width(maximumStateWidth)
            .fillMaxSize(),
        painter = painter,
        imageSize = imageSize,
        tintColor = tintColor,
        imageContentDescription = imageContentDescription,
        title = title,
        description = description,
        primaryButtonText = primaryButtonText,
        onPrimaryButtonClicked = onPrimaryButtonClicked,
        secondaryButtonText = secondaryButtonText,
        onSecondaryButtonClicked = onSecondaryButtonClicked,
    )
}

@Suppress("ThrowingExceptionsWithoutMessageOrCause")
@Preview(showBackground = true)
@Composable
fun OfflineWarpStatePreview() {
    OfflineWarpState()
}
