package com.schibsted.nmp.warp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.schibsted.nmp.warp.components.ext.edgePadding
import com.schibsted.nmp.warp.components.ext.shadowMedium
import com.schibsted.nmp.warp.components.shapes.calloutShape
import com.schibsted.nmp.warp.components.utils.Edge
import com.schibsted.nmp.warp.components.utils.EdgePositionProvider
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * @param text The text to be displayed in the callout
 * @param state The state of the callout. Used to show or hide the callout.
 * @param size The size of the callout. Small or Default.
 * @param type The type of the callout. Popover or Inline.
 * @param inlineModifier Modifier for the inline callout.
 * @param horizontalOffset The horizontal offset of the callout.
 * @param verticalOffset The vertical offset of the callout.
 * @param edge The edge of the callout. Top, Bottom, Leading, Trailing.
 * @param closable Whether the callout is closable.
 * @param dismissPopoverOnClickOutside Whether the popover should be dismissed when clicking outside.
 * @param onDismiss The callback to be invoked when the callout is dismissed.
 * @param paddingOffset The padding offset of the parent layout. This is needed when the callout is too large and needs to re-adjust it's arrow towards the anchor.
 * @param anchorView The view to be used as the anchor for the callout.
 */
@Composable
fun WarpCallout(
    text: String,
    state: CalloutState,
    size: CalloutSize = CalloutSize.Default,
    type: CalloutType = CalloutType.Popover,
    inlineModifier: Modifier = Modifier,
    horizontalOffset: Dp = 0.dp,
    verticalOffset: Dp = 0.dp,
    edge: Edge = Edge.Top,
    closable: Boolean = false,
    dismissPopoverOnClickOutside: Boolean = true,
    onDismiss: () -> Unit,
    paddingOffset: Dp? = null,
    anchorView: @Composable (() -> Unit)? = null,
) {
    val calloutColors = getCalloutColors()

    val textStyle = when (size) {
        CalloutSize.Small -> WarpTextStyle.Caption
        CalloutSize.Default -> WarpTextStyle.Body
    }
    val popupPositionProvider = EdgePositionProvider(
        offset = DpOffset(horizontalOffset, verticalOffset),
        density = LocalDensity.current,
        edge = edge
    )

    var anchorWidth: Dp? = null
    var anchorPosition: Offset? = null

    Box {
        anchorView?.let { anchor ->
            Box(
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        anchorWidth = coordinates.size.width.dp
                        anchorPosition = coordinates.positionInWindow()
                    }
            ) {
                anchor()
            }
        }
        if (state.isVisible) {
            when (type) {
                CalloutType.Popover -> {
                    Popup(
                        popupPositionProvider = popupPositionProvider,
                        onDismissRequest = onDismiss,
                        properties = PopupProperties(
                            dismissOnClickOutside = dismissPopoverOnClickOutside
                        )
                    ) {
                        CalloutView(
                            Modifier,
                            dimensions,
                            calloutColors,
                            edge,
                            text,
                            textStyle,
                            closable,
                            anchorWidth,
                            anchorPosition,
                            paddingOffset,
                            false,
                            onDismiss
                        )
                    }
                }

                CalloutType.Inline -> {
                    CalloutView(
                        inlineModifier,
                        dimensions,
                        calloutColors,
                        edge,
                        text,
                        textStyle,
                        closable,
                        anchorWidth,
                        anchorPosition,
                        paddingOffset,
                        true,
                        onDismiss
                    )
                }
            }
        }
    }
}

@Composable
private fun CalloutView(
    modifier: Modifier,
    dimensions: WarpDimensions,
    calloutColors: DefaultWarpCalloutColors,
    edge: Edge,
    text: String,
    textStyle: WarpTextStyle,
    closable: Boolean,
    anchorWidth: Dp?,
    anchorPosition: Offset?,
    paddingOffset: Dp? = null,
    inline: Boolean,
    onDismiss: () -> Unit
) {
    val shadowModifier =
        if (inline) Modifier.shadow(
            0.5.dp,
            calloutShape(edge, anchorWidth, anchorPosition, paddingOffset)
        ) else Modifier.shadowMedium(calloutShape(edge, anchorWidth, anchorPosition, paddingOffset))
    Box(
        modifier = Modifier
            .then(modifier)
            .then(shadowModifier)
            .border(
                dimensions.borderWidth2,
                calloutColors.border, calloutShape(edge, anchorWidth, anchorPosition, paddingOffset)
            )
            .background(calloutColors.background)
            .edgePadding(edge)

    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensions.space05),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            WarpText(
                modifier = Modifier
                    .weight(if (closable) 0.9f else 1f, fill = false),
                text = text,
                color = calloutColors.text,
                style = textStyle,
                overflow = TextOverflow.Clip,
                softWrap = true,
            )
            if (closable) {
                WarpIcon(
                    icon = icons.close,
                    size = dimensions.icon.small,
                    modifier = Modifier
                        .weight(0.1f, fill = false)
                        .align(Alignment.CenterVertically)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }) {
                            onDismiss()
                        }
                )
            }
        }
    }
}

@Stable
class CalloutState(
    isVisible: Boolean = false
) {
    var isVisible: Boolean by mutableStateOf(isVisible)
}

enum class CalloutType {
    Popover,
    Inline
}

enum class CalloutSize {
    Small,
    Default
}

@Composable
fun getCalloutColors() = DefaultWarpCalloutColors(
    text = colors.text.default,
    background = colors.components.callout.background,
    border = colors.components.callout.border
)

data class DefaultWarpCalloutColors(
    val text: Color,
    val background: Color,
    val border: Color
)

@Preview(showBackground = true)
@Composable
fun PreviewWarpCallout() {
    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        WarpCallout(
            text = "This is a small callout ",
            state = CalloutState(),
            edge = Edge.Top,
            closable = true,
            size = CalloutSize.Small,
            onDismiss = {}
        ) {
            WarpText(
                text = "meow"
            )
        }
        WarpCallout(
            text = "This is a default callout ",
            state = CalloutState(),
            edge = Edge.Trailing,
            closable = true,
            size = CalloutSize.Default,
            onDismiss = {}
        ) {
            WarpText(
                text = "meow"
            )
        }
    }
}
