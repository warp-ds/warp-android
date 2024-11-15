import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.components.ext.shadowMedium
import com.schibsted.nmp.warp.components.shapes.CalloutShape
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions


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
    onDismiss: () -> Unit,
    anchorView: @Composable (() -> Unit)? = null,
) {
    val calloutColors = getCalloutColors()

    val textStyle = when (size) {
        CalloutSize.Small -> WarpTextStyle.Caption
        CalloutSize.Default -> WarpTextStyle.Body
    }
    val popupPositionProvider = CalloutPositionProvider(
        offset = DpOffset(horizontalOffset, verticalOffset),
        density = LocalDensity.current,
        edge = edge
    )
    val shadowModifier =
        if (type == CalloutType.Popover) Modifier.shadowMedium(CalloutShape(edge)) else Modifier.shadow(
            0.5.dp,
            CalloutShape(edge)
        )

    Box {
        anchorView?.let { it() }
        if (state.isVisible) {
            when (type) {
                CalloutType.Popover -> {
                    Popup(
                        popupPositionProvider = popupPositionProvider,
                        onDismissRequest = onDismiss,
                        properties = PopupProperties(focusable = true)
                    ) {
                        CalloutView(
                            shadowModifier,
                            dimensions,
                            calloutColors,
                            edge,
                            text,
                            textStyle,
                            closable,
                            onDismiss
                        )
                    }
                }

                CalloutType.Inline -> {
                    CalloutView(
                        inlineModifier.then(shadowModifier),
                        dimensions,
                        calloutColors,
                        edge,
                        text,
                        textStyle,
                        closable,
                        onDismiss
                    )
                }
            }
        }
    }
}

@Composable
private fun CalloutView(
    inlineModifier: Modifier,
    dimensions: WarpDimensions,
    calloutColors: DefaultWarpCalloutColors,
    edge: Edge,
    text: String,
    textStyle: WarpTextStyle,
    closable: Boolean,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .then(inlineModifier)
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
                WarpIcon(
                    icon = icons.close,
                    size = dimensions.icon.small,
                    modifier = Modifier
                        .padding(start = dimensions.space05)
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

internal class CalloutPositionProvider(
    val offset: DpOffset,
    val density: Density,
    val edge: Edge,
) : PopupPositionProvider {

    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        val contentOffsetX = with(density) { offset.x.roundToPx() }
        val contentOffsetY = with(density) { offset.y.roundToPx() }

        val xOffset = when (edge) {
            Edge.Top -> 0
            Edge.Bottom -> 0
            Edge.Leading -> -anchorBounds.width
            Edge.Trailing -> anchorBounds.width
        }
        val yOffset = when (edge) {
            Edge.Top -> anchorBounds.height
            Edge.Bottom -> -anchorBounds.height
            Edge.Leading -> 0
            Edge.Trailing -> 0
        }
        val alignment = when (edge) {
            Edge.Top -> Alignment.TopCenter
            Edge.Bottom -> Alignment.BottomCenter
            Edge.Leading -> Alignment.CenterStart
            Edge.Trailing -> Alignment.CenterEnd
        }
        val anchorAlignmentPoint = alignment.align(
            IntSize.Zero,
            anchorBounds.size,
            layoutDirection
        )
        val popupAlignmentPoint = -alignment.align(
            IntSize.Zero,
            popupContentSize,
            layoutDirection
        )
        val resolvedUserOffset = IntOffset(
            contentOffsetX * (if (layoutDirection == LayoutDirection.Ltr) 1 else -1) - xOffset,
            contentOffsetY + yOffset
        )

        return anchorBounds.topLeft +
                anchorAlignmentPoint +
                popupAlignmentPoint +
                resolvedUserOffset

    }
}

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


@Composable
fun getCalloutColors() = DefaultWarpCalloutColors(
    text = colors.components.callout.text,
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
