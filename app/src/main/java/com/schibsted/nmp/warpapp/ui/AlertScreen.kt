package com.schibsted.nmp.warpapp.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpAlert
import com.schibsted.nmp.warp.components.WarpAlertType
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun AlertScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "WarpAlert", onUp = onUp) {
        AlertContent()
    }
}

@Composable
private fun AlertContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the info variant of the alert element",
            body = "I am an excellent message for the user."
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            body = "This is the critical variant of the alert element without title ",
            type = WarpAlertType.Critical
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the positive variant of the alert element, with a very very long title so long that it will wrap",
            body = "With an additional description",
            type = WarpAlertType.Positive
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the critical variant of the alert element",
            body = "With an additional description",
            type = WarpAlertType.Critical
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the warning variant of the alert element",
            body = "With an additional description that is very long, so long that it will probably become a new line",
            type = WarpAlertType.Warning
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the warning variant of the alert element",
            body = "With an additional description.",
            type = WarpAlertType.Info,
            linkText = "A link, just in case you wanna click",
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the positive variant with a secondary button",
            body = "With an additional description",
            type = WarpAlertType.Positive,
            secondaryButtonText = "Button",
            secondaryButtonAction = { Log.d("Warp", "Warp Alert Secondary button clicked") }
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the positive variant with a quiet button",
            body = "With an additional description",
            type = WarpAlertType.Positive,
            quietButtonText = "Quiet Button",
            quietButtonAction = { Log.d("Warp", "Warp Alert Secondary button clicked") }
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the critical variant of the alert element",
            body = "With an additional description and buttons for further action",
            type = WarpAlertType.Critical,
            secondaryButtonText = "Button",
            secondaryButtonAction = { Log.d("Warp", "Warp Alert Secondary button clicked") },
            quietButtonText = "Quiet Button",
            quietButtonAction = { Log.d("Warp", "Warp Alert Quiet button clicked") }
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the info variant with all options",
            body = "You can read more about much more. There's also buttons that you can click",
            type = WarpAlertType.Warning,
            linkText = "A link to read more here",
            linkAction = { Log.d("Warp", "Warp Alert Link clicked") },
            secondaryButtonText = "Button",
            secondaryButtonAction = { Log.d("Warp", "Warp Alert Secondary button clicked") },
            quietButtonText = "Quiet Button",
            quietButtonAction = { Log.d("Warp", "Warp Alert Quiet button clicked") }
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            body = "This is the info variant with all options except the title. You can read more about much more. There's also buttons that you can click",
            type = WarpAlertType.Warning,
            linkText = "A link to read more here",
            linkAction = { Log.d("Warp", "Warp Alert Link clicked") },
            secondaryButtonText = "Button",
            secondaryButtonAction = { Log.d("Warp", "Warp Alert Secondary button clicked") },
            quietButtonText = "Quiet Button",
            quietButtonAction = { Log.d("Warp", "Warp Alert Quiet button clicked") }
        )
    }
}