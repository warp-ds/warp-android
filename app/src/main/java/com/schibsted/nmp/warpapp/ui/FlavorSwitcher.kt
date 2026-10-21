package com.schibsted.nmp.warpapp.ui

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.schibsted.nmp.warp.components.TopAppBarAction
import com.schibsted.nmp.warp.components.TopAppBarMenuItem
import com.schibsted.nmp.warpapp.MainViewModel

private val flavors = listOf(
    "finn" to "Finn",
    "tori" to "Tori",
    "dba" to "DBA",
    "blocket" to "Blocket",
    "vend" to "Vend",
    "neutral" to "Neutral",
)

/**
 * A top app bar menu for switching between brand flavors, with the current one checked.
 */
@Composable
fun flavorSwitcherAction(): TopAppBarAction.Menu {
    val viewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity)
    val current = rememberCurrentFlavor()
    return TopAppBarAction.Menu(
        flavors.map { (id, name) ->
            TopAppBarMenuItem(text = name, checked = id == current) { viewModel.setFlavor(id) }
        }
    )
}

/**
 * Gets the current flavor name as a display string.
 */
@Composable
fun rememberCurrentFlavor(): String {
    val viewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity)
    return viewModel.flavor.collectAsState().value
}
