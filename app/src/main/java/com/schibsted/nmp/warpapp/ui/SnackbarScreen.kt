package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.schibsted.nmp.warp.components.WarpSnackbarVisuals
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.utils.WarpSnackbarScenario
import com.schibsted.nmp.warp.utils.WarpSnackbarScenarios
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
            WarpSnackbarScenarios.all.forEach { scenario ->
                WarpButton(
                    modifier = Modifier.padding(bottom = dimensions.space2),
                    text = scenario.name,
                    onClick = {
                        showWarpSnackbar(
                            snackbarHostState = snackbarHostState,
                            scope = scope,
                            scenario = scenario
                        )
                    }
                )
            }
        }
    }
}

fun showWarpSnackbar(
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    scenario: WarpSnackbarScenario
) {
    scope.launch {
        snackbarHostState.showSnackbar(
            WarpSnackbarVisuals(
                message = scenario.message,
                type = scenario.type,
                actionLabel = scenario.actionLabel,
                withDismissAction = scenario.withDismissAction,
                duration = scenario.duration
            )
        )
    }
}
