package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.schibsted.nmp.warp.components.WarpDivider
import com.schibsted.nmp.warp.components.WarpState
import com.schibsted.nmp.warp.components.WarpStateType
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warpapp.R

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
            type = WarpStateType.Loading
        )
        WarpDivider()
        WarpState(
            modifier = Modifier.fillMaxWidth(),
            title = "Custom state",
            description = "Custom things happening here. Custom text and custom content. Unpredictable",
            icon = icons.shovel,
            primaryButtonText = "Okay",
            quietButtonText = "Meow?"
        )
        WarpDivider()
        WarpState(
            modifier = Modifier.fillMaxWidth(),
            title = "Custom state",
            description = "Custom things happening here. Custom text and custom content. Unpredictable",
            painter = painterResource(R.drawable.warp_placeholder),
            primaryButtonText = "Okay",
            quietButtonText = "Meow?"
        )
    }
}
