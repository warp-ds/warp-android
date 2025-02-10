package com.schibsted.nmp.warp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
fun WarpToast(
    modifier: Modifier = Modifier,
    text: String,
    type: WarpToastType = WarpToastType.Success,
    duration: Long = WarpToastDuration.SHORT,
    onDismiss: () -> Unit = { },
) {
    val colors = when (type) {
        WarpToastType.Success -> WarpToastColors(
            background = colors.background.positiveSubtle,
            border = colors.border.positiveSubtle,
            icon = colors.icon.positive
        )

        WarpToastType.Warning -> WarpToastColors(
            background = colors.background.warningSubtle,
            border = colors.border.warningSubtle,
            icon = colors.icon.warning
        )

        WarpToastType.Error -> WarpToastColors(
            background = colors.background.negativeSubtle,
            border = colors.border.negativeSubtle,
            icon = colors.icon.negative
        )
    }
    val icon = when (type) {
        WarpToastType.Success -> icons.success
        WarpToastType.Warning -> icons.warning
        WarpToastType.Error -> icons.error
    }
    var showToast by remember { mutableStateOf(false) }
    val timer = Timer("ToastTimer", true)
    val dismissAction = {
        showToast = false
        timer.cancel()
        timer.purge()
        onDismiss.invoke()
    }

    DisposableEffect(
        key1 = false
    ) {
        showToast = true
        timer.schedule(duration) {
            showToast = false
            onDismiss.invoke()
        }
        onDispose {
            timer.cancel()
            timer.purge()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = dimensions.space2),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = showToast,
            enter = fadeIn(),
            exit = fadeOut()

        ) {
            Row(
                modifier = Modifier
                    .background(
                        color = colors.background,
                        shape = shapes.roundedMedium
                    )
                    .border(
                        width = dimensions.borderWidth2,
                        color = colors.border,
                        shape = shapes.roundedMedium
                    )
                    .padding(dimensions.space05)
                    .animateContentSize()
                    .then(modifier),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Row(
                    modifier = Modifier
                        .weight(4f)
                        .padding(start = dimensions.space1),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    WarpIcon(
                        icon = icon,
                        color = colors.icon,
                        size = dimensions.icon.small
                    )
                    Spacer(modifier = Modifier.width(dimensions.space1))
                    WarpText(
                        text = text,
                        style = WarpTextStyle.Caption,
                        maxLines = 2
                    )
                }
                IconButton(
                    onClick = {
                        dismissAction()
                    },
                ) {
                    WarpIcon(
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }) {
                            dismissAction()
                        },
                        icon = icons.close,
                        size = dimensions.icon.small
                    )
                }
            }
        }
    }
}

data class WarpToastColors(
    val background: Color,
    val border: Color,
    val icon: Color
)

enum class WarpToastType {
    Success,
    Warning,
    Error
}

object WarpToastDuration {
    const val SHORT = 3000L
    const val LONG = 10000L
    const val INFINITE = 6000000L
}