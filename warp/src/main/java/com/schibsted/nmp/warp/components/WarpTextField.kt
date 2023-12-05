@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warp.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalDimensions
import com.schibsted.nmp.warp.theme.LocalTypography
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
 * @param leadingIcon The icon to be displayed on the left side of the textfield
 * @param trailingIcon The icon to be displayed on the right side of the textfield
 * @param isError set to true to show the error state of the textfield. default value is false
 * @param visualTransformation The visual transformation to be applied on the textfield. default value is VisualTransformation.None
 * @param keyboardOptions The keyboard options to be applied on the textfield. default value is KeyboardOptions.Default
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
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String,
    optionalLabel: String? = null,
    placeholderText: String? = null,
    helpText: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        LocalDimensions provides dimensions
    ) {
        val labelColor = colors.text.default
        val placeholderColor = colors.text.placeholder
        val colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colors.text.default,
            containerColor = colors.background.default,
            cursorColor = colors.icon.default,
            errorCursorColor = colors.border.negative,
            errorLabelColor = colors.text.negative,
            focusedBorderColor = colors.border.selected.ifTrueOtherwise(!readOnly) { Color.Transparent },
            focusedLeadingIconColor = colors.icon.default,
            unfocusedLeadingIconColor = colors.icon.default,
            focusedTrailingIconColor = colors.icon.default,
            unfocusedTrailingIconColor = colors.icon.default,
            disabledBorderColor = colors.border.disabled.ifTrueOtherwise(!readOnly) { Color.Transparent },
            errorBorderColor = colors.border.negative.ifTrueOtherwise(!readOnly) { Color.Transparent },
            errorLeadingIconColor = colors.icon.negative,
            disabledTextColor = colors.text.disabled,
            disabledLabelColor = colors.text.disabled,
            unfocusedBorderColor = colors.border.default.ifTrueOtherwise(!readOnly) { Color.Transparent },
            unfocusedLabelColor = colors.text.default,
            errorSupportingTextColor = colors.text.negative,
            focusedSupportingTextColor = colors.text.default,
            unfocusedSupportingTextColor = colors.text.default

        )

        Column(modifier) {

            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(vertical = dimensions.space025, horizontal = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                WarpText(text = label, style = WarpTextStyle.Title5, color = labelColor)
                optionalLabel?.let {
                    WarpText(
                        text = it,
                        style = WarpTextStyle.Detail,
                        color = labelColor,
                        modifier = Modifier.padding(horizontal = dimensions.space1)
                    )
                }
            }

            val placeholder: @Composable () -> Unit = {
                placeholderText?.let {
                    WarpText(
                        text = it,
                        style = WarpTextStyle.Body,
                        color = placeholderColor
                    )
                }
            }

            val helpLabel: @Composable () -> Unit = {
                helpText?.let {
                    Text(
                        text = it
                    )
                }
            }

            OutlinedTextField(
                value,
                onValueChange,
                modifier,
                enabled,
                readOnly,
                textStyle = typography.body,
                null,
                placeholder,
                leadingIcon,
                trailingIcon,
                helpLabel,
                isError,
                visualTransformation,
                keyboardOptions,
                keyboardActions,
                singleLine,
                maxLines,
                interactionSource,
                shape = TextFieldDefaults.outlinedShape,
                colors
            )
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
