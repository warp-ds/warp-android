package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpDivider
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun DividerScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpDivider",
        onUp = onUp
    ) {
        DividerScreenContent()
    }
}

@Composable
fun DividerScreenContent() {
    Column(
        modifier = Modifier
            .padding(dimensions.space3)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = dimensions.space3)
                .fillMaxWidth()
                .height(300.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WarpText(text = "Vertical divider", style = WarpTextStyle.Title4)
            WarpDivider(modifier = Modifier.padding(dimensions.space2), Orientation.Vertical)
            WarpDivider(
                modifier = Modifier.padding(dimensions.space2),
                Orientation.Vertical,
                dashed = true
            )
            WarpText(text = "Vertical dashed divider", style = WarpTextStyle.Title4)
        }

        WarpText(text = "Horizontal divider", style = WarpTextStyle.Title4)
        WarpDivider(modifier = Modifier.padding(dimensions.space2))
        WarpDivider(modifier = Modifier.padding(dimensions.space2), dashed = true)
        WarpText(text = "Horizontal dashed divider", style = WarpTextStyle.Title4)
    }
}