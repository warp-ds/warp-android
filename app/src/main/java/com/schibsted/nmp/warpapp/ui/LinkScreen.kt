package com.schibsted.nmp.warpapp.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpLink
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun LinkScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpLink",
        onUp = onUp
    ) {
        LinkScreenContent()
    }
}

@Composable
fun LinkScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2)
    ) {
        WarpLink(
            text = "Warp link",
            onClick = { Log.d("WarpLink", "Clicked") })
        WarpLink(
            text = "Warp link with icon",
            onClick = { Log.d("WarpLink", "Clicked link") },
            icon = icons.linkExternal
            )
        WarpLink(
            text = "Warp link with underline",
            onClick = { Log.d("WarpLink", "Clicked link") },
            icon = icons.link,
            underline = true
        )
        WarpLink(
            text = "Warp link with a very long link text with icon. Also extra long text. Loooooong",
            onClick = { Log.d("WarpLink", "Clicked long link") },
            icon = icons.smileyHappy
        )
    }
}
