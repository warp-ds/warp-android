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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpDialog
import com.schibsted.nmp.warp.components.WarpDialogIcon
import com.schibsted.nmp.warp.components.WarpFullScreenDialog
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextField
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

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
    var openBasic by rememberSaveable { mutableStateOf(false) }
    var openWithIcon by rememberSaveable { mutableStateOf(false) }
    var openWithImage by rememberSaveable { mutableStateOf(false) }
    var openSingleButton by rememberSaveable { mutableStateOf(false) }
    var openLongText by rememberSaveable { mutableStateOf(false) }
    var openFullScreen by rememberSaveable { mutableStateOf(false) }
    var fullScreenTitle by rememberSaveable { mutableStateOf("") }
    var fullScreenNotes by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2),
    ) {
        WarpButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Basic dialog",
            onClick = { openBasic = true },
            style = WarpButtonStyle.Primary,
        )
        WarpButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensions.space15),
            text = "With Warp icon",
            onClick = { openWithIcon = true },
            style = WarpButtonStyle.Primary,
        )
        WarpButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensions.space15),
            text = "With image",
            onClick = { openWithImage = true },
            style = WarpButtonStyle.Primary,
        )
        WarpButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensions.space15),
            text = "Single button",
            onClick = { openSingleButton = true },
            style = WarpButtonStyle.Primary,
        )
        WarpButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensions.space15),
            text = "Lots of text",
            onClick = { openLongText = true },
            style = WarpButtonStyle.Primary,
        )
        WarpButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensions.space15),
            text = "Full-screen dialog",
            onClick = { openFullScreen = true },
            style = WarpButtonStyle.Primary,
        )
    }

    if (openBasic) {
        WarpDialog(
            title = "Basic dialog title",
            body = "A description should be a short, complete sentence.",
            secondaryButtonText = "Cancel",
            primaryButtonText = "Confirm",
            onSecondaryButtonClick = { openBasic = false },
            onPrimaryButtonClick = { openBasic = false },
            onDismiss = { openBasic = false },
        )
    }
    if (openWithIcon) {
        WarpDialog(
            title = "Basic dialog title",
            body = "A description should be a short, complete sentence.",
            icon = WarpDialogIcon.Icon(icons.starEmpty),
            secondaryButtonText = "Cancel",
            primaryButtonText = "Confirm",
            onSecondaryButtonClick = { openWithIcon = false },
            onPrimaryButtonClick = { openWithIcon = false },
            onDismiss = { openWithIcon = false },
        )
    }
    if (openWithImage) {
        val finnLogo = icons.finnLarge // TODO FK: using deprecated code
        WarpDialog(
            title = "Welcome to Finn",
            body = "A description should be a short, complete sentence.",
            icon = WarpDialogIcon.Image(
                painter = rememberVectorPainter(image = finnLogo.vector),
                contentDescription = finnLogo.description,
            ),
            secondaryButtonText = "Cancel",
            primaryButtonText = "Confirm",
            onSecondaryButtonClick = { openWithImage = false },
            onPrimaryButtonClick = { openWithImage = false },
            onDismiss = { openWithImage = false },
        )
    }
    if (openSingleButton) {
        WarpDialog(
            title = "Single action",
            body = "Only a primary button is shown.",
            primaryButtonText = "Got it",
            onPrimaryButtonClick = { openSingleButton = false },
            onDismiss = { openSingleButton = false },
        )
    }
    if (openLongText) {
        WarpDialog(
            title = "This dialog title is long enough to wrap onto multiple lines",
            body = "If a dialog needs this much explanation, it is probably the wrong container. " +
                "Dialogs interrupt the user and disable everything behind them, so they should " +
                "hold a single decision or a short, critical message.\n\n" +
                "For long-form content, guide the user to a dedicated screen or a bottom sheet. " +
                "For non-urgent information, an inline callout or a snackbar is usually kinder.",
            secondaryButtonText = "Cancel",
            primaryButtonText = "Okay",
            onSecondaryButtonClick = { openLongText = false },
            onPrimaryButtonClick = { openLongText = false },
            onDismiss = { openLongText = false },
        )
    }
    if (openFullScreen) {
        WarpFullScreenDialog(
            title = "New event",
            onDismiss = { openFullScreen = false },
            actionText = "Save",
            onActionClick = { openFullScreen = false },
        ) {
            WarpText(
                text = "Full-screen dialogs are used for a series of tasks such as creating a calendar entry with a title, date, location, and time.",
                style = WarpTextStyle.Body,
                modifier = Modifier.padding(bottom = dimensions.space3),
            )
            WarpTextField(
                value = fullScreenTitle,
                onValueChange = { fullScreenTitle = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensions.space2),
                label = "Event title",
                placeholderText = "Team offsite",
            )
            WarpTextField(
                value = fullScreenNotes,
                onValueChange = { fullScreenNotes = it },
                modifier = Modifier.fillMaxWidth(),
                label = "Notes",
                placeholderText = "Add any extra details",
            )
        }
    }
}
