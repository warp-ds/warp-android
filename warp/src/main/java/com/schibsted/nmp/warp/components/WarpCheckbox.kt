package com.schibsted.nmp.warp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalDimensions
import com.schibsted.nmp.warp.theme.LocalShapes
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes

/**
 * A checkbox in the warp design system.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/checkbox/)
 *
 * @param modifier The modifier to be applied to the checkbox.
 * @param text The text to be displayed next to the checkbox.
 * @param onCheckedChange A lambda that will be invoked when the checkbox is selected.
 * @param style The style to be applied on the checkbox. Default is WarpCheckboxStyle.Neutral
 * @param enabled Set to false to disable the checkbox. Default value is true.
 * @param checked Set to true if the checkbox is selected. Default value is false.
 * @param isError Set to true if the checkbox is in an invalid state. Default value is false.
 */
@Composable
fun WarpCheckbox(
    modifier: Modifier = Modifier,
    text: String,
    subText: @Composable (() -> Unit)? = null,
    onCheckedChange: ((Boolean) -> Unit) = {},
    style: WarpCheckboxStyle = WarpCheckboxStyle.Neutral,
    enabled: Boolean = true,
    checked: Boolean = false,
    isError: Boolean = false
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

        if (isError) {
            warpCheckboxColors = checkboxStyleNegative()
        }

        WarpCheckboxView(
            modifier = modifier,
            text = text,
            subText = subText,
            onCheckedChange = onCheckedChange,
            warpCheckboxColors = warpCheckboxColors,
            checked = checked,
            enabled = ((style != WarpCheckboxStyle.Disabled) && enabled)
        )

    }
}

/**
 * A group of checkboxes in the warp design system.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/checkbox/)
 *
 * @param modifier The modifier to be applied to the checkbox group.
 * @param orientation The orientation of the checkbox group. Default is Orientation.Vertical
 * @param title The optional title of the checkbox group.
 * @param options The list of options to be displayed in the checkbox group.
 * @param selectedOption The list of selected options. Default is null.
 * @param helpText The optional help text of the checkbox group.
 * @param enabled Set to false to disable the checkbox group. Default value is true.
 * @param isError Set to true if the checkbox group is in an invalid state. Default value is false.
 * @param onOptionSelected A lambda that will be invoked when an option is selected. Returns a mutable list of selected options.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WarpCheckboxGroup(
    modifier: Modifier = Modifier,
    orientation: Orientation = Orientation.Vertical,
    title: String? = null,
    options: List<String>,
    selectedOption: List<String>? = null,
    helpText: String? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    onOptionSelected: (MutableList<String>) -> Unit
) {
    require(options.size > 1) { "CheckboxGroup must have more than 1 element" }
    val selectedOptionSet: MutableSet<String> = selectedOption?.toMutableSet() ?: mutableSetOf()

    val verticalModifier =
        if (orientation.equals(Orientation.Vertical)) modifier.selectableGroup() else modifier

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {


        Column(
            modifier = verticalModifier,
            verticalArrangement = Arrangement.spacedBy(dimensions.space2)
        ) {
            title?.let {
                WarpText(
                    text = title,
                    style = WarpTextStyle.Title5,
                    modifier = Modifier.semantics { heading() })
            }
            if (orientation == Orientation.Horizontal) {
                Row(
                    modifier
                        .horizontalScroll(rememberScrollState())
                        .selectableGroup(),
                    horizontalArrangement = Arrangement.spacedBy(dimensions.space1)
                ) {
                    createCheckboxes(
                        options,
                        selectedOptionSet,
                        onOptionSelected,
                        selectedOption,
                        enabled,
                        isError
                    )

                }
            } else {
                createCheckboxes(
                    options,
                    selectedOptionSet,
                    onOptionSelected,
                    selectedOption,
                    enabled,
                    isError
                )
            }
            val helpTextColor = when {
                isError -> colors.text.negative
                !enabled -> colors.text.disabled
                else -> colors.text.subtle
            }
            helpText?.let {
                WarpText(
                    modifier = Modifier.padding(vertical = dimensions.space025),
                    text = helpText,
                    style = WarpTextStyle.Detail,
                    color = helpTextColor
                )
            }
        }
    }
}

@Composable
private fun createCheckboxes(
    options: List<String>,
    selectedOptionSet: MutableSet<String>,
    onOptionSelected: (MutableList<String>) -> Unit,
    selectedOption: List<String>?,
    enabled: Boolean,
    isError: Boolean
) {
    options.forEach { text ->
        WarpCheckbox(
            text = text,
            onCheckedChange = { isChecked ->
                selectedOptionSet.apply {
                    if (isChecked) add(text) else remove(text)
                }
                onOptionSelected(selectedOptionSet.toMutableList())
            },
            checked = selectedOption?.contains(text) ?: false,
            enabled = enabled,
            isError = isError
        )
    }
}

@Composable
private fun WarpCheckboxView(
    modifier: Modifier = Modifier,
    text: String,
    subText: @Composable() (() -> Unit)?,
    onCheckedChange: (Boolean) -> Unit = {},
    warpCheckboxColors: WarpCheckboxStyleColors,
    enabled: Boolean = true,
    checked: Boolean = false,
) {
    val isChecked = remember { mutableStateOf(checked) }
    val checkboxColor = remember { mutableStateOf(if (checked) warpCheckboxColors.selectedBackground else warpCheckboxColors.background) }
    val checkboxBorder = remember { mutableStateOf(if (checked) warpCheckboxColors.selectedBorder else warpCheckboxColors.border) }

    val rowModifier = modifier.toggleableIfEnabled(
        enabled = enabled,
        isChecked = isChecked.value,
        onCheckedChange = { checkboxChecked ->
            isChecked.value = checkboxChecked
            checkboxColor.value = if (checkboxChecked) warpCheckboxColors.selectedBackground else warpCheckboxColors.background
            checkboxBorder.value = if (checkboxChecked) warpCheckboxColors.selectedBorder else warpCheckboxColors.border
            onCheckedChange(checkboxChecked)
        }
    )

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
                    imageVector = ImageVector.vectorResource(id = R.drawable.warp_check),
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
        Spacer(modifier = Modifier.width(dimensions.space05))
        subText?.let { it() }
    }
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