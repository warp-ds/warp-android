package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpTooltip
import com.schibsted.nmp.warp.components.WarpTooltipState
import com.schibsted.nmp.warp.components.utils.Edge
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun TooltipScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpTooltip",
        onUp = onUp
    ) {
        TooltipScreenContent()
    }
}

@Composable
fun TooltipScreenContent() {
    val paddingValue = dimensions.space1
    Column(
        modifier = Modifier
            .padding(paddingValue)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensions.space2, Alignment.CenterVertically)
    ) {
        val topState = remember { WarpTooltipState(false) }
        val leadingState = remember { WarpTooltipState(false) }
        val trailingState = remember { WarpTooltipState(false) }
        val bottomState = remember { WarpTooltipState(false) }
        val inlineState = remember { WarpTooltipState(true) }

        WarpTooltip(
            modifier = Modifier.padding(horizontal = dimensions.space1),
            state = topState,
            text = "Top",
            edge = Edge.Bottom
        ) {
            WarpButton(
                text = "Top Tooltip",
                onClick = { topState.isVisible = !topState.isVisible })
        }
        WarpTooltip(
            state = leadingState,
            text = "Leading",
            edge = Edge.Trailing
        ) {
            WarpButton(
                "Leading Tooltip",
                { leadingState.isVisible = !leadingState.isVisible })
        }
        WarpTooltip(
            state = trailingState,
            text = "Trailing",
            edge = Edge.Leading
        ) {
            WarpButton(
                "Trailing Tooltip",
                { trailingState.isVisible = !trailingState.isVisible })
        }
        WarpTooltip(
            modifier = Modifier.padding(horizontal = dimensions.space1),
            state = bottomState,
            text = "Bottom",
            edge = Edge.Top
        ) {
            WarpButton(
                text = "Bottom Tooltip",
                onClick = { bottomState.isVisible = !bottomState.isVisible })
        }
        WarpTooltip(
            modifier = Modifier.padding(top = dimensions.space4),
            state = inlineState,
            inline = true,
            text = "Inline",
            edge = Edge.Top
        )
    }
}
