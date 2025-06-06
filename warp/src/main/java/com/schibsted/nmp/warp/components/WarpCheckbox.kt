package com.schibsted.nmp.warp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalDimensions
import com.schibsted.nmp.warp.theme.LocalShapes
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes

/**
 * A checkbox in the warp design system.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/checkbox/)
 *
 * @param modifier The modifier to be applied to the checkbox.
 * @param checked Set to true if the checkbox is selected.
 * @param label The text to be displayed next to the checkbox.
 * @param extraText The optional text to be displayed next to the label.
 * @param slot The optional composable to be displayed next to the label or extraText if provided.
 * @param onCheckedChange A lambda that will be invoked when the checkbox is selected.
 * @param style The style to be applied on the checkbox. Default is WarpCheckboxStyle.Default
 * @param enabled Set to false to disable the checkbox. Default value is true.
 * @param isError Set to true if the checkbox is in an invalid state. Default value is false.
 */
@Composable
fun WarpCheckbox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    label: String,
    extraText: String? = null,
    slot: @Composable (() -> Unit)? = null,
    onCheckedChange: ((Boolean) -> Unit) = {},
    style: WarpCheckboxStyle = WarpCheckboxStyle.Default,
    enabled: Boolean = true,
    isError: Boolean = false
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalShapes provides shapes,
        LocalDimensions provides dimensions
    ) {
        var warpCheckboxColors: WarpCheckboxStyleColors = when (style) {
            WarpCheckboxStyle.Default -> checkboxStyleDefault()
            WarpCheckboxStyle.Disabled -> checkboxStyleDisabled()
            WarpCheckboxStyle.Negative -> checkboxStyleNegative()
        }

        if (!enabled) {
            warpCheckboxColors = checkboxStyleDisabled()
        }

        if (isError) {
            warpCheckboxColors = checkboxStyleNegative()
        }

        WarpCheckboxView(
            modifier = modifier,
            label = label,
            extraText = extraText,
            slot = slot,
            onCheckedChange = onCheckedChange,
            warpCheckboxColors = warpCheckboxColors,
            checked = checked,
            enabled = ((style != WarpCheckboxStyle.Disabled) && enabled)
        )
    }
}

@Composable
private fun WarpCheckboxView(
    modifier: Modifier = Modifier,
    label: String,
    extraText: String?,
    slot: @Composable (() -> Unit)?,
    onCheckedChange: (Boolean) -> Unit = {},
    warpCheckboxColors: WarpCheckboxStyleColors,
    enabled: Boolean = true,
    checked: Boolean = false,
) {
    val checkboxColor =
        if (checked) warpCheckboxColors.selectedBackground else warpCheckboxColors.background
    val checkboxBorder =
        if (checked) warpCheckboxColors.selectedBorder else warpCheckboxColors.border

    val rowModifier = modifier
        .semantics(mergeDescendants = true) {}
        .toggleableIfEnabled(
            enabled = enabled,
            isChecked = checked,
            onCheckedChange = onCheckedChange
        )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = rowModifier
            .defaultMinSize(
                minWidth = dimensions.minimumTouchSize,
                minHeight = dimensions.minimumTouchSize
            )
    ) {
        WarpCheckboxIndicator(
            isChecked = checked,
            checkboxColor = checkboxColor,
            checkboxBorder = checkboxBorder
        )
        label.takeIf { it.isNotEmpty() }?.let {
            Spacer(modifier = Modifier.width(dimensions.space1))
            WarpText(
                text = label,
                style = WarpTextStyle.Body,
                color = warpCheckboxColors.text
            )
            Spacer(modifier = Modifier.width(dimensions.space05))
        }
        extraText?.let {
            WarpText(
                text = it,
                style = WarpTextStyle.Body,
                color = warpCheckboxColors.extraText,
                softWrap = false
            )
        }
        slot?.let {
            Spacer(modifier = Modifier.width(dimensions.space05))
            it()
        }
    }
}

@Composable
fun WarpCheckbox(
    modifier: Modifier = Modifier,
    onCheckedChange: ((Boolean) -> Unit) = {},
    style: WarpCheckboxStyle = WarpCheckboxStyle.Default,
    enabled: Boolean = true,
    checked: Boolean = false,
    isError: Boolean = false
) {
    WarpCheckbox(
        modifier = modifier.clip(warpCheckboxShape()),
        label = "",
        onCheckedChange = onCheckedChange,
        style = style,
        enabled = enabled,
        checked = checked,
        isError = isError
    )
}

@Composable
private fun WarpCheckboxIndicator(
    isChecked: Boolean,
    checkboxColor: Color,
    checkboxBorder: Color
) {
    val checkboxShape = warpCheckboxShape()
    var borderColor by remember { mutableStateOf(checkboxBorder) }
    val focusedBorderColor = colors.border.focus
    Box(
        modifier = Modifier
            .requiredSize(dimensions.components.checkbox.checkboxSize)
            .background(
                color = checkboxColor,
                shape = checkboxShape
            )
            .onFocusChanged {
                borderColor = if (it.isFocused) focusedBorderColor else checkboxBorder
            }
            .focusable()
            .border(
                width = dimensions.borderWidth1,
                color = borderColor,
                shape = checkboxShape
            ),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isChecked,
        ) {
            WarpIcon(
                modifier = Modifier
                    .height(dimensions.components.checkbox.iconHeight)
                    .semantics { this.invisibleToUser() },
                size = dimensions.icon.small,
                icon = icons.check,
                color = colors.icon.inverted
            )
        }
    }
}

@Composable
private fun warpCheckboxShape(): RoundedCornerShape {
    return RoundedCornerShape(dimensions.components.checkbox.cornerRadius)
}

@Composable
private fun Modifier.toggleableIfEnabled(
    enabled: Boolean,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
): Modifier {
    return if (enabled) {
        this.toggleable(
            value = isChecked,
            role = Role.Checkbox,
            onValueChange = onCheckedChange
        )
    } else {
        this
    }
}

enum class WarpCheckboxStyle {
    Default,
    Disabled,
    Negative,
}

data class WarpCheckboxStyleColors(
    val text: Color,
    val extraText: Color,
    val background: Color,
    val border: Color,
    val selectedBackground: Color,
    val selectedBorder: Color
)

@Composable
internal fun checkboxStyleDefault() = WarpCheckboxStyleColors(
    text = colors.text.default,
    extraText = colors.text.subtle,
    background = colors.background.default,
    border = colors.border.default,
    selectedBackground = colors.background.primary,
    selectedBorder = colors.border.primary
)

@Composable
internal fun checkboxStyleDisabled() = WarpCheckboxStyleColors(
    text = colors.text.disabled,
    extraText = colors.text.disabled,
    background = colors.background.disabledSubtle,
    border = colors.border.disabled,
    selectedBackground = colors.background.disabled,
    selectedBorder = colors.border.disabled
)

@Composable
internal fun checkboxStyleNegative() = WarpCheckboxStyleColors(
    text = colors.text.default,
    extraText = colors.text.subtle,
    background = colors.background.default,
    border = colors.border.negative,
    selectedBackground = colors.background.negative,
    selectedBorder = colors.border.negative
)

@Composable
@Preview
fun WarpCheckboxPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensions.space1)
    ) {
        WarpCheckbox(checked = false, label = "Default", onCheckedChange = { })
        WarpCheckbox(label = "Selected", checked = true, onCheckedChange = { })
        WarpCheckbox(
            checked = false,
            label = "Disabled",
            style = WarpCheckboxStyle.Disabled,
            onCheckedChange = { })
        WarpCheckbox(
            label = "Selected disabled",
            checked = true,
            style = WarpCheckboxStyle.Disabled,
            onCheckedChange = { })
        WarpCheckbox(
            checked = false,
            label = "Negative",
            style = WarpCheckboxStyle.Negative,
            onCheckedChange = { })
        WarpCheckbox(
            label = "Selected negative",
            checked = true,
            style = WarpCheckboxStyle.Negative,
            onCheckedChange = { })
    }
}
