package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.schibsted.nmp.warp.components.WarpCheckbox
import com.schibsted.nmp.warp.components.WarpCheckboxGroup
import com.schibsted.nmp.warp.components.WarpCheckboxStyle
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.colors
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
        verticalArrangement = Arrangement.spacedBy(WarpTheme.dimensions.space2),
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(WarpTheme.dimensions.space2)
    ) {
        WarpCheckbox(
            label = "Neutral checkbox",
            onCheckedChange = { }
        )
        WarpCheckbox(
            label = "With subText",
            onCheckedChange = { },
            extraText = "So extra"
        )
        WarpCheckbox(
            label = "With extra text & icon",
            onCheckedChange = { },
            extraText = "So extra",
            slot = {
                Icon(
                    Icons.Filled.AccountCircle,
                    contentDescription = "Content description for the leading icon",
                    tint = colors.icon.disabled
                )
            }
        )
        WarpCheckbox(
            label = "With icon",
            onCheckedChange = { },
            slot = {
                Icon(
                    Icons.Filled.AccountCircle,
                    contentDescription = "Content description for the leading icon",
                    tint = colors.icon.disabled
                )
            }
        )
        WarpCheckbox(
            label = "Selected neutral checkbox",
            checked = true,
            onCheckedChange = { })
        WarpCheckbox(
            label = "Disabled checkbox",
            style = WarpCheckboxStyle.Disabled,
            onCheckedChange = { })
        WarpCheckbox(
            label = "Selected disabled checkbox",
            checked = true,
            style = WarpCheckboxStyle.Disabled,
            onCheckedChange = { })
        WarpCheckbox(
            label = "Negative checkbox",
            style = WarpCheckboxStyle.Negative,
            onCheckedChange = { })
        WarpCheckbox(
            label = "Selected negative checkbox",
            checked = true,
            style = WarpCheckboxStyle.Negative,
            onCheckedChange = { })
        WarpText(text = "Checkbox group", style = WarpTextStyle.Title3)

        val checkboxOptions = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
        val preSelectedOptions = listOf("Option 1", "Option 3")
        val onOptionsSelected = remember { mutableListOf(checkboxOptions) }
        WarpCheckboxGroup(
            title = "Vertical",
            helpText = "Help text",
            orientation = Orientation.Vertical,
            options = checkboxOptions,
            selectedOptions = preSelectedOptions,
            onOptionsSelected = { onOptionsSelected },
            isError = false
        )
        WarpCheckboxGroup(
            title = "Horizontal",
            helpText = "Help me",
            orientation = Orientation.Horizontal,
            options = checkboxOptions,
            selectedOptions = preSelectedOptions,
            onOptionsSelected = { onOptionsSelected },
            isError = false
        )
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