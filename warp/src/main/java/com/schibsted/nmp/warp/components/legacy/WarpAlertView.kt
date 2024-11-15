package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
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

    var title: String? = null
        set(value) {
            field = value
            disposeComposition()
        }

    var body: String = ""
        set(value) {
            field = value
            disposeComposition()
        }

    var linkText: String? = null
        set(value) {
            field = value
            disposeComposition()
        }

    private var linkAction: OnClickListener? = null

    fun setLinkOnClickListener(onClick: OnClickListener?) {
        linkAction = OnClickListener { }
        onClick?.let { linkAction = it }
    }

    var secondaryButtonText: String? = null
        set(value) {
            field = value
            disposeComposition()
        }

    private var secondaryButtonAction: OnClickListener? = null

    fun setSecondaryButtonOnClickListener(onClick: OnClickListener?) {
        secondaryButtonAction = OnClickListener { }
        onClick?.let { secondaryButtonAction = it }
    }

    var quietButtonText: String? = null
        set(value) {
            field = value
            disposeComposition()
        }

    private var quietButtonAction: OnClickListener? = null

    fun setQuietButtonOnClickListener(onClick: OnClickListener?) {
        quietButtonAction = OnClickListener { }
        onClick?.let { quietButtonAction = it }
    }

    private var typesList = listOf(
        WarpAlertType.Info,
        WarpAlertType.Positive,
        WarpAlertType.Critical,
        WarpAlertType.Warning
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpAlert) {
            type = typesList[getInt(R.styleable.WarpAlert_warpAlertType, 0)]
            title = getTextFromIdOrString(R.styleable.WarpAlert_title, context)
            body = getTextFromIdOrString(R.styleable.WarpAlert_body, context) ?: ""
            linkText = getTextFromIdOrString(R.styleable.WarpAlert_linkText, context)
            secondaryButtonText =
                getTextFromIdOrString(R.styleable.WarpAlert_secondaryButtonText, context)
            quietButtonText =
                getTextFromIdOrString(R.styleable.WarpAlert_quietButtonText, context)
        }
    }

    @Composable
    override fun Content() {
        theme {
            WarpAlert(
                type = type,
                title = title,
                body = body,
                linkText = linkText,
                linkAction = { linkAction?.onClick(this@WarpAlertView) },
                secondaryButtonText = secondaryButtonText,
                secondaryButtonAction = { secondaryButtonAction?.onClick(this@WarpAlertView) },
                quietButtonText = quietButtonText,
                quietButtonAction = { quietButtonAction?.onClick(this@WarpAlertView) }
            )
        }
    }
}