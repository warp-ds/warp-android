package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalTypography
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.typography

@Composable
fun WarpText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = colors.text.default,
    style: WarpTextStyle = WarpTextStyle.Body,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
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
            color = color,
            style = textStyle,
            maxLines = maxLines,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap
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

@Composable
@Preview
fun WarpTextPreview() {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensions.space1)
        ) {
            WarpText(
                text = "Meow",
                Modifier
                    .padding(vertical = dimensions.space1, horizontal = dimensions.space2),
                style = WarpTextStyle.Display,
                color = colors.components.button.primary.text
            )
        }

}