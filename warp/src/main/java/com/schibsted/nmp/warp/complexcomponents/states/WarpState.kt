package com.schibsted.nmp.warp.complexcomponents.states

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.ImageSize
import com.schibsted.nmp.warp.theme.WarpDimensions

@Composable
internal fun WarpState(
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    tintColor: Color? = null,
    imageSize: ImageSize = ImageSize.ICON,
    imageContentDescription: String? = null,
    title: String? = null,
    description: String? = null,
    primaryButtonText: String? = null,
    onPrimaryButtonClicked: () -> Unit = {},
    secondaryButtonText: String? = null,
    onSecondaryButtonClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        painter?.let {
            Image(
                painter = it,
                contentDescription = imageContentDescription,
                modifier = Modifier.size(imageSize.size),
                colorFilter = tintColor?.let { ColorFilter.tint(it) }
            )
            Spacer(modifier = Modifier.size(WarpDimensions.space2))
        }
        title?.let {
            WarpText(
                text = it,
                textAlign = TextAlign.Center,
                style = WarpTextStyle.Title3
            )
        }
        description?.let {
            WarpText(
                text = it,
                textAlign = TextAlign.Center,
                style = WarpTextStyle.Body
            )
        }
        if (primaryButtonText != null || secondaryButtonText != null) {
            Spacer(modifier = Modifier.size(WarpDimensions.space2))
            MatchedMaxWidthComposables(
                @Composable {
                    primaryButtonText?.let {
                        WarpButton(
                            text = it,
                            onClick = onPrimaryButtonClicked
                        )
                    }
                },
                @Composable {
                    secondaryButtonText?.let {
                        WarpButton(
                            text = it,
                            onClick = onSecondaryButtonClicked,
                            style = WarpButtonStyle.Quiet
                        )
                    }
                },
                spacing = WarpDimensions.space1
            )
        }
    }
}

@Composable
private fun MatchedMaxWidthComposables(
    vararg composables: @Composable () -> Unit,
    spacing: Dp = 0.dp,
) {
    SubcomposeLayout { constraints ->
        val measurables = composables.mapIndexed { index, composable ->
            subcompose(index) { composable() }.firstOrNull()
        }.filterNotNull()
        if (measurables.isEmpty()) {
            return@SubcomposeLayout layout(0, 0) {}
        }

        val maxIntrinsicWidth = measurables.maxOfOrNull {
            it.maxIntrinsicWidth(constraints.maxWidth)
        } ?: 0
        val childConstraints = constraints.copy(
            minWidth = maxIntrinsicWidth,
            maxWidth = maxIntrinsicWidth
        )
        val placeables = measurables.map { it.measure(childConstraints) }
        val spacingPx = spacing.toPx().toInt()
        val totalHeight =
            placeables.sumOf { it.height } + spacingPx * (placeables.size - 1).coerceAtLeast(0)

        if (placeables.isEmpty()) {
            layout(0, 0) {}
        } else {
            layout(width = maxIntrinsicWidth, height = totalHeight) {
                var y = 0
                placeables.forEachIndexed { index, placeable ->
                    placeable.placeRelative(0, y)
                    y += placeable.height
                    if (index < placeables.lastIndex) y += spacingPx
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StateViewPreview() {
    WarpState(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(R.drawable.sparkles),
        imageContentDescription = stringResource(R.string.sparkles),
        title = "No data",
        description = "No data available",
        primaryButtonText = "Retry",
        onPrimaryButtonClicked = {},
        secondaryButtonText = "Get something random",
        onSecondaryButtonClicked = {},
    )
}
