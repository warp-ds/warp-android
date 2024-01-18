package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpAlert
import com.schibsted.nmp.warp.components.WarpAlertType
import com.schibsted.nmp.warp.components.ext.getTextFromIdOrString
import org.koin.java.KoinJavaComponent.inject

class WarpAlertView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context = context, attrs = attrs, defStyleAttr = defStyleAttr) {

    val theme: LegacyWarpTheme by inject(LegacyWarpTheme::class.java)

    var type: WarpAlertType = WarpAlertType.Info
        set(value) {
            field = value
            disposeComposition()
        }

    var title: String = ""
        set(value) {
            field = value
            disposeComposition()
        }

    var body: String = ""
        set(value) {
            field = value
            disposeComposition()
        }

    private var typesList = listOf(
        WarpAlertType.Info,
        WarpAlertType.Positive,
        WarpAlertType.Critical,
        WarpAlertType.Warning
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpAlertView) {
            type = typesList[getInt(R.styleable.WarpAlertView_warpAlertType, 0)]
            title = getTextFromIdOrString(R.styleable.WarpAlertView_title, context)
            body = getTextFromIdOrString(R.styleable.WarpAlertView_body, context)
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