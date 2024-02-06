package com.schibsted.nmp.warpapp.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
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
            WarpText(modifier = Modifier.padding(16.dp), text = "Neutral")
        }
        val iconTest : @Composable () -> Unit = {
            Icon(
                Icons.Filled.ShoppingCart,
                contentDescription = "Content description for the leading icon",
                tint = colors.icon.primary
            )
        }
        WarpBox(
            boxStyle = WarpBoxStyle.INFO,
            heading = "Hello Box! ",
            icon = iconTest,
            text = "This is a box with an icon and a heading thing. This is a box with an icon and a heading. This is a box with an icon and a heading",
            link = "This is a link",
            linkAction = { Log.d("Meow", "Link click") },
            buttonText = "This is a button",
            buttonAction = { Log.d("Meow", "Button click") }
        )
        WarpBox(
            boxStyle = WarpBoxStyle.BORDERED,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        ) {
            WarpText(modifier = Modifier.padding(16.dp), text = "Bordered")
        }
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