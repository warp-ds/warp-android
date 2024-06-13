package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.schibsted.nmp.warp.components.WarpCheckbox
import com.schibsted.nmp.warp.components.WarpCheckboxStyle
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import com.schibsted.nmp.warpapp.BrandTheme

@Composable
fun CheckboxScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "Checkboxes", onUp = onUp) {
        CheckboxScreenContent()
    }
}

@Composable
private fun CheckboxScreenContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(WarpTheme.dimensions.space1),
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(WarpTheme.dimensions.space2)
    ) {
        WarpCheckbox(text = "Neutral checkbox", onCheckedChange = { })
        WarpCheckbox(text = "Selected neutral checkbox", checked = true, onCheckedChange = { })
        WarpCheckbox(
            text = "Disabled checkbox",
            style = WarpCheckboxStyle.Disabled,
            onCheckedChange = { })
        WarpCheckbox(
            text = "Selected disabled checkbox",
            checked = true,
            style = WarpCheckboxStyle.Disabled,
            onCheckedChange = { })
        WarpCheckbox(
            text = "Negative checkbox",
            style = WarpCheckboxStyle.Negative,
            onCheckedChange = { })
        WarpCheckbox(
            text = "Selected negative checkbox",
            checked = true,
            style = WarpCheckboxStyle.Negative,
            onCheckedChange = { })
    }
}

@Preview
@Composable
fun CheckboxPreviews(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor).invoke {
        CheckboxScreenContent()
    }
}