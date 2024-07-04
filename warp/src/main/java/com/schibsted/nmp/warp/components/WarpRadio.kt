package com.schibsted.nmp.warp.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.RadioButtonTokens.RadioAnimationDuration
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * A Radio button in the warp design system.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/radio/)
 *
 * @param modifier The modifier to be applied to the radio button.
 * @param text The text to be displayed next to the radio button.
 * @param selected Set to true if the radio button is selected. Default value is false.
 * @param isError Set to true if the radio button is in an invalid state. Default value is false.
 * @param enabled Set to false to disable the radio button. Default value is true.
 * @param onClick An optional click handler that will be invoked when the radio button is clicked.
 * @param interactionSource The [MutableInteractionSource] representing the stream of [Interaction]s for this radio button.
 */
@Composable
fun WarpRadio(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean = false,
    isError: Boolean = false,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val selectableModifier = Modifier
        .selectable(
            selected = selected,
            onClick = onClick ?: {},
            enabled = enabled,
            role = Role.RadioButton,
            interactionSource = interactionSource,
            indication = null
        )

    Row(
        modifier = modifier
            .then(selectableModifier)
            .semantics(mergeDescendants = true) {},
        verticalAlignment = Alignment.CenterVertically,
    ) {
        WarpRadioButtonView(
            selected = selected,
            enabled = enabled,
            isError = isError,
            interactionSource = interactionSource,
            colors = defaultRadioColors(),
        )
        Spacer(modifier = Modifier.width(dimensions.space1))
        WarpText(text = text)
    }
}

@Composable
fun WarpRadioButtonView(
    selected: Boolean = false,
    enabled: Boolean = true,
    isError: Boolean = false,
    colors: WarpRadioButtonColors,
    interactionSource: MutableInteractionSource
) {
    val interacted by interactionSource.collectIsInteractedAsState()
    val finalStrokeWidth = dimensions.borderWidth1
    val finalStrokeSelectedWidth = dimensions.space15
    val strokeWidth = animateDpAsState(
        targetValue = if (selected) finalStrokeSelectedWidth / 2 else finalStrokeWidth / 2,
        animationSpec = tween(durationMillis = RadioAnimationDuration), label = ""
    )
    val radioColor = colors.radioColor(enabled, selected, isError, interacted)
    val optionalFillColor = colors.optionalFillColor(enabled, isError, interacted)
    val iconSize = dimensions.components.radioIconSize
    Canvas(
        Modifier
            .wrapContentSize(Alignment.Center)
            .padding(dimensions.space025)
            .requiredSize(iconSize)
    ) {
        // Draw the radio button
        drawCircle(
            radioColor.value,
            radius = (iconSize / 2).toPx() - strokeWidth.value.toPx() / 2,
            style = Stroke(strokeWidth.value.toPx())
        )
        optionalFillColor?.let {
            drawCircle(
                it.value,
                (iconSize / 2).toPx() - strokeWidth.value.toPx(),
                style = Fill
            )
        }
    }
}

@Composable
fun MutableInteractionSource.collectIsInteractedAsState(): State<Boolean> {
    val isPressed by collectIsPressedAsState()
    val isFocused by collectIsFocusedAsState()
    val isHovered by collectIsHoveredAsState()
    return remember { mutableStateOf(isPressed || isFocused || isHovered) }
}


class WarpRadioButtonColors internal constructor(
    private val unselectedColor: Color,
    private val selectedColor: Color,
    private val pressedColor: Color,
    private val disabledColor: Color,
    private val errorColor: Color,
    private val disabledInteriorColor: Color,
    private val pressedDefaultInteriorColor: Color,
    private val pressedErrorInteriorColor: Color
) {

    @Composable
    internal fun radioColor(
        enabled: Boolean,
        selected: Boolean,
        isError: Boolean,
        interacted: Boolean
    ): State<Color> {
        val target = when {
            isError -> errorColor
            enabled && selected -> selectedColor
            enabled && interacted -> pressedColor
            enabled && !interacted -> unselectedColor
            else -> disabledColor
        }

        // If not enabled 'snap' to the disabled state, as there should be no animations between
        // enabled / disabled.
        return if (enabled) {
            animateColorAsState(
                target,
                tween(durationMillis = RadioAnimationDuration),
                label = "colorAnimation"
            )
        } else {
            rememberUpdatedState(target)
        }
    }

    @Composable
    internal fun optionalFillColor(
        enabled: Boolean,
        isError: Boolean,
        interacted: Boolean
    ): State<Color>? {
        return when {
            !enabled -> rememberUpdatedState(disabledInteriorColor)
            interacted && !isError -> rememberUpdatedState(pressedDefaultInteriorColor)
            interacted && isError -> rememberUpdatedState(pressedErrorInteriorColor)
            else -> null
        }
    }
}

@Composable
internal fun defaultRadioColors() = WarpRadioButtonColors(
    unselectedColor = colors.border.default,
    selectedColor = colors.border.selected,
    pressedColor = colors.border.primary,
    disabledColor = colors.border.disabled,
    errorColor = colors.border.negative,
    disabledInteriorColor = colors.background.disabledSubtle,
    pressedDefaultInteriorColor = colors.background.hover,
    pressedErrorInteriorColor = colors.background.negativeSubtle,
)


internal object RadioButtonTokens {
    const val RadioAnimationDuration = 100
}

/**
 * A group of Radio buttons in the warp design system.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/radio/)
 *
 * @param options The list of options for the radio group
 * @param selectedOption The currently selected option in the radio group
 * @param helpText An optional help text that will be displayed below the radio group. Null by default
 * @param enabled Set to false to disable the radio group. Default value is true
 * @param isError Set to true if the radio group is in an error state. Default value is false
 * @param onOptionSelected A function that will be invoked when an option in the radio group is selected. Make sure to change the state of currently selected option
 */
@Composable
fun VerticalWarpRadioGroup(
    modifier: Modifier = Modifier,
    title: String? = null,
    options: List<String>,
    selectedOption: String? = null,
    helpText: String? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    onOptionSelected: (String) -> Unit
) {
    require(options.isNotEmpty()) { "RadioGroup must have more than 1 element" }
    // Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(modifier.selectableGroup(), verticalArrangement = Arrangement.spacedBy(dimensions.space2)) {
        title?.let {
            WarpText(
                text = title,
                style = WarpTextStyle.Title5,
                modifier = Modifier.semantics { heading() })
        }
        options.forEach { text ->
            WarpRadio(
                text = text,
                onClick = { onOptionSelected(text) },
                selected = (text == selectedOption),
                enabled = enabled,
                isError = isError,
            )
        }

        val helpTextColor =
            rememberUpdatedState(if (isError) colors.text.negative else if (!enabled) colors.text.disabled else colors.text.subtle).value
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

@Composable
fun HorizontalWarpRadioGroup(
    modifier: Modifier = Modifier,
    title: String? = null,
    options: List<String>,
    selectedOption: String? = null,
    helpText: String? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    onOptionSelected: (String) -> Unit
) {
    require(options.isNotEmpty()) { "RadioGroup must have more than 1 element" }
    // Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column {
        title?.let {
            WarpText(
                text = title,
                style = WarpTextStyle.Title5,
                modifier = Modifier.semantics { heading() })
        }
        Spacer(modifier = Modifier.height(dimensions.space2))
        Row(
            modifier.selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(dimensions.space1)
        ) {
            options.forEach { text ->
                WarpRadio(
                    text = text,
                    onClick = { onOptionSelected(text) },
                    selected = (text == selectedOption),
                    enabled = enabled,
                    isError = isError,
                )
            }
        }
        Spacer(modifier = Modifier.height(dimensions.space2))
        val helpTextColor =
            rememberUpdatedState(if (isError) colors.text.negative else if (!enabled) colors.text.disabled else colors.text.default).value
        helpText?.let {
            WarpText(
                modifier = Modifier.padding(
                    vertical = dimensions.space025,
                    horizontal = 0.dp
                ),
                text = helpText,
                style = WarpTextStyle.Detail,
                color = helpTextColor
            )
        }
    }
}

@Preview
@Composable
fun WarpRadioButtonPreview() {
    WarpRadio(text = "RadioButton", selected = true)
}
