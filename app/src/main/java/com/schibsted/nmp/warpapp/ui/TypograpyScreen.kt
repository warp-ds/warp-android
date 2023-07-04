package com.schibsted.nmp.warpapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.schibsted.nmp.warpapp.BrandTheme

@Composable
fun TypographyScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "Typography", onUp = onUp) {

    }
}

@Preview
@Composable
internal fun PreviewTypographyScreen(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor) {
        TypographyScreen {

        }
    }
}

