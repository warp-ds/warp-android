package com.schibsted.nmp.warp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions


@Composable
fun WarpState(
    modifier: Modifier = Modifier,
    type: WarpStateType? = null,
    painter: Painter? = null,
    icon: WarpIconResource? = null,
    tintColor: Color? = null,
    imageSize: Dp? = null,
    imageContentDescription: String? = null,
    title: String? = null,
    description: String? = null,
    primaryButtonText: String? = null,
    onPrimaryButtonClicked: () -> Unit = {},
    quietButtonText: String? = null,
    onQuietButtonClicked: () -> Unit = {},
    showLogo: Boolean = false
) {

    val stateStyle = when (type) {
        WarpStateType.NoSearchResults -> getNoSearchResultsStyle()
        WarpStateType.LoadFailed -> getLoadFailedStyle()
        WarpStateType.Offline -> getOfflineStyle()
        WarpStateType.Login -> getLoginStyle()
        WarpStateType.Verify -> getVerifyStyle()
        WarpStateType.Loading -> getLoadingStyle()
        else -> null
    }

    WarpStateView(
        modifier = modifier,
        icon = icon ?: stateStyle?.icon,
        painter,
        imageContentDescription,
        imageSize = imageSize,
        tintColor,
        title = title ?: stateStyle?.title,
        description = description ?: stateStyle?.description,
        primaryButtonText = primaryButtonText ?: stateStyle?.primaryButtonText,
        quietButtonText = quietButtonText ?: stateStyle?.quietButtonText,
        onPrimaryButtonClicked = onPrimaryButtonClicked,
        onQuietButtonClicked = onQuietButtonClicked,
        showLogo = type == WarpStateType.Login || showLogo,
        showLoading = type == WarpStateType.Loading
    )
}

data class WarpStateStyle(
    val icon: WarpIconResource? = null,
    val title: String? = null,
    val description: String? = null,
    val primaryButtonText: String? = null,
    val quietButtonText: String? = null
)

@Composable
fun getNoSearchResultsStyle() = WarpStateStyle(
    icon = icons.search,
    title = stringResource(R.string.no_search_results_title),
    description = stringResource(R.string.no_search_results_description),
    primaryButtonText = stringResource(R.string.no_search_results_primary_button_text),
    quietButtonText = stringResource(R.string.no_search_results_quiet_button_text),
)

@Composable
fun getLoadFailedStyle() = WarpStateStyle(
    icon = icons.smileyNeutral,
    title = stringResource(R.string.failed_to_load_title),
    description = stringResource(R.string.failed_to_load_description),
    primaryButtonText = stringResource(R.string.failed_to_load_primary_button_text)
)

@Composable
fun getOfflineStyle() = WarpStateStyle(
    icon = icons.wifi,
    title = stringResource(R.string.offline_title),
    description = stringResource(R.string.offline_description),
    primaryButtonText = stringResource(R.string.offline_primary_button_text)
)

@Composable
fun getLoginStyle() = WarpStateStyle(
    icon = icons.user,
    title = stringResource(R.string.login_title),
    description = stringResource(R.string.login_description),
    primaryButtonText = stringResource(R.string.login_primary_button_text),
    quietButtonText = stringResource(R.string.login_quiet_button_text)
)

@Composable
fun getVerifyStyle() = WarpStateStyle(
    icon = icons.verification,
    title = stringResource(R.string.verify_title),
    description = stringResource(R.string.verify_description),
    primaryButtonText = stringResource(R.string.verify_primary_button_text),
)

@Composable
fun getLoadingStyle() = WarpStateStyle(
    description = stringResource(R.string.loading_description)
)

@Composable
private fun WarpStateView(
    modifier: Modifier,
    icon: WarpIconResource?,
    painter: Painter?,
    imageContentDescription: String?,
    imageSize: Dp?,
    tintColor: Color?,
    title: String?,
    description: String?,
    primaryButtonText: String?,
    quietButtonText: String?,
    onPrimaryButtonClicked: () -> Unit,
    onQuietButtonClicked: () -> Unit,
    showLogo: Boolean,
    showLoading: Boolean
) {
    Column(
        modifier = modifier.padding(dimensions.space3),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        painter?.let {
            Image(
                painter = it,
                contentDescription = imageContentDescription,
                modifier = Modifier.size(imageSize ?: dimensions.illustration)
            )
            Spacer(modifier = Modifier.size(dimensions.space3))

        } ?: icon?.let {
            WarpIcon(
                icon = it,
                size = imageSize ?: dimensions.icon.xlarge,
                color = tintColor ?: colors.icon.primary
            )
            Spacer(modifier = Modifier.size(dimensions.space3))
        }
        title?.let {
            WarpText(
                modifier = Modifier.padding(bottom = dimensions.space05),
                text = it,
                textAlign = TextAlign.Center,
                style = WarpTextStyle.Title3
            )
        }
        if (showLoading) {
            WarpSpinner()
            Spacer(modifier = Modifier.size(dimensions.space3))
        }
        description?.let {
            WarpText(
                modifier = Modifier.padding(bottom = dimensions.space3),
                text = it,
                textAlign = TextAlign.Center,
                style = WarpTextStyle.Body
            )
        }
        if (primaryButtonText != null || quietButtonText != null) {
            primaryButtonText?.let {
                WarpButton(
                    text = it,
                    onClick = onPrimaryButtonClicked
                )
            }
            Spacer(Modifier.size(dimensions.space1))
            quietButtonText?.let {
                WarpButton(
                    text = it,
                    onClick = onQuietButtonClicked,
                    style = WarpButtonStyle.Quiet
                )
            }
        }
        if (showLogo) {
            Spacer(Modifier.size(dimensions.space3))
            Image(
                painter = painterResource(R.drawable.warp_partofvend),
                contentDescription = stringResource(R.string.vend),
            )
        }
    }
}

enum class WarpStateType {
    NoSearchResults,
    LoadFailed,
    Loading,
    Login,
    Offline,
    Verify
}

@Preview(showBackground = true)
@Composable
fun StateViewPreview() {
    WarpState(
        modifier = Modifier.fillMaxSize(),
        icon = icons.sparkles,
        title = "No data",
        description = "No data available",
        primaryButtonText = "Retry",
        onPrimaryButtonClicked = {},
        quietButtonText = "Get something random",
        onQuietButtonClicked = {},
    )
}