package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpPopover
import com.schibsted.nmp.warp.components.WarpPopoverState
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.utils.Edge
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun PopoverScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpPopover",
        onUp = onUp
    ) {
        PopoverScreenContent()
    }
}

@Composable
fun PopoverScreenContent() {
    val paddingValue = dimensions.space1
    Column(
        modifier = Modifier
            .padding(paddingValue)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensions.space2, Alignment.CenterVertically)
    ) {
        val topState = remember { WarpPopoverState(false) }
        val leadingState = remember { WarpPopoverState(false) }
        val trailingState = remember { WarpPopoverState(false) }
        val bottomState = remember { WarpPopoverState(false) }
        val inlineState = remember { WarpPopoverState(true) }

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(
                dimensions.space2,
                Alignment.CenterVertically
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            WarpText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")

            //Top
            WarpPopover(
                modifier = Modifier
                    .requiredWidth(300.dp)
                    .padding(horizontal = dimensions.space1),
                state = topState,
                title = "Top",
                body = "Top Popover body Top Popover body Top Popover body Top top Popover body",
                edge = Edge.Bottom,
                onDismiss = { topState.isVisible = false }
            ) {
                WarpButton(
                    text = "Top Popover",
                    onClick = { topState.isVisible = !topState.isVisible })
            }

            //Trailing
            WarpPopover(
                modifier = Modifier.width(200.dp),
                state = trailingState,
                title = "Trailing",
                body = "Trailing Pop over body Trailing Pop over body Trailing Pop over body",
                edge = Edge.Leading,
                onDismiss = { trailingState.isVisible = false }
            ) {
                WarpButton(
                    "Trailing Popover",
                    { trailingState.isVisible = !trailingState.isVisible })
            }
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(
                dimensions.space2,
                Alignment.CenterVertically
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            //Leading
            WarpPopover(
                state = leadingState,
                title = "Leading",
                body = "Leading Popover body ",
                edge = Edge.Trailing,
                onDismiss = { leadingState.isVisible = false }
            ) {
                WarpButton(
                    text = "Leading Popover",
                    onClick = { leadingState.isVisible = !leadingState.isVisible })
            }
            //Bottom
            WarpPopover(
                modifier = Modifier.padding(horizontal = dimensions.space1),
                state = bottomState,
                title = "Bottom",
                body = "Bottom Popover body Bottom Popover body Bottom Popover body Bottom Popover body",
                edge = Edge.Top,
                onDismiss = { bottomState.isVisible = false }
            ) {
                WarpButton(
                    text = "Bottom Popover",
                    onClick = { bottomState.isVisible = !bottomState.isVisible })
            }
        }
        WarpPopover(
            modifier = Modifier.padding(vertical = 100.dp),
            state = inlineState,
            inline = true,
            title = "Inline",
            verticalOffset = 300.dp,
            body = "Inline Popover body Inline Popover body Inline Popover body Inline Popover body",
            edge = Edge.Top
        )
    }
}