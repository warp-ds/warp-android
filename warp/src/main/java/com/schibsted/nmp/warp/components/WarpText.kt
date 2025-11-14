package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.typography

/**
 * A Text component in the warp design system.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/text/)
 * @param text The text to be displayed. Can be either a String or an AnnotatedString
 * @param modifier Modifier for the text. Default value is Modifier
 * @param color The color of the text. Default value is colors.text.default
 * @param style The style of the text. Default value is WarpTextStyle.Body. See [WarpTextStyle] for more styles.
 * Other styles include Display, Title1, Title2, Title3, Title4, Title5, Title6, Preamble, Body, BodyStrong, Caption, CaptionStrong, Detail, DetailStrong
 * @param maxLines The maximum number of lines to be displayed on the text. Default value is Int.MAX_VALUE
 * @param textAlign The alignment of the text. Default value is null
 * @param overflow The overflow of the text. Default value is TextOverflow.Ellipsis
 * @param softWrap set to true to wrap the text. Default value is true
 * @param textDecoration The decoration of the text. Default value is null
 * @param onTextLayout Callback that is triggered when the text layout is calculated. Default value is null
 */
@Composable
fun WarpText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = colors.text.default,
    style: WarpTextStyle = WarpTextStyle.Body,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    textDecoration: TextDecoration? = null,
    onTextLayout: ((result: TextLayoutResult) -> Unit)? = null
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
        minLines = minLines,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        textDecoration = textDecoration,
        onTextLayout = onTextLayout
    )
}

@Composable
fun WarpText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color = colors.text.default,
    style: WarpTextStyle = WarpTextStyle.Body,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    textDecoration: TextDecoration? = null,
    onTextLayout: (result: TextLayoutResult) -> Unit = {}
) {
    val textStyle = getTextStyle(style)

    Text(
        text = text,
        modifier = modifier.then(
            Modifier.semantics(properties = { contentDescription = text.text })
        ),
        color = color,
        style = textStyle,
        maxLines = maxLines,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        textDecoration = textDecoration,
        onTextLayout = onTextLayout
    )
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
            color = colors.text.default
        )
    }
}