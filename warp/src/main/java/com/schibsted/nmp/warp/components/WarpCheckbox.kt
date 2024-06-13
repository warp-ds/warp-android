package com.schibsted.nmp.warp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalDimensions
import com.schibsted.nmp.warp.theme.LocalShapes
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes

@Composable
fun WarpCheckbox(
    text: String,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    style: WarpCheckboxStyle = WarpCheckboxStyle.Neutral,
    enabled: Boolean = true,
    checked: Boolean = false,
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalShapes provides shapes,
        LocalDimensions provides dimensions
    ) {
        var warpCheckboxColors: WarpCheckboxStyleColors = when (style) {
            WarpCheckboxStyle.Neutral -> checkboxStyleNeutral()
            WarpCheckboxStyle.Disabled -> checkboxStyleDisabled()
            WarpCheckboxStyle.Negative -> checkboxStyleNegative()
        }

        if (!enabled) {
            warpCheckboxColors = checkboxStyleDisabled()
        }

        WarpCheckboxView(
            text = text,
            onCheckedChange = onCheckedChange,
            warpCheckboxColors = warpCheckboxColors,
            checked = checked,
            enabled = enabled,
            modifier = modifier
        )

    }
}

// Custom checkbox view as the Material 3 checkbox doesn't support setting the border color separately
@Composable
private fun WarpCheckboxView(
    text: String,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    warpCheckboxColors: WarpCheckboxStyleColors,
    enabled: Boolean = true,
    checked: Boolean = false,
) {
    val isChecked = remember { mutableStateOf(checked) }
    val checkboxColor = remember {
        mutableStateOf(if (checked) warpCheckboxColors.selectedBackground else warpCheckboxColors.background)
    }
    val checkboxBorder = remember {
        mutableStateOf(if (checked) warpCheckboxColors.selectedBorder else warpCheckboxColors.border)
    }
    val rowModifier = if (enabled) {
        modifier
            .toggleable(
                value = isChecked.value,
                role = Role.Checkbox,
                onValueChange = { checkboxChecked ->
                    isChecked.value = checkboxChecked

                    if (isChecked.value) {
                        checkboxColor.value = warpCheckboxColors.selectedBackground
                        checkboxBorder.value = warpCheckboxColors.selectedBorder
                    } else {
                        checkboxColor.value = warpCheckboxColors.background
                        checkboxBorder.value = warpCheckboxColors.border
                    }

                    onCheckedChange?.let {
                        it(checkboxChecked)
                    }
                }
            )
    } else modifier
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = rowModifier
    ) {

        Box(
            modifier = modifier
                .requiredSize(dimensions.components.checkbox.checkboxSize)
                .background(
                    checkboxColor.value,
                    shape = RoundedCornerShape(
                        dimensions.components.checkbox.cornerRadius
                    )
                )
                .border(
                    width = dimensions.borderWidth1,
                    color = checkboxBorder.value,
                    shape = RoundedCornerShape(
                        dimensions.components.checkbox.cornerRadius
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            val density = LocalDensity.current
            val size = dimensions.components.checkbox.checkboxSize
            val duration = 200

            this@Row.AnimatedVisibility(
                visible = isChecked.value,
                enter = slideInHorizontally(animationSpec = tween(duration)) {
                    with(density) { (size * -0.5f).roundToPx() }
                } + expandHorizontally(
                    expandFrom = Alignment.Start,
                    animationSpec = tween(duration)
                ),
                exit = fadeOut()) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = colors.icon.inverted,
                    modifier = Modifier.height(dimensions.components.checkbox.iconHeight)
                )
            }
        }
        Spacer(modifier = Modifier.width(dimensions.space05))
        WarpText(
            text = text,
            style = WarpTextStyle.Body,
            color = warpCheckboxColors.text
        )
    }
}

enum class WarpCheckboxStyle {
    Neutral,
    Disabled,
    Negative,
}

data class WarpCheckboxStyleColors(
    val text: Color,
    val background: Color,
    val border: Color,
    val selectedBackground: Color,
    val selectedBorder: Color
)

@Composable
internal fun checkboxStyleNeutral() = WarpCheckboxStyleColors(
    text = colors.text.default,
    background = colors.background.default,
    border = colors.border.default,
    selectedBackground = colors.background.primary,
    selectedBorder = colors.border.primary
)

@Composable
internal fun checkboxStyleDisabled() = WarpCheckboxStyleColors(
    text = colors.text.disabled,
    background = colors.background.disabledSubtle,
    border = colors.border.disabled,
    selectedBackground = colors.background.disabled,
    selectedBorder = colors.border.disabled
)

@Composable
internal fun checkboxStyleNegative() = WarpCheckboxStyleColors(
    text = colors.text.default,
    background = colors.background.default,
    border = colors.border.negative,
    selectedBackground = colors.background.negative,
    selectedBorder = colors.border.negative
)

@Composable
@Preview
fun warpCheckboxPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensions.space1)
    ) {
        WarpCheckbox(text = "Neutral", onCheckedChange = { })
        WarpCheckbox(text = "Selected", checked = true, onCheckedChange = { })
        WarpCheckbox(text = "Disabled", style = WarpCheckboxStyle.Disabled, onCheckedChange = { })
        WarpCheckbox(
            text = "Selected disabled",
            checked = true,
            style = WarpCheckboxStyle.Disabled,
            onCheckedChange = { })
        WarpCheckbox(text = "Negative", style = WarpCheckboxStyle.Negative, onCheckedChange = { })
        WarpCheckbox(
            text = "Selected negative",
            checked = true,
            style = WarpCheckboxStyle.Negative,
            onCheckedChange = { })
    }
}