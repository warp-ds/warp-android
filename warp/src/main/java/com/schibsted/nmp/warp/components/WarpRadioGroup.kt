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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * A group of Radio buttons in the warp design system.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/radio/)
 *
 * @param modifier The modifier to be applied to the radio group.
 * @param orientation The orientation of the radio group. Default value is Vertical.
 * @param title The title of the radio group. Default value is null.
 * @param options The list of options for the radio group
 * @param selectedOption The currently selected option in the radio group
 * @param helpText An optional help text that will be displayed below the radio group. Null by default
 * @param enabled Set to false to disable the radio group. Default value is true
 * @param isError Set to true if the radio group is in an error state. Default value is false
 * @param onOptionSelected A function that will be invoked when an option in the radio group is selected. Make sure to change the state of currently selected option
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WarpRadioGroup(
    modifier: Modifier = Modifier,
    orientation: Orientation = Orientation.Vertical,
    title: String? = null,
    options: List<String>,
    selectedOption: String? = null,
    helpText: String? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    onOptionSelected: (String) -> Unit
) {
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        require(options.size > 1) { "RadioGroup must have more than 1 element" }
        val verticalModifier =
            if (orientation == Orientation.Vertical) modifier.selectableGroup() else modifier
        Column(
            verticalModifier,
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
                    createRadio(options, onOptionSelected, selectedOption, enabled, isError)

                }
            } else {
                createRadio(options, onOptionSelected, selectedOption, enabled, isError)
            }

            val helpTextColor = rememberUpdatedState(
                when {
                    isError -> colors.text.negative
                    !enabled -> colors.text.disabled
                    else -> colors.text.subtle
                }
            )
            helpText?.let {
                WarpText(
                    modifier = Modifier.padding(vertical = dimensions.space025),
                    text = helpText,
                    style = WarpTextStyle.Detail,
                    color = helpTextColor.value
                )
            }
        }
    }
}

@Composable
private fun createRadio(
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    selectedOption: String?,
    enabled: Boolean,
    isError: Boolean
) {
    options.forEach { text ->
        WarpRadio(
            label = text,
            onClick = { onOptionSelected(text) },
            selected = (text == selectedOption),
            enabled = enabled,
            isError = isError,
        )
    }
}