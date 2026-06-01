package com.schibsted.nmp.warpapp.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpScaffold
import com.schibsted.nmp.warp.components.WarpSelect
import com.schibsted.nmp.warp.components.WarpSnackbar
import com.schibsted.nmp.warp.components.WarpSnackbarType
import com.schibsted.nmp.warp.components.WarpSnackbarVisuals
import com.schibsted.nmp.warp.components.WarpSwitch
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextField
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.colors
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
    val context = LocalContext.current
    var showCustomDialog by remember { mutableStateOf(false) }
    var customBodyText by remember { mutableStateOf("This is the body text") }
    var customActionText by remember { mutableStateOf("") }
    var customDismissable by remember { mutableStateOf(true) }
    var customType by remember { mutableStateOf("Neutral") }
    var customDuration by remember { mutableStateOf(SnackbarDuration.Short.name) }

    val snackbarTypes = listOf("Neutral", "Positive", "Negative", "Warning", "Info")
    val neutralWithIconScenario = WarpSnackbarScenarios.neutralWithIcon()

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

            WarpButton(
                modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Neutral w/custom icon",
                onClick = {
                    showWarpSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        scenario = neutralWithIconScenario
                    )
                }
            )

            WarpButton(
                modifier = Modifier.padding(bottom = dimensions.space2),
                text = "Custom",
                onClick = {
                    showCustomDialog = true
                }
            )
        }
    }

    if (showCustomDialog) {
        Dialog(
            onDismissRequest = { showCustomDialog = false },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            )
        ) {
            Card(
                modifier = Modifier
                    .padding(horizontal = dimensions.space2)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(dimensions.borderRadius3),
                colors = CardDefaults.cardColors(
                    containerColor = colors.surface.elevated100,
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(dimensions.space2)
                        .fillMaxWidth()
                ) {
                    WarpText(
                        text = "Custom Snackbar",
                        style = WarpTextStyle.Title3,
                        modifier = Modifier.padding(bottom = dimensions.space2)
                    )

                    WarpTextField(
                        value = customBodyText,
                        onValueChange = { customBodyText = it },
                        label = "Body text",
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(dimensions.space2))

                    WarpTextField(
                        value = customActionText,
                        onValueChange = { customActionText = it },
                        label = "Action text",
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(dimensions.space2))

                    WarpSelect(
                        value = customType,
                        onValueChange = { customType = it },
                        label = "Type",
                        items = snackbarTypes,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(dimensions.space2))

                    WarpSelect(
                        value = customDuration,
                        onValueChange = { customDuration = it },
                        label = "Duration",
                        items = SnackbarDuration.entries.map { it.name },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(dimensions.space2))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        WarpText(text = "Dismissable")
                        WarpSwitch(
                            checked = customDismissable,
                            onCheckedChange = { customDismissable = it }
                        )
                    }

                    Spacer(modifier = Modifier.height(dimensions.space3))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimensions.space2, Alignment.End)
                    ) {
                        WarpButton(
                            text = "Cancel",
                            onClick = { showCustomDialog = false },
                            style = WarpButtonStyle.Secondary
                        )
                        WarpButton(
                            text = "Confirm",
                            onClick = {
                                val type = when (customType) {
                                    "Positive" -> WarpSnackbarType.Positive
                                    "Negative" -> WarpSnackbarType.Negative
                                    "Warning" -> WarpSnackbarType.Warning
                                    "Info" -> WarpSnackbarType.Info
                                    else -> WarpSnackbarType.Neutral()
                                }
                                try {
                                    val visuals = WarpSnackbarVisuals(
                                        message = customBodyText,
                                        actionLabel = customActionText.takeIf { it.isNotEmpty() },
                                        withDismissAction = customDismissable,
                                        type = type,
                                        duration = SnackbarDuration.valueOf(customDuration)
                                    )
                                    scope.launch {
                                        snackbarHostState.showSnackbar(visuals)
                                    }
                                    showCustomDialog = false
                                } catch (e: IllegalArgumentException) {
                                    Toast.makeText(
                                        context,
                                        e.message ?: "Invalid snackbar configuration",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            },
                            style = WarpButtonStyle.Primary
                        )
                    }
                }
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
