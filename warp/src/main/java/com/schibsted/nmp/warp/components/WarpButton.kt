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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.utils.InternalWarpApi
import com.schibsted.nmp.warp.theme.Transparent
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes

/**
 * A Button in the warp design system, with styles, sizes, and loading and disabled states.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/buttons/)
 *
 * Slot icons are drawn as given: pass `color = LocalContentColor.current` to [WarpIcon] so they
 * follow the button's content colour (e.g. on [WarpButtonStyle.Primary] or when disabled).
 *
 * The slot content ([leadingIcon], [trailingIcon]) is measured with intrinsic sizes so the button
 * can size itself to its content. It must therefore support intrinsic measurement: do not use
 * `SubcomposeLayout`-based layouts such as `BoxWithConstraints` or lazy layouts.
 *
 * @param text The text to be displayed on the button
 * @param onClick lambda to be invoked when clicked
 * @param modifier Modifier for the button
 * @param enabled set to false to disable the button
 * @param style Appearance of the button. Defaults to [WarpButtonStyle.Primary]
 * @param maxLines limits the lines of the text on the button
 * @param loading shows the loading state; clicks are ignored while loading
 * @param leadingIcon composable shown before the text
 * @param trailingIcon composable shown after the text
 * @param size Size of the button. Defaults to [WarpButtonSize.Medium]
 * @param interactionSource Optional source for hover/focus/press interactions
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
    size: WarpButtonSize = WarpButtonSize.Medium,
    interactionSource: MutableInteractionSource? = null,
) {
    if (style.isLegacy) {
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
            small = size != WarpButtonSize.Medium,
            interactionSource = interactionSource,
        )
    } else {
        WarpButtonSurface(
            onClick = onClick,
            modifier = modifier,
            style = style,
            size = size,
            enabled = enabled,
            loading = loading,
            iconOnly = false,
            interactionSource = interactionSource,
            forcedState = null,
        ) {
            leadingIcon?.invoke()
            WarpButtonLabel(text, maxLines)
            trailingIcon?.invoke()
        }
    }
}

/**
 * A Button in the warp design system with drawable-resource icons, tinted with the button's
 * content colour. See the slot-icon overload for the other parameters.
 *
 * @param leadingIconContentDescr content description of the leading icon, or null if decorative
 * @param trailingIconContentDescr content description of the trailing icon, or null if decorative
 * @param iconModifier Modifier for both icons
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
    size: WarpButtonSize = WarpButtonSize.Medium,
    iconModifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource? = null,
) {
    if (style.isLegacy) {
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
            small = size != WarpButtonSize.Medium,
            iconModifier = iconModifier,
            interactionSource = interactionSource,
        )
    } else {
        WarpButtonSurface(
            onClick = onClick,
            modifier = modifier,
            style = style,
            size = size,
            enabled = enabled,
            loading = loading,
            iconOnly = false,
            interactionSource = interactionSource,
            forcedState = null,
        ) {
            WarpButtonDrawableContent(
                text = text,
                maxLines = maxLines,
                leadingIcon = leadingIcon,
                leadingIconContentDescr = leadingIconContentDescr,
                trailingIcon = trailingIcon,
                trailingIconContentDescr = trailingIconContentDescr,
                iconSize = size.metrics.iconSize,
                iconModifier = iconModifier,
            )
        }
    }
}

/**
 * A Button in the warp design system with custom row content. New styles provide the content
 * colour and the size's text style to [content].
 *
 * The content is measured with intrinsic sizes so the button can size itself to its content. It
 * must therefore support intrinsic measurement: do not use `SubcomposeLayout`-based layouts such
 * as `BoxWithConstraints` or lazy layouts.
 *
 * @param onClick lambda to be invoked when clicked
 * @param modifier Modifier for the button. Default value is Modifier
 * @param enabled set to false to disable the button. Default value is true
 * @param style Appearance of the button. Defaults to [WarpButtonStyle.Primary]
 * @param loading shows the loading state; clicks are ignored while loading. Default value is false
 * @param size Size of the button. Defaults to [WarpButtonSize.Medium]
 * @param interactionSource Optional source for hover/focus/press interactions
 * @param content The content to display inside the button
 */
@Composable
fun WarpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: WarpButtonStyle = WarpButtonStyle.Primary,
    loading: Boolean = false,
    size: WarpButtonSize = WarpButtonSize.Medium,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit
) {
    if (style.isLegacy) {
        LegacyWarpButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            style = style,
            loading = loading,
            small = size != WarpButtonSize.Medium,
            interactionSource = interactionSource,
            content = content,
        )
    } else {
        WarpButtonSurface(
            onClick = onClick,
            modifier = modifier,
            style = style,
            size = size,
            enabled = enabled,
            loading = loading,
            iconOnly = false,
            interactionSource = interactionSource,
            forcedState = null,
            content = content,
        )
    }
}

/**
 * [WarpButton] rendered in a fixed [forcedState], for snapshots/previews. Previous styles render
 * as their new equivalent.
 */
@InternalWarpApi
@Composable
fun WarpButton(
    text: String,
    onClick: () -> Unit,
    forcedState: WarpButtonState,
    modifier: Modifier = Modifier,
    style: WarpButtonStyle = WarpButtonStyle.Primary,
    size: WarpButtonSize = WarpButtonSize.Medium,
    loading: Boolean = false,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
) {
    WarpButtonSurface(
        onClick = onClick,
        modifier = modifier,
        style = style.modernEquivalent(),
        size = size,
        enabled = forcedState != WarpButtonState.Disabled,
        loading = loading,
        iconOnly = false,
        interactionSource = null,
        forcedState = forcedState,
    ) {
        WarpButtonDrawableContent(
            text = text,
            maxLines = 1,
            leadingIcon = leadingIcon,
            leadingIconContentDescr = null,
            trailingIcon = trailingIcon,
            trailingIconContentDescr = null,
            iconSize = size.metrics.iconSize,
            iconModifier = Modifier,
        )
    }
}

@Deprecated(
    message = "Use the overload with size: WarpButtonSize",
    replaceWith = ReplaceWith(
        "WarpButton(text = text, onClick = onClick, modifier = modifier, enabled = enabled, style = style, maxLines = maxLines, loading = loading, leadingIcon = leadingIcon, trailingIcon = trailingIcon, size = if (small) WarpButtonSize.Small else WarpButtonSize.Medium)",
        "com.schibsted.nmp.warp.components.WarpButtonSize"
    )
)
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
    small: Boolean,
) {
    WarpButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = style,
        maxLines = maxLines,
        loading = loading,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        size = if (small) WarpButtonSize.Small else WarpButtonSize.Medium,
    )
}

@Deprecated(
    message = "Use the overload with size: WarpButtonSize",
    replaceWith = ReplaceWith(
        "WarpButton(text = text, onClick = onClick, modifier = modifier, enabled = enabled, style = style, maxLines = maxLines, loading = loading, leadingIcon = leadingIcon, leadingIconContentDescr = leadingIconContentDescr, trailingIcon = trailingIcon, trailingIconContentDescr = trailingIconContentDescr, size = if (small) WarpButtonSize.Small else WarpButtonSize.Medium, iconModifier = iconModifier)",
        "com.schibsted.nmp.warp.components.WarpButtonSize"
    )
)
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
    small: Boolean,
    iconModifier: Modifier = Modifier,
) {
    WarpButton(
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
        size = if (small) WarpButtonSize.Small else WarpButtonSize.Medium,
        iconModifier = iconModifier,
    )
}

@Deprecated(
    message = "Use the overload with size: WarpButtonSize",
    replaceWith = ReplaceWith(
        "WarpButton(onClick = onClick, modifier = modifier, enabled = enabled, style = style, loading = loading, size = if (small) WarpButtonSize.Small else WarpButtonSize.Medium, content = content)",
        "com.schibsted.nmp.warp.components.WarpButtonSize"
    )
)
@Composable
fun WarpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: WarpButtonStyle = WarpButtonStyle.Primary,
    loading: Boolean = false,
    small: Boolean,
    content: @Composable RowScope.() -> Unit
) {
    WarpButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = style,
        loading = loading,
        size = if (small) WarpButtonSize.Small else WarpButtonSize.Medium,
        content = content,
    )
}

/**
 * Visual style of a [WarpButton] / [WarpIconButton], named after the `Type` variants of the Warp
 * Buttons design. The last three are the previous utility styles, kept with their previous look.
 */
enum class WarpButtonStyle {
    Primary,
    Secondary,
    Quiet,
    Overlay,
    OverlayElevated,
    OverlayInvertedStatic,
    SecondaryInvertedStatic,
    Negative,
    NegativeQuiet,
    @Deprecated("Use Overlay", ReplaceWith("WarpButtonStyle.Overlay"))
    Utility,
    @Deprecated("Use Quiet", ReplaceWith("WarpButtonStyle.Quiet"))
    UtilityQuiet,
    @Deprecated("Use OverlayElevated", ReplaceWith("WarpButtonStyle.OverlayElevated"))
    UtilityOverlay,
}

/**
 * Size of a [WarpButton] / [WarpIconButton]: 32dp, 40dp or 48dp high. Smaller sizes keep a 48dp
 * touch target.
 */
enum class WarpButtonSize { ExtraSmall, Small, Medium }

/**
 * Visual states of a [WarpButton] / [WarpIconButton]. Only used to force a state in snapshots/previews.
 */
enum class WarpButtonState { Default, Hover, Active, Focus, Disabled }

// The previous utility styles, which keep rendering through LegacyWarpButton.
@Suppress("DEPRECATION")
internal val WarpButtonStyle.isLegacy: Boolean
    get() = when (this) {
        WarpButtonStyle.Utility,
        WarpButtonStyle.UtilityQuiet,
        WarpButtonStyle.UtilityOverlay -> true
        else -> false
    }

// Closest new style for a previous one; keep in sync with the @Deprecated ReplaceWith targets.
@Suppress("DEPRECATION")
internal fun WarpButtonStyle.modernEquivalent(): WarpButtonStyle = when (this) {
    WarpButtonStyle.Utility -> WarpButtonStyle.Overlay
    WarpButtonStyle.UtilityQuiet -> WarpButtonStyle.Quiet
    WarpButtonStyle.UtilityOverlay -> WarpButtonStyle.OverlayElevated
    else -> this
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

fun Modifier.loadingAnimation(): Modifier = composed { loadingAnimation(shapes.components.button) }

/**
 * Loading stripes clipped to [shape]; used by buttons while `loading = true`.
 */
fun Modifier.loadingAnimation(shape: Shape): Modifier = composed {
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
        .clip(shape)
        .background(loadingColors.background.default, shape)
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
        WarpButton(text = "Take me to NMP", onClick = {}, style = WarpButtonStyle.Primary)
        WarpButton(text = "Buy a duck", onClick = {}, style = WarpButtonStyle.Secondary)
        WarpButton(text = "Sell a duck", onClick = {}, style = WarpButtonStyle.Quiet)
        WarpButton(text = "Duck", onClick = {}, style = WarpButtonStyle.Overlay)
        WarpButton(text = "Duck duck", onClick = {}, style = WarpButtonStyle.OverlayElevated)
        WarpButton(text = "This duck cannot be sold!", onClick = {}, style = WarpButtonStyle.Negative)
        WarpButton(text = "This duck was already sold!", onClick = {}, style = WarpButtonStyle.NegativeQuiet)
        WarpButton(text = "Loading", loading = true, onClick = {})
        WarpButton(text = "Small", onClick = {}, size = WarpButtonSize.Small)
        WarpButton(
            onClick = { },
            text = "With icon",
            leadingIcon = R.drawable.warp_activeads,
            leadingIconContentDescr = "Clear icon",
        )
        WarpIconButton(icon = icons.stroller, contentDescription = "Stroller", onClick = {})
    }
}
