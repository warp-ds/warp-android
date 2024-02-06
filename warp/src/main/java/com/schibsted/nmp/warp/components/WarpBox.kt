package com.schibsted.nmp.warp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes

/**
 * A component for separating content areas on a screen.
 * For more information, look [here](https://warp-ds.github.io/tech-docs/components/box/)
 * @param boxStyle The style that will be applied to the component. Default is WarpBoxStyle.NEUTRAL. Options are WarpBoxStyle.NEUTRAL, WarpBoxStyle.INFO, WarpBoxStyle.BORDERED
 * @param modifier Modifier applied to the component. Default is Modifier
 * @param icon Optional icon that will be rendered inside the box
 * @param heading Optional heading that will be rendered inside the box
 * @param text Optional text that will be rendered inside the box
 * @param link Optional link that will be rendered inside the box. If this is set, linkAction must also be set
 * @param linkAction Action that will be triggered when the link is clicked
 * @param buttonText Optional button that will be rendered inside the box. The button style is WarpButtonStyle.Secondary. If this is set, buttonAction must also be set
 *
 */
@Composable
fun WarpBox(
    boxStyle: WarpBoxStyle,
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null,
    heading: String? = null,
    text: String? = null,
    link: String? = null,
    linkAction: (() -> Unit)? = null,
    buttonText: String? = null,
    buttonAction: (() -> Unit)? = null,
) {
    WarpBox(
        boxStyle = boxStyle,
        modifier = modifier
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(dimensions.space3)
        ) {
            val (iconRef, headingRef, bodyRef, linkRef, btnRef) = createRefs()
            val padding = dimensions.space2
            val iconModifier = Modifier.padding(end = padding)
            val bottomPadding = Modifier.padding(bottom = padding)

            //Optional Icon
            Box(modifier = Modifier
                .then(icon?.let { iconModifier } ?: Modifier)
                .constrainAs(iconRef) {
                    end.linkTo(headingRef.start)
                    start.linkTo(parent.start)
                    top.linkTo(headingRef.top)
                }) {
                icon?.let { it() }
            }
            //Optional heading
            Box(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(headingRef) {
                    linkTo(
                        top = parent.top,
                        bottom = bodyRef.top
                    )
                    start.linkTo(bodyRef.start)
                    end.linkTo(parent.end)
                    width = Dimension.preferredWrapContent
                }) {
                heading?.let {
                    WarpText(
                        text = heading,
                        style = WarpTextStyle.Title3,
                        color = colors.text.default,
                        modifier = text?.let { bottomPadding } ?: link?.let { bottomPadding } ?: buttonText?.let { bottomPadding }  ?: Modifier
                    )
                }
            }
            //Optional body text
            Box(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bodyRef) {
                    start.linkTo(iconRef.end)
                    end.linkTo(parent.end)
                    top.linkTo(headingRef.bottom)
                    bottom.linkTo(linkRef.top)
                    width = Dimension.preferredWrapContent
                }) {
                text?.let {
                    WarpText(
                        text = text,
                        style = WarpTextStyle.Body,
                        color = colors.text.default,
                        modifier = link?.let { bottomPadding } ?: buttonText?.let { bottomPadding }  ?: Modifier
                    )
                }
            }
            //Optional link
            Box(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(linkRef) {
                    start.linkTo(iconRef.end)
                    top.linkTo(bodyRef.bottom)
                    bottom.linkTo(btnRef.top)
                    end.linkTo(parent.end)
                    width = Dimension.preferredWrapContent
                }) {
                link?.let {
                    WarpText(
                        text = link,
                        style = WarpTextStyle.Body,
                        color = colors.text.link,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .clickable { linkAction?.invoke() }
                            .then(buttonText?.let { bottomPadding } ?: Modifier)
                    )
                }
            }
            //Optional button
            Box(modifier = Modifier
                .constrainAs(btnRef) {
                    start.linkTo(iconRef.end)
                    top.linkTo(linkRef.bottom)
                }) {
                buttonText?.let {
                    WarpButton(
                        text = buttonText,
                        onClick = { buttonAction?.invoke() },
                        buttonStyle = WarpButtonStyle.Secondary,
                    )
                }
            }
        }
    }
}

/**
 * A component for separating content areas on a screen.
 * For more information, look [here](https://warp-ds.github.io/tech-docs/components/box/)
 * @param modifier Modifier applied to the component
 * @param boxStyle The style that will be applied to the component. Default is WarpBoxStyle.NEUTRAL
 * @param content The content that will be rendered inside the box
 */
@Composable
fun WarpBox(
    modifier: Modifier = Modifier,
    boxStyle: WarpBoxStyle = WarpBoxStyle.NEUTRAL,
    content: @Composable () -> Unit
) {
    val style = when (boxStyle) {
        WarpBoxStyle.NEUTRAL -> WarpComponentBackground(colors.background.subtle, null)
        WarpBoxStyle.INFO -> WarpComponentBackground(colors.background.infoSubtle, null)
        WarpBoxStyle.BORDERED -> WarpComponentBackground(
            colors.background.default,
            colors.border.default
        )
    }

    Surface(
        modifier = modifier,
        shape = shapes.medium,
        border = style.border?.let { BorderStroke(width = dimensions.borderWidth2, color = it) },
        color = style.background,
        content = content
    )
}

/**
 * An enum representing the different styles of the WarpBox component
 */
enum class WarpBoxStyle {
    NEUTRAL,
    INFO,
    BORDERED
}

internal data class WarpComponentBackground(
    val background: Color,
    val border: Color?
)

@Preview
@Composable
fun BoxPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        WarpBox(
            modifier = Modifier
                .padding(8.dp),
            boxStyle = WarpBoxStyle.NEUTRAL
        ) {
            WarpText(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                text = "This is the neutral style"
            )
        }
        WarpBox(
            modifier = Modifier
                .padding(8.dp),
            boxStyle = WarpBoxStyle.INFO
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(), text = "This is the info style"
            )
        }
        WarpBox(
            modifier = Modifier
                .padding(8.dp),
            boxStyle = WarpBoxStyle.BORDERED
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                text = "This is the bordered style"
            )
        }
    }
}
