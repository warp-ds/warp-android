package com.schibsted.nmp.warp.complexcomponents.states

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun LoginWarpState(
    modifier: Modifier = Modifier,
    painter: Painter? = painterResource(R.drawable.circleuser),
    tintColor: Color? = WarpTheme.colors.icon.primary,
    imageSize: ImageSize = ImageSize.ICON,
    imageContentDescription: String? = stringResource(R.string.circleuser),
    title: String? = stringResource(R.string.login_title_default),
    description: String? = stringResource(R.string.login_message_default),
    primaryButtonText: String? = stringResource(R.string.login),
    onPrimaryButtonClicked: () -> Unit = {},
    secondaryButtonText: String? = stringResource(
        R.string.login_button_open_account
    ),
    onSecondaryButtonClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .padding(WarpDimensions.space4)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WarpState(
            modifier = Modifier
                .width(maximumStateWidth)
                .fillMaxWidth(),
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
        Image(
            modifier = Modifier.padding(top = WarpDimensions.space2),
            painter = painterResource(R.drawable.login_branding),
            contentDescription = stringResource(R.string.login_branding),
        )
    }
}

@Suppress("ThrowingExceptionsWithoutMessageOrCause")
@Preview(showBackground = true)
@Composable
fun LoginWarpStatePreview() {
    LoginWarpState()
}
