package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpDatePicker
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun DatePickerScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpDatePicker",
        onUp = onUp
    ) {
        DatePickerScreenContent()
    }
}

@Composable
fun DatePickerScreenContent() {
    Column(
        modifier = Modifier
            .padding(vertical = dimensions.space7, horizontal = dimensions.space2)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        WarpDatePicker()
    }
}
