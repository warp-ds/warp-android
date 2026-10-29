package com.schibsted.nmp.warp.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.typography

// Heights follow the Android Button design; paddings, icon sizes and the gap follow Material 3
// (ButtonXSmallTokens / ButtonSmallTokens / ButtonDefaults.ContentPadding, *IconButtonTokens).
internal class WarpButtonMetrics(
    val height: Dp,
    val horizontalPadding: Dp,
    val iconSize: Dp,
    val iconOnlyIconSize: Dp,
)

internal val WarpButtonSize.metrics: WarpButtonMetrics
    get() = when (this) {
        WarpButtonSize.Medium -> WarpButtonMetrics(height = 48.dp, horizontalPadding = 24.dp, iconSize = 24.dp, iconOnlyIconSize = 24.dp)
        WarpButtonSize.Small -> WarpButtonMetrics(height = 40.dp, horizontalPadding = 16.dp, iconSize = 20.dp, iconOnlyIconSize = 24.dp)
        WarpButtonSize.ExtraSmall -> WarpButtonMetrics(height = 32.dp, horizontalPadding = 16.dp, iconSize = 20.dp, iconOnlyIconSize = 20.dp)
    }

private val WarpButtonSize.textStyle: TextStyle
    @Composable get() = if (this == WarpButtonSize.ExtraSmall) typography.title5 else typography.title4

private val IconGap = 8.dp
private val FocusGapWidth = 1.dp
private val FocusRingWidth = 3.dp

private class WarpButtonPalette(
    val fill: Color,
    val hoverFill: Color,
    val activeFill: Color,
    val content: Color,
    val icon: Color,
    val disabledFill: Color,
    val disabledContent: Color,
    val disabledIcon: Color,
)

// Styles with default text/icon colours that turn to the disabled text/icon colours.
@Composable
private fun neutralContentPalette(fill: Color, hoverFill: Color, activeFill: Color, disabledFill: Color) =
    WarpButtonPalette(
        fill = fill,
        hoverFill = hoverFill,
        activeFill = activeFill,
        content = colors.text.default,
        icon = colors.icon.default,
        disabledFill = disabledFill,
        disabledContent = colors.text.disabled,
        disabledIcon = colors.icon.disabled,
    )

@Composable
private fun warpButtonPalette(style: WarpButtonStyle): WarpButtonPalette = when (style) {
    WarpButtonStyle.Primary -> WarpButtonPalette(
        fill = colors.components.button.primaryBackground,
        hoverFill = colors.components.button.primaryBackgroundHover,
        activeFill = colors.components.button.primaryBackgroundActive,
        content = colors.text.inverted,
        icon = colors.icon.inverted,
        disabledFill = colors.background.disabled,
        disabledContent = colors.text.inverted,
        disabledIcon = colors.icon.inverted,
    )
    WarpButtonStyle.Secondary -> neutralContentPalette(
        fill = WarpButtonInterimColors.neutral,
        hoverFill = WarpButtonInterimColors.neutralHover,
        activeFill = WarpButtonInterimColors.neutralActive,
        disabledFill = WarpButtonInterimColors.neutral,
    )
    WarpButtonStyle.Quiet -> neutralContentPalette(
        fill = Color.Transparent,
        hoverFill = WarpButtonInterimColors.neutralHover,
        activeFill = WarpButtonInterimColors.neutralActive,
        disabledFill = Color.Transparent,
    )
    WarpButtonStyle.Overlay, WarpButtonStyle.OverlayElevated -> neutralContentPalette(
        fill = colors.surface.elevated300,
        hoverFill = colors.surface.elevated300Hover,
        activeFill = colors.surface.elevated300Active,
        disabledFill = colors.background.disabledSubtle,
    )
    WarpButtonStyle.OverlayInvertedStatic -> WarpButtonPalette(
        fill = WarpButtonInterimColors.overlayInvertedStatic,
        hoverFill = WarpButtonInterimColors.overlayInvertedStaticHover,
        activeFill = WarpButtonInterimColors.overlayInvertedStaticActive,
        content = colors.text.invertedStatic,
        icon = colors.icon.invertedStatic,
        disabledFill = WarpButtonInterimColors.overlayInvertedStatic,
        disabledContent = colors.text.disabled,
        disabledIcon = colors.icon.disabled,
    )
    WarpButtonStyle.SecondaryInvertedStatic -> WarpButtonPalette(
        fill = WarpButtonInterimColors.secondaryInvertedStatic,
        hoverFill = WarpButtonInterimColors.secondaryInvertedStaticHover,
        activeFill = WarpButtonInterimColors.secondaryInvertedStaticActive,
        content = colors.text.invertedStatic,
        icon = colors.icon.invertedStatic,
        disabledFill = Color.Transparent,
        disabledContent = colors.text.disabled,
        disabledIcon = colors.icon.disabled,
    )
    WarpButtonStyle.Negative -> WarpButtonPalette(
        fill = colors.background.negative,
        hoverFill = colors.background.negativeHover,
        activeFill = colors.background.negativeActive,
        content = colors.text.inverted,
        icon = colors.icon.inverted,
        disabledFill = colors.background.disabled,
        disabledContent = colors.text.inverted,
        disabledIcon = colors.icon.inverted,
    )
    WarpButtonStyle.NegativeQuiet -> WarpButtonPalette(
        fill = Color.Transparent,
        hoverFill = WarpButtonInterimColors.negativeHover,
        activeFill = WarpButtonInterimColors.negativeActive,
        content = colors.text.negative,
        icon = colors.icon.negative,
        disabledFill = Color.Transparent,
        disabledContent = colors.text.disabled,
        disabledIcon = colors.icon.disabled,
    )
    else -> warpButtonPalette(style.modernEquivalent())
}

private val LocalWarpButtonIconColor = compositionLocalOf { Color.Unspecified }

/**
 * The new-style button container: pill surface, state-driven fill, focus ring, loading stripes
 * and a 48dp minimum touch target. [content] is laid out in a centred row with an 8dp gap and
 * gets the state's content colour and the size's text style.
 */
@Composable
internal fun WarpButtonSurface(
    onClick: () -> Unit,
    modifier: Modifier,
    style: WarpButtonStyle,
    size: WarpButtonSize,
    enabled: Boolean,
    loading: Boolean,
    iconOnly: Boolean,
    interactionSource: MutableInteractionSource?,
    forcedState: WarpButtonState?,
    content: @Composable RowScope.() -> Unit,
) {
    val source = interactionSource ?: remember { MutableInteractionSource() }
    val isHovered by source.collectIsHoveredAsState()
    val isFocused by source.collectIsFocusedAsState()
    val isPressed by source.collectIsPressedAsState()
    val state = forcedState ?: when {
        !enabled -> WarpButtonState.Disabled
        isPressed -> WarpButtonState.Active
        isHovered -> WarpButtonState.Hover
        isFocused -> WarpButtonState.Focus
        else -> WarpButtonState.Default
    }
    val disabled = state == WarpButtonState.Disabled
    val showFocusRing = state == WarpButtonState.Focus || (forcedState == null && isFocused)

    val palette = warpButtonPalette(style)
    val fill = when {
        loading -> Color.Transparent
        disabled -> palette.disabledFill
        state == WarpButtonState.Active -> palette.activeFill
        state == WarpButtonState.Hover -> palette.hoverFill
        else -> palette.fill
    }
    val contentColor = when {
        loading -> colors.text.default
        disabled -> palette.disabledContent
        else -> palette.content
    }
    val iconColor = when {
        loading -> colors.icon.default
        disabled -> palette.disabledIcon
        else -> palette.icon
    }
    val elevated = style.modernEquivalent() == WarpButtonStyle.OverlayElevated && !loading
    //disable click when button is in loading state
    val clickAction: () -> Unit = if (loading) {
        {}
    } else onClick
    val loadingDescription = stringResource(R.string.loading_description)
    val metrics = size.metrics
    // Lets a multi-line label grow the button; a single line stays within the minimum height.
    val verticalPadding = if (size == WarpButtonSize.ExtraSmall) 4.dp else 8.dp

    Surface(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .focusRing(visible = showFocusRing, ringColor = colors.border.focus, gapColor = colors.background.default)
            .then(if (loading) Modifier.loadingAnimation(CircleShape) else Modifier)
            .then(if (loading) Modifier.semantics { stateDescription = loadingDescription } else Modifier)
            .clickable(
                interactionSource = source,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = clickAction
            )
            .then(if (!enabled) Modifier.focusable(interactionSource = source) else Modifier),
        shape = CircleShape,
        color = fill,
        contentColor = contentColor,
        shadowElevation = if (elevated) dimensions.shadowSmall else 0.dp,
    ) {
        CompositionLocalProvider(LocalWarpButtonIconColor provides iconColor) {
            ProvideTextStyle(size.textStyle) {
                Row(
                    modifier = if (iconOnly) {
                        Modifier.size(metrics.height)
                    } else {
                        Modifier
                            .width(IntrinsicSize.Max)
                            .heightIn(min = metrics.height)
                            .padding(horizontal = metrics.horizontalPadding, vertical = verticalPadding)
                    },
                    horizontalArrangement = Arrangement.spacedBy(IconGap, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    content = content
                )
            }
        }
    }
}

// Ring hugging the pill, drawn outside the bounds so focusing never shifts layout.
private fun Modifier.focusRing(visible: Boolean, ringColor: Color, gapColor: Color): Modifier =
    if (!visible) this else drawBehind {
        val gap = FocusGapWidth.toPx()
        drawPillOutline(offset = 0f, strokeWidth = gap, color = gapColor)
        drawPillOutline(offset = gap, strokeWidth = FocusRingWidth.toPx(), color = ringColor)
    }

private fun DrawScope.drawPillOutline(offset: Float, strokeWidth: Float, color: Color) {
    val outset = offset + strokeWidth / 2
    drawRoundRect(
        color = color,
        topLeft = Offset(-outset, -outset),
        size = Size(size.width + 2 * outset, size.height + 2 * outset),
        cornerRadius = CornerRadius(size.height / 2 + outset),
        style = Stroke(width = strokeWidth)
    )
}

/** Single label of a new-style button; ellipsizes before it squeezes the icons. */
@Composable
internal fun RowScope.WarpButtonLabel(text: String, maxLines: Int) {
    Text(
        text = text,
        modifier = Modifier.weight(1f, fill = false),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center
    )
}

/** Icon tinted with the button's current icon colour, unless it is a multi-colour brand icon. */
@Composable
internal fun WarpButtonIcon(
    icon: ImageVector,
    contentDescription: String?,
    size: Dp,
    modifier: Modifier = Modifier,
    keepBrandColors: Boolean = false,
) {
    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        tint = if (keepBrandColors) Color.Unspecified else LocalWarpButtonIconColor.current,
        modifier = Modifier.size(size).then(modifier)
    )
}

@Composable
internal fun RowScope.WarpButtonDrawableContent(
    text: String,
    maxLines: Int,
    @DrawableRes leadingIcon: Int?,
    leadingIconContentDescr: String?,
    @DrawableRes trailingIcon: Int?,
    trailingIconContentDescr: String?,
    iconSize: Dp,
    iconModifier: Modifier,
) {
    leadingIcon?.let {
        WarpButtonIcon(ImageVector.vectorResource(it), leadingIconContentDescr, iconSize, iconModifier)
    }
    WarpButtonLabel(text, maxLines)
    trailingIcon?.let {
        WarpButtonIcon(ImageVector.vectorResource(it), trailingIconContentDescr, iconSize, iconModifier)
    }
}
