package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpDialog
import com.schibsted.nmp.warp.components.WarpFullScreenDialog
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextField
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.utils.WarpDialogScenario
import com.schibsted.nmp.warp.utils.WarpDialogScenarios

private const val ImageScenarioName = "With image"
private const val FullScreenScenarioName = "Full-screen dialog"

@Composable
fun DialogScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpDialog",
        onUp = onUp,
    ) {
        DialogScreenContent()
    }
}

@Composable
fun DialogScreenContent() {
    var activeScenarioName by rememberSaveable { mutableStateOf<String?>(null) }
    val withIcon = WarpDialogScenarios.withIcon()
    val withImage = WarpDialogScenarios.withImage()
    val staticScenarios = WarpDialogScenarios.all + withIcon

    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensions.space15)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2),
    ) {
        staticScenarios.forEach { scenario ->
            WarpButton(
                modifier = buttonModifier,
                text = scenario.name,
                onClick = { activeScenarioName = scenario.name },
                style = WarpButtonStyle.Primary,
            )
        }
        WarpButton(
            modifier = buttonModifier,
            text = withImage.name,
            onClick = { activeScenarioName = ImageScenarioName },
            style = WarpButtonStyle.Primary,
        )
        WarpButton(
            modifier = buttonModifier,
            text = FullScreenScenarioName,
            onClick = { activeScenarioName = FullScreenScenarioName },
            style = WarpButtonStyle.Primary,
        )
    }

    val activeScenario = when (activeScenarioName) {
        ImageScenarioName -> withImage
        FullScreenScenarioName -> null
        else -> staticScenarios.find { it.name == activeScenarioName }
    }
    activeScenario?.let { scenario ->
        DialogFromScenario(scenario) { activeScenarioName = null }
    }

    if (activeScenarioName == FullScreenScenarioName) {
        NewEventFullScreenDialog(onDismiss = { activeScenarioName = null })
    }
}

@Composable
private fun DialogFromScenario(scenario: WarpDialogScenario, onDismiss: () -> Unit) {
    WarpDialog(
        title = scenario.title,
        body = scenario.body,
        icon = scenario.icon,
        primaryButtonText = scenario.primaryButtonText,
        secondaryButtonText = scenario.secondaryButtonText,
        onPrimaryButtonClick = onDismiss,
        onSecondaryButtonClick = onDismiss,
        onDismiss = onDismiss,
    )
}

@Composable
private fun NewEventFullScreenDialog(onDismiss: () -> Unit) {
    var title by rememberSaveable { mutableStateOf("") }
    var notes by rememberSaveable { mutableStateOf("") }
    WarpFullScreenDialog(
        title = "New event",
        onDismiss = onDismiss,
        actionText = "Save",
        onActionClick = onDismiss,
    ) {
        WarpText(
            text = "Full-screen dialogs are used for a series of tasks such as creating a calendar entry with a title, date, location, and time.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(bottom = dimensions.space3),
        )
        WarpTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensions.space2),
            label = "Event title",
            placeholderText = "Team offsite",
        )
        WarpTextField(
            value = notes,
            onValueChange = { notes = it },
            modifier = Modifier.fillMaxWidth(),
            label = "Notes",
            placeholderText = "Add any extra details",
        )
    }
}
