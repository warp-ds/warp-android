package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AbstractComposeView
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.components.ext.getTextFromIdOrString
import com.schibsted.nmp.warp.theme.WarpTheme
import org.koin.java.KoinJavaComponent

class WarpTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    val theme: LegacyWarpTheme by KoinJavaComponent.inject(LegacyWarpTheme::class.java)

    var style: WarpTextStyle = WarpTextStyle.Display
        set(value) {
            field = value
            disposeComposition()
        }

    var text = ""
        set(value) {
            field = value
            disposeComposition()
        }

    @ColorInt
    var color: Int = 0
        set(value) {
            field = value
            disposeComposition()
        }

    private var stylesList = listOf(
        Pair(0, WarpTextStyle.Display),
        Pair(1, WarpTextStyle.Title1),
        Pair(2, WarpTextStyle.Title2),
        Pair(3, WarpTextStyle.Title3),
        Pair(4, WarpTextStyle.Title4),
        Pair(5, WarpTextStyle.Title5),
        Pair(6, WarpTextStyle.Title6),
        Pair(7, WarpTextStyle.Preamble),
        Pair(8, WarpTextStyle.Body),
        Pair(9, WarpTextStyle.BodyStrong),
        Pair(10, WarpTextStyle.Caption),
        Pair(11, WarpTextStyle.CaptionStrong),
        Pair(12, WarpTextStyle.Detail),
        Pair(13, WarpTextStyle.DetailStrong),
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpText) {
            val styleInt = getInteger(R.styleable.WarpText_warpTextStyle, 0)
            style = stylesList.first { it.first == styleInt }.second
            text = getTextFromIdOrString(R.styleable.WarpText_text, context) ?: ""
            color = getColor(R.styleable.WarpText_color, 0)
        }
    }

    @Composable
    override fun Content() {
        theme {
            val textColor = if (color == 0) WarpTheme.colors.text.default else Color(color)
            WarpText(text = text, style = style, color = textColor)
        }
    }
}
