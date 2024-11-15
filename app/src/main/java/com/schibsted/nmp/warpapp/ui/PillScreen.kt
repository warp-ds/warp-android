package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpPill
import com.schibsted.nmp.warp.components.WarpPillStyle
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

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
            .padding(dimensions.space2)
    ) {
        WarpText(text = "Suggestion pill")
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            WarpPill(
                text = "Suggestion",
                onClick = { },
                style = WarpPillStyle.Suggestion,
                closable = true,
                selected = false,
                modifier = Modifier
                    .padding(bottom = dimensions.space2, end = dimensions.space1)
            )
            WarpPill(
                text = "Suggestion",
                onClick = { },
                style = WarpPillStyle.Suggestion,
                modifier = Modifier
                    .padding(bottom = dimensions.space2, end = dimensions.space1)
            )
            WarpPill(
                text = "Link",
                onClick = { },
                style = WarpPillStyle.Suggestion,
                icon = com.google.android.material.R.drawable.material_ic_keyboard_arrow_next_black_24dp,
                modifier = Modifier
                    .padding(bottom = dimensions.space2)
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
                    .padding(end = dimensions.space1)
            )
            WarpPill(
                text = "Filter",
                onClick = { },
                style = WarpPillStyle.Filter,
                modifier = Modifier
                    .padding(end = dimensions.space1)
            )
            WarpPill(
                text = "Link",
                onClick = { },
                icon = com.google.android.material.R.drawable.material_ic_keyboard_arrow_next_black_24dp,
                style = WarpPillStyle.Filter
            )
        }
    }
}
