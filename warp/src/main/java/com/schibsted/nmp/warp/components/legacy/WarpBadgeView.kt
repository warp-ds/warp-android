package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpBadge
import com.schibsted.nmp.warp.components.WarpBadgeAlignment
import com.schibsted.nmp.warp.components.WarpBadgeStyle
import org.koin.java.KoinJavaComponent.inject


class WarpBadgeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    val theme: LegacyWarpTheme by inject(LegacyWarpTheme::class.java)

    var style: WarpBadgeStyle = WarpBadgeStyle.Neutral
        set(value) {
            field = value
            disposeComposition()
        }

    var alignmentStyle: WarpBadgeAlignment = WarpBadgeAlignment.None
        set(value) {
            field = value
            disposeComposition()
        }

    var text: CharSequence? = null
        set(value) {
            field = value
            disposeComposition()
        }

    private var alignmentStylesList = listOf(
        WarpBadgeAlignment.None,
        WarpBadgeAlignment.TopStart,
        WarpBadgeAlignment.TopEnd,
        WarpBadgeAlignment.BottomStart,
        WarpBadgeAlignment.BottomEnd
    )

    private var stylesList = listOf(
        WarpBadgeStyle.Info,
        WarpBadgeStyle.Success,
        WarpBadgeStyle.Warning,
        WarpBadgeStyle.Error,
        WarpBadgeStyle.Disabled,
        WarpBadgeStyle.Neutral,
        WarpBadgeStyle.Sponsored,
        WarpBadgeStyle.Price,
    )

    init {
        val androidAttrs = intArrayOf(android.R.attr.text)
        context.withStyledAttributes(attrs, androidAttrs) {
            text = getText(0)
        }
        context.withStyledAttributes(attrs, R.styleable.WarpBadge) {
            style = stylesList[getInt(R.styleable.WarpBadge_warpBadgeStyle, 5)]
            alignmentStyle = alignmentStylesList[getInt(R.styleable.WarpBadge_alignmentStyle, 0)]
        }
    }

    @Composable
    override fun Content() {
        theme {
            WarpBadge(
                text = text.toString(),
                style = style,
                alignmentStyle = alignmentStyle
            )
        }
    }
}
