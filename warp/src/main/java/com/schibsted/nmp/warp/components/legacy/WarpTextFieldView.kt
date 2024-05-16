package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpTextField
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.components.ext.getTextFromIdOrString
import com.schibsted.nmp.warp.components.getTextStyle
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
    var optionalLabel : String? = null
        set(value) {
            field = value
            disposeComposition()
        }
    var placeholderText : String? = null
        set(value) {
            field = value
            disposeComposition()
        }
    var helpText : String? = null
        set(value) {
            field = value
            disposeComposition()
        }
    var prefixText : String? = null
        set(value) {
            field = value
            disposeComposition()
        }
    var suffixText : String? = null
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
    var leadingIconContentDescr : String? = null
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
    var trailingIconContentDescr : String? = null
        set(value) {
            field = value
            disposeComposition()
        }
    var isError = false
        set(value) {
            field = value
            disposeComposition()
        }
    var singleLine = false
        set(value) {
            field = value
            disposeComposition()
        }
    var maxLines = Int.MAX_VALUE
        set(value) {
            field = value
            disposeComposition()
        }

    var textStyle: WarpTextStyle = WarpTextStyle.Body
        set(value) {
            field = value
            disposeComposition()
        }

    private var stylesList = listOf(
        WarpTextStyle.Display,
        WarpTextStyle.Title1,
        WarpTextStyle.Title2,
        WarpTextStyle.Title3,
        WarpTextStyle.Title4,
        WarpTextStyle.Title5,
        WarpTextStyle.Title6,
        WarpTextStyle.Preamble,
        WarpTextStyle.Body,
        WarpTextStyle.BodyStrong,
        WarpTextStyle.Caption,
        WarpTextStyle.CaptionStrong,
        WarpTextStyle.Detail,
        WarpTextStyle.DetailStrong
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpTextField) {
            textFieldEnabled = getBoolean(R.styleable.WarpTextField_textFieldEnabled, true)
            readOnly = getBoolean(R.styleable.WarpTextField_readOnly, false)
            label = getTextFromIdOrString(R.styleable.WarpTextField_label, context) ?: ""
            optionalLabel = getTextFromIdOrString(R.styleable.WarpTextField_optionalLabel, context)
            placeholderText =
                getTextFromIdOrString(R.styleable.WarpTextField_placeholderText, context)
            helpText = getTextFromIdOrString(R.styleable.WarpTextField_helpText, context)
            suffixText = getTextFromIdOrString(R.styleable.WarpTextField_suffixText, context)
            prefixText = getTextFromIdOrString(R.styleable.WarpTextField_prefixText, context)
            leadingIcon = getResourceId(R.styleable.WarpTextField_leadingIcon, 0)
            leadingIconContentDescr =
                getTextFromIdOrString(R.styleable.WarpTextField_leadingIconContentDescr, context)
            trailingIcon = getResourceId(R.styleable.WarpTextField_trailingIcon, 0)
            trailingIconContentDescr =
                getTextFromIdOrString(R.styleable.WarpTextField_trailingIconContentDescr, context)
            isError = getBoolean(R.styleable.WarpTextField_isError, false)
            singleLine = getBoolean(R.styleable.WarpTextField_singleLine, false)
            maxLines = getInt(R.styleable.WarpTextField_maxLines, Int.MAX_VALUE)
            val styleInt = getInteger(R.styleable.WarpText_warpTextStyle, 8)
            textStyle = stylesList[styleInt]
        }
    }

    @Composable
    override fun Content() {
        val width = if (this.layoutParams.width == MATCH_PARENT) {
            Modifier.fillMaxWidth()
        } else Modifier
        val height = if (this.layoutParams.height == MATCH_PARENT) {
            Modifier.fillMaxHeight()
        } else Modifier

        val modifier = width.then(height)

        theme {
            var value by rememberSaveable { mutableStateOf("") }

            WarpTextField(
                value = value,
                onValueChange = { value = it },
                modifier = modifier,
                enabled = textFieldEnabled,
                readOnly = readOnly,
                label = label,
                optionalLabel = optionalLabel,
                placeholderText = placeholderText,
                helpText = helpText,
                prefixText = prefixText,
                suffixText = suffixText,
                leadingIcon = if (leadingIcon != 0) ({
                    Icon(
                        painterResource(id = leadingIcon),
                        leadingIconContentDescr,
                        tint = Color.Unspecified
                    )
                }) else null,
                trailingIcon = if (trailingIcon != 0) {
                    {
                        Icon(
                            painterResource(id = trailingIcon),
                            trailingIconContentDescr,
                            tint = Color.Unspecified
                        )
                    }
                } else null,
                isError = isError,
                singleLine = singleLine,
                maxLines = maxLines,
                textStyle = getTextStyle(style = textStyle),
            )
        }
    }
}
