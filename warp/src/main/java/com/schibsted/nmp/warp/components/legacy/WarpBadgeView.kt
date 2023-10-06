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
import org.koin.java.KoinJavaComponent


class WarpBadgeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    val theme: LegacyWarpTheme by KoinJavaComponent.inject(LegacyWarpTheme::class.java)

    private var style: WarpBadgeStyle = WarpBadgeStyle.Info
    private var alignmentStyle: WarpBadgeAlignment = WarpBadgeAlignment.None
    private var text: CharSequence? = null

    init {
        val androidAttrs = intArrayOf(android.R.attr.text)
        context.withStyledAttributes(attrs, androidAttrs) {
            text = getText(0)
        }
        context.withStyledAttributes(attrs, R.styleable.WarpBadge) {
            style = getStyle(getInt(R.styleable.WarpBadge_warpBadgeStyle, 0))
            alignmentStyle = getAlignmentStyle(getInt(R.styleable.WarpBadge_alignmentStyle, 0))
        }
    }

    @Composable
    override fun Content() {
        theme.getTheme {
            WarpBadge(
                text = text.toString(),
                style = style,
                alignmentStyle = alignmentStyle
            )
        }
    }

    private fun getAlignmentStyle(alignmentInt: Int): WarpBadgeAlignment =
        when (alignmentInt) {
            1 -> WarpBadgeAlignment.TopStart
            2 -> WarpBadgeAlignment.TopEnd
            3 -> WarpBadgeAlignment.BottomStart
            4 -> WarpBadgeAlignment.BottomEnd
            else -> WarpBadgeAlignment.None
        }

    private fun getStyle(styleInt: Int): WarpBadgeStyle =
        when (styleInt) {
            0 -> WarpBadgeStyle.Info
            1 -> WarpBadgeStyle.Success
            2 -> WarpBadgeStyle.Warning
            3 -> WarpBadgeStyle.Error
            4 -> WarpBadgeStyle.Disabled
            else -> WarpBadgeStyle.Neutral
        }
}