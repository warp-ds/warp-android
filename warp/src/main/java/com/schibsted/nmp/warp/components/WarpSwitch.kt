package com.schibsted.nmp.warp.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalDimensions
import com.schibsted.nmp.warp.theme.LocalShapes
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes
import kotlinx.coroutines.launch

/**
 * A switch component that can be toggled on and off. The switch can be disabled.
 * @param modifier Modifier applied to the view. Default value is Modifier
 * @param checked State of the switch. Default value is false
 * @param onCheckedChange Invoked when the switch is toggled
 * @param enabled Set to false to disable the switch. Default value is true
 */
@Composable
fun WarpSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit) = { },
    enabled: Boolean = true,
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalShapes provides shapes,
        LocalDimensions provides dimensions
    ) {
        val switchColors = if (!enabled) disabledSwitchColors() else neutralSwitchColors()

        val height = dimensions.components.switch.height
        val width = dimensions.components.switch.width
        val thumbSize = dimensions.components.switch.thumbSize
        val offsetValue = if (checked) 1f else 0f
        val offset = remember {
            Animatable(offsetValue)
        }

        val scope = rememberCoroutineScope()
        DisposableEffect(checked) {
            if (offset.targetValue != offsetValue) {
                scope.launch { offset.animateTo(offsetValue, animationSpec = tween(200)) }
            }
            onDispose { }
        }

        Row(
            modifier = modifier.then(Modifier
                .height(height)
                .width(width)
                .clip(RoundedCornerShape(height))
                .background(
                    lerp(
                        switchColors.uncheckedTrackColor,
                        switchColors.checkedTrackColor,
                        offset.value
                    )
                )
                .toggleable(value = checked, enabled = enabled, onValueChange = onCheckedChange, role = Role.Switch)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .offset(x = (width - height) * offset.value)
                    .requiredSize(thumbSize)
                    .padding(dimensions.components.switch.thumbPadding)
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape)
                    .background(switchColors.thumbColor)
            )
        }
    }
}

internal data class WarpSwitchColors(
    val thumbColor: Color,
    val checkedTrackColor: Color,
    val uncheckedTrackColor: Color
)

@Composable
internal fun neutralSwitchColors() = WarpSwitchColors(
    thumbColor = colors.background.default,
    checkedTrackColor = colors.background.primary,
    uncheckedTrackColor = colors.components.switch.handleBackground
)

@Composable
internal fun disabledSwitchColors() = WarpSwitchColors(
    thumbColor = colors.background.disabled,
    checkedTrackColor = colors.background.disabledSubtle,
    uncheckedTrackColor = colors.background.disabledSubtle,
)

@Preview(showBackground = false)
@Composable
fun WarpSwitchPreview() {
    Column {
        var value by remember { mutableStateOf(true) }
        WarpSwitch(
            checked = value,
            modifier = Modifier.padding(dimensions.space2),
            onCheckedChange = { value = it })
        WarpSwitch(
            checked = value,
            modifier = Modifier.padding(dimensions.space2),
            onCheckedChange = { value = it })
    }

}