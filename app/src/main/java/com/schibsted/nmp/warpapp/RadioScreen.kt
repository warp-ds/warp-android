package com.schibsted.nmp.warpapp

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.schibsted.nmp.warp.components.HorizontalWarpRadioGroup
import com.schibsted.nmp.warp.components.VerticalWarpRadioGroup
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import com.schibsted.nmp.warpapp.ui.DetailsScaffold

@Composable
fun RadioScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "Buttons", onUp = onUp) {
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
        VerticalRadioGroupSample("Default", enabled = true, isError = false)

        VerticalRadioGroupSample("Disabled", enabled = false, isError = false)

        VerticalRadioGroupSample("Error", enabled = true, isError = true)

        HorizontalRadioGroupSample("Default", enabled = true, isError = false)

        HorizontalRadioGroupSample("Disabled", enabled = false, isError = false)

        HorizontalRadioGroupSample("Error", enabled = true, isError = true)
    }
}

@Composable
fun VerticalRadioGroupSample(title: String, enabled: Boolean, isError: Boolean) {
    val radioOptions = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    VerticalWarpRadioGroup(
        title = title,
        options = radioOptions,
        selectedOption = selectedOption,
        enabled = enabled,
        isError = isError,
        onOptionSelected = onOptionSelected,
        helpText = "Required"
    )
}

@Composable
fun HorizontalRadioGroupSample(title: String, enabled: Boolean, isError: Boolean) {
    val radioOptions = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    HorizontalWarpRadioGroup(
        title = title,
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        options = radioOptions,
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