package com.schibsted.nmp.warp.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarpSlider(
    modifier: Modifier = Modifier,
    items: List<Any>,
    enabled: Boolean = true,
    initialStartItem: Any? = null,
    onValueChangeFinished: (() -> Unit) = {},
    onValueChanged: ((Any) -> Unit) = {},
    blockDrag: Boolean = false,
    showTooltip: Boolean = true,
    showRange: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    WarpRangeSlider(
        modifier = modifier,
        items = items,
        enabled = enabled,
        initialStartItem = initialStartItem,
        onValueChangeFinished = onValueChangeFinished,
        onLeftValueChanged = onValueChanged,
        blockDrag = blockDrag,
        showTooltips = showTooltip,
        showRange = showRange,
        startInteractionSource = interactionSource
    )
}
