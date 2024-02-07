package com.schibsted.nmp.warpapp.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpBox
import com.schibsted.nmp.warp.components.WarpBoxStyle
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
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
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        WarpBox(
            boxStyle = WarpBoxStyle.NEUTRAL,
            modifier = Modifier
                .padding(vertical = dimensions.space1)
                .fillMaxWidth()
        ) {
            WarpText(modifier = Modifier.padding(dimensions.space2), text = "Neutral")
        }
        val icon: @Composable () -> Unit = {
            Icon(
                Icons.Filled.AccountCircle,
                contentDescription = "Content description for the leading icon",
                tint = colors.icon.primary
            )
        }
        WarpBox(
            boxStyle = WarpBoxStyle.INFO,
            modifier = Modifier
                .padding(vertical = dimensions.space1)
                .fillMaxWidth()
        ) {
            WarpText(modifier = Modifier.padding(dimensions.space2), text = "Info")
        }
        WarpBox(
            boxStyle = WarpBoxStyle.BORDERED,
            modifier = Modifier
                .padding(vertical = dimensions.space1)
                .fillMaxWidth()
        ) {
            WarpText(modifier = Modifier.padding(dimensions.space2), text = "Bordered")
        }
        WarpBox(
            modifier = Modifier
                .padding(vertical = dimensions.space1)
                .fillMaxWidth(),
            heading = "Warp Box!",
            text = "Neutral box "
        )
        WarpBox(
            modifier = Modifier
                .padding(vertical = dimensions.space1)
                .fillMaxWidth(),
            boxStyle = WarpBoxStyle.BORDERED,
            text = "Bordered box with optional link",
            link = "This is a link",
            linkAction = { Log.d("Meow", "Link click") },
        )
        WarpBox(
            modifier = Modifier
                .padding(vertical = dimensions.space1)
                .fillMaxWidth(),
            heading = "Warp Box!",
            text = "Neutral box with optional heading and button",
            buttonText = "This is a button",
            buttonAction = { Log.d("Meow", "Button click") }
        )
        WarpBox(
            modifier = Modifier
                .padding(vertical = dimensions.space1)
                .fillMaxWidth(),
            boxStyle = WarpBoxStyle.INFO,
            heading = "Warp Box!",
            icon = icon,
            text = "Info box with optional heading and icon"
        )
        WarpBox(
            modifier = Modifier
                .padding(vertical = dimensions.space1)
                .fillMaxWidth(),
            boxStyle = WarpBoxStyle.INFO,
            heading = "Hello Box! ",
            icon = icon,
            text = "This is a box has all optional UI elements.",
            link = "This is a link",
            linkAction = { Log.d("Meow", "Link click") },
            buttonText = "This is a button",
            buttonAction = { Log.d("Meow", "Button click") }
        )
    }
}

@Preview
@Composable
internal fun PreviewBoxScreen(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor).invoke {
        BoxScreenContent()
    }
}