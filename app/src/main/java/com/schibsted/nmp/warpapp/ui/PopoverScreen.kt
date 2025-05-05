package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpPopover
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
        WarpPopover()
    }
}