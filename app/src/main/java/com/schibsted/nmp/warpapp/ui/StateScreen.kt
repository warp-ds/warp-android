package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpDivider
import com.schibsted.nmp.warp.components.WarpState
import com.schibsted.nmp.warp.components.WarpStateType
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun StateScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "WarpState", onUp = onUp) {
        StateContent()
    }
}

@Composable
fun StateContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensions.space3),
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(dimensions.space2)
    ) {
        WarpState(type = WarpStateType.NoSearchResults)
        WarpDivider()
        WarpState(type = WarpStateType.LoadFailed)
        WarpDivider()
        WarpState(type = WarpStateType.Offline)
        WarpDivider()
        WarpState(type = WarpStateType.Login)
        WarpDivider()
        WarpState(type = WarpStateType.Verify)
        WarpDivider()
        WarpState(
            modifier = Modifier.fillMaxWidth(),
            type = WarpStateType.Loading)
    }
}
