package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpTextField
import com.schibsted.nmp.warp.components.ext.getTextFromIdOrString
import org.koin.java.KoinJavaComponent.inject


class WarpTextFieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    val theme: LegacyWarpTheme by inject(LegacyWarpTheme::class.java)

    var textFieldEnabled = true
        set(value) {
            field = value
            disposeComposition()
        }
    var readOnly = false
        set(value) {
            field = value
            disposeComposition()
        }
    var label = ""
        set(value) {
            field = value
            disposeComposition()
        }
    var optionalLabel = ""
        set(value) {
            field = value
            disposeComposition()
        }
    var placeholderText = ""
        set(value) {
            field = value
            disposeComposition()
        }
    var helpText = ""
        set(value) {
            field = value
            disposeComposition()
        }
    @DrawableRes
    var leadingIcon = 0
        set(value) {
            field = value
            disposeComposition()
        }
    var leadingIconContentDescr = ""
        set(value) {
            field = value
            disposeComposition()
        }
    @DrawableRes
    var trailingIcon = 0
        set(value) {
            field = value
            disposeComposition()
        }
    var trailingIconContentDescr = ""
        set(value) {
            field = value
            disposeComposition()
        }
    var isError = false
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
        theme {
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
}
