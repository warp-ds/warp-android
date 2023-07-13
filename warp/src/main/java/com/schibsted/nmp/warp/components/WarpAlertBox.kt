package com.schibsted.nmp.warp.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.schibsted.nmp.warp.theme.WarpBrandedTheme
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider

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
        WarpAlertType.Info -> WarpTheme.colors.components.alert.info to Icons.Filled.Info
        WarpAlertType.Positive -> WarpTheme.colors.components.alert.positive to Icons.Filled.CheckCircle
        WarpAlertType.Negative -> WarpTheme.colors.components.alert.negative to Icons.Filled.Info
        WarpAlertType.Warning -> WarpTheme.colors.components.alert.warning to Icons.Filled.Warning
    }

        ConstraintLayout(
            modifier = modifier
                .alertBorder(
                    WarpTheme.dimensions.borderWidth3.dp,
                    WarpTheme.dimensions.borderWidth1.dp,
                    colors.background,
                    colors.leftBorder,
                    colors.border,
                    WarpTheme.shapes.medium)
                .padding(WarpTheme.dimensions.space2.dp)


        ) {

            val (iconRef, titleRef, bodyRef) = createRefs()
            Icon(
                modifier = Modifier.constrainAs(iconRef) {
                    start.linkTo(parent.start)
                    top.linkTo(titleRef.top)
                    bottom.linkTo(titleRef.bottom)
                },
                tint = colors.leftBorder,
                imageVector = icon,
                contentDescription = ""
            )
            val margin = WarpTheme.dimensions.space1.dp
            val endMargin = WarpTheme.dimensions.space2.dp
            // TODO: check what style, margins and so on should be used
            Text(
                modifier = Modifier
                    .constrainAs(titleRef) {
                        start.linkTo(iconRef.end, margin = margin)
                        end.linkTo(parent.end, margin = endMargin)
                        top.linkTo(parent.top)
                        width = Dimension.preferredWrapContent
                    },
                text = title,
                style = WarpTheme.typography.captionStrong
            )
            body?.let {
                Text(
                    modifier = Modifier
                        .constrainAs(bodyRef) {
                            start.linkTo(iconRef.end, margin = margin)
                            top.linkTo(titleRef.bottom, margin = margin)
                            end.linkTo(parent.end, margin = endMargin)
                            width = Dimension.fillToConstraints
                        },
                    text = body,
                    style = WarpTheme.typography.caption
                )

        }
    }
}

enum class WarpAlertType {
    Info,
    Positive,
    Negative,
    Warning
}

@Preview
@Composable
fun WarpAlertPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    WarpBrandedTheme(
        flavor = flavor,
        isSystemInDarkTheme()
    ) {
        Column(Modifier.fillMaxWidth()) {
            WarpAlert(
                modifier = Modifier.padding(WarpTheme.dimensions.space2.dp),
                title = "This is the info variant of the alert element",
                body = "I am an excellent message for the user.")
            WarpAlert(
                modifier = Modifier.padding(WarpTheme.dimensions.space2.dp),
                title = "This is the positive variant of the alert element, with a very very long title so long that it will wrap",
                body = "With an additional description",
                type = WarpAlertType.Positive
            )
            WarpAlert(
                modifier = Modifier.padding(WarpTheme.dimensions.space2.dp),
                title = "This is the negative variant of the alert element",
                body = "With an additional description",
                type = WarpAlertType.Negative
            )
            WarpAlert(
                modifier = Modifier.padding(WarpTheme.dimensions.space2.dp),
                title = "This is the info variant of the alert element",
                body = "With an additional description that is very long, so long that it will probably become a new line",
                type = WarpAlertType.Warning
            )

        }
    }
}
