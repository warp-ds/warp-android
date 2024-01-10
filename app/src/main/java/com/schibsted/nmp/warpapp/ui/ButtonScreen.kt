@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import com.schibsted.nmp.warpapp.BrandTheme

@Composable
fun ButtonScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "Buttons", onUp = onUp) {
        ButtonScreenContent()
    }
}

@Composable
private fun ButtonScreenContent() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .padding(vertical = 16.dp)
    ) {
        for (style in WarpButtonStyle.values()) {
            WarpText(text = style.name, modifier = Modifier.padding(top = 16.dp, bottom = 4.dp, start = 16.dp, end = 16.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
            ) {
                WarpButton(onClick = { /*TODO*/ }, buttonStyle = style, text = "Click me")
                WarpButton(onClick = { /*TODO*/ }, buttonStyle = style, text = "Loading", loading = true)
                WarpButton(onClick = { /*TODO*/ }, buttonStyle = style, text = "Disabled", enabled = false)
            }
        }
        WarpText(text = "Full width", modifier = Modifier.padding(top = 16.dp, bottom = 4.dp, start = 16.dp, end = 16.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        ) {
            WarpButton(onClick = { /*TODO*/ }, buttonStyle = WarpButtonStyle.Primary, text = "Full width primary", modifier = Modifier
                .fillMaxWidth())
        }
    }
}


@Preview
@Composable
fun ButtonPreviews(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor).invoke {
        ButtonScreenContent()
    }
}