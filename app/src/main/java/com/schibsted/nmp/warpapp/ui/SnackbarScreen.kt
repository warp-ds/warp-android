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
            WarpButton(modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Show snackbar",
                onClick = {
                    showSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "Snackbar message"
                    )
                })
            WarpButton(modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Show snackbar with action",
                onClick = {
                    showSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "Snackbar message",
                        actionLabel = "Action"
                    )
                })
            WarpButton(modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Show snackbar with longer action",
                onClick = {
                    showSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "Snackbar message",
                        actionLabel = "Longer Action",

                    )
                })
            WarpButton(modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Show success snackbar",
                onClick = {
                    showWarpSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "Success!",
                        type = com.schibsted.nmp.warp.components.WarpSnackbarType.SUCCESS
                    )
                })
            WarpButton(modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Show error snackbar",
                onClick = {
                    showWarpSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "Something went wrong",
                        type = com.schibsted.nmp.warp.components.WarpSnackbarType.ERROR
                    )
                })
            WarpButton(modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Show warning snackbar",
                onClick = {
                    showWarpSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "Warning message",
                        type = com.schibsted.nmp.warp.components.WarpSnackbarType.WARNING
                    )
                })
            WarpButton(modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Show info snackbar",
                onClick = {
                    showWarpSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "Information",
                        type = com.schibsted.nmp.warp.components.WarpSnackbarType.INFO
                    )
                })
            WarpButton(modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Show success with longer action",
                onClick = {
                    showWarpSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "Successfully saved your changes",
                        type = com.schibsted.nmp.warp.components.WarpSnackbarType.SUCCESS,
                        actionLabel = "View Details"
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
    type: com.schibsted.nmp.warp.components.WarpSnackbarType,
    actionLabel: String? = null,
    withDismissAction: Boolean = true,
    duration: SnackbarDuration = SnackbarDuration.Short,
) {
    scope.launch {
        snackbarHostState.showSnackbar(
            com.schibsted.nmp.warp.components.WarpSnackbarVisuals(
                message = message,
                type = type,
                actionLabel = actionLabel,
                withDismissAction = withDismissAction,
                duration = duration
            )
        )
    }
}
