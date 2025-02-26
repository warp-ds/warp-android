package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.components.WarpExpandable
import com.schibsted.nmp.warp.components.WarpExpandableType
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun ExpandableScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "WarpExpandable", onUp = onUp) {
        ExpandableScreenContent()
    }
}

@Composable
private fun ExpandableScreenContent() {
    Column(
        modifier = Modifier
            .padding(dimensions.space2)
            .fillMaxWidth()
    ) {
        WarpText("Default expandable")
        WarpExpandable(
            modifier = Modifier.padding(
                vertical = dimensions.space1,
                horizontal = dimensions.space2
            ),
            title = "Title",
            initiallyExpanded = true
        ) {
            WarpText("Byte was a sleek, silver-furred cat with LED-bright eyes and a knack for coding.")
        }

        WarpText(
            modifier = Modifier.padding(top = dimensions.space3, bottom = dimensions.space2),
            text = "Boxed style"
        )

        WarpExpandable(
            modifier = Modifier.padding(
                vertical = dimensions.space1,
                horizontal = dimensions.space2
            ),
            title = "Title",
            type = WarpExpandableType.Box,
            initiallyExpanded = false
        ) {
            Column(Modifier.padding(bottom = dimensions.space1)) {
                WarpText("Byte was a sleek, silver-furred cat with LED-bright eyes and a knack for coding. By day, she prowled the office, batting at stray USB cables and diagnosing minor hardware issues with a tilt of her head. By night, Byte tapped her paws on a miniature keyboard, crafting clever hacks and futuristic software. ")
            }
        }

    }
}

@Composable
@Preview
private fun ExpandableScreenPreview() {
    ExpandableScreenContent()
}