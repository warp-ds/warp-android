package com.schibsted.nmp.warpapp.ui

import CalloutPosition
import CalloutSize
import Edge
import WarpCallout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import calculateCalloutPosition
import com.schibsted.nmp.warp.components.WarpButton
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
    var position by remember { mutableStateOf(CalloutPosition()) }
    val view = LocalView.current.rootView
    //var coordinates by remember { mutableStateOf(LayoutCoordinates()) }
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2),

    ) {
        val show = remember { mutableStateOf(false) }
        WarpButton(
            modifier = Modifier
                .onGloballyPositioned {
                    position = calculateCalloutPosition(it, view,Edge.Leading)
            },
            text = "Show callout",
            onClick = {
                show.value = !show.value
            }
        )
        if (show.value){
            WarpCallout(
                text = "This is a callout",
                edge = Edge.Leading,
                size = CalloutSize.Small,
                closable = true,
                onDismiss = { show.value = false },
                //position = position
            )
        }
    }
}

@Composable
@Preview
fun CalloutScreenPreview() {
    CalloutScreenContent()
}