package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpBox
import com.schibsted.nmp.warp.components.WarpBoxStyle
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import com.schibsted.nmp.warpapp.BrandTheme

@Composable
fun BoxScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpBox",
        onUp = onUp
    ) {
        BoxScreenContent()
    }
}

@Composable
private fun BoxScreenContent() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        WarpBox(
            boxStyle = WarpBoxStyle.NEUTRAL,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        ) {
            Text(modifier = Modifier.padding(16.dp), text = "Neutral")
        }
        WarpBox(
            boxStyle = WarpBoxStyle.INFO,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        ) {
            Text(modifier = Modifier.padding(16.dp), text = "Info")
        }
        WarpBox(
            boxStyle = WarpBoxStyle.BORDERED,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        ) {
            Text(modifier = Modifier.padding(16.dp), text = "Bordered")
        }
    }
}

@Preview
@Composable
internal fun PreviewBoxScreen(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor).getTheme {
        BoxScreenContent()
    }
}