package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.ext.getTextFromIdOrString
import org.koin.java.KoinJavaComponent.inject


/**
 * View class version of the composable WarpButton intended for use in legacy xml layouts.
 * Can be declared in xml layouts as any other view component.
 * Following custom attributes can be assigned through xml
 * @param app:warpButtonStyle WarpButtonStyle as defined in the composable
 * @param app:buttonText the button text, string or reference id
 * @param app:enabled sets the button in enabled or disabled mode
 * @param app:loading sets the button in loading mode showing the loading animation
 * @param app:buttonLeadingIcon sets the icon to be displayed in the button before the text, reference id
 * @param app:buttonLeadingIconContentDescr sets the content description for the icon
 * @param app:buttonTrailingIcon sets the icon to be displayed in the button after the text, reference id
 * @param app:buttonTrailingIconContentDescr sets the content description for the icon
 */
class WarpButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    val theme: LegacyWarpTheme by inject(LegacyWarpTheme::class.java)

    var style: WarpButtonStyle = WarpButtonStyle.Primary
        set(value) {
            field = value
            disposeComposition()
        }

    var text = ""
        set(value) {
            field = value
            disposeComposition()
        }

    var loading = false
        set(value) {
            field = value
            disposeComposition()
        }

    var buttonEnabled = true
        set(value) {
            field = value
            disposeComposition()
        }

    @DrawableRes
    var leadingIcon: Int = 0
        set(value) {
            field = value
            disposeComposition()
        }

    var leadingIconContentDescription: String? = null
        set(value) {
            field = value
            disposeComposition()
        }

    @DrawableRes
    var trailingIcon: Int = 0
        set(value) {
            field = value
            disposeComposition()
        }

    var trailingIconContentDescription: String? = null
        set(value) {
            field = value
            disposeComposition()
        }

    var small = false
        set(value) {
            field = value
            disposeComposition()
        }

    private var clickListener = OnClickListener { }

    override fun setOnClickListener(onCLick: OnClickListener?) {
        onCLick?.let { clickListener = it }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        buttonEnabled = enabled
        disposeComposition()
    }

    private var stylesList = listOf(
        WarpButtonStyle.Primary,
        WarpButtonStyle.Secondary,
        WarpButtonStyle.Quiet,
        WarpButtonStyle.Negative,
        WarpButtonStyle.NegativeQuiet,
        WarpButtonStyle.Utility,
        WarpButtonStyle.UtilityQuiet,
        WarpButtonStyle.UtilityOverlay,
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpButton) {
            val styleInt = getInteger(R.styleable.WarpButton_warpButtonStyle, 0)
            style = stylesList[styleInt]
            text = getTextFromIdOrString(R.styleable.WarpButton_buttonText, context) ?: ""
            loading = getBoolean(R.styleable.WarpButton_loading, false)
            buttonEnabled = getBoolean(R.styleable.WarpButton_enabled, true)
            leadingIcon = getResourceId(R.styleable.WarpButton_buttonLeadingIcon, 0)
            leadingIconContentDescription = getTextFromIdOrString(R.styleable.WarpButton_buttonLeadingIconContentDescr, context)
            trailingIcon = getResourceId(R.styleable.WarpButton_buttonTrailingIcon, 0)
            trailingIconContentDescription = getTextFromIdOrString(R.styleable.WarpButton_buttonTrailingIconContentDescr, context)
            small = getBoolean(R.styleable.WarpButton_buttonSmall, false)
        }
    }

    @Composable
    override fun Content() {
        theme {
            WarpButton(
                text = text,
                onClick = { clickListener.onClick(this@WarpButtonView) },
                style = style,
                loading = loading,
                enabled = buttonEnabled,
                leadingIcon = if(leadingIcon != 0) leadingIcon else null,
                leadingIconContentDescr = leadingIconContentDescription,
                trailingIcon = if(trailingIcon != 0) trailingIcon else null,
                trailingIconContentDescr = trailingIconContentDescription,
                small = small
            )
        }
    }
}
