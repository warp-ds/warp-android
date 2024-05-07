package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpModal
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun ModalScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpModal",
        onUp = onUp
    ) {
        ModalScreenContent()
    }
}

@Composable
fun ModalScreenContent() {
    var openModal by rememberSaveable { mutableStateOf(false) }
    var openModalOneButton by rememberSaveable { mutableStateOf(false) }
    var openModalMuchText by rememberSaveable { mutableStateOf(false) }
    var openModalCloseIcon by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2)
    ) {
        WarpButton(
            modifier = Modifier
                .fillMaxWidth()
                .width(dimensions.space4),
            text = "Open modal",
            onClick = { openModal = true },
            style = WarpButtonStyle.Primary
        )
        WarpButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensions.space3)
                .width(dimensions.space4),
            text = "Single button",
            onClick = { openModalOneButton = true },
            style = WarpButtonStyle.Primary
        )
        WarpButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensions.space3)
                .width(dimensions.space4),
            text = "Lots of text",
            onClick = { openModalMuchText = true },
            style = WarpButtonStyle.Primary
        )
        WarpButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensions.space3)
                .width(dimensions.space4),
            text = "No buttons",
            onClick = { openModalCloseIcon = true },
            style = WarpButtonStyle.Primary
        )
    }


    if (openModal) {
        WarpModal(
            title = "Hello warp!",
            body = "Meow meow meow",
            dismissButtonText = "Nope",
            confirmButtonText = "Meow",
            onConfirm = { openModal = false },
            onDismiss = { openModal = false }
        )
    }
    if (openModalOneButton) {
        WarpModal(
            title = "Hello warp!",
            body = "Only confirm button",
            confirmButtonText = "Close it",
            onConfirm = { openModalOneButton = false },
            onDismiss = { openModalOneButton = false }
        )
    }
    if (openModalMuchText) {
        WarpModal(
            title = "Hello warp!",
            subTitle = "Whats up?",
            body = "If you are going to design or develop for Schibsted Nordic Marketplaces you are in the right place! Warp is the design system for all our marketplaces.\n" +
                    "\n" +
                    "A good place to start is Getting started for developers or Getting started for designers.",
            dismissButtonText = "Cancel",
            confirmButtonText = "Okay",
            onConfirm = { openModalMuchText = false },
            onDismiss = { openModalMuchText = false }
        )
    }
    if (openModalCloseIcon) {
        WarpModal(
            title = "Hello warp!",
            body = "Who needs buttons? A modal with no buttons, just X :)",
            showCloseIcon = true,
            onDismiss = { openModalCloseIcon = false }
        )
    }
}
