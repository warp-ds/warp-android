package com.schibsted.nmp.warp.components

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpBrandedTheme

class WarpButtonCompat @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    val buttonStyle: WarpButtonStyle
    val text: String
    val enabled: Boolean
    val loading: Boolean

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.WarpButtonCompat,
            0, 0
        ).apply {
            try {
                text = getString(R.styleable.WarpButtonCompat_warpButtonText).toString()
                buttonStyle = WarpButtonStyle.values()[getInt(
                    R.styleable.WarpButtonCompat_warpButtonStyle,
                    0
                )]
                enabled = getBoolean(R.styleable.WarpButtonCompat_warpButtonEnabled, true)
                loading = getBoolean(R.styleable.WarpButtonCompat_warpButtonLoading, false)
            } finally {
                recycle()
            }
        }
    }

    @Composable
    override fun Content() {
        val nightMode = when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            Configuration.UI_MODE_NIGHT_NO -> false
            else -> false
        }

        //todo need to get flavor here somehow
        WarpBrandedTheme(darkTheme = nightMode, flavor = "finn") {
            WarpButton(
                text = text,
                onClick = { },
                buttonStyle = buttonStyle,
                enabled = enabled,
                loading = loading
            )
        }
    }
}
