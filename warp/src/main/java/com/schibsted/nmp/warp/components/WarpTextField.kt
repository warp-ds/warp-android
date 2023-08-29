@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warp.components

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.ext.getTextFromIdOrString
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalDimensions
import com.schibsted.nmp.warp.theme.LocalTypography
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

class WarpTextFieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    private var textFieldEnabled = true
        set(value) {
            field = value
            disposeComposition()
        }
    private var readOnly = false
        set(value) {
            field = value
            disposeComposition()
        }
    private var label = ""
        set(value) {
            field = value
            disposeComposition()
        }
    private var optionalLabel = ""
        set(value) {
            field = value
            disposeComposition()
        }
    private var placeholderText = ""
        set(value) {
            field = value
            disposeComposition()
        }
    private var helpText = ""
        set(value) {
            field = value
            disposeComposition()
        }
    private var leadingIcon = 0
        set(value) {
            field = value
            disposeComposition()
        }
    private var leadingIconContentDescr = ""
        set(value) {
            field = value
            disposeComposition()
        }
    private var trailingIcon = 0
        set(value) {
            field = value
            disposeComposition()
        }
    private var trailingIconContentDescr = ""
        set(value) {
            field = value
            disposeComposition()
        }
    private var isError = false
        set(value) {
            field = value
            disposeComposition()
        }

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpTextField) {
            textFieldEnabled = getBoolean(R.styleable.WarpTextField_textFieldEnabled, true)
            readOnly = getBoolean(R.styleable.WarpTextField_readOnly, false)
            label = getTextFromIdOrString(R.styleable.WarpTextField_label, context)
            optionalLabel = getTextFromIdOrString(R.styleable.WarpTextField_optionalLabel, context)
            placeholderText =
                getTextFromIdOrString(R.styleable.WarpTextField_placeholderText, context)
            helpText = getTextFromIdOrString(R.styleable.WarpTextField_helpText, context)
            leadingIcon = getResourceId(R.styleable.WarpTextField_leadingIcon, 0)
            leadingIconContentDescr =
                getTextFromIdOrString(R.styleable.WarpTextField_leadingIconContentDescr, context)
            trailingIcon = getResourceId(R.styleable.WarpTextField_trailingIcon, 0)
            trailingIconContentDescr =
                getTextFromIdOrString(R.styleable.WarpTextField_trailingIconContentDescr, context)
            isError = getBoolean(R.styleable.WarpTextField_isError, false)
        }
    }

    @Composable
    override fun Content() {

        var value by rememberSaveable { mutableStateOf("") }

        WarpTextField(
            value = value,
            onValueChange = { value = it },
            enabled = textFieldEnabled,
            readOnly = readOnly,
            label = label,
            optionalLabel = optionalLabel,
            placeholderText = placeholderText,
            helpText = helpText,
            leadingIcon = if (leadingIcon != 0) ({
                Icon(
                    painterResource(id = leadingIcon),
                    leadingIconContentDescr
                )
            }) else null,
            trailingIcon = if (trailingIcon != 0) {
                { Icon(painterResource(id = trailingIcon), trailingIconContentDescr) }
            } else null,
            isError = isError
        )
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
    WarpTextField(
        value = "",
        label = "Hello",
        optionalLabel = "Hey hey",
        helpText = "Help text",
        placeholderText = "Hint",
        onValueChange = {}
    )
}
