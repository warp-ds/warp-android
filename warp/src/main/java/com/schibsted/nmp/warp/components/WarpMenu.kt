package com.schibsted.nmp.warp.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.schibsted.nmp.warp.components.utils.InternalWarpApi
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

// Mirrors M3 DropdownMenuContent's column padding (Menu.kt:386-412); WarpMenuContent must match it.
private val MenuVerticalPadding = 8.dp

private object WarpMenuContainer {
    val shape: Shape
        @Composable get() = RoundedCornerShape(dimensions.borderRadius3)
    val color: Color
        @Composable get() = colors.surface.elevated300
    val tonalElevation: Dp = 0.dp
    val shadowElevation: Dp
        @Composable get() = dimensions.shadowMedium
}

/**
 * A dropdown menu anchored to its parent. Place it in a `Box` together with the anchor
 * (e.g. an overflow icon button); it opens below the anchor while [expanded] is true.
 *
 * Menus do not close themselves when an item is clicked: each item's `onClick` should also set
 * [expanded] to false.
 *
 * ```
 * Box {
 *     WarpButton(text = "More", onClick = { expanded = true })
 *     WarpMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
 *         WarpMenuItem(text = "Edit", onClick = { expanded = false; onEdit() })
 *     }
 * }
 * ```
 *
 * @param expanded Whether the menu is shown
 * @param onDismissRequest Invoked on outside click or back press
 * @param modifier Modifier for the menu container
 * @param offset Offset from the anchor
 * @param scrollState Scroll state of the menu content
 * @param properties Popup properties. Defaults to a focusable popup, as in Material 3
 * @param content Menu items, typically [WarpMenuItem] and [WarpMenuDivider]
 */
@Composable
fun WarpMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    scrollState: ScrollState = rememberScrollState(),
    properties: PopupProperties = PopupProperties(focusable = true),
    content: @Composable ColumnScope.() -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        offset = offset,
        scrollState = scrollState,
        properties = properties,
        shape = WarpMenuContainer.shape,
        containerColor = WarpMenuContainer.color,
        tonalElevation = WarpMenuContainer.tonalElevation,
        shadowElevation = WarpMenuContainer.shadowElevation,
        border = null,
        content = content,
    )
}

/**
 * A horizontal separator between groups of [WarpMenuItem]s.
 */
@Composable
fun WarpMenuDivider(modifier: Modifier = Modifier) {
    WarpDivider(modifier = modifier.padding(vertical = dimensions.space1))
}

/**
 * Static rendering of the [WarpMenu] container without a Popup, for snapshots and previews.
 */
@InternalWarpApi
@Composable
fun WarpMenuContent(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = WarpMenuContainer.shape,
        color = WarpMenuContainer.color,
        tonalElevation = WarpMenuContainer.tonalElevation,
        shadowElevation = WarpMenuContainer.shadowElevation,
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = MenuVerticalPadding)
                .width(IntrinsicSize.Max),
            content = content
        )
    }
}

/**
 * Visual states of a [WarpMenuItem]. Only used to force a state in snapshots/previews.
 */
enum class WarpMenuItemState { Default, Hover, Focus, Pressed, Disabled }

/**
 * A single row in a [WarpMenu].
 *
 * Menus do not close themselves: [onClick] should also set the menu's `expanded` to false.
 *
 * @param text Label of the item
 * @param onClick Invoked when the item is clicked
 * @param modifier Modifier for the item
 * @param supportingText Optional second line under [text]
 * @param icon Optional leading icon. Receives the colour for the item's current state
 * @param trailingIcon Optional trailing icon. Receives the colour for the item's current state
 * @param selected Shows a leading check (replacing [icon]) and the selected fill
 * @param destructive Uses negative text/icon colours, for actions like delete
 * @param enabled Disabled items are not clickable and use disabled colours
 * @param interactionSource Optional source for hover/focus/press interactions
 */
@Composable
fun WarpMenuItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    icon: (@Composable (color: Color) -> Unit)? = null,
    trailingIcon: (@Composable (color: Color) -> Unit)? = null,
    selected: Boolean = false,
    destructive: Boolean = false,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
) {
    WarpMenuItemImpl(
        text = text,
        onClick = onClick,
        modifier = modifier,
        supportingText = supportingText,
        icon = icon,
        trailingIcon = trailingIcon,
        selected = selected,
        destructive = destructive,
        enabled = enabled,
        interactionSource = interactionSource,
        forcedState = null,
    )
}

/**
 * [WarpMenuItem] rendered in a fixed [forcedState], for snapshots/previews.
 */
@InternalWarpApi
@Composable
fun WarpMenuItem(
    text: String,
    onClick: () -> Unit,
    forcedState: WarpMenuItemState,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    icon: (@Composable (color: Color) -> Unit)? = null,
    trailingIcon: (@Composable (color: Color) -> Unit)? = null,
    selected: Boolean = false,
    destructive: Boolean = false,
) {
    WarpMenuItemImpl(
        text = text,
        onClick = onClick,
        modifier = modifier,
        supportingText = supportingText,
        icon = icon,
        trailingIcon = trailingIcon,
        selected = selected,
        destructive = destructive,
        enabled = forcedState != WarpMenuItemState.Disabled,
        interactionSource = null,
        forcedState = forcedState,
    )
}

@Composable
private fun WarpMenuItemImpl(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    supportingText: String?,
    icon: (@Composable (color: Color) -> Unit)?,
    trailingIcon: (@Composable (color: Color) -> Unit)?,
    selected: Boolean,
    destructive: Boolean,
    enabled: Boolean,
    interactionSource: MutableInteractionSource?,
    forcedState: WarpMenuItemState?,
) {
    val source = interactionSource ?: remember { MutableInteractionSource() }
    val isHovered by source.collectIsHoveredAsState()
    val isFocused by source.collectIsFocusedAsState()
    val isPressed by source.collectIsPressedAsState()
    val state = forcedState ?: when {
        !enabled -> WarpMenuItemState.Disabled
        isPressed -> WarpMenuItemState.Pressed
        isFocused -> WarpMenuItemState.Focus
        isHovered -> WarpMenuItemState.Hover
        else -> WarpMenuItemState.Default
    }
    val disabled = state == WarpMenuItemState.Disabled

    val fill = when {
        disabled -> Color.Transparent
        state == WarpMenuItemState.Pressed || selected -> colors.surface.elevated300Active
        state == WarpMenuItemState.Hover || state == WarpMenuItemState.Focus -> colors.surface.elevated300Hover
        else -> Color.Transparent
    }
    val textColor = when {
        disabled -> colors.text.disabled
        destructive -> colors.text.negative
        else -> colors.text.default
    }
    val supportingTextColor = if (disabled) colors.text.disabled else colors.text.subtle
    val iconColor = when {
        disabled -> colors.icon.disabled
        destructive -> colors.icon.negative
        else -> colors.icon.default
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .sizeIn(minWidth = 112.dp, maxWidth = 280.dp, minHeight = 48.dp)
            .clickable(
                interactionSource = source,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            )
            .then(if (selected) Modifier.semantics { this.selected = true } else Modifier)
            .background(fill)
            .padding(
                start = dimensions.space2,
                end = dimensions.space2,
                top = dimensions.space1,
                bottom = dimensions.space1
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensions.space15)
    ) {
        if (selected) {
            Icon(
                imageVector = icons.check.vector,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(dimensions.icon.default)
            )
        } else {
            icon?.invoke(iconColor)
        }
        Column(modifier = Modifier.weight(1f)) {
            WarpText(text = text, color = textColor, style = WarpTextStyle.Body)
            supportingText?.let {
                WarpText(text = it, color = supportingTextColor, style = WarpTextStyle.Caption)
            }
        }
        trailingIcon?.invoke(iconColor)
    }
}
