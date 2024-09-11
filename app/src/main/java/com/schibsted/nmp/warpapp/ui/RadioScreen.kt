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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.schibsted.nmp.warp.components.WarpRadio
import com.schibsted.nmp.warp.components.WarpRadioGroup
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import com.schibsted.nmp.warpapp.BrandTheme

@Composable
fun RadioScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "Radio", onUp = onUp) {
        RadioScreenContent()
    }
}

@Composable
private fun RadioScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(dimensions.space2),
        verticalArrangement = Arrangement.spacedBy(dimensions.space2)
    ) {
        WarpRadio(label = "Single Radio on", selected = true, enabled = true)
        WarpRadio(label = "Single Radio off", selected = false, enabled = true)
        WarpRadio(label = "Radio with extraText", selected = true, enabled = true, extraText = "(Extra)")
        WarpRadio(label = "Radio with icon", selected = true, enabled = true, slot = {
            Icon(
                Icons.Filled.AccountCircle,
                contentDescription = "Content description for the leading icon",
                tint = colors.icon.disabled
            )
        })

        RadioGroupSample("Default", enabled = true, isError = false, orientation = Orientation.Vertical)
        RadioGroupSample("Disabled", enabled = false, isError = false, orientation = Orientation.Vertical)
        RadioGroupSample("Error", enabled = true, isError = true, orientation = Orientation.Vertical)
        RadioGroupSample("Default", enabled = true, isError = false, orientation = Orientation.Horizontal)
        RadioGroupSample("Disabled", enabled = false, isError = false, orientation = Orientation.Horizontal)
        RadioGroupSample("Error", enabled = true, isError = true, orientation = Orientation.Horizontal)
    }
}

@Composable
fun RadioGroupSample(title: String, enabled: Boolean, isError: Boolean, orientation: Orientation) {
    val radioOptions = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    WarpRadioGroup(
        title = title,
        options = radioOptions,
        orientation = orientation,
        selectedOption = selectedOption,
        enabled = enabled,
        isError = isError,
        onOptionSelected = onOptionSelected,
        helpText = "Required"
    )
}

@Preview
@Composable
fun RadioPreviews(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor).invoke {
        RadioScreenContent()
    }
}
