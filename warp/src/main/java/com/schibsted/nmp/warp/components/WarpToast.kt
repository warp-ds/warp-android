package com.schibsted.nmp.warp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpDimensions.adaptDpToFontScale
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes
import java.util.Timer
import kotlin.concurrent.schedule

/**
 * @param modifier Modifier for the toast
 * @param state State of the toast. Used to show or hide the toast and to set/change the text.
 * @param type Type of the toast. Success, Warning or Error.
 * @param duration Duration of the toast. Short, medium or infinite.
 * @param dismiss Show dismiss button. Visible by default.
 * @param onDismiss Callback to be invoked when the toast is dismissed.
 */
@Composable
fun WarpToast(
    modifier: Modifier = Modifier,
    state: WarpToastState,
    type: WarpToastType = WarpToastType.Success,
    duration: Long = WarpToastDuration.SHORT,
    dismiss: Boolean = true,
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

    DisposableEffect(
        key1 = state.updateState
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
    if (state.isNotEmpty() && showToast) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .semantics {
                    liveRegion =
                        LiveRegionMode.Polite
                },
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val minSize = adaptDpToFontScale(56.dp)
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
                    .defaultMinSize(minWidth = minSize, minHeight = minSize)
                    .then(modifier),
                verticalAlignment = Alignment.Top,
            ) {
                Row(
                    modifier = Modifier.defaultMinSize(minWidth = minSize, minHeight = minSize),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    WarpIcon(
                        icon = icon,
                        color = colors.icon,
                        size = adaptDpToFontScale(dimensions.icon.default)
                    )
                }
                state.message.value?.let {
                    Row(
                        modifier
                            .weight(1f)
                            .defaultMinSize(minHeight = minSize),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        WarpText(
                            modifier = Modifier.padding(vertical = dimensions.space2),
                            text = it,
                            style = WarpTextStyle.Caption,
                        )
                    }
                }
                if (dismiss) {
                    IconButton(
                        modifier = Modifier.defaultMinSize(minWidth = minSize, minHeight = minSize),
                        onClick = {
                            onDismiss.invoke()
                            showToast = false
                            timer.cancel()
                            timer.purge()
                        },
                    ) {
                        WarpIcon(
                            icon = icons.close,
                            size = adaptDpToFontScale(dimensions.icon.small)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(dimensions.space2))
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
    const val SHORT = 5000L
    const val MEDIUM = 10000L
    const val INFINITE = 6000000L
}

class WarpToastState {

    private val _message = mutableStateOf<String?>(null)
    val message: State<String?> = _message

    var updateState by mutableStateOf(false)
        private set

    fun showToast(message: String) {
        _message.value = message
        updateState = !updateState
    }

    fun isNotEmpty(): Boolean {
        return _message.value != null
    }
}