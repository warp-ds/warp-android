package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2)
            .verticalScroll(rememberScrollState())
    ) {

    }
}