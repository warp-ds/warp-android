@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warp.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalDimensions
import com.schibsted.nmp.warp.theme.LocalTypography
import com.schibsted.nmp.warp.theme.WarpBrandedTheme
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.typography
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider

@Composable
fun WarpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = typography.body,
    label: String? = null,
    hintText: String? = null,
    helpText: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.outlinedShape
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        LocalDimensions provides dimensions
    ) {
        val labelColor = colors.components.label.text
        val hintColor = colors.components.textField.hintText
        val colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colors.components.textField.text,
            containerColor = colors.components.textField.background,
            cursorColor = colors.components.textField.caret,
            errorCursorColor = colors.components.textField.errorBorder,
            errorLabelColor = colors.components.textField.errorText,
            focusedBorderColor = colors.components.textField.activeBorder.ifTrueOtherwise(!readOnly) { Color.Transparent },
            focusedLeadingIconColor =  colors.components.textField.iconTint,
            unfocusedLeadingIconColor =  colors.components.textField.iconTint,
            focusedTrailingIconColor = colors.components.textField.iconTint,
            unfocusedTrailingIconColor = colors.components.textField.iconTint,
            disabledBorderColor = colors.components.textField.disabledBorder.ifTrueOtherwise(!readOnly) { Color.Transparent },
            errorBorderColor = colors.components.textField.errorBorder.ifTrueOtherwise(!readOnly) { Color.Transparent },
            errorLeadingIconColor = colors.components.textField.errorIconColor,
            disabledTextColor = colors.components.textField.disabledText,
            disabledLabelColor = colors.components.textField.disabledText,
            unfocusedBorderColor = colors.components.textField.border.ifTrueOtherwise(!readOnly) { Color.Transparent },
            unfocusedLabelColor = colors.components.textField.text,
            errorSupportingTextColor = colors.components.textField.errorText
        )


        Column(modifier) {

            label?.let {
                Box(Modifier.padding(vertical = dimensions.space025.dp)) {
                    WarpText(text = it, style = WarpTextStyle.Title5, color = labelColor)
                }
            }

            val hint: @Composable () -> Unit = {
                hintText?.let {
                    WarpText(
                        text = it,
                        style = WarpTextStyle.Body,
                        color = hintColor
                    )
                }
            }

            val helpLabel: @Composable () -> Unit = {
                helpText?.let {
                    WarpText(
                        text = it,
                        style = WarpTextStyle.Detail
                    )
                }
        }

            OutlinedTextField(
                value,
                onValueChange,
                modifier,
                enabled,
                readOnly,
                textStyle,
                null,
                hint,
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
                shape,
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
fun WarpTextFieldPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    WarpBrandedTheme(flavor = flavor, darkTheme = isSystemInDarkTheme()) {
        WarpTextField(
            "hello",
            {}
        )
    }
}