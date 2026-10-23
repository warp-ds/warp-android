package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpMenu
import com.schibsted.nmp.warp.components.WarpMenuContent
import com.schibsted.nmp.warp.components.WarpMenuDivider
import com.schibsted.nmp.warp.components.WarpMenuItem
import com.schibsted.nmp.warp.components.WarpMenuItemState
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.components.utils.InternalWarpApi
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun MenuScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpMenu",
        onUp = onUp
    ) {
        MenuScreenContent()
    }
}

@Composable
private fun MenuScreenContent() {
    Column(
        modifier = Modifier
            .padding(dimensions.space2)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(dimensions.space2)
    ) {
        WarpText(text = "Overflow menu", style = WarpTextStyle.Title4)
        OverflowMenuDemo()
        WarpText(text = "States", style = WarpTextStyle.Title4)
        MenuStateGallery()
    }
}

@Composable
private fun OverflowMenuDemo() {
    var expanded by remember { mutableStateOf(false) }
    var sortNewest by remember { mutableStateOf(true) }
    Box {
        WarpButton(
            text = "More actions",
            style = WarpButtonStyle.Secondary,
            onClick = { expanded = true },
            trailingIcon = { WarpIcon(icon = icons.dotsVertical) }
        )
        WarpMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            WarpMenuItem(
                text = "Edit",
                icon = icons.edit,
                onClick = { expanded = false }
            )
            WarpMenuItem(
                text = "Share",
                supportingText = "Send a link to the ad",
                icon = icons.share,
                onClick = { expanded = false }
            )
            WarpMenuItem(
                text = "Sort by newest",
                selected = sortNewest,
                onClick = { sortNewest = !sortNewest; expanded = false }
            )
            WarpMenuItem(text = "Archive", enabled = false, onClick = {})
            WarpMenuDivider()
            WarpMenuItem(
                text = "Delete",
                destructive = true,
                icon = icons.bin,
                onClick = { expanded = false }
            )
        }
    }
}

@OptIn(InternalWarpApi::class)
@Composable
private fun MenuStateGallery() {
    WarpMenuContent {
        WarpMenuItemState.values().forEach { state ->
            WarpMenuItem(
                text = state.name,
                onClick = {},
                forcedState = state,
                icon = icons.edit
            )
        }
        WarpMenuItem(text = "Selected", onClick = {}, forcedState = WarpMenuItemState.Default, icon = icons.edit, selected = true)
        WarpMenuItem(text = "Destructive", onClick = {}, forcedState = WarpMenuItemState.Default, destructive = true)
    }
}
