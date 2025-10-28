package com.schibsted.nmp.warp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * A Dropdown list component in the warp design system.
 * @param modifier Modifier for the dropdown list. Default value is Modifier
 * @param value The choice entered by user
 * @param onValueChange lambda to be invoked when choice is changed
 * @param label The label to be displayed above the dropdown component
 * @param optionalLabel The optional label to be displayed next to the label
 * @param enabled set to false to disable the dropdown list. Default value is true
 * @param placeholderText The hint text to be displayed on the dropdown list
 * @param helpText The help text to be displayed below the dropdown list
 * @param items The list of items to be displayed in the dropdown list
 * @param readOnly set to true to disable the dropdown list but show the value. Default value is false
 * @param expanded set to true to expand the dropdown list. Default value is false
 * @param isError set to true to show the error state
 */
@Composable
fun WarpSelect(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    optionalLabel: String? = null,
    enabled: Boolean = true,
    placeholderText: String? = null,
    helpText: String? = null,
    items: List<String>? = null,
    readOnly: Boolean = false,
    expanded: Boolean = false,
    isError: Boolean = false
) {
    items?.size?.let { require(it > 0) { "Select items list cannot be empty" } }
    var expanded by remember { mutableStateOf(expanded) }
    val onClick = Modifier.clickable {
        expanded = !expanded
    }
    val selectModifier = if (enabled && !readOnly) onClick else Modifier

    Box(modifier = modifier) {
        WarpSelectView(
            value = value,
            onValueChange = onValueChange,
            modifier = selectModifier,
            label = label,
            optionalLabel = optionalLabel,
            placeholderText = placeholderText,
            helpText = helpText,
            enabled = enabled,
            readOnly = readOnly,
            isError = isError
        )
        DropdownMenu(
            expanded = expanded,
            scrollState = rememberScrollState(),
            onDismissRequest = { expanded = false },
            containerColor = colors.background.default
        ) {
            items?.forEach {
                DropdownMenuItem(
                    text = { WarpText(it) },
                    onClick = {
                        expanded = false
                        onValueChange(it)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WarpSelectView(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String?,
    optionalLabel: String?,
    placeholderText: String?,
    helpText: String?,
    enabled: Boolean,
    readOnly: Boolean,
    isError: Boolean
) {
    val textFieldColors = warpSelectColors(readOnly, enabled)
    val interactionSource = remember { MutableInteractionSource() }

    val placeholder: @Composable () -> Unit = {
        placeholderText?.let {
            WarpText(
                text = it,
                style = WarpTextStyle.Body,
                color = colors.text.placeholder
            )
        }
    }

    Column(modifier) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top = dimensions.space025, bottom = dimensions.space05),
            verticalAlignment = Alignment.CenterVertically
        ) {
            label?.let {
                WarpText(
                    text = label,
                    style = WarpTextStyle.Title5,
                    color = colors.text.default
                )
                optionalLabel?.let {
                    WarpText(
                        text = it,
                        style = WarpTextStyle.Detail,
                        color = colors.text.default,
                        modifier = Modifier.padding(horizontal = dimensions.space1)
                    )
                }
            }
        }

        val focused by interactionSource.collectIsFocusedAsState()
        //Value text color should change to default if isError is true and the textfield is focused
        val textColorValue = when {
            !enabled -> colors.text.disabled
            focused -> colors.text.default
            else -> colors.text.default
        }
        val iconColorValue = if (!enabled) colors.icon.disabled else colors.icon.default
        val iconColor = rememberUpdatedState(iconColorValue).value
        val textColor = rememberUpdatedState(textColorValue).value
        //Help text color should remain the same if isError is true and the textfield is focused
        val helpTextColor =
            rememberUpdatedState(if (isError) colors.text.negative else if (!enabled) colors.text.disabled else colors.text.default).value
        val mergedTextStyle =
            getTextStyle(style = WarpTextStyle.Body).merge(TextStyle(color = textColor))
        val cursorColor =
            rememberUpdatedState(if (isError) colors.icon.negative else colors.icon.default).value
        val cursorHandleColor =
            rememberUpdatedState(if (isError) colors.icon.negative else colors.border.focus).value

        val customTextSelectionColors = TextSelectionColors(
            handleColor = cursorHandleColor,
            backgroundColor = cursorHandleColor,
        )

        CompositionLocalProvider(
            LocalTextSelectionColors provides customTextSelectionColors
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .height(dimensions.components.textFieldHeight)
                    .fillMaxWidth(),
                enabled = false,
                readOnly = readOnly,
                textStyle = mergedTextStyle,
                cursorBrush = SolidColor(cursorColor),
                visualTransformation = VisualTransformation.None,
                singleLine = true,
                interactionSource = interactionSource,
                decorationBox = @Composable { innerTextField ->
                    OutlinedTextFieldDefaults.DecorationBox(
                        value = value,
                        visualTransformation = VisualTransformation.None,
                        innerTextField = innerTextField,
                        placeholder = placeholder,
                        label = null,
                        trailingIcon = {
                            if (!readOnly) {
                                WarpIcon(
                                    icon = icons.chevronDown,
                                    color = iconColor,
                                    size = dimensions.icon.small
                                )
                            } else {
                                null
                            }
                        },
                        supportingText = null,
                        singleLine = true,
                        enabled = true,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = textFieldColors,
                        contentPadding = PaddingValues(
                            start = dimensions.space1.ifTrueOtherwise(!readOnly) { 0.dp },
                            end = dimensions.space1
                        ),
                        container = {
                            OutlinedTextFieldDefaults.ContainerBox(
                                enabled,
                                isError,
                                interactionSource,
                                textFieldColors,
                                OutlinedTextFieldDefaults.shape
                            )
                        }
                    )
                }
            )
        }
        helpText?.let {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(
                    top = dimensions.space05,
                    bottom = dimensions.space025
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                WarpText(
                    text = helpText,
                    style = WarpTextStyle.Detail,
                    color = helpTextColor
                )
            }
        }
    }
}

@Composable
fun warpSelectColors(readOnly: Boolean = false, enabled: Boolean = true) =
    OutlinedTextFieldDefaults.colors(
        unfocusedTextColor = colors.text.default,
        unfocusedBorderColor = colors.border.strong.ifTrueOtherwise(!readOnly) { Color.Transparent },
        unfocusedLabelColor = colors.text.default,
        unfocusedLeadingIconColor = colors.icon.default,
        unfocusedTrailingIconColor = colors.icon.default,
        unfocusedSupportingTextColor = colors.text.default,
        unfocusedPlaceholderColor = colors.text.placeholder,
        unfocusedPrefixColor = colors.text.default,
        unfocusedSuffixColor = colors.text.default,
        unfocusedContainerColor = colors.background.default,

        focusedTextColor = colors.text.default,
        focusedBorderColor = colors.border.focus.ifTrueOtherwise(!readOnly) { Color.Transparent },
        focusedLeadingIconColor = colors.icon.default,
        focusedSupportingTextColor = colors.text.default,
        focusedTrailingIconColor = colors.icon.default,
        focusedPlaceholderColor = colors.text.placeholder,
        focusedPrefixColor = colors.text.default,
        focusedSuffixColor = colors.text.default,
        focusedContainerColor = colors.background.default,

        cursorColor = colors.icon.default,
        errorTextColor = colors.text.negative,
        errorCursorColor = colors.border.negative,
        errorLabelColor = colors.text.negative,
        errorBorderColor = colors.border.negative.ifTrueOtherwise(!readOnly) { Color.Transparent },
        errorLeadingIconColor = colors.icon.negative,
        errorSupportingTextColor = colors.text.negative,
        errorPrefixColor = colors.text.negative,
        errorSuffixColor = colors.text.negative,
        errorTrailingIconColor = colors.icon.negative,
        errorContainerColor = colors.background.default,

        disabledBorderColor = colors.border.disabled.ifTrueOtherwise(!readOnly && !enabled) { Color.Transparent },
        disabledContainerColor = if (!enabled) colors.background.disabledSubtle else colors.background.default,
        disabledTextColor = if (!enabled) colors.text.disabled else colors.text.default,
        disabledLabelColor = if (!enabled) colors.text.disabled else colors.text.default,
        disabledPrefixColor = if (!enabled) colors.text.disabled else colors.text.default,
        disabledSuffixColor = if (!enabled) colors.text.disabled else colors.text.default,
        disabledSupportingTextColor = if (!enabled) colors.text.disabled else colors.text.default,
        disabledLeadingIconColor = if (!enabled) colors.icon.disabled else colors.text.default,
        disabledTrailingIconColor = if (!enabled) colors.icon.disabled else colors.text.default,
        disabledPlaceholderColor = if (!enabled) colors.text.disabled else colors.text.default
    )