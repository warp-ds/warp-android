package com.schibsted.nmp.warp.components

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.ext.getTextFromIdOrString
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalTypography
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.typography
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider

@Composable
fun WarpText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = colors.components.label.text,
    style: WarpTextStyle = WarpTextStyle.Body,
    maxLines: Int = 1,
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
    ) {

        val textStyle = getTextStyle(style)

        Text(
            text = text,
            modifier = modifier.then(
                Modifier.semantics(properties = { contentDescription = text })
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = maxLines,
            style = textStyle,
            color = color
        )
    }
}

@Composable
fun getTextStyle(style: WarpTextStyle): TextStyle =
    when (style) {
        WarpTextStyle.Display -> typography.display
        WarpTextStyle.Title1 -> typography.title1
        WarpTextStyle.Title2 -> typography.title2
        WarpTextStyle.Title3 -> typography.title3
        WarpTextStyle.Title4 -> typography.title4
        WarpTextStyle.Title5 -> typography.title5
        WarpTextStyle.Title6 -> typography.title6
        WarpTextStyle.Preamble -> typography.preamble
        WarpTextStyle.Body -> typography.body
        WarpTextStyle.BodyStrong -> typography.bodyStrong
        WarpTextStyle.Caption -> typography.caption
        WarpTextStyle.CaptionStrong -> typography.captionStrong
        WarpTextStyle.Detail -> typography.detail
        WarpTextStyle.DetailStrong -> typography.detailStrong
    }

enum class WarpTextStyle {
    Display,
    Title1,
    Title2,
    Title3,
    Title4,
    Title5,
    Title6,
    Preamble,
    Body,
    BodyStrong,
    Caption,
    CaptionStrong,
    Detail,
    DetailStrong
}

class WarpTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    private var style: WarpTextStyle = WarpTextStyle.Display
    private var text = ""
    @ColorInt
    private var color: Int = 0
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

    fun setStyle(style: WarpTextStyle) {
        this.style = style
        disposeComposition()
    }

    fun setText(text: String) {
        this.text = text
        disposeComposition()
    }

    fun setColor(@ColorInt color:  Int){
        this.color = color
        disposeComposition()
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpText) {
            val styleInt = getInteger(R.styleable.WarpText_warpTextStyle, 0)
            style = stylesList.first { it.first == styleInt }.second
            text = getTextFromIdOrString(R.styleable.WarpText_text, context)
            color = getColor(R.styleable.WarpText_color, 0)
        }
    }

    @Composable
    override fun Content() {
        val textColor = if (color == 0) colors.components.label.text else Color(color)
        WarpText(text = text, style = style, color = textColor)
    }

}

@Composable
@Preview
fun WarpTextPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            WarpText(
                text = "Meow",
                Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                style = WarpTextStyle.Display,
                color = WarpTheme.colors.components.button.primary.text
            )
        }

}