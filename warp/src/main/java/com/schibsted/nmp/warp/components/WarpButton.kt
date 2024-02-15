package com.schibsted.nmp.warp.components

import androidx.annotation.DrawableRes
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalDimensions
import com.schibsted.nmp.warp.theme.LocalShapes
import com.schibsted.nmp.warp.theme.LocalTypography
import com.schibsted.nmp.warp.theme.Transparent
import com.schibsted.nmp.warp.theme.WarpRippleTheme
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes
import com.schibsted.nmp.warp.theme.WarpTheme.typography

/**
 * A Button in the warp design system. The button supports a variety of styles and loading and disabled states.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/buttons/)
 * @param text The text to be displayed on the button
 * @param onClick lambda to be invoked when clicked
 * @param enabled set to false to disable the button. Default value is true
 * @param modifier Modifier for the button. Default value is Modifier
 * @param buttonStyle Controls the appearance of the button. Default value is WarpButtonStyle.Primary. Other options are WarpButtonStyle.Secondary, WarpButtonStyle.Quiet, WarpButtonStyle.Negative, WarpButtonStyle.NegativeQuiet, WarpButtonStyle.Utility, WarpButtonStyle.UtilityQuiet, WarpButtonStyle.UtilityOverlay
 * @param maxLines limits the lines of the text on the button. Default value is 1
 * @param loading set to true to enable the loading state. Default value is false
 * @param leadingIcon The drawable resource id for the icon to be displayed on the button in front of the text. Default value is null
 * @param leadingIconContentDescr The content description for the icon. Please provide this for accessibility purposes if icon is to be displayed. Default value is null
 * @param trailingIcon The drawable resource id for the icon to be displayed on the button after the text. Default value is null
 * @param trailingIconContentDescr The content description for the icon. Please provide this for accessibility purposes if icon is to be displayed. Default value is null
 */
@Composable
fun WarpButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonStyle: WarpButtonStyle = WarpButtonStyle.Primary,
    maxLines: Int = 1,
    loading: Boolean = false,
    @DrawableRes leadingIcon: Int? = null,
    leadingIconContentDescr: String? = null,
    @DrawableRes trailingIcon: Int? = null,
    trailingIconContentDescr: String? = null
) {
    WarpButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        buttonStyle = buttonStyle,
        loading = loading
    ) {
        leadingIcon?.let {
            Icon(
                modifier = Modifier
                    .padding(end = dimensions.space1),
                imageVector = ImageVector.vectorResource(leadingIcon),
                contentDescription = leadingIconContentDescr
            )
        }
        Text(
            text = text,
            modifier = Modifier
                .padding(vertical = dimensions.space1)
                .semantics(properties = { contentDescription = text }),
            overflow = TextOverflow.Ellipsis,
            maxLines = maxLines,
            style = typography.title4,
        )
        trailingIcon?.let {
            Icon(
                modifier = Modifier
                    .padding(start = dimensions.space1),
                imageVector = ImageVector.vectorResource(trailingIcon),
                contentDescription = trailingIconContentDescr
            )
        }
    }
}

/**
 * A Button in the warp design system. The button supports a variety of styles and loading and disabled states.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/buttons/)
 * @param onClick lambda to be invoked when clicked
 * @param enabled set to false to disable the button. default value is true
 * @param modifier Modifier for the button. Default value is Modifier
 * @param buttonStyle Controls the appearance of the button. Default value is WarpButtonStyle.Primary. Other options are WarpButtonStyle.Secondary, WarpButtonStyle.Quiet, WarpButtonStyle.Negative, WarpButtonStyle.NegativeQuiet, WarpButtonStyle.Utility, WarpButtonStyle.UtilityQuiet, WarpButtonStyle.UtilityOverlay
 * @param loading set to true to enable the loading state
 * @param content The content to display inside the button
 */
@Composable
fun WarpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonStyle: WarpButtonStyle = WarpButtonStyle.Primary,
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
            WarpButtonStyle.Primary -> buttonStylePrimary()
            WarpButtonStyle.Secondary -> buttonStyleSecondary()
            WarpButtonStyle.Quiet -> buttonStyleQuiet()
            WarpButtonStyle.Negative -> buttonStyleNegative()
            WarpButtonStyle.NegativeQuiet -> buttonStyleNegativeQuiet()
            WarpButtonStyle.Utility -> buttonStyleUtility()
            WarpButtonStyle.UtilityOverlay -> buttonStyleUtilityOverlay()
            WarpButtonStyle.UtilityQuiet -> buttonStyleUtilityQuiet()
        }

        val buttonColors = ButtonDefaults.buttonColors(
            containerColor = warpButtonColors.background.default,
            contentColor = warpButtonColors.text,

            disabledContainerColor = colors.background.disabled,
            disabledContentColor = colors.text.inverted
        )

        val loadingColors = ButtonDefaults.buttonColors(
            containerColor = Transparent,
            contentColor = colors.text.default,
            disabledContainerColor = colors.background.disabled,
            disabledContentColor = colors.text.inverted
        )

        val borderStroke = warpButtonColors.border?.let {
            BorderStroke(
                dimensions.space025,
                it.default
            )
        }

        val elevation =
            if (!loading && buttonStyle == WarpButtonStyle.UtilityOverlay) dimensions.shadowSmall else 0.dp

        val buttonModifier = if (loading) modifier.loadingAnimation() else modifier
        val colors = if (loading) loadingColors else buttonColors
        //disable click when button is in loading state
        val clickAction = if (loading) {
            {}
        } else onClick

        CompositionLocalProvider(
            LocalRippleTheme provides WarpRippleTheme(warpButtonColors.background.active)
        ) {
            Button(
                modifier = buttonModifier,
                onClick = clickAction,
                enabled = enabled,
                shape = shapes.medium,
                colors = colors,
                border = borderStroke,
                content = content,
                contentPadding = PaddingValues(
                    horizontal = dimensions.space2,
                    vertical = dimensions.space1
                ),
                elevation = ButtonDefaults.buttonElevation(elevation)
            )
        }
    }
}

enum class WarpButtonStyle {
    Primary,
    Secondary,
    Quiet,
    Negative,
    NegativeQuiet,
    Utility,
    UtilityQuiet,
    UtilityOverlay,
}

data class WarpButtonStyleColors(
    val text: Color,
    val background: WarpButtonElementColors,
    val border: WarpButtonElementColors?,
)

data class WarpButtonElementColors(
    val default: Color,
    val active: Color
)

@Composable
internal fun buttonStylePrimary() = WarpButtonStyleColors(
    text = colors.text.inverted,
    background = WarpButtonElementColors(
        default = colors.components.button.primary.first,
        active = colors.components.button.primary.second
    ),
    border = null,
)

@Composable
internal fun buttonStyleSecondary() = WarpButtonStyleColors(
    text = colors.text.link,
    background = WarpButtonElementColors(
        default = colors.background.default,
        active = colors.background.active
    ),
    border = WarpButtonElementColors(
        default = colors.border.default,
        active = colors.border.active
    )
)

@Composable
internal fun buttonStyleQuiet() = WarpButtonStyleColors(
    text = colors.text.link,
    background = WarpButtonElementColors(
        default = Transparent,
        active = colors.background.active
    ),
    border = null
)

@Composable
internal fun buttonStyleNegative() = WarpButtonStyleColors(
    text = colors.text.inverted,
    background = WarpButtonElementColors(
        default = colors.background.negative,
        active = colors.background.negativeActive
    ),
    border = null
)

@Composable
internal fun buttonStyleNegativeQuiet() = WarpButtonStyleColors(
    text = colors.text.negative,
    background = WarpButtonElementColors(
        default = Transparent,
        active = colors.background.negativeSubtleActive
    ),
    border = null
)

@Composable
internal fun buttonStyleUtility() = WarpButtonStyleColors(
    text = colors.text.default,
    background = WarpButtonElementColors(
        default = colors.background.default,
        active = colors.background.active
    ),
    border = WarpButtonElementColors(
        default = colors.border.default,
        active = colors.border.active
    )
)

@Composable
internal fun buttonStyleUtilityOverlay() = WarpButtonStyleColors(
    text = colors.text.default,
    background = WarpButtonElementColors(
        default = colors.background.default,
        active = colors.background.active
    ),
    border = null
)

@Composable
internal fun buttonStyleUtilityQuiet() = WarpButtonStyleColors(
    text = colors.text.default,
    background = WarpButtonElementColors(
        default = Transparent,
        active = colors.background.subtleActive
    ),
    border = null
)

@Composable
internal fun buttonStyleLoading() = WarpButtonStyleColors(
    text = colors.text.default,
    background = WarpButtonElementColors(
        default = colors.components.button.loading.first,
        active = colors.components.button.loading.second
    ),
    border = null
)

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

    val loadingColors = buttonStyleLoading()
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
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensions.space1)
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
            buttonStyle = WarpButtonStyle.Quiet
        )
        WarpButton(
            text = "This duck cannot be sold!",
            onClick = {},
            buttonStyle = WarpButtonStyle.Negative
        )
        WarpButton(
            text = "This duck was already sold!",
            onClick = {},
            buttonStyle = WarpButtonStyle.NegativeQuiet
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
        WarpButton(
            onClick = { },
            buttonStyle = WarpButtonStyle.Primary,
            text = "With icon",
            leadingIcon = R.drawable.ic_logo_finn,
            leadingIconContentDescr = "Clear icon",
        )
    }
}
