package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpToast
import com.schibsted.nmp.warp.components.WarpToastDuration
import com.schibsted.nmp.warp.components.WarpToastType
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun ToastScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpToast",
        onUp = onUp
    ) {
        ToastScreenContent()
    }
}

@Composable
fun ToastScreenContent() {
    var showToastSuccess by remember { mutableStateOf(false) }
    var showToastWarning by remember { mutableStateOf(false) }
    var showToastNegative by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(dimensions.space1)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        WarpButton(modifier = Modifier.padding(bottom = dimensions.space2),
            text = "Show Success toast",
            onClick = {
                showToastSuccess = true
            })
        WarpButton(modifier = Modifier.padding(bottom = dimensions.space2),
            text = "Show Warning toast",
            onClick = {
                showToastWarning = true
            })
        WarpButton(modifier = Modifier.padding(bottom = dimensions.space2),
            text = "Show Negative toast",
            onClick = {
                showToastNegative = true
            })
        if (showToastSuccess) {
            WarpToast(
                text = "Toast success",
                type = WarpToastType.Success,
                duration = WarpToastDuration.INFINITE,
                onDismiss = { showToastSuccess = false }
            )
        }
        if (showToastWarning) {
            WarpToast(
                text = "Toast warning",
                type = WarpToastType.Warning,
                duration = WarpToastDuration.LONG,
                onDismiss = { showToastWarning = false }
            )
        }
        if (showToastNegative) {
            WarpToast(
                text = "Toast error",
                type = WarpToastType.Error,
                duration = WarpToastDuration.SHORT,
                onDismiss = { showToastNegative = false }
            )
        }
    }
}