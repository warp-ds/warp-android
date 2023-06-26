package com.schibsted.nmp.warp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalDimensions
import com.schibsted.nmp.warp.theme.LocalShapes
import com.schibsted.nmp.warp.theme.LocalTypography
import com.schibsted.nmp.warp.theme.WarpBrandedTheme
import com.schibsted.nmp.warp.theme.WarpButtonStyleColors
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes
import com.schibsted.nmp.warp.theme.WarpTheme.typography

@Composable
fun WarpButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonStyle: WarpButtonStyle,
    maxLines: Int = 1,
    loading: Boolean = false
) {
    WarpButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        buttonStyle = buttonStyle,
        loading = loading
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .semantics(properties = { contentDescription = text }),
            overflow = TextOverflow.Ellipsis,
            maxLines = maxLines,
            style = typography.title4,
        )
    }
}

@Composable
fun WarpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonStyle: WarpButtonStyle,
    loading: Boolean = false,
    content: @Composable RowScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalShapes provides shapes,
        LocalTypography provides typography,
        LocalDimensions provides dimensions
    ) {
        val buttonModifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .then(modifier)

        val warpButtonColors: WarpButtonStyleColors = when (buttonStyle) {
            WarpButtonStyle.Primary -> colors.button.primary
            WarpButtonStyle.Secondary -> colors.button.secondary
            WarpButtonStyle.Tertiary -> colors.button.quiet
            WarpButtonStyle.Critical -> colors.button.negative
            WarpButtonStyle.CriticalQuiet -> colors.button.negativeQuiet
            WarpButtonStyle.Utility -> colors.button.utility
            WarpButtonStyle.UtilityOverlay -> colors.button.utilityOverlay
            WarpButtonStyle.UtilityQuiet -> colors.button.utilityQuiet
        }

        val buttonColors = ButtonDefaults.buttonColors(
            containerColor = warpButtonColors.background.default,
            contentColor = warpButtonColors.text,
            disabledContainerColor = colors.button.disabled.background.default,
            disabledContentColor = colors.button.disabled.text
        )

        val borderStroke = warpButtonColors.border?.default?.let {
            BorderStroke(
                dimensions.space025.dp,
                it
            )
        }

        val elevation = if (buttonStyle == WarpButtonStyle.UtilityOverlay) dimensions.shadowSmall.dp else 0.dp

        Button(
            modifier = buttonModifier,
            onClick = onClick,
            enabled = enabled,
            shape = shapes.medium,
            colors = buttonColors,
            border = borderStroke,
            content = content,
            contentPadding = PaddingValues(horizontal = dimensions.space2.dp),
            elevation = ButtonDefaults.buttonElevation(elevation)
        )
    }
}

sealed class WarpButtonStyle {
    object Primary : WarpButtonStyle()
    object Secondary : WarpButtonStyle()
    object Tertiary : WarpButtonStyle()
    object Critical : WarpButtonStyle()
    object CriticalQuiet : WarpButtonStyle()
    object Utility : WarpButtonStyle()
    object UtilityQuiet : WarpButtonStyle()
    object UtilityOverlay : WarpButtonStyle()
}

internal class WarpButtonPreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("finn", "tori")

}

@Composable
@Preview
fun WarpButtonPreview(
    @PreviewParameter(WarpButtonPreviewParameterProvider::class) flavor: String
) {
    WarpBrandedTheme(
        flavor = flavor,
        darkTheme = false
    )
    {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            WarpButton(
                text = "Take me to NMP",
                onClick = {},
                buttonStyle = WarpButtonStyle.Primary
            )
            WarpButton(
                text = "Buy a duck",
                onClick = {},
                buttonStyle = WarpButtonStyle.Secondary
            )
            WarpButton(
                text = "Sell a duck",
                onClick = {},
                buttonStyle = WarpButtonStyle.Tertiary
            )
            WarpButton(
                text = "This duck cannot be sold!",
                onClick = {},
                buttonStyle = WarpButtonStyle.Critical
            )
            WarpButton(
                text = "This duck was already sold!",
                onClick = {},
                buttonStyle = WarpButtonStyle.CriticalQuiet
            )
            WarpButton(
                text = "Duck",
                onClick = {},
                buttonStyle = WarpButtonStyle.Utility
            )
            WarpButton(
                text = "Duck duck",
                onClick = {},
                buttonStyle = WarpButtonStyle.UtilityQuiet
            )
            WarpButton(
                text = "Duck duck duck",
                onClick = {},
                buttonStyle = WarpButtonStyle.UtilityOverlay
            )
        }
    }
}
