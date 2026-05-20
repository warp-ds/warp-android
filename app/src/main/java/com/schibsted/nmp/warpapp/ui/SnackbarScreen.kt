package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpScaffold
import com.schibsted.nmp.warp.components.WarpSnackbar
import com.schibsted.nmp.warp.components.WarpSnackbarType
import com.schibsted.nmp.warp.components.WarpSnackbarVisuals
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SnackbarScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpSnackbar",
        onUp = onUp
    ) {
        SnackbarScreenContent()
    }
}

@Composable
fun SnackbarScreenContent() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    WarpScaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            ) { data ->
                WarpSnackbar(
                    snackbarData = data,
                )
            }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(dimensions.space1)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            WarpButton(
                modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Neutral w/dismiss icon",
                onClick = {
                    showSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "This is a neutral message",
                    )
                })
            WarpButton(
                modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Success w/action to dismiss",
                onClick = {
                    showWarpSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "Successfully saved",
                        type = WarpSnackbarType.SUCCESS,
                        actionLabel = "OK",
                        withDismissAction = false
                    )
                })
            WarpButton(
                modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Error multiline no action",
                onClick = {
                    showWarpSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "Something went wrong. Hang in there while we work things out.",
                        type = WarpSnackbarType.ERROR
                    )
                })
            WarpButton(
                modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Warning w/long action & text",
                onClick = {
                    showWarpSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "This action cannot be undone and will permanently delete all your data",
                        type = WarpSnackbarType.WARNING,
                        actionLabel = "Let's do it!"
                    )
                })
            WarpButton(
                modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Info w/short action",
                onClick = {
                    showWarpSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "New features available",
                        type = WarpSnackbarType.INFO,
                        actionLabel = "View"
                    )
                })
            WarpButton(
                modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Info w/long action",
                onClick = {
                    showWarpSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "New features available",
                        type = WarpSnackbarType.INFO,
                        actionLabel = "View details"
                    )
                })
        }
    }
}

fun showSnackbar(
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    message: String,
    actionLabel: String? = null,
    withDismissAction: Boolean = true,
    duration: SnackbarDuration = SnackbarDuration.Short,
) {
    scope.launch {
        snackbarHostState.showSnackbar(
            message = message,
            withDismissAction = withDismissAction,
            actionLabel = actionLabel,
            duration = duration
        )
    }
}

fun showWarpSnackbar(
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    message: String,
    type: WarpSnackbarType,
    actionLabel: String? = null,
    withDismissAction: Boolean = true,
    duration: SnackbarDuration = SnackbarDuration.Short,
) {
    scope.launch {
        snackbarHostState.showSnackbar(
            WarpSnackbarVisuals(
                message = message,
                type = type,
                actionLabel = actionLabel,
                withDismissAction = withDismissAction,
                duration = duration
            )
        )
    }
}
