@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warp.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.typography

/**
 * A Textfield in the warp design system.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/textfield/)
 * @param value The text entered by user
 * @param onValueChange lambda to be invoked when text is changed
 * @param modifier Modifier for the textfield. Default value is Modifier
 * @param enabled set to false to disable the textfield. default value is true
 * @param readOnly set to true to disable the textfield but show the value. default value is false
 * @param label The label to be displayed on the textfield
 * @param optionalLabel The optional label to be displayed on the textfield horizontally next to the label
 * @param placeholderText The hint text to be displayed on the textfield
 * @param helpText The help text to be displayed below the textfield
 * @param prefixText The text to be displayed on the left side of the textfield before the input text
 * @param suffixText The text to be displayed on the right side of the textfield after the input text
 * @param leadingIcon The icon to be displayed on the left side of the textfield
 * @param trailingIcon The icon to be displayed on the right side of the textfield
 * @param isError set to true to show the error state of the textfield. default value is false
 * @param visualTransformation The visual transformation to be applied on the textfield. default value is VisualTransformation.None
 * @param keyboardOptions The keyboard options to be applied on the textfield. default value is KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
 * @param keyboardActions The keyboard actions to be applied on the textfield. default value is KeyboardActions.Default
 * @param singleLine set to true to limit the textfield to single line. default value is false
 * @param maxLines The maximum number of lines to be displayed on the textfield. default value is Int.MAX_VALUE
 * @param interactionSource The interaction source to be applied on the textfield. default value is MutableInteractionSource()
 */
@Composable
fun WarpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    optionalLabel: String? = null,
    placeholderText: String? = null,
    helpText: String? = null,
    prefixText: String? = null,
    suffixText: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    textStyle: TextStyle = typography.body,
) {
    val textFieldColors = OutlinedTextFieldDefaults.colors(
        unfocusedTextColor = colors.text.default,
        unfocusedBorderColor = colors.border.default.ifTrueOtherwise(!readOnly) { Color.Transparent },
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

        disabledBorderColor = colors.border.disabled.ifTrueOtherwise(!readOnly) { Color.Transparent },
        disabledContainerColor = colors.background.disabledSubtle,
        disabledTextColor = colors.text.disabled,
        disabledLabelColor = colors.text.disabled,
        disabledPrefixColor = colors.text.disabled,
        disabledSuffixColor = colors.text.disabled,
        disabledSupportingTextColor = colors.text.disabled,
        disabledLeadingIconColor = colors.icon.disabled,
        disabledTrailingIconColor = colors.icon.disabled,
        disabledPlaceholderColor = colors.text.disabled
    )

    val placeholder: @Composable () -> Unit = {
        placeholderText?.let {
            WarpText(
                text = it,
                style = WarpTextStyle.Body,
                color = colors.text.placeholder
            )
        }
    }

    val prefix: @Composable () -> Unit = {
        prefixText?.let {
            WarpText(
                text = it,
                style = WarpTextStyle.Body,
                color = colors.text.default
            )
        }
    }

    val suffix: @Composable () -> Unit = {
        suffixText?.let {
            WarpText(
                text = it,
                style = WarpTextStyle.Body,
                color = colors.text.default
            )
        }
    }

    Column(modifier) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(vertical = dimensions.space025, horizontal = 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            label?.let {
                WarpText(text = label, style = WarpTextStyle.Title5, color = colors.text.default)
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
            isError -> colors.text.negative
            else -> colors.text.default
        }
        val textColor = rememberUpdatedState(textColorValue).value
        //Help text color should remain the same if isError is true and the textfield is focused
        val helpTextColor = rememberUpdatedState(if (isError) colors.text.negative else if (!enabled) colors.text.disabled else colors.text.default).value
        val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))
        val cursorColor = rememberUpdatedState(if (isError) colors.icon.negative else colors.icon.default).value
        val cursorHandleColor = rememberUpdatedState(if (isError) colors.icon.negative else colors.border.focus).value

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
                modifier = modifier,
                enabled = enabled,
                readOnly = readOnly,
                textStyle = mergedTextStyle,
                cursorBrush = SolidColor(cursorColor),
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                minLines = 1,
                interactionSource = interactionSource,
                decorationBox = @Composable { innerTextField ->
                    OutlinedTextFieldDefaults.DecorationBox(
                        value = value,
                        visualTransformation = visualTransformation,
                        innerTextField = innerTextField,
                        placeholder = placeholder,
                        label = null,
                        leadingIcon = leadingIcon,
                        trailingIcon = trailingIcon,
                        prefix = prefix,
                        suffix = suffix,
                        supportingText = null,
                        singleLine = singleLine,
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = textFieldColors,
                        contentPadding = PaddingValues(start = dimensions.space2.ifTrueOtherwise(!readOnly) { 0.dp }, top = dimensions.space2, end = dimensions.space2, bottom = dimensions.space2),
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
                modifier = Modifier.padding(vertical = dimensions.space025, horizontal = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                WarpText(text = helpText, style = WarpTextStyle.Detail, color = helpTextColor)
            }
        }
    }
}

fun <T> T.ifTrueOtherwise(predicate: Boolean, block: () -> T): T = if (predicate) {
    this
} else {
    block()
}


@Preview
@Composable
fun WarpTextFieldPreview() {
    WarpTextField(
        value = "",
        label = "Hello",
        optionalLabel = "Hey hey",
        helpText = "Help text",
        placeholderText = "Hint",
        onValueChange = {}
    )
}
