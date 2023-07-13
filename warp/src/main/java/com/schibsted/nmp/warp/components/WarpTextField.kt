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
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.LayoutDirection
import com.schibsted.nmp.warp.theme.WarpBrandedTheme
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider

@Composable
fun WarpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.outlinedShape
) {
    val colors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = WarpTheme.colors.components.textField.text,
        containerColor = WarpTheme.colors.components.textField.background,
        cursorColor = WarpTheme.colors.components.textField.caret,
        errorCursorColor = WarpTheme.colors.components.textField.errorBorder, // TODO: should this be this color?

        focusedBorderColor = WarpTheme.colors.components.textField.activeBorder.ifTrueOtherwise(!readOnly) { Color.Transparent },
        disabledBorderColor = WarpTheme.colors.components.textField.disabledBorder.ifTrueOtherwise(!readOnly) { Color.Transparent },
        errorBorderColor = WarpTheme.colors.components.textField.errorBorder.ifTrueOtherwise(!readOnly) { Color.Transparent },
        errorLabelColor = WarpTheme.colors.components.textField.errorText,
        errorLeadingIconColor = WarpTheme.colors.components.textField.errorIconColor,
        disabledTextColor = WarpTheme.colors.components.textField.disabledText,
        disabledLabelColor = WarpTheme.colors.components.textField.disabledText,
        unfocusedBorderColor = WarpTheme.colors.components.textField.border.ifTrueOtherwise(!readOnly) { Color.Transparent},
        unfocusedLabelColor = WarpTheme.colors.components.textField.text,
    )

    // TODO: This is unfinished, need feedback from designers on where to place the labels

    Column(modifier) {

        label?.let {
            Box(modifier.padding(start = TextFieldDefaults.textFieldWithLabelPadding().calculateLeftPadding(LayoutDirection.Ltr))) {
                label.invoke()
            }
        }

        OutlinedTextField(
            value,
            onValueChange,
            modifier,
            enabled,
            readOnly,
            textStyle,
            null, // replace this with label if we want to use the built in one
            placeholder,
            leadingIcon,
            trailingIcon,
            supportingText,
            isError,
            visualTransformation,
            keyboardOptions,
            keyboardActions,
            singleLine,
            maxLines,
            interactionSource,
            shape,
            colors)

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