package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpSelect
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun SelectScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpSelect",
        onUp = onUp
    ) {
        SelectScreenContent()
    }
}

@Composable
fun SelectScreenContent() {
    var choice by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2)
            .verticalScroll(rememberScrollState())
    ) {
        WarpSelect(
            modifier = Modifier.padding(bottom = dimensions.space3),
            value = choice,
            onValueChange = { choice = it },
            label = "Label",
            placeholderText = "Select an option",
            items = listOf("One", "Two", "Three", "Four", "Five", "Six"),
            helpText = "Help text"
        )
        WarpSelect(
            modifier = Modifier.padding(bottom = dimensions.space3),
            value = choice,
            onValueChange = { choice = it },
            label = "Error",
            placeholderText = "Select an option",
            items = listOf("One", "Two", "Three", "Four", "Five", "Six"),
            isError = true,
            helpText = "Help text"
        )
        WarpSelect(
            modifier = Modifier.padding(bottom = dimensions.space3),
            value = choice,
            onValueChange = { choice = it },
            label = "ReadOnly",
            placeholderText = "Select an option",
            items = listOf("One", "Two", "Three", "Four", "Five", "Six"),
            readOnly = true,
            helpText = "Read only"
        )
        WarpSelect(
            modifier = Modifier.padding(bottom = dimensions.space3),
            value = choice,
            onValueChange = { choice = it },
            label = "Disabled",
            placeholderText = "Select an option",
            items = listOf("One", "Two", "Three", "Four", "Five", "Six"),
            enabled = false,
            helpText = "Disabled"
        )
    }
}