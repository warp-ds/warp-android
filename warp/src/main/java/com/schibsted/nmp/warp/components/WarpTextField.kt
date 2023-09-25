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
        val labelColor = colors.components.label.text
        val placeholderColor = colors.components.textField.hintText
        val colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colors.components.textField.text,
            containerColor = colors.components.textField.background,
            cursorColor = colors.components.textField.caret,
            errorCursorColor = colors.components.textField.errorBorder,
            errorLabelColor = colors.components.textField.errorText,
            focusedBorderColor = colors.components.textField.activeBorder.ifTrueOtherwise(!readOnly) { Color.Transparent },
            focusedLeadingIconColor = colors.components.textField.iconTint,
            unfocusedLeadingIconColor = colors.components.textField.iconTint,
            focusedTrailingIconColor = colors.components.textField.iconTint,
            unfocusedTrailingIconColor = colors.components.textField.iconTint,
            disabledBorderColor = colors.components.textField.disabledBorder.ifTrueOtherwise(!readOnly) { Color.Transparent },
            errorBorderColor = colors.components.textField.errorBorder.ifTrueOtherwise(!readOnly) { Color.Transparent },
            errorLeadingIconColor = colors.components.textField.errorIconColor,
            disabledTextColor = colors.components.textField.disabledText,
            disabledLabelColor = colors.components.textField.disabledText,
            unfocusedBorderColor = colors.components.textField.border.ifTrueOtherwise(!readOnly) { Color.Transparent },
            unfocusedLabelColor = colors.components.textField.text,
            errorSupportingTextColor = colors.components.textField.errorText,
            focusedSupportingTextColor = colors.components.textField.text,
            unfocusedSupportingTextColor = colors.components.textField.text

        )

        Column(modifier) {

            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(vertical = dimensions.space025.dp, horizontal = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                WarpText(text = label, style = WarpTextStyle.Title5, color = labelColor)
                optionalLabel?.let {
                    WarpText(
                        text = label,
                        style = WarpTextStyle.Detail,
                        color = labelColor,
                        modifier = Modifier.padding(horizontal = dimensions.space1.dp)
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
