package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

    private var clickListener : OnClickListener? = null

    override fun setOnClickListener(onCLick: OnClickListener?) {
        onCLick?.let { clickListener = it }
    }

    private var stylesList = listOf(
        WarpTextStyle.Display,
        WarpTextStyle.Title1,
        WarpTextStyle.Title2,
        WarpTextStyle.Title3,
        WarpTextStyle.Title4,
        WarpTextStyle.Title5,
        WarpTextStyle.Title6,
        WarpTextStyle.Preamble,
        WarpTextStyle.Body,
        WarpTextStyle.BodyStrong,
        WarpTextStyle.Caption,
        WarpTextStyle.CaptionStrong,
        WarpTextStyle.Detail,
        WarpTextStyle.DetailStrong

    )

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpText) {
            val styleInt = getInteger(R.styleable.WarpText_warpTextStyle, 8)
            style = stylesList[styleInt]
            text = getTextFromIdOrString(R.styleable.WarpText_text, context) ?: ""
            color = getColor(R.styleable.WarpText_color, 0)
        }
    }

    @Composable
    override fun Content() {
        theme {
            val textColor = if (color == 0) WarpTheme.colors.text.default else Color(color)
            val modifier = clickListener?.let { Modifier.clickable { it.onClick(this@WarpTextView)} }  ?: Modifier
            WarpText(
                text = text,
                modifier = modifier,
                style = style,
                color = textColor)
        }
    }
}
