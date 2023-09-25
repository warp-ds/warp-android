package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.ext.getTextFromIdOrString


/**
 * View class version of the composable WarpButton intended for use in legacy xml layouts.
 * Can be declared in xml layouts as any other view component.
 * Following custom attributes can be assigned through xml
 * @param app:warpButtonStyle WarpButtonStyle as defined in the composable
 * @param app:text the button text, string or reference id
 * @param app:enabled sets the button in enabled or disabled mode
 * @param app:loading sets the button in loading mode showing the loading animation
 */
class WarpButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    private var style: WarpButtonStyle = WarpButtonStyle.Primary
    private var text = ""
    private var loading = false
    private var buttonEnabled = true
    private var clickListener = OnClickListener { }

    override fun setOnClickListener(onCLick: OnClickListener?) {
        onCLick?.let { clickListener = it }
    }

    fun setLoading(loading: Boolean) {
        this.loading = loading
        disposeComposition()
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        buttonEnabled = enabled
        disposeComposition()
    }

    fun setText(text: String) {
        this.text = text
        disposeComposition()
    }

    fun setStyle(style: WarpButtonStyle) {
        this.style = style
        disposeComposition()
    }

    private var stylesList = listOf(
        WarpButtonStyle.Primary,
        WarpButtonStyle.Secondary,
        WarpButtonStyle.Tertiary,
        WarpButtonStyle.Critical,
        WarpButtonStyle.CriticalQuiet,
        WarpButtonStyle.Utility,
        WarpButtonStyle.UtilityQuiet,
        WarpButtonStyle.UtilityOverlay,
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpButton) {
            val styleInt = getInteger(R.styleable.WarpButton_warpButtonStyle, 0)
            style = stylesList[styleInt]
            text = getTextFromIdOrString(R.styleable.WarpButton_buttonText, context)
            loading = getBoolean(R.styleable.WarpButton_loading, false)
            buttonEnabled = getBoolean(R.styleable.WarpButton_enabled, true)
        }
    }

    @Composable
    override fun Content() {
        WarpButton(
            text = text,
            onClick = { clickListener.onClick(this@WarpButtonView) },
            buttonStyle = style,
            loading = loading,
            enabled = buttonEnabled
        )
    }
}
