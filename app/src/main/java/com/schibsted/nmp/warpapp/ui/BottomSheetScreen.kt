package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpBottomSheet
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun BottomSheetScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpBottomSheet",
        onUp = onUp
    ) {
        BottomSheetScreenContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreenContent() {
    var openSheet by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2)
    ) {
        WarpButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Open bottom sheet",
            onClick = { openSheet = true },
            style = WarpButtonStyle.Primary,
        )
    }

    if (openSheet) {
        WarpBottomSheet(onDismiss = { openSheet = false }) {
            Column(modifier = Modifier.padding(dimensions.space3)) {
                WarpText(
                    text = "Bottom sheet",
                    style = WarpTextStyle.Title3,
                )
                WarpText(
                    modifier = Modifier.padding(top = dimensions.space2),
                    text = "Supplementary content anchored to the bottom of the screen. Swipe down, tap the scrim, or press back to dismiss.",
                    style = WarpTextStyle.Body,
                )
            }
        }
    }
}
