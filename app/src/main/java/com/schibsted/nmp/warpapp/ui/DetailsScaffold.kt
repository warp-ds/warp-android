@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.TopAppBarConfiguration
import com.schibsted.nmp.warp.components.TopAppBarNavigation
import com.schibsted.nmp.warp.components.WarpScaffold
import com.schibsted.nmp.warp.components.WarpTopAppBar

@Composable
internal fun DetailsScaffold(title: String, onUp: () -> Unit, content: @Composable () -> Unit) {
    val currentFlavor = rememberCurrentFlavor()

    WarpScaffold(
        topBar = {
            WarpTopAppBar(
                configuration = TopAppBarConfiguration(
                    title = "$title ($currentFlavor)",
                    navigation = TopAppBarNavigation.Back(onUp),
                    actions = listOf(flavorSwitcherAction()),
                ),
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            content()
        }
    }
}