package com.schibsted.nmp.warp.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalDimensions
import com.schibsted.nmp.warp.theme.LocalShapes
import com.schibsted.nmp.warp.theme.LocalTypography
import com.schibsted.nmp.warp.theme.Transparent
import com.schibsted.nmp.warp.theme.WarpBrandedTheme
import com.schibsted.nmp.warp.theme.WarpButtonStyleColors
import com.schibsted.nmp.warp.theme.WarpRippleTheme
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes
import com.schibsted.nmp.warp.theme.WarpTheme.typography
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider

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
        val warpButtonColors: WarpButtonStyleColors = when (buttonStyle) {
            WarpButtonStyle.Primary -> colors.components.button.primary
            WarpButtonStyle.Secondary -> colors.components.button.secondary
            WarpButtonStyle.Tertiary -> colors.components.button.quiet
            WarpButtonStyle.Critical -> colors.components.button.negative
            WarpButtonStyle.CriticalQuiet -> colors.components.button.negativeQuiet
            WarpButtonStyle.Utility -> colors.components.button.utility
            WarpButtonStyle.UtilityOverlay -> colors.components.button.utilityOverlay
            WarpButtonStyle.UtilityQuiet -> colors.components.button.utilityQuiet
        }

        val buttonColors = ButtonDefaults.buttonColors(
            containerColor = warpButtonColors.background.default,
            contentColor = warpButtonColors.text,
            disabledContainerColor = colors.components.button.disabled.background.default,
            disabledContentColor = colors.components.button.disabled.text
        )

        val loadingColors = ButtonDefaults.buttonColors(
            containerColor = Transparent,
            contentColor = colors.components.button.loading.text,
            disabledContainerColor = colors.components.button.disabled.background.default,
            disabledContentColor = colors.components.button.disabled.text
        )

        val borderStroke = warpButtonColors.border?.let {
             BorderStroke(
                dimensions.space025.dp,
                it.default
            )
        }

        val elevation = if (!loading && buttonStyle == WarpButtonStyle.UtilityOverlay) dimensions.shadowSmall.dp else 0.dp

        val buttonModifier = if (loading) modifier.loadingAnimation() else modifier
        val colors = if (loading) loadingColors else buttonColors

        CompositionLocalProvider(
            LocalRippleTheme provides WarpRippleTheme(warpButtonColors.background.active)
        ) {
            Button(
                modifier = buttonModifier,
                onClick = onClick,
                enabled = enabled,
                shape = shapes.medium,
                colors = colors,
                border = borderStroke,
                content = content,
                contentPadding = PaddingValues(horizontal = dimensions.space2.dp, vertical = dimensions.space1.dp),
                elevation = ButtonDefaults.buttonElevation(elevation)
            )
        }
    }
}

enum class WarpButtonStyle {
    Primary,
    Secondary,
    Tertiary,
    Critical,
    CriticalQuiet,
    Utility,
    UtilityQuiet,
    UtilityOverlay,
}

fun Modifier.loadingAnimation(): Modifier = composed {
    val transition = rememberInfiniteTransition()
    val offset by transition.animateFloat(
        label = "LoadingAnimation",
        initialValue = 0f,
        targetValue = 69f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing)
        )
    )

    val loadingColors = colors.components.button.loading
    this
        .clip(shapes.medium)
        .background(loadingColors.background.default, shapes.medium)
        .drawWithContent {
            clipRect {
                val a = 8.5f
                val steps = IntProgression.fromClosedRange(
                    -size.width.toInt(),
                    size.width.toInt(),
                    (a * 8).toInt()
                )
                for (x in steps) {
                    drawLine(
                        loadingColors.background.active,
                        Offset(x + a + offset, size.height + a),
                        Offset(x + size.height + a + offset, -a),
                        strokeWidth = 3 * a
                    )
                }
            }
            this.drawContent()
        }
}

@Composable
@Preview
fun WarpButtonPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    WarpBrandedTheme(
        flavor = flavor,
        darkTheme = false
    )
    {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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
            WarpButton(
                text = "Loading",
                loading = true,
                onClick = {},
                buttonStyle = WarpButtonStyle.UtilityQuiet
            )
        }
    }
}
