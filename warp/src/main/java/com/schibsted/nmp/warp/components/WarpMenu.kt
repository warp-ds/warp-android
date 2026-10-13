package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.utils.InternalWarpApi
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
