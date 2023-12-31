package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.colors


/**
 * An inline component used to display different types of messages.
 * For more information, look [here](https://warp-ds.github.io/tech-docs/components/alert/)
 * @param modifier Modifier applied to the component
 * @param type The type of alert, this decided the look and icon of the component
 * @param title The title of the message
 * @param body An optional text with more information
 */
@Composable
fun WarpAlert(
    modifier: Modifier = Modifier,
    type: WarpAlertType = WarpAlertType.Info,
    title: String,
    body: String? = null
) {
    val (colors, icon) = when (type) {
        // TODO: Replace with correct icons once they are part of the theme
        WarpAlertType.Info -> alertStyleInfo() to Icons.Filled.Info
        WarpAlertType.Positive -> alertStylePositive() to Icons.Filled.CheckCircle
        WarpAlertType.Negative -> alertStyleNegative() to Icons.Filled.Info
        WarpAlertType.Warning -> alertStyleWarning() to Icons.Filled.Warning
    }

    ConstraintLayout(
        modifier = modifier
            .alertBorder(
                WarpTheme.dimensions.borderWidth3,
                WarpTheme.dimensions.borderWidth1,
                colors.background,
                colors.border,
                colors.subtleBorder,
                WarpTheme.shapes.medium
            )
            .padding(
                horizontal = WarpTheme.dimensions.space2,
                vertical = WarpTheme.dimensions.space25
            )
    ) {
        val (iconRef, titleRef, bodyRef) = createRefs()
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
        val margin = WarpTheme.dimensions.space1
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
        body?.let {
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
        }
    }
}

@Composable
fun WarpAlert(
    modifier: Modifier = Modifier,
    type: WarpAlertType = WarpAlertType.Info,
    content: @Composable () -> Unit
) {
    val colors = when (type) {
        WarpAlertType.Info -> alertStyleInfo()
        WarpAlertType.Positive -> alertStylePositive()
        WarpAlertType.Negative -> alertStyleNegative()
        WarpAlertType.Warning -> alertStyleWarning()
    }
    Box(
        modifier = modifier
            .alertBorder(
                WarpTheme.dimensions.borderWidth3,
                WarpTheme.dimensions.borderWidth1,
                colors.background,
                colors.border,
                colors.subtleBorder,
                WarpTheme.shapes.medium
            )
    ) {
        content()
    }
}


enum class WarpAlertType {
    Info,
    Positive,
    Negative,
    Warning
}

internal data class WarpAlertStyleColors (
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
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the info variant of the alert element",
            body = "I am an excellent message for the user."
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the positive variant of the alert element, with a very very long title so long that it will wrap",
            body = "With an additional description",
            type = WarpAlertType.Positive
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "Negative variant",
            body = "With an additional description",
            type = WarpAlertType.Negative
        )
        WarpAlert(
            modifier = Modifier
                .padding(WarpTheme.dimensions.space2)
                .fillMaxWidth(),
            title = "Negative variant",
            body = "With an additional description",
            type = WarpAlertType.Negative
        )
        WarpAlert(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            title = "This is the info variant of the alert element",
            body = "With an additional description that is very long, so long that it will probably become a new line",
            type = WarpAlertType.Warning
        )

    }
}
