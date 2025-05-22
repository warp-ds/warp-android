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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
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
        verticalArrangement = Arrangement.spacedBy(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(WarpTheme.dimensions.space2)
    ) {
        var isNeutralChecked by remember { mutableStateOf(false) }
        WarpCheckbox(
            checked = isNeutralChecked,
            label = "Neutral checkbox",
            onCheckedChange = { isNeutralChecked = it }
        )
        var isSubTextChecked by remember { mutableStateOf(false) }
        WarpCheckbox(
            checked = isSubTextChecked,
            label = "With subText",
            onCheckedChange = { isSubTextChecked = it },
            extraText = "So extra"
        )
        var isExtraTextWithIconChecked by remember { mutableStateOf(false) }
        WarpCheckbox(
            checked = isExtraTextWithIconChecked,
            label = "With extra text & icon",
            onCheckedChange = { isExtraTextWithIconChecked = it },
            extraText = "So extra",
            slot = {
                Icon(
                    Icons.Filled.AccountCircle,
                    contentDescription = "Content description for the leading icon",
                    tint = colors.icon.disabled
                )
            }
        )
        var isIconOnlyChecked by remember { mutableStateOf(false) }
        WarpCheckbox(
            checked = isIconOnlyChecked,
            label = "With icon",
            onCheckedChange = { isIconOnlyChecked = it },
            slot = {
                Icon(
                    Icons.Filled.AccountCircle,
                    contentDescription = "Content description for the leading icon",
                    tint = colors.icon.disabled
                )
            }
        )
        var isSelectedNeutralChecked by remember { mutableStateOf(true) }
        WarpCheckbox(
            checked = isSelectedNeutralChecked,
            label = "Selected neutral checkbox",
            onCheckedChange = { isSelectedNeutralChecked = it })
        var isDisabledChecked by remember { mutableStateOf(false) }
        WarpCheckbox(
            checked = isDisabledChecked,
            label = "Disabled checkbox",
            style = WarpCheckboxStyle.Disabled,
            onCheckedChange = { isDisabledChecked = it })
        var isSelectedDisabledChecked by remember { mutableStateOf(true) }
        WarpCheckbox(
            label = "Selected disabled checkbox",
            checked = isSelectedDisabledChecked,
            style = WarpCheckboxStyle.Disabled,
            onCheckedChange = { isSelectedDisabledChecked = it })
        var isNegativeChecked by remember { mutableStateOf(false) }
        WarpCheckbox(
            checked = isNegativeChecked,
            label = "Negative checkbox",
            style = WarpCheckboxStyle.Negative,
            onCheckedChange = { isNegativeChecked = it })
        var isSelectedNegativeChecked by remember { mutableStateOf(true) }
        WarpCheckbox(
            label = "Selected negative checkbox",
            checked = isSelectedNegativeChecked,
            style = WarpCheckboxStyle.Negative,
            onCheckedChange = { isSelectedNegativeChecked = it })
        val focus = remember { FocusRequester() }
        WarpCheckbox(
            modifier = Modifier.focusRequester(focus),
            label = "Focused checkbox",
            checked = isSelectedNegativeChecked,
            style = WarpCheckboxStyle.Default,
            onCheckedChange = { isSelectedNegativeChecked = it
                focus.requestFocus() })
        WarpText(text = "Checkbox group", style = WarpTextStyle.Title3)

        val checkboxOptions = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
        var preSelectedOptions by remember { mutableStateOf(listOf("Option 1", "Option 3")) }
        WarpCheckboxGroup(
            title = "Vertical",
            helpText = "Help text",
            orientation = Orientation.Vertical,
            options = checkboxOptions,
            selectedOptions = preSelectedOptions,
            onOptionsSelected = { preSelectedOptions = it },
            isError = false
        )
        WarpCheckboxGroup(
            title = "Horizontal",
            helpText = "Help me",
            orientation = Orientation.Horizontal,
            options = checkboxOptions,
            selectedOptions = preSelectedOptions,
            onOptionsSelected = { preSelectedOptions = it },
            isError = false
        )
        WarpText(text = "Checkbox without label", style = WarpTextStyle.Title3)
        var isCheckboxWithoutLabelChecked by remember { mutableStateOf(false) }
        WarpCheckbox(
            checked = isCheckboxWithoutLabelChecked,
            onCheckedChange = { isCheckboxWithoutLabelChecked = it })
        var isCheckboxWithoutLabelSelected by remember { mutableStateOf(true) }
        WarpCheckbox(
            onCheckedChange = { isCheckboxWithoutLabelSelected = it },
            checked = isCheckboxWithoutLabelSelected
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