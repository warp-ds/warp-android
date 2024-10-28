import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.components.ext.shadowMedium
import com.schibsted.nmp.warp.components.shapes.CalloutShape
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions


@Composable
fun WarpCallout(
    text: String,
    size: CalloutSize = CalloutSize.Default,
    type: CalloutType = CalloutType.Popover,
    edge: Edge = Edge.Top,
    //position: CalloutPosition,
    closable: Boolean = false,
    onDismiss: () -> Unit
) {
    val calloutColors = getCalloutColors()
    val textStyle = when(size){
        CalloutSize.Small -> WarpTextStyle.Caption
        CalloutSize.Default -> WarpTextStyle.Body
    }
    val shadowModifier = if (type == CalloutType.Popover) Modifier.shadowMedium(CalloutShape(edge)) else Modifier

    //var offset = position.offset
    val horizontalPaddingInPx = with(LocalDensity.current) {
        16.dp.toPx()
    }

    /*var arrowPositionX by remember { mutableStateOf(position.centerPositionX) }
    val popupPositionProvider = remember(arrowDirection, offset) {
        CalloutPositionProvider(
            alignment = Alignment.TopCenter,
            offset = offset,
            horizontalPaddingInPx = horizontalPaddingInPx,
            centerPositionX = position.centerPositionX,
        ) { position ->
            arrowPositionX = position
        }
    }*/
    Popup(
        alignment = Alignment.TopStart,
        offset = IntOffset(20, 30),
        onDismissRequest = onDismiss,
        properties = PopupProperties(focusable = true)
    ) {
        Box(
            modifier = Modifier
                .then(shadowModifier)
                .border(
                    dimensions.borderWidth2,
                    calloutColors.border, CalloutShape(edge)
                )
                .background(calloutColors.background)
                .calloutPadding(edge)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensions.space05),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                WarpText(
                    text = text,
                    color = calloutColors.text,
                    style = textStyle,
                    softWrap = true,

                    )
                if (closable) {
                    Icon(
                        imageVector = icons.close,
                        contentDescription = "Close",
                        tint = WarpTheme.colors.icon.default,
                        modifier = Modifier
                            .padding(start = dimensions.space05)
                            .size(dimensions.icon.small)
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
}

internal class CalloutPositionProvider(
    val alignment: Alignment,
    val offset: IntOffset,
    val centerPositionX: Float,
    val horizontalPaddingInPx: Float,
    private val onArrowPositionX: (Float) -> Unit,
) : PopupPositionProvider {

    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        var popupPosition = IntOffset(0, 0)

        // Get the aligned point inside the parent
        val parentAlignmentPoint = alignment.align(
            IntSize.Zero,
            IntSize(anchorBounds.width, anchorBounds.height),
            layoutDirection
        )
        // Get the aligned point inside the child
        val relativePopupPos = alignment.align(
            IntSize.Zero,
            IntSize(popupContentSize.width, popupContentSize.height),
            layoutDirection
        )

        // Add the position of the parent
        popupPosition += IntOffset(anchorBounds.left, anchorBounds.top)

        // Add the distance between the parent's top left corner and the alignment point
        popupPosition += parentAlignmentPoint

        // Subtract the distance between the children's top left corner and the alignment point
        popupPosition -= IntOffset(relativePopupPos.x, relativePopupPos.y)

        // Add the user offset
        val resolvedOffset = IntOffset(
            offset.x * (if (layoutDirection == LayoutDirection.Ltr) 1 else -1),
            offset.y
        )

        popupPosition += resolvedOffset

        val leftSpace = centerPositionX - horizontalPaddingInPx
        val rightSpace = windowSize.width - centerPositionX - horizontalPaddingInPx

        val tooltipWidth = popupContentSize.width
        val halfPopupContentSize = popupContentSize.center.x

        val fullPadding = horizontalPaddingInPx * 2

        val maxTooltipSize = windowSize.width - fullPadding

        val isCentralPositionTooltip =
            halfPopupContentSize <= leftSpace && halfPopupContentSize <= rightSpace

        when {
            isCentralPositionTooltip -> {
                popupPosition =
                    IntOffset(centerPositionX.toInt() - halfPopupContentSize, popupPosition.y)
                val arrowPosition = halfPopupContentSize.toFloat() - horizontalPaddingInPx
                onArrowPositionX.invoke(arrowPosition)
            }

            tooltipWidth >= maxTooltipSize -> {
                popupPosition =
                    IntOffset(windowSize.center.x - halfPopupContentSize, popupPosition.y)
                val arrowPosition = centerPositionX - popupPosition.x - horizontalPaddingInPx
                onArrowPositionX.invoke(arrowPosition)
            }

            halfPopupContentSize > rightSpace -> {
                popupPosition = IntOffset(centerPositionX.toInt(), popupPosition.y)
                val arrowPosition =
                    halfPopupContentSize + (halfPopupContentSize - rightSpace) - fullPadding

                onArrowPositionX.invoke(arrowPosition)
            }

            halfPopupContentSize > leftSpace -> {
                popupPosition = IntOffset(0, popupPosition.y)
                val arrowPosition = centerPositionX - horizontalPaddingInPx
                onArrowPositionX.invoke(arrowPosition)
            }

            else -> {
                val position = centerPositionX
                onArrowPositionX.invoke(position)
            }
        }

        return popupPosition
    }
}


fun calculateCalloutPosition(
    coordinates: LayoutCoordinates?,
    view: View,
    arrowDirection: Edge
): CalloutPosition {
    coordinates ?: return CalloutPosition()

    val visibleWindowBounds = android.graphics.Rect()
    view.getWindowVisibleDisplayFrame(visibleWindowBounds)

    val boundsInWindow = coordinates.boundsInWindow()

    val heightAbove = boundsInWindow.top - visibleWindowBounds.top
    val heightBelow = visibleWindowBounds.bottom - visibleWindowBounds.top - boundsInWindow.bottom

    val centerPositionX = boundsInWindow.right - (boundsInWindow.right - boundsInWindow.left) / 2

    val offsetX = centerPositionX - visibleWindowBounds.centerX()

    return if (heightAbove < heightBelow) {
        val offset = IntOffset(
            y = coordinates.size.height,
            x = offsetX.toInt()
        )
        CalloutPosition(
            offset = offset,
            arrowDirection = Edge.Top,
            centerPositionX = centerPositionX,
        )
    } else {
        CalloutPosition(
            offset = IntOffset(
                y = -coordinates.size.height,
                x = offsetX.toInt()
            ),
            arrowDirection = Edge.Bottom,
            centerPositionX = centerPositionX,
        )
    }
}

class CalloutPosition(
    val offset: IntOffset = IntOffset(0, 0),
    val centerPositionX: Float = 0f,
    val arrowDirection: Edge = Edge.Top,
)

enum class CalloutType {
    Popover,
    Inline
}

enum class CalloutSize {
    Small,
    Default
}

enum class Edge {
    Top,
    Bottom,
    Leading,
    Trailing
}

@Composable
private fun Modifier.calloutPadding(edge: Edge): Modifier = composed {
    when (edge) {
        Edge.Top -> this.padding(
            top = dimensions.space25,
            bottom = dimensions.space1,
            start = dimensions.space2,
            end = dimensions.space2
        )

        Edge.Bottom -> this.padding(
            top = dimensions.space1,
            bottom = dimensions.space25,
            start = dimensions.space2,
            end = dimensions.space2
        )

        Edge.Leading -> this.padding(
            top = dimensions.space1,
            bottom = dimensions.space1,
            start = dimensions.space25,
            end = dimensions.space2
        )

        Edge.Trailing -> this.padding(
            top = dimensions.space1,
            bottom = dimensions.space1,
            start = dimensions.space2,
            end = dimensions.space25
        )
    }
}

/*
@Composable
fun getCalloutColors() = DefaultWarpCalloutColors(
    text = colors.components.callout.text,
    background = colors.components.callout.background,
    border = colors.components.callout.border
)*/
internal val Green200 = Color(0xffaad6c4)
internal val Green400 = Color(0xff62b294)
internal val Gray900 = Color(0xff1b1b1f)


@Composable
fun getCalloutColors() = DefaultWarpCalloutColors(
    text = Gray900,
    background = Green200,
    border = Green400
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
        var coordinates: LayoutCoordinates? = null
        val view = LocalView.current.rootView
        WarpText(
            modifier = Modifier.onGloballyPositioned {
                coordinates = it
            },
            text = "meow"
        )

        WarpCallout(
            text = "This is a small callout ",
            edge = Edge.Top,
            closable = true,
            size = CalloutSize.Small,
            //position = CalloutPosition(),
            onDismiss = {}
        )
        WarpCallout(
            text = "This is a default callout ",
            edge = Edge.Trailing,
            closable = true,
            size = CalloutSize.Default,
            //position = CalloutPosition(),
            onDismiss = {}
        )
    }
}
