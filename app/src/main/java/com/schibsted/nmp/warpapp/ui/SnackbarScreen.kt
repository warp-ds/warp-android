package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
    var actionOnNewLine by remember { mutableStateOf(false) }

    WarpScaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            ) { data ->
                WarpSnackbar(
                    snackbarData = data,
                    actionOnNewLine = actionOnNewLine,
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
                    actionOnNewLine = false
                    showSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "Snackbar message"
                    )
                })
            WarpButton(modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Show snackbar with action",
                onClick = {
                    actionOnNewLine = false
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
                    actionOnNewLine = true
                    showSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        message = "Snackbar message",
                        actionLabel = "Longer Action",

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
