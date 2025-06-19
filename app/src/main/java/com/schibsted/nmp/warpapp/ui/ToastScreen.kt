package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpToast
import com.schibsted.nmp.warp.components.WarpToastDuration
import com.schibsted.nmp.warp.components.WarpToastState
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
    val toastSuccess = remember { WarpToastState() }
    val toastWarning = remember { WarpToastState() }
    val toastError = remember { WarpToastState() }

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
                toastSuccess.showToast("Toast success")
            })
        WarpButton(modifier = Modifier.padding(bottom = dimensions.space2),
            text = "Show Warning toast",
            onClick = {
                toastWarning.showToast("Toast warning")
            })
        WarpButton(modifier = Modifier.padding(bottom = dimensions.space2),
            text = "Show Negative toast",
            onClick = {
                toastError.showToast("Toast error")
            })
        WarpToast(
            state = toastSuccess
        )
        WarpToast(
            state = toastWarning,
            type = WarpToastType.Warning,
            duration = WarpToastDuration.MEDIUM
        )
        WarpToast(
            state = toastError,
            type = WarpToastType.Error,
            duration = WarpToastDuration.INFINITE
        )
    }
}
