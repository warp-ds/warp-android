package com.schibsted.nmp.warp.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.schibsted.nmp.warp.components.ext.alertBorder
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes


/**
 * An inline component used to display different types of messages. Can display a title, a body, a link, a secondary button and a quiet button or all of these together.
 * For more information, look [here](https://warp-ds.github.io/tech-docs/components/alert/)
 *
 * @param modifier Modifier applied to the component. Defaults to [Modifier]
 * @param type The type of alert, this decides the look and icon of the component. Defaults to [WarpAlertType.Info]
 * @param title The title of the message
 * @param body An optional text with more information. Defaults to null
 * @param linkText An optional text for a link. Defaults to null
 * @param linkAction An optional action for the link. Defaults to null
 * @param secondaryButtonText An optional text for a secondary button. Defaults to null
 * @param secondaryButtonAction An optional action for the secondary button. Defaults to null
 * @param quietButtonText An optional text for a quiet button. Defaults to null
 * @param quietButtonAction An optional action for the quiet button. Defaults to null
 */
@Composable
fun WarpAlert(
    modifier: Modifier = Modifier,
    type: WarpAlertType = WarpAlertType.Info,
    title: String,
    body: String,
    linkText: String? = null,
    linkAction: (() -> Unit)? = null,
    secondaryButtonText: String? = null,
    secondaryButtonAction: (() -> Unit)? = null,
    quietButtonText: String? = null,
    quietButtonAction: (() -> Unit)? = null,
) {
    val (colors, icon) = when (type) {
        WarpAlertType.Info -> alertStyleInfo() to Icons.Filled.Info
        WarpAlertType.Positive -> alertStylePositive() to Icons.Filled.CheckCircle
        WarpAlertType.Critical -> alertStyleNegative() to Icons.Filled.Info
        WarpAlertType.Warning -> alertStyleWarning() to Icons.Filled.Warning
    }

    ConstraintLayout(
        modifier = modifier
            .alertBorder(
                dimensions.borderWidth3,
                dimensions.borderWidth1,
                colors.background,
                colors.border,
                colors.subtleBorder,
                shapes.roundedMedium
            )
            .padding(
                horizontal = dimensions.space2,
                vertical = dimensions.space25
            )
    ) {
        val (iconRef, titleRef, bodyRef, linkRef, secondaryBtnRef, quietBtnRef) = createRefs()
        Icon(
            modifier = Modifier.constrainAs(iconRef) {
                start.linkTo(parent.start)
                top.linkTo(titleRef.top)
                bottom.linkTo(titleRef.bottom)
            },
            tint = colors.icon,
            imageVector = icon,
            contentDescription = ""
        )

        val margin = dimensions.space1
        val buttonMargin = dimensions.space2

        WarpText(
            modifier = Modifier
                .constrainAs(titleRef) {
                    linkTo(start = iconRef.end, end = parent.end, startMargin = margin, bias = 0f)
                    top.linkTo(parent.top)
                    width = Dimension.preferredWrapContent
                },
            text = title,
            color = colors.text,
            style = WarpTextStyle.Title4
        )
        WarpText(
            modifier = Modifier
                .constrainAs(bodyRef) {
                    linkTo(
                        start = iconRef.end,
                        startMargin = margin,
                        end = parent.end,
                        bias = 0f
                    )
                    top.linkTo(titleRef.bottom, margin = margin)
                    width = Dimension.preferredWrapContent
                },
            text = body,
            color = colors.text,
            style = WarpTextStyle.Body
        )
        linkText?.let {
            WarpText(
                modifier = Modifier
                    .constrainAs(linkRef) {
                        linkTo(
                            start = iconRef.end,
                            startMargin = margin,
                            end = parent.end,
                            bias = 0f
                        )
                        top.linkTo(bodyRef.bottom, margin = margin)
                        width = Dimension.preferredWrapContent
                    }
                    .clickable { linkAction?.invoke() },
                text = linkText,
                style = WarpTextStyle.Body,
                color = WarpTheme.colors.text.link,
                textDecoration = TextDecoration.Underline,
            )
        }
        secondaryButtonText?.let {
            val secondaryBtnAnchor = linkText?.let { linkRef.bottom } ?: bodyRef.bottom
            WarpButton(
                modifier = Modifier
                    .constrainAs(secondaryBtnRef) {
                        linkTo(
                            start = iconRef.end,
                            startMargin = margin,
                            end = parent.end,
                            bias = 0f
                        )
                        top.linkTo(secondaryBtnAnchor, margin = buttonMargin)
                        width = Dimension.preferredWrapContent
                    },
                text = secondaryButtonText,
                onClick = { secondaryButtonAction?.invoke() },
                style = WarpButtonStyle.Secondary
            )
        }
        quietButtonText?.let {
            val quietBtnAnchorTop = linkText?.let { linkRef.bottom } ?: bodyRef.bottom
            val quietBtnAnchorStart =
                secondaryButtonText?.let { secondaryBtnRef.end } ?: iconRef.end
            WarpButton(
                modifier = Modifier
                    .constrainAs(quietBtnRef) {
                        linkTo(
                            start = quietBtnAnchorStart,
                            startMargin = margin,
                            end = parent.end,
                            bias = 0f
                        )
                        top.linkTo(quietBtnAnchorTop, margin = buttonMargin)
                        width = Dimension.preferredWrapContent
                    },
                text = quietButtonText,
                onClick = { quietButtonAction?.invoke() },
                style = WarpButtonStyle.Quiet
            )
        }
    }
}

enum class WarpAlertType {
    Info,
    Positive,
    Critical,
    Warning
}

internal data class WarpAlertStyleColors(
    val text: Color,
    val background: Color,
    val subtleBorder: Color,
    val border: Color,
    val icon: Color
)

@Composable
internal fun alertStyleInfo() = WarpAlertStyleColors(
    text = colors.text.default,
    background = colors.background.infoSubtle,
    subtleBorder = colors.border.infoSubtle,
    border = colors.border.info,
    icon = colors.icon.info
)

@Composable
internal fun alertStylePositive() = WarpAlertStyleColors(
    text = colors.text.default,
    background = colors.background.positiveSubtle,
    subtleBorder = colors.border.positiveSubtle,
    border = colors.border.positive,
    icon = colors.icon.positive
)

@Composable
internal fun alertStyleNegative() = WarpAlertStyleColors(
    text = colors.text.default,
    background = colors.background.negativeSubtle,
    subtleBorder = colors.border.negativeSubtle,
    border = colors.border.negative,
    icon = colors.icon.negative
)

@Composable
internal fun alertStyleWarning() = WarpAlertStyleColors(
    text = colors.text.default,
    background = colors.background.warningSubtle,
    subtleBorder = colors.border.warningSubtle,
    border = colors.border.warning,
    icon = colors.icon.warning
)

@Preview
@Composable
fun WarpAlertPreview() {
    Column(Modifier.fillMaxWidth()) {
        WarpAlert(
            modifier = Modifier.padding(dimensions.space2),
            title = "This is the info variant of the alert element",
            body = "I am an excellent message for the user."
        )
        WarpAlert(
            modifier = Modifier.padding(dimensions.space2),
            title = "This is the positive variant of the alert element, with a very very long title so long that it will wrap",
            body = "With an additional description",
            type = WarpAlertType.Positive
        )
        WarpAlert(
            modifier = Modifier.padding(dimensions.space2),
            title = "Critical variant",
            body = "With an additional description",
            type = WarpAlertType.Critical
        )
        WarpAlert(
            modifier = Modifier
                .padding(dimensions.space2)
                .fillMaxWidth(),
            title = "Critical variant",
            body = "With an additional description",
            type = WarpAlertType.Critical
        )
        WarpAlert(
            modifier = Modifier.padding(dimensions.space2),
            title = "This is the warning variant of the alert element",
            body = "With an additional description that is very long, so long that it will probably become a new line",
            type = WarpAlertType.Warning
        )
        WarpAlert(
            modifier = Modifier.padding(dimensions.space2),
            title = "This is the positive variant with a quiet button",
            body = "With an additional description",
            type = WarpAlertType.Positive,
            quietButtonText = "Quiet Button",
            quietButtonAction = { Log.d("Warp", "Warp Alert Secondary button clicked") }
        )
        WarpAlert(
            modifier = Modifier.padding(dimensions.space2),
            title = "This is the critical variant of the alert element",
            body = "With an additional description and buttons for further action",
            type = WarpAlertType.Critical,
            secondaryButtonText = "Button",
            secondaryButtonAction = { Log.d("Warp", "Warp Alert Secondary button clicked") },
            quietButtonText = "Quiet Button",
            quietButtonAction = { Log.d("Warp", "Warp Alert Quiet button clicked") }
        )
        WarpAlert(
            modifier = Modifier.padding(dimensions.space2),
            title = "This is the info variant with all options",
            body = "You can read more about much more. There's also buttons that you can click",
            type = WarpAlertType.Warning,
            linkText = "A link to read more here",
            linkAction = { Log.d("Warp", "Warp Alert Link clicked") },
            secondaryButtonText = "Button",
            secondaryButtonAction = { Log.d("Warp", "Warp Alert Secondary button clicked") },
            quietButtonText = "Quiet Button",
            quietButtonAction = { Log.d("Warp", "Warp Alert Quiet button clicked") }
        )
    }
}
