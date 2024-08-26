package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpSpinner
import com.schibsted.nmp.warp.components.WarpSpinnerSize
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun SpinnerScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpSpinner",
        onUp = onUp
    ) {
        SpinnerScreenContent()
    }
}

@Composable
fun SpinnerScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2)
    ) {
        WarpText(text = "Default", modifier = Modifier.padding(bottom = dimensions.space05))
        Row(horizontalArrangement = Arrangement.Center) {
            WarpSpinner()
        }

        WarpText(text = "Small", modifier = Modifier.padding(top = dimensions.space2, bottom = dimensions.space05))
        Row(horizontalArrangement = Arrangement.Center) {
            WarpSpinner(size = WarpSpinnerSize.Small)
        }

        WarpText(text = "Large", modifier = Modifier.padding(top = dimensions.space2, bottom = dimensions.space05))
        Row(horizontalArrangement = Arrangement.Center) {
            WarpSpinner(size = WarpSpinnerSize.Large)
        }
    }
}