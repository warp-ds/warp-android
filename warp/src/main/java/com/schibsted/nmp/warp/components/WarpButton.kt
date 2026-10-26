package com.schibsted.nmp.warp.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.Transparent
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes

/**
 * A Button in the warp design system. The button supports a variety of styles and loading and disabled states.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/buttons/)
 * @param text The text to be displayed on the button
 * @param onClick lambda to be invoked when clicked
 * @param enabled set to false to disable the button. Default value is true
 * @param modifier Modifier for the button. Default value is Modifier
 * @param style Controls the appearance of the button. Default value is WarpButtonStyle.Primary. Other options are WarpButtonStyle.Secondary, WarpButtonStyle.Quiet, WarpButtonStyle.Negative, WarpButtonStyle.NegativeQuiet, WarpButtonStyle.Utility, WarpButtonStyle.UtilityQuiet, WarpButtonStyle.UtilityOverlay
 * @param maxLines limits the lines of the text on the button. Default value is 1
 * @param loading set to true to enable the loading state. Default value is false
 * @param leadingIcon The composable for the icon to be displayed on the button in front of the text. Default value is null
 * @param trailingIcon The composable for the icon to be displayed on the button after the text. Default value is null
 * @param small set to true to get the small version. Default value is false
 */
@Composable
fun WarpButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: WarpButtonStyle = WarpButtonStyle.Primary,
    maxLines: Int = 1,
    loading: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    small: Boolean = false
) {
    LegacyWarpLabelButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = style,
        maxLines = maxLines,
        loading = loading,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        small = small,
        interactionSource = null,
    )
}


/**
 * A Button in the warp design system. The button supports a variety of styles and loading and disabled states.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/buttons/)
 * @param text The text to be displayed on the button
 * @param onClick lambda to be invoked when clicked
 * @param enabled set to false to disable the button. Default value is true
 * @param modifier Modifier for the button. Default value is Modifier
 * @param style Controls the appearance of the button. Default value is WarpButtonStyle.Primary. Other options are WarpButtonStyle.Secondary, WarpButtonStyle.Quiet, WarpButtonStyle.Negative, WarpButtonStyle.NegativeQuiet, WarpButtonStyle.Utility, WarpButtonStyle.UtilityQuiet, WarpButtonStyle.UtilityOverlay
 * @param maxLines limits the lines of the text on the button. Default value is 1
 * @param loading set to true to enable the loading state. Default value is false
 * @param leadingIcon The drawable resource id for the icon to be displayed on the button in front of the text. Default value is null
 * @param leadingIconContentDescr The content description for the icon. Please provide this for accessibility purposes if icon is to be displayed. Default value is null
 * @param trailingIcon The drawable resource id for the icon to be displayed on the button after the text. Default value is null
 * @param trailingIconContentDescr The content description for the icon. Please provide this for accessibility purposes if icon is to be displayed. Default value is null
 * @param small set to true to get the small version. Default value is false
 * @param iconModifier Modifier for the icon. Default value is Modifier
 */
@Composable
fun WarpButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: WarpButtonStyle = WarpButtonStyle.Primary,
    maxLines: Int = 1,
    loading: Boolean = false,
    @DrawableRes leadingIcon: Int? = null,
    leadingIconContentDescr: String? = null,
    @DrawableRes trailingIcon: Int? = null,
    trailingIconContentDescr: String? = null,
    small: Boolean = false,
    iconModifier: Modifier = Modifier
) {
    LegacyWarpDrawableButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = style,
        maxLines = maxLines,
        loading = loading,
        leadingIcon = leadingIcon,
        leadingIconContentDescr = leadingIconContentDescr,
        trailingIcon = trailingIcon,
        trailingIconContentDescr = trailingIconContentDescr,
        small = small,
        iconModifier = iconModifier,
        interactionSource = null,
    )
}

/**
 * A Button in the warp design system. The button supports a variety of styles and loading and disabled states.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/buttons/)
 * @param onClick lambda to be invoked when clicked
 * @param enabled set to false to disable the button. default value is true
 * @param modifier Modifier for the button. Default value is Modifier
 * @param style Controls the appearance of the button. Default value is WarpButtonStyle.Primary. Other options are WarpButtonStyle.Secondary, WarpButtonStyle.Quiet, WarpButtonStyle.Negative, WarpButtonStyle.NegativeQuiet, WarpButtonStyle.Utility, WarpButtonStyle.UtilityQuiet, WarpButtonStyle.UtilityOverlay
 * @param loading set to true to enable the loading state
 * @param small set to true to get the small version. Default value is false
 * @param content The content to display inside the button
 */
@Composable
fun WarpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: WarpButtonStyle = WarpButtonStyle.Primary,
    loading: Boolean = false,
    small: Boolean = false,
    content: @Composable RowScope.() -> Unit
) {
    LegacyWarpButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = style,
        loading = loading,
        small = small,
        interactionSource = null,
        content = content,
    )
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
        default = colors.components.button.primaryBackground,
        active = colors.components.button.primaryBackgroundActive
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
        active = colors.background.active
    ),
    border = null
)

@Composable
internal fun buttonStyleLoading() = WarpButtonStyleColors(
    text = colors.text.default,
    background = WarpButtonElementColors(
        default = if (isSystemInDarkTheme()) Color(0xFF47474f) else Color(0xfff6f6f6), //light - Gray50 dark- Gray700
        active = if (isSystemInDarkTheme()) Color(0xFF1b1b1f) else Color(0XFFDEDEE3) //light - Gray200 dark - Gray900
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
        .clip(shapes.components.button)
        .background(loadingColors.background.default, shapes.components.button)
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
            style = WarpButtonStyle.Primary
        )
        WarpButton(
            text = "Buy a duck",
            onClick = {},
            style = WarpButtonStyle.Secondary
        )
        WarpButton(
            text = "Sell a duck",
            onClick = {},
            style = WarpButtonStyle.Quiet
        )
        WarpButton(
            text = "This duck cannot be sold!",
            onClick = {},
            style = WarpButtonStyle.Negative
        )
        WarpButton(
            text = "This duck was already sold!",
            onClick = {},
            style = WarpButtonStyle.NegativeQuiet
        )
        WarpButton(
            text = "Duck",
            onClick = {},
            style = WarpButtonStyle.Utility
        )
        WarpButton(
            text = "Duck duck",
            onClick = {},
            style = WarpButtonStyle.UtilityQuiet
        )
        WarpButton(
            text = "Duck duck duck",
            onClick = {},
            style = WarpButtonStyle.UtilityOverlay
        )
        WarpButton(
            text = "Loading",
            loading = true,
            onClick = {},
            style = WarpButtonStyle.UtilityQuiet
        )
        WarpButton(
            onClick = { },
            style = WarpButtonStyle.Primary,
            text = "With icon",
            leadingIcon = R.drawable.warp_activeads,
            leadingIconContentDescr = "Clear icon",
        )
        WarpButton(
            onClick = { },
            text = "Warp icon",
            leadingIcon = { WarpIcon(icon = icons.stroller) },
        )
    }
}
