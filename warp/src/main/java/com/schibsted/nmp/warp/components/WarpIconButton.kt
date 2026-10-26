package com.schibsted.nmp.warp.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.utils.InternalWarpApi
import com.schibsted.nmp.warp.theme.WarpBrandIconResource
import com.schibsted.nmp.warp.theme.WarpIconResource

/**
 * A circular icon-only button in the warp design system, with the same styles, sizes and states
 * as [WarpButton]. Previous [WarpButtonStyle] values render as their new equivalent.
 *
 * @param icon The icon to show, tinted with the style's content colour (brand icons keep theirs)
 * @param contentDescription Accessible label of the button; pass null only when the button is
 * described by something else on screen
 * @param onClick lambda to be invoked when clicked
 * @param modifier Modifier for the button
 * @param style Appearance of the button. Defaults to [WarpButtonStyle.Prominent]
 * @param size 32dp, 40dp or 48dp circle; smaller sizes keep a 48dp touch target
 * @param enabled set to false to disable the button
 * @param loading shows the loading state; clicks are ignored while loading
 * @param interactionSource Optional source for hover/focus/press interactions
 */
@Composable
fun WarpIconButton(
    icon: WarpIconResource,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: WarpButtonStyle = WarpButtonStyle.Prominent,
    size: WarpButtonSize = WarpButtonSize.Medium,
    enabled: Boolean = true,
    loading: Boolean = false,
    interactionSource: MutableInteractionSource? = null,
) {
    WarpIconButtonImpl(
        icon = icon,
        contentDescription = contentDescription,
        onClick = onClick,
        modifier = modifier,
        style = style,
        size = size,
        enabled = enabled,
        loading = loading,
        interactionSource = interactionSource,
        forcedState = null,
    )
}

/**
 * [WarpIconButton] rendered in a fixed [forcedState], for snapshots/previews.
 */
@InternalWarpApi
@Composable
fun WarpIconButton(
    icon: WarpIconResource,
    contentDescription: String?,
    onClick: () -> Unit,
    forcedState: WarpButtonState,
    modifier: Modifier = Modifier,
    style: WarpButtonStyle = WarpButtonStyle.Prominent,
    size: WarpButtonSize = WarpButtonSize.Medium,
    loading: Boolean = false,
) {
    WarpIconButtonImpl(
        icon = icon,
        contentDescription = contentDescription,
        onClick = onClick,
        modifier = modifier,
        style = style,
        size = size,
        enabled = forcedState != WarpButtonState.Disabled,
        loading = loading,
        interactionSource = null,
        forcedState = forcedState,
    )
}

@Composable
private fun WarpIconButtonImpl(
    icon: WarpIconResource,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier,
    style: WarpButtonStyle,
    size: WarpButtonSize,
    enabled: Boolean,
    loading: Boolean,
    interactionSource: MutableInteractionSource?,
    forcedState: WarpButtonState?,
) {
    WarpButtonSurface(
        onClick = onClick,
        modifier = modifier,
        style = style.modernEquivalent(),
        size = size,
        enabled = enabled,
        loading = loading,
        iconOnly = true,
        interactionSource = interactionSource,
        forcedState = forcedState,
    ) {
        WarpButtonIcon(
            icon = icon.vector,
            contentDescription = contentDescription,
            size = size.metrics.iconOnlyIconSize,
            keepBrandColors = icon is WarpBrandIconResource,
        )
    }
}
