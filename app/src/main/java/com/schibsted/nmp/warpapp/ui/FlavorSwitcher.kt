package com.schibsted.nmp.warpapp.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warpapp.MainViewModel

/**
 * A reusable flavor switcher menu button for the top app bar.
 * Shows a dropdown menu to switch between different brand flavors.
 */
@Composable
fun FlavorSwitcher() {
    val viewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity)
    var menuVisible by remember { mutableStateOf(false) }

    IconButton(onClick = { menuVisible = !menuVisible }) {
        WarpIcon(icon = icons.dotsVertical)
    }

    DropdownMenu(
        modifier = Modifier.background(WarpTheme.colors.background.subtle),
        expanded = menuVisible,
        onDismissRequest = { menuVisible = false }
    ) {
        DropdownMenuItem(
            text = { WarpText("Finn") },
            onClick = {
                viewModel.setFlavor("finn")
                menuVisible = false
            }
        )
        DropdownMenuItem(
            text = { WarpText("Tori") },
            onClick = {
                viewModel.setFlavor("tori")
                menuVisible = false
            }
        )
        DropdownMenuItem(
            text = { WarpText("DBA") },
            onClick = {
                viewModel.setFlavor("dba")
                menuVisible = false
            }
        )
        DropdownMenuItem(
            text = { WarpText("Blocket") },
            onClick = {
                viewModel.setFlavor("blocket")
                menuVisible = false
            }
        )
        DropdownMenuItem(
            text = { WarpText("Vend") },
            onClick = {
                viewModel.setFlavor("vend")
                menuVisible = false
            }
        )
        DropdownMenuItem(
            text = { WarpText("Neutral") },
            onClick = {
                viewModel.setFlavor("neutral")
                menuVisible = false
            }
        )
    }
}

/**
 * Gets the current flavor name as a display string.
 */
@Composable
fun rememberCurrentFlavor(): String {
    val viewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity)
    return viewModel.flavor.collectAsState().value
}
