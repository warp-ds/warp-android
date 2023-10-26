package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpAlert
import com.schibsted.nmp.warp.components.WarpAlertType
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun AlertScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "WarpAlertBox", onUp = onUp) {
        AlertContent()
    }
}

@Composable
private fun AlertContent() {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the info variant of the alert element",
            body = "I am an excellent message for the user.")
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the positive variant of the alert element, with a very very long title so long that it will wrap",
            body = "With an additional description",
            type = WarpAlertType.Positive
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the negative variant of the alert element",
            body = "With an additional description",
            type = WarpAlertType.Negative
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the info variant of the alert element",
            body = "With an additional description that is very long, so long that it will probably become a new line",
            type = WarpAlertType.Warning
        )
    }
}