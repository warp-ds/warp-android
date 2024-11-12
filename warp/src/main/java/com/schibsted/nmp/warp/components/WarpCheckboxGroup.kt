package com.schibsted.nmp.warp.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * A group of checkboxes in the warp design system.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/checkbox/)
 *
 * @param modifier The modifier to be applied to the checkbox group.
 * @param orientation The orientation of the checkbox group. Default is Orientation.Vertical
 * @param title The optional title of the checkbox group.
 * @param options The list of options to be displayed in the checkbox group.
 * @param selectedOptions The list of selected options. Default is null.
 * @param helpText The optional help text of the checkbox group.
 * @param enabled Set to false to disable the checkbox group. Default value is true.
 * @param isError Set to true if the checkbox group is in an invalid state. Default value is false.
 * @param onOptionsSelected A lambda that will be invoked when an option is selected. Returns a mutable list of selected options.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WarpCheckboxGroup(
    modifier: Modifier = Modifier,
    orientation: Orientation = Orientation.Vertical,
    title: String? = null,
    options: List<String>,
    selectedOptions: List<String>? = null,
    helpText: String? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    onOptionsSelected: (MutableList<String>) -> Unit
) {
    require(options.size > 1) { "CheckboxGroup must have more than 1 element" }
    val selectedOptionSet: MutableSet<String> = selectedOptions?.toMutableSet() ?: mutableSetOf()

    val verticalModifier =
        if (orientation == Orientation.Vertical) modifier.selectableGroup() else modifier

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        Column(
            modifier = verticalModifier,
            verticalArrangement = Arrangement.spacedBy(dimensions.space2)
        ) {
            title?.let {
                WarpText(
                    text = title,
                    style = WarpTextStyle.Title5,
                    modifier = Modifier.semantics { heading() })
            }
            if (orientation == Orientation.Horizontal) {
                Row(
                    modifier
                        .horizontalScroll(rememberScrollState())
                        .selectableGroup(),
                    horizontalArrangement = Arrangement.spacedBy(dimensions.space1)
                ) {
                    createCheckboxes(
                        options,
                        selectedOptionSet,
                        onOptionsSelected,
                        selectedOptions,
                        enabled,
                        isError
                    )
                }
            } else {
                createCheckboxes(
                    options,
                    selectedOptionSet,
                    onOptionsSelected,
                    selectedOptions,
                    enabled,
                    isError
                )
            }
            val helpTextColor = when {
                isError -> colors.text.negative
                !enabled -> colors.text.disabled
                else -> colors.text.subtle
            }
            helpText?.let {
                WarpText(
                    modifier = Modifier.padding(vertical = dimensions.space025),
                    text = helpText,
                    style = WarpTextStyle.Detail,
                    color = helpTextColor
                )
            }
        }
    }
}

@Composable
private fun createCheckboxes(
    options: List<String>,
    selectedOptionSet: MutableSet<String>,
    onOptionSelected: (MutableList<String>) -> Unit,
    selectedOption: List<String>?,
    enabled: Boolean,
    isError: Boolean
) {
    options.forEach { text ->
        WarpCheckbox(
            label = text,
            onCheckedChange = { isChecked ->
                selectedOptionSet.apply {
                    if (isChecked) add(text) else remove(text)
                }
                onOptionSelected(selectedOptionSet.toMutableList())
            },
            checked = selectedOption?.contains(text) ?: false,
            enabled = enabled,
            isError = isError
        )
    }
}