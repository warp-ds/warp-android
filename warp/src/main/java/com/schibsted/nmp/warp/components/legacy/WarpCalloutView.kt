package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.unit.dp
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.CalloutSize
import com.schibsted.nmp.warp.components.CalloutState
import com.schibsted.nmp.warp.components.CalloutType
import com.schibsted.nmp.warp.components.WarpCallout
import com.schibsted.nmp.warp.components.ext.getTextFromIdOrString
import com.schibsted.nmp.warp.components.utils.Edge
import org.koin.java.KoinJavaComponent.inject

class WarpCalloutView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    val theme: LegacyWarpTheme by inject(LegacyWarpTheme::class.java)

    var text = ""
        set(value) {
            field = value
            disposeComposition()
        }

    var isVisible = false
        set(value) {
            field = value
            disposeComposition()
        }

    lateinit var state: CalloutState

    var size = CalloutSize.Default
        set(value) {
            field = value
            disposeComposition()
        }
    private val sizeList = listOf(
        CalloutSize.Small,
        CalloutSize.Default
    )

    var type = CalloutType.Popover
        set(value) {
            field = value
            disposeComposition()
        }
    private val typeList = listOf(
        CalloutType.Popover,
        CalloutType.Inline
    )

    var horizontalOffset = 0
        set(value) {
            field = value
            disposeComposition()
        }

    var verticalOffset = 0
        set(value) {
            field = value
            disposeComposition()
        }
    var edge = Edge.Top
        set(value) {
            field = value
            disposeComposition()
        }
    private val edgeList = listOf(
        Edge.Top,
        Edge.Bottom,
        Edge.Leading,
        Edge.Trailing,
    )

    var closable = false
        set(value) {
            field = value
            disposeComposition()
        }

    private var onDismissClickListener: OnClickListener? = null

    fun setOnDismissClickListener(onClick: OnClickListener?) {
        onDismissClickListener = OnClickListener { }
        onClick?.let { onDismissClickListener = it }
    }

    var anchor: @Composable (() -> Unit)? = null
        set(value) {
            field = value
            disposeComposition()
        }

    fun setAnchorView(anchorView: @Composable (() -> Unit)) {
        this.anchor = anchorView
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpCallout) {
            text = getTextFromIdOrString(R.styleable.WarpCallout_calloutText, context) ?: ""
            isVisible = getBoolean(R.styleable.WarpCallout_calloutIsVisible, false)
            size = sizeList[getInteger(R.styleable.WarpCallout_calloutSize, 1)]
            type = typeList[getInteger(R.styleable.WarpCallout_calloutType, 0)]
            horizontalOffset = getInteger(R.styleable.WarpCallout_calloutHorizontalOffset, 0)
            verticalOffset = getInteger(R.styleable.WarpCallout_calloutVerticalOffset, 0)
            edge = edgeList[getInteger(R.styleable.WarpCallout_calloutEdge, 0)]
            closable = getBoolean(R.styleable.WarpCallout_calloutIsClosable, false)
        }
    }

    @Composable
    override fun Content() {
        theme {
            state = remember { CalloutState(isVisible) }
            WarpCallout(
                text = text,
                edge = edge,
                state = state,
                horizontalOffset = horizontalOffset.dp,
                verticalOffset = verticalOffset.dp,
                type = type,
                size = size,
                closable = closable,
                onDismiss = { state.isVisible = false },
                anchorView = anchor
            )
        }
    }
}
