package com.schibsted.nmp.warp.components.ext

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.schibsted.nmp.warp.components.utils.Edge
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
internal fun Modifier.edgePadding(edge: Edge): Modifier = composed {
    when (edge) {
        Edge.Top -> this.padding(
            top = dimensions.space25,
            bottom = dimensions.space1,
            start = dimensions.space2,
            end = dimensions.space2
        )

        Edge.Bottom -> this.padding(
            top = dimensions.space1,
            bottom = dimensions.space25,
            start = dimensions.space2,
            end = dimensions.space2
        )

        Edge.Leading -> this.padding(
            top = dimensions.space1,
            bottom = dimensions.space1,
            start = dimensions.space25,
            end = dimensions.space2
        )

        Edge.Trailing -> this.padding(
            top = dimensions.space1,
            bottom = dimensions.space1,
            start = dimensions.space2,
            end = dimensions.space25
        )
    }
}

@Composable
internal fun Modifier.tooltipPadding(edge: Edge): Modifier = composed {
    when (edge) {
        Edge.Top -> this.padding(
            top = dimensions.space2,
            bottom = dimensions.space1,
            start = dimensions.space15,
            end = dimensions.space15
        )

        Edge.Bottom -> this.padding(
            top = dimensions.space1,
            bottom = dimensions.space2,
            start = dimensions.space15,
            end = dimensions.space15
        )

        Edge.Leading -> this.padding(
            top = dimensions.space1,
            bottom = dimensions.space1,
            start = dimensions.space2,
            end = dimensions.space15
        )

        Edge.Trailing -> this.padding(
            top = dimensions.space1,
            bottom = dimensions.space1,
            start = dimensions.space15,
            end = dimensions.space2
        )
    }
}