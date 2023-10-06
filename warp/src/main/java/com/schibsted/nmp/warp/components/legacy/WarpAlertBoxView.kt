package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpAlert
import com.schibsted.nmp.warp.components.WarpAlertType
import org.koin.java.KoinJavaComponent.inject

class WarpAlertBoxView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AbstractComposeView(context = context, attrs = attrs, defStyleAttr = defStyleAttr) {

    val theme: LegacyWarpTheme by inject(LegacyWarpTheme::class.java)

    private var type: WarpAlertType = WarpAlertType.Info
    private var title: String = ""
    private var body: String? = null

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpAlertBoxView) {
            type = getAlertType(getInt(R.styleable.WarpAlertBoxView_warpAlertType, 0))
            title = getString(R.styleable.WarpAlertBoxView_title) ?: ""
            body = getString(R.styleable.WarpAlertBoxView_body)
        }
    }

    private fun getAlertType(enumValue: Int): WarpAlertType {
        return when (enumValue) {
            0 -> WarpAlertType.Info
            1 -> WarpAlertType.Positive
            2 -> WarpAlertType.Negative
            3 -> WarpAlertType.Warning
            else -> WarpAlertType.Info
        }
    }

    @Composable
    override fun Content() {
        theme {
            WarpAlert(
                type = type,
                title = title,
                body = body
            )
        }
    }
}