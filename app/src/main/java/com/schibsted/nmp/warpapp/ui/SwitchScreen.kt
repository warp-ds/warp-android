package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpSwitch
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun SwitchScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpSwitch",
        onUp = onUp
    ) {
        SwitchScreenContent()
    }
}

@Composable
fun SwitchScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2),
        verticalArrangement = Arrangement.spacedBy(dimensions.space2)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpText(text = "Switch on", modifier = Modifier.padding(end = dimensions.space2))
            var checked by remember { mutableStateOf(true) }
            WarpSwitch(
                checked = checked,
                onCheckedChange = { checked = it }
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpText(text = "Switch off", modifier = Modifier.padding(end = dimensions.space2))
            var checked by remember { mutableStateOf(false) }
            WarpSwitch(
                modifier = Modifier.padding(end = dimensions.space2),
                checked = checked,
                onCheckedChange = { checked = it }
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpText(
                text = "Disabled switch on",
                modifier = Modifier.padding(end = dimensions.space2)
            )
            var checked by remember { mutableStateOf(true) }
            WarpSwitch(
                checked = checked,
                onCheckedChange = { checked = it },
                enabled = false
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WarpText(
                text = "Disabled switch off",
                modifier = Modifier.padding(end = dimensions.space2)
            )
            var checked by remember { mutableStateOf(false) }
            WarpSwitch(
                checked = checked,
                onCheckedChange = { checked = it },
                enabled = false
            )
        }
    }
}
