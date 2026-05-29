@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpScaffold
import com.schibsted.nmp.warp.components.WarpTopAppBar
import com.schibsted.nmp.warp.theme.WarpResources.icons

@Composable
internal fun DetailsScaffold(title: String, onUp: () -> Unit, content: @Composable () -> Unit) {
    val currentFlavor = rememberCurrentFlavor()

    WarpScaffold(
        topBar = {
            WarpTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onUp) {
                        WarpIcon(icon = icons.arrowLeft)
                    }
                },
                titleText = "$title ($currentFlavor)",
                actions = {
                    FlavorSwitcher()
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            content()
        }
    }
}
