package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpCard
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun CardScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "Card", onUp = onUp) {
        WarpCardContent()
    }
}

@Composable
private fun WarpCardContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(WarpTheme.dimensions.space2),
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(WarpTheme.dimensions.space2)
    ) {
        WarpCard(title = "", subtitle = "") {
            Column(
                modifier = Modifier.padding(dimensions.space2)
            ) {
                WarpText(text = "This is a card")
            }
        }
    }
}
