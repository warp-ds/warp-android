package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.CalloutSize
import com.schibsted.nmp.warp.components.CalloutState
import com.schibsted.nmp.warp.components.CalloutType
import com.schibsted.nmp.warp.components.Edge
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpCallout
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun CalloutScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpCallout",
        onUp = onUp
    ) {
        CalloutScreenContent()
    }
}

@Composable
fun CalloutScreenContent() {
    Column(
        modifier = Modifier
            .padding(dimensions.space2)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val topStartState = remember { CalloutState(false) }
        val bottomStartState = remember { CalloutState(false) }
        val bottomCenterState = remember { CalloutState(false) }
        val bottomEndState = remember { CalloutState(false) }
        val topCenterState = remember { CalloutState(false) }
        val topEndState = remember { CalloutState(false) }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            WarpCallout(
                text = "This is a callout",
                edge = Edge.Leading,
                state = topStartState,
                type = CalloutType.Popover,
                size = CalloutSize.Small,
                closable = false,
                onDismiss = { topStartState.isVisible = false },
            ) {
                WarpButton (
                    text = "Start",
                    onClick = {
                        topStartState.isVisible = !topStartState.isVisible
                    }
                )
            }
            WarpCallout(
                text = "This is a callout",
                state = topCenterState,
                edge = Edge.Top,
                size = CalloutSize.Small,
                closable = false,
                onDismiss = { topCenterState.isVisible = false },
            ) {
                WarpButton(
                    text = "Center Top",
                    onClick = {
                        topCenterState.isVisible = !topCenterState.isVisible
                    }
                )
            }
            WarpCallout(
                text = "This is a callout",
                state = topEndState,
                edge = Edge.Trailing,
                size = CalloutSize.Small,
                closable = false,
                onDismiss = { topEndState.isVisible = false },
            ) {
                WarpButton(
                    text = "End",
                    onClick = {
                        topEndState.isVisible = !topEndState.isVisible
                    }
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            WarpCallout(
                text = "This is a callout",
                state = bottomStartState,
                type = CalloutType.Inline,
                inlineModifier = Modifier.padding(0.dp),
                edge = Edge.Leading,
                size = CalloutSize.Small,
                closable = true,
                onDismiss = { bottomStartState.isVisible = false },
            ) {
                WarpButton(
                    text = "Inline",
                    onClick = {
                        bottomStartState.isVisible = !bottomStartState.isVisible
                    }
                )
            }
            WarpCallout(
                text = "This is a callout",
                state = bottomCenterState,
                edge = Edge.Bottom,
                size = CalloutSize.Small,
                closable = true,
                dismissPopoverOnClickOutside = false,
                onDismiss = { bottomCenterState.isVisible = false },
            ) {
                WarpButton(
                    text = "Center Bottom",
                    onClick = {
                        bottomCenterState.isVisible = !bottomCenterState.isVisible
                    }
                )
            }
            WarpCallout(
                text = "This is a callout",
                state = bottomEndState,
                edge = Edge.Trailing,
                size = CalloutSize.Small,
                closable = true,
                onDismiss = { bottomEndState.isVisible = false },
            ) {
                WarpButton(
                    text = "End",
                    onClick = {
                        bottomEndState.isVisible = !bottomEndState.isVisible
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun CalloutScreenPreview() {
    CalloutScreenContent()
}
