package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpRadio
import com.schibsted.nmp.warp.components.ext.getTextFromIdOrString
import org.koin.java.KoinJavaComponent.inject

class WarpRadioView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    val theme: LegacyWarpTheme by inject(LegacyWarpTheme::class.java)
    var text: String = ""
        set(value) {
            field = value
            disposeComposition()
        }

    var radioSelected: Boolean = false
        set(value) {
            field = value
            disposeComposition()
        }

    var radioEnabled: Boolean = true
        set(value) {
            field = value
            disposeComposition()
        }

    private var clickListener = OnClickListener { }

    override fun setOnClickListener(onClick: OnClickListener?) {
        onClick?.let { clickListener = it }
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpRadio) {
            text = getTextFromIdOrString(R.styleable.WarpRadio_radioText, context) ?: ""
            radioSelected = getBoolean(R.styleable.WarpRadio_radioSelected, false)
            radioEnabled = getBoolean(R.styleable.WarpRadio_radioEnabled, true)
        }
    }

    @Composable
    override fun Content() {
        theme {
            WarpRadio(
                text = text,
                onClick = { clickListener.onClick(this@WarpRadioView) },
                selected = radioSelected,
                enabled = radioEnabled
            )
        }
    }
}