package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpPill
import com.schibsted.nmp.warp.components.WarpPillStyle
import com.schibsted.nmp.warp.components.WarpText

@Composable
fun PillScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpPill",
        onUp = onUp
    ) {
        PillScreenContent()
    }
}

@Composable
fun PillScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        WarpText(text = "Suggestion pill")
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            WarpPill(
                text = "Suggestion",
                onClick = { },
                style = WarpPillStyle.Suggestion,
                closable = true,
                modifier = Modifier
                    .padding(bottom = 16.dp, end = 16.dp)
            )
            WarpPill(
                text = "Suggestion",
                onClick = { },
                style = WarpPillStyle.Suggestion,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )
        }

        WarpText(text = "Filter pill")
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            WarpPill(
                text = "Filter",
                onClick = { },
                closable = true,
                style = WarpPillStyle.Filter,
                modifier = Modifier
                    .padding(end = 16.dp)
            )
            WarpPill(
                text = "Filter",
                onClick = { },
                style = WarpPillStyle.Filter
            )
        }
    }
}
