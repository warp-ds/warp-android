package com.schibsted.nmp.warp.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ripple
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
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.components.RadioButtonTokens.RadioAnimationDuration
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * A Radio button in the warp design system.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/radio/)
 *
 * @param modifier The modifier to be applied to the radio button.
 * @param label The text to be displayed next to the radio button.
 * @param selected Set to true if the radio button is selected. Default value is false.
 * @param isError Set to true if the radio button is in an invalid state. Default value is false.
 * @param enabled Set to false to disable the radio button. Default value is true.
 * @param extraText An optional text that will be displayed next to the label. Null by default.
 * @param slot An optional composable view that will be displayed next to the text. Null by default.
 * @param onClick An optional click handler that will be invoked when the radio button is clicked.
 * @param interactionSource The [MutableInteractionSource] representing the stream of Interactions for this radio button.
 */
@Composable
fun WarpRadio(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean = false,
    isError: Boolean = false,
    enabled: Boolean = true,
    extraText: String? = null,
    slot: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val textColor = rememberUpdatedState(
        when {
            isError -> colors.text.default
            !enabled -> colors.text.disabled
            else -> colors.text.default
        }
    )
    val extraTextColor = rememberUpdatedState(
        when {
            isError -> colors.text.subtle
            !enabled -> colors.text.disabled
            else -> colors.text.subtle
        }
    )
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
            .defaultMinSize(
                minWidth = dimensions.minimumTouchSize,
                minHeight = dimensions.minimumTouchSize
            )
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
        WarpText(
            text = label,
            style = WarpTextStyle.Body,
            color = textColor.value,
        )
        extraText?.let {
            Spacer(modifier = Modifier.width(dimensions.space05))
            WarpText(
                text = it,
                style = WarpTextStyle.Body,
                color = extraTextColor.value,
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
internal fun WarpRadioButtonView(
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
            .indication(
                interactionSource,
                ripple(bounded = false, radius = dimensions.components.radioIconSize)
            )
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

internal class WarpRadioButtonColors internal constructor(
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

@Preview
@Composable
fun WarpRadioButtonPreview() {
    WarpRadio(label = "RadioButton", selected = true)
}
