package com.schibsted.nmp.warp.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SliderColors
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.AwaitPointerEventScope
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.PointerType
import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.ViewConfiguration
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setProgress
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import androidx.compose.ui.util.fastFirst
import androidx.compose.ui.util.fastFirstOrNull
import androidx.compose.ui.util.lerp
import androidx.compose.ui.util.packFloats
import androidx.compose.ui.util.unpackFloat1
import androidx.compose.ui.util.unpackFloat2
import com.schibsted.nmp.warp.components.utils.Edge
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.roundToInt
import kotlin.math.sign


/**
 * [WarpRangeSlider] is an adaptation of Material 3 RangeSlider that operates on a discrete set of items.
 *
 * It allows the user to select a range of values from a provided list of items.
 * The two thumbs, representing the start and end of the selection, can be moved independently
 * but are constrained to prevent overlap.
 *
 * Unlike the standard Material 3 RangeSlider, this component uses [WarpRangeSliderState] to manage
 * the selected items and their positions. Each step on the slider corresponds to an item in the list.
 *
 * **Key differences from Material 3 RangeSlider:**
 *
 * * Operates on a discrete set of items instead of a continuous range.
 * * Uses [WarpRangeSliderState] to manage the selected items and their positions.
 * * Prevents thumb overlap by constraining their movement based on the selected items.
 * * Provides callbacks for value changes and when the drag gesture is finished.
 * * Offers options to reset the selection to the start or end when the corresponding thumb reaches the terminal.
 * * Allows blocking the drag gesture.
 *
 * @param modifier Modifiers to be applied to the Range Slider layout.
 * @param items The list of items to be displayed on the slider.
 * @param enabled Whether the component is enabled and can be interacted with.
 * @param initialStartItem The initial item selected by the start thumb.
 * @param initialEndItem The initial item selected by the end thumb.
 * @param onValueChangeFinished Callback invoked when the user finishes dragging either thumb.
 * @param onLeftValueChanged Callback invoked when the value of the left thumb changes.
 * @param onRightValueChanged Callback invoked when the value of the right thumb changes.
 * @param resetAtStartTerminal Whether to reset the selection to the start when the start thumb reaches the start terminal.
 * @param resetAtEndTerminal Whether to reset the selection to the end when the end thumb reaches the end terminal.
 * @param blockDrag Whether to block the drag gesture.
 * @param startInteractionSource The [MutableInteractionSource] for the start thumb.
 * @param endInteractionSource The [MutableInteractionSource] for the end thumb.
 */

@Composable
@ExperimentalMaterial3Api
fun WarpRangeSlider(
    modifier: Modifier = Modifier,
    items: List<Any>,
    enabled: Boolean = true,
    initialStartItem: Any? = null,
    initialEndItem: Any? = null,
    onValueChangeFinished: (() -> Unit) = {},
    onLeftValueChanged: ((Any) -> Unit) = {},
    onRightValueChanged: ((Any) -> Unit) = {},
    resetAtStartTerminal: Boolean = false,
    resetAtEndTerminal: Boolean = false,
    blockDrag: Boolean = false,
    showTooltips: Boolean = true,
    startInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    endInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val startItem = initialStartItem ?: items.first()
    val endItem = initialEndItem ?: items.last()


    val rangeSliderState = WarpRangeSliderState(
        initialStartItem = startItem,
        initialEndItem = endItem,
        items = items,
        resetAtStartTerminal = resetAtStartTerminal,
        resetAtEndTerminal = resetAtEndTerminal,
        onLeftValueChanged = onLeftValueChanged,
        onRightValueChanged = onRightValueChanged,
        onValueChangeFinished = onValueChangeFinished
    )


    val startThumb: @Composable (WarpRangeSliderState) -> Unit = {
        WarpSliderDefaults.Thumb(
            interactionSource = startInteractionSource,
            enabled = enabled
        )
    }

    val endThumb: @Composable (WarpRangeSliderState) -> Unit = {
        WarpSliderDefaults.Thumb(
            interactionSource = endInteractionSource,
            enabled = enabled
        )
    }
    val track: @Composable (WarpRangeSliderState) -> Unit = { state ->
        WarpSliderDefaults.Track(
            enabled = enabled,
            state = state
        )
    }

    LaunchedEffect(blockDrag) {
        rangeSliderState.blockDrag = blockDrag
    }

    WarpRangeSliderImpl(
        modifier = modifier,
        state = rangeSliderState,
        enabled = enabled,
        startInteractionSource = startInteractionSource,
        endInteractionSource = endInteractionSource,
        startThumb = startThumb,
        endThumb = endThumb,
        track = track,
        showTooltips = showTooltips
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WarpRangeSliderImpl(
    modifier: Modifier,
    state: WarpRangeSliderState,
    enabled: Boolean,
    startInteractionSource: MutableInteractionSource,
    endInteractionSource: MutableInteractionSource,
    startThumb: @Composable ((WarpRangeSliderState) -> Unit),
    endThumb: @Composable ((WarpRangeSliderState) -> Unit),
    track: @Composable ((WarpRangeSliderState) -> Unit),
    showTooltips: Boolean
) {
    state.isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

    val pressDrag = Modifier.rangeSliderPressDragModifier(
        state,
        startInteractionSource,
        endInteractionSource,
        enabled
    )

    val startThumbSemantics = Modifier.rangeSliderStartThumbSemantics(state, enabled)
    val endThumbSemantics = Modifier.rangeSliderEndThumbSemantics(state, enabled)

    val startContentDescription = "Slider Range Start"
    val endContentDescription = "Slider Range End"

    val tooltipPaddingPx = with(LocalDensity.current) { dimensions.space1.roundToPx() }

    val startTooltipText by remember {
        derivedStateOf { state.currentLeftItem.toString() }
    }
    val startTooltipState = remember { WarpTooltipState(false) }

    val endTooltipText by remember {
        derivedStateOf { state.currentRightItem.toString() }
    }
    val endTooltipState = remember { WarpTooltipState(false) }

    if (showTooltips) {
        LaunchedEffect(startInteractionSource) {
            startInteractionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> startTooltipState.isVisible = true
                    is PressInteraction.Release -> startTooltipState.isVisible = false
                    is PressInteraction.Cancel -> startTooltipState.isVisible = false
                    is DragInteraction.Start -> startTooltipState.isVisible = true
                    is DragInteraction.Stop -> startTooltipState.isVisible = false
                    is DragInteraction.Cancel -> startTooltipState.isVisible = false
                }
            }
        }
        LaunchedEffect(endInteractionSource) {
            endInteractionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> endTooltipState.isVisible = true
                    is PressInteraction.Release -> endTooltipState.isVisible = false
                    is PressInteraction.Cancel -> endTooltipState.isVisible = false
                    is DragInteraction.Start -> endTooltipState.isVisible = true
                    is DragInteraction.Stop -> endTooltipState.isVisible = false
                    is DragInteraction.Cancel -> endTooltipState.isVisible = false
                }
            }
        }
    }

    Layout(
        {
            if (showTooltips) {
                Box(modifier = Modifier
                    .layoutId(WarpRangeSliderComponents.STARTTOOLTIP)
                    .semantics(mergeDescendants = true) {
                        contentDescription = startContentDescription
                    }
                    .focusable(enabled, startInteractionSource)
                    .then(startThumbSemantics))
                {
                    WarpTooltip(
                        state = startTooltipState,
                        text = startTooltipText,
                        edge = Edge.Bottom,
                        autoCenter = false,
                        inline = true
                    )
                }
                Box(modifier = Modifier
                    .layoutId(WarpRangeSliderComponents.ENDTOOLTIP)
                    .semantics(mergeDescendants = true) {
                        contentDescription = startContentDescription
                    }
                    .focusable(enabled, startInteractionSource)
                    .then(startThumbSemantics))
                {
                    WarpTooltip(
                        state = endTooltipState,
                        text = endTooltipText,
                        edge = Edge.Bottom,
                        autoCenter = false,
                        inline = true
                    )
                }
            }
            Box(modifier = Modifier
                .layoutId(WarpRangeSliderComponents.STARTTHUMB)
                .semantics(mergeDescendants = true) {
                    contentDescription = startContentDescription
                }
                .focusable(enabled, startInteractionSource)
                .then(startThumbSemantics)
            ) {
                startThumb(state)
            }
            Box(modifier = Modifier
                .layoutId(WarpRangeSliderComponents.ENDTHUMB)
                .semantics(mergeDescendants = true) {
                    contentDescription = endContentDescription
                }
                .focusable(enabled, endInteractionSource)
                .then(endThumbSemantics)
            ) { endThumb(state) }
            Box(modifier = Modifier.layoutId(WarpRangeSliderComponents.TRACK)) {
                track(state)
            }
        },
        modifier = modifier
            .minimumInteractiveComponentSize()
            .requiredSizeIn(
                minWidth = dimensions.space25,
                minHeight = dimensions.space25
            )
            .then(pressDrag)
    ) { measurables, constraints ->

        val startThumbPlaceable = measurables.fastFirst {
            it.layoutId == WarpRangeSliderComponents.STARTTHUMB
        }.measure(
            constraints
        )

        val startTooltipPlaceable = if (showTooltips) {
            measurables.fastFirst {
                it.layoutId == WarpRangeSliderComponents.STARTTOOLTIP
            }.measure(
                constraints
            )
        } else null

        val endTooltipPlaceable = if (showTooltips) {
            measurables.fastFirst {
                it.layoutId == WarpRangeSliderComponents.ENDTOOLTIP
            }.measure(
                constraints
            )
        } else null

        val endThumbPlaceable = measurables.fastFirst {
            it.layoutId == WarpRangeSliderComponents.ENDTHUMB
        }.measure(
            constraints
        )

        val trackPlaceable = measurables.fastFirst {
            it.layoutId == WarpRangeSliderComponents.TRACK
        }.measure(
            constraints.offset(
                horizontal = -(startThumbPlaceable.width + endThumbPlaceable.width) / 2
            ).copy(minHeight = 0)
        )

        val sliderWidth = trackPlaceable.width +
                (startThumbPlaceable.width + endThumbPlaceable.width) / 2

        val sliderHeight = maxOf(
            trackPlaceable.height,
            startThumbPlaceable.height,
            endThumbPlaceable.height
        )

        state.startThumbWidth = startThumbPlaceable.width.toFloat()
        state.endThumbWidth = endThumbPlaceable.width.toFloat()
        state.totalWidth = sliderWidth

        state.setUpSteps()

        val trackOffsetX = startThumbPlaceable.width / 2
        val startThumbOffsetX = (trackPlaceable.width * state.coercedActiveRangeStartAsFraction)
            .roundToInt()

        // When start thumb and end thumb have different widths,
        // we need to add a correction for the centering of the slider.
        val endCorrection = (startThumbPlaceable.width - endThumbPlaceable.width) / 2
        val endThumbOffsetX =
            (trackPlaceable.width * state.coercedActiveRangeEndAsFraction + endCorrection)
                .roundToInt()

        val trackOffsetY = (sliderHeight - trackPlaceable.height) / 2
        val startThumbOffsetY = (sliderHeight - startThumbPlaceable.height) / 2
        val endThumbOffsetY = (sliderHeight - endThumbPlaceable.height) / 2

        val startTooltipOffsetX =
            startTooltipPlaceable?.let { startThumbOffsetX + (startThumbPlaceable.width - startTooltipPlaceable.width) / 2 }
                ?: 0
        val startTooltipOffsetY =
            startTooltipPlaceable?.let { startThumbOffsetY - tooltipPaddingPx - startTooltipPlaceable.height }
                ?: 0
        val endTooltipOffsetX =
            endTooltipPlaceable?.let { endThumbOffsetX + (endThumbPlaceable.width - endTooltipPlaceable.width) / 2 }
                ?: 0
        val endTooltipOffsetY =
            endTooltipPlaceable?.let { endThumbOffsetY - tooltipPaddingPx - endTooltipPlaceable.height }
                ?: 0

        layout(
            sliderWidth,
            sliderHeight
        ) {
            startTooltipPlaceable?.placeRelative(
                startTooltipOffsetX,
                startTooltipOffsetY
            )
            endTooltipPlaceable?.placeRelative(
                endTooltipOffsetX,
                endTooltipOffsetY
            )
            trackPlaceable.placeRelative(
                trackOffsetX,
                trackOffsetY
            )
            startThumbPlaceable.placeRelative(
                startThumbOffsetX,
                startThumbOffsetY
            )
            endThumbPlaceable.placeRelative(
                endThumbOffsetX,
                endThumbOffsetY
            )
        }
    }
}

// Scale x1 from a1..b1 range to a2..b2 range
private fun scale(a1: Float, b1: Float, x1: Float, a2: Float, b2: Float) =
    lerp(a2, b2, calcFraction(a1, b1, x1))

// Calculate the 0..1 fraction that `pos` value represents between `a` and `b`
private fun calcFraction(a: Float, b: Float, pos: Float) =
    (if (b - a == 0f) 0f else (pos - a) / (b - a)).coerceIn(0f, 1f)

@OptIn(ExperimentalMaterial3Api::class)
@Stable
private fun Modifier.rangeSliderPressDragModifier(
    state: WarpRangeSliderState,
    startInteractionSource: MutableInteractionSource,
    endInteractionSource: MutableInteractionSource,
    enabled: Boolean
): Modifier =
    if (enabled) {
        pointerInput(startInteractionSource, endInteractionSource, state) {
            val WarpRangeSliderLogic = WarpRangeSliderLogic(
                state,
                startInteractionSource,
                endInteractionSource
            )
            coroutineScope {
                awaitEachGesture {
                    val downEvent = awaitFirstDown(requireUnconsumed = false)
                    val interaction = DragInteraction.Start()
                    var posX = if (state.isRtl)
                        state.totalWidth - downEvent.position.x else downEvent.position.x
                    val compare = WarpRangeSliderLogic.compareOffsets(posX)
                    var draggingStart = if (compare != 0) {
                        compare < 0
                    } else {
                        state.rawOffsetStart > posX
                    }

                    awaitSlop(downEvent.id, downEvent.type)?.let {
                        val slop = viewConfiguration.pointerSlop(downEvent.type)
                        val shouldUpdateCapturedThumb = abs(state.rawOffsetEnd - posX) < slop &&
                                abs(state.rawOffsetStart - posX) < slop
                        if (shouldUpdateCapturedThumb) {
                            val dir = it.second
                            draggingStart = if (state.isRtl) dir >= 0f else dir < 0f
                            posX += it.first.positionChange().x
                        }
                    }

                    if (state.blockDrag) {
                        DragInteraction.Cancel(interaction)
                        return@awaitEachGesture
                    }

                    WarpRangeSliderLogic.captureThumb(
                        draggingStart,
                        posX,
                        interaction,
                        this@coroutineScope
                    )

                    val finishInteraction = try {
                        val success = horizontalDrag(pointerId = downEvent.id) {
                            if (state.blockDrag) {
                                return@horizontalDrag
                            }
                            val deltaX = it.positionChange().x
                            state.onDrag(draggingStart, if (state.isRtl) -deltaX else deltaX)
                        }
                        if (success) {
                            DragInteraction.Stop(interaction)
                        } else {
                            DragInteraction.Cancel(interaction)
                        }
                    } catch (e: CancellationException) {
                        DragInteraction.Cancel(interaction)
                    }

                    state.gestureEndAction()
                    launch {
                        WarpRangeSliderLogic
                            .activeInteraction(draggingStart)
                            .emit(finishInteraction)
                    }
                }
            }
        }
    } else {
        this
    }

@OptIn(ExperimentalMaterial3Api::class)
private fun Modifier.rangeSliderStartThumbSemantics(
    state: WarpRangeSliderState,
    enabled: Boolean,
): Modifier {
    val valueRange = state.valueRange.start..state.activeRangeEnd

    return semantics {

        if (!enabled) disabled()
        setProgress(
            action = { targetValue ->
                var newValue = targetValue.coerceIn(
                    valueRange.start,
                    valueRange.endInclusive
                )
                val originalVal = newValue
                val resolvedValue = if (state.startSteps > 0) {
                    var distance: Float = newValue
                    for (i in 0..state.startSteps + 1) {
                        val stepValue = lerp(
                            valueRange.start,
                            valueRange.endInclusive,
                            i.toFloat() / (state.startSteps + 1)
                        )
                        if (abs(stepValue - originalVal) <= distance) {
                            distance = abs(stepValue - originalVal)
                            newValue = stepValue
                        }
                    }
                    newValue
                } else {
                    newValue
                }

                // This is to keep it consistent with AbsSeekbar.java: return false if no
                // change from current.
                if (resolvedValue == state.activeRangeStart) {
                    false
                } else {
                    val resolvedRange = WarpSliderRange(resolvedValue, state.activeRangeEnd)
                    val activeRange =
                        WarpSliderRange(state.activeRangeStart, state.activeRangeEnd)
                    if (resolvedRange != activeRange) {
                        state.activeRangeStart = resolvedRange.start
                        state.activeRangeEnd = resolvedRange.endInclusive
                    }
                    state.onValueChangeFinished()
                    true
                }
            }
        )
    }.progressSemantics(
        state.activeRangeStart,
        valueRange,
        state.startSteps
    )
}

@OptIn(ExperimentalMaterial3Api::class)
private fun Modifier.rangeSliderEndThumbSemantics(
    state: WarpRangeSliderState,
    enabled: Boolean,
): Modifier {
    val valueRange = state.activeRangeStart..state.valueRange.endInclusive

    return semantics {
        if (!enabled) disabled()

        setProgress(
            action = { targetValue ->
                var newValue = targetValue.coerceIn(valueRange.start, valueRange.endInclusive)
                val originalVal = newValue
                val resolvedValue = if (state.endSteps > 0) {
                    var distance: Float = newValue
                    for (i in 0..state.endSteps + 1) {
                        val stepValue = lerp(
                            valueRange.start,
                            valueRange.endInclusive,
                            i.toFloat() / (state.endSteps + 1)
                        )
                        if (abs(stepValue - originalVal) <= distance) {
                            distance = abs(stepValue - originalVal)
                            newValue = stepValue
                        }
                    }
                    newValue
                } else {
                    newValue
                }

                // This is to keep it consistent with AbsSeekbar.java: return false if no
                // change from current.
                if (resolvedValue == state.activeRangeEnd) {
                    false
                } else {
                    val resolvedRange = WarpSliderRange(state.activeRangeStart, resolvedValue)
                    val activeRange =
                        WarpSliderRange(state.activeRangeStart, state.activeRangeEnd)
                    if (resolvedRange != activeRange) {
                        state.activeRangeStart = resolvedRange.start
                        state.activeRangeEnd = resolvedRange.endInclusive
                    }
                    state.onValueChangeFinished()
                    true
                }
            }
        )
    }.progressSemantics(
        state.activeRangeEnd,
        valueRange,
        state.endSteps
    )
}

private suspend fun AwaitPointerEventScope.awaitSlop(
    id: PointerId,
    type: PointerType,
): Pair<PointerInputChange, Float>? {
    var initialDelta = 0f
    val postPointerSlop = { pointerInput: PointerInputChange, offset: Float ->
        pointerInput.consume()
        initialDelta = offset
    }
    val afterSlopResult = awaitHorizontalPointerSlopOrCancellation(id, type, postPointerSlop)
    return if (afterSlopResult != null) afterSlopResult to initialDelta else null
}

internal suspend fun AwaitPointerEventScope.awaitHorizontalPointerSlopOrCancellation(
    pointerId: PointerId,
    pointerType: PointerType,
    onPointerSlopReached: (change: PointerInputChange, overSlop: Float) -> Unit,
) = awaitPointerSlopOrCancellation(
    pointerId = pointerId,
    pointerType = pointerType,
    onPointerSlopReached = onPointerSlopReached,
    getDragDirectionValue = { it.x }
)

private suspend inline fun AwaitPointerEventScope.awaitPointerSlopOrCancellation(
    pointerId: PointerId,
    pointerType: PointerType,
    onPointerSlopReached: (PointerInputChange, Float) -> Unit,
    getDragDirectionValue: (Offset) -> Float,
): PointerInputChange? {
    if (currentEvent.isPointerUp(pointerId)) {
        return null // The pointer has already been lifted, so the gesture is canceled
    }
    val touchSlop = viewConfiguration.pointerSlop(pointerType)
    var pointer: PointerId = pointerId
    var totalPositionChange = 0f

    while (true) {
        val event = awaitPointerEvent()
        val dragEvent = event.changes.fastFirstOrNull { it.id == pointer }!!
        if (dragEvent.isConsumed) {
            return null
        } else if (dragEvent.changedToUpIgnoreConsumed()) {
            val otherDown = event.changes.fastFirstOrNull { it.pressed }
            if (otherDown == null) {
                // This is the last "up"
                return null
            } else {
                pointer = otherDown.id
            }
        } else {
            val currentPosition = dragEvent.position
            val previousPosition = dragEvent.previousPosition
            val positionChange = getDragDirectionValue(currentPosition) -
                    getDragDirectionValue(previousPosition)
            totalPositionChange += positionChange

            val inDirection = abs(totalPositionChange)
            if (inDirection < touchSlop) {
                // verify that nothing else consumed the drag event
                awaitPointerEvent(PointerEventPass.Final)
                if (dragEvent.isConsumed) {
                    return null
                }
            } else {
                onPointerSlopReached(
                    dragEvent,
                    totalPositionChange - (sign(totalPositionChange) * touchSlop)
                )
                if (dragEvent.isConsumed) {
                    return dragEvent
                } else {
                    totalPositionChange = 0f
                }
            }
        }
    }
}

private fun PointerEvent.isPointerUp(pointerId: PointerId): Boolean =
    changes.fastFirstOrNull { it.id == pointerId }?.pressed != true

private val mouseSlop = 0.125.dp
private val defaultTouchSlop = 18.dp // The default touch slop on Android devices
private val mouseToTouchSlopRatio = mouseSlop / defaultTouchSlop

internal fun ViewConfiguration.pointerSlop(pointerType: PointerType): Float {
    return when (pointerType) {
        PointerType.Mouse -> touchSlop * mouseToTouchSlopRatio
        else -> touchSlop
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private class WarpRangeSliderLogic(
    val state: WarpRangeSliderState,
    val startInteractionSource: MutableInteractionSource,
    val endInteractionSource: MutableInteractionSource,
) {
    fun activeInteraction(draggingStart: Boolean): MutableInteractionSource =
        if (draggingStart) startInteractionSource else endInteractionSource

    fun compareOffsets(eventX: Float): Int {
        val diffStart = abs(state.activeRangeStart - eventX)
        val diffEnd = abs(state.activeRangeEnd - eventX)
        return diffStart.compareTo(diffEnd)
    }


    fun captureThumb(
        draggingStart: Boolean,
        posX: Float,
        interaction: Interaction,
        scope: CoroutineScope,
    ) {
        state.onDrag(
            draggingStart,
            posX - if (draggingStart) state.rawOffsetStart else state.rawOffsetEnd
        )
        scope.launch {
            activeInteraction(draggingStart).emit(interaction)
        }
    }
}

@Stable
internal object WarpSliderDefaults {

    private val warpSliderColors: SliderColors
        @Composable
        get() {
            return SliderColors(
                thumbColor = colors.background.primary,
                activeTrackColor = colors.background.primary,
                activeTickColor = colors.background.primary,
                inactiveTrackColor = colors.background.disabledSubtle,
                inactiveTickColor = colors.background.disabledSubtle,
                disabledThumbColor = colors.background.disabledSubtle,
                disabledActiveTrackColor = colors.background.disabledSubtle,
                disabledActiveTickColor = colors.background.disabledSubtle,
                disabledInactiveTrackColor = colors.background.disabledSubtle,
                disabledInactiveTickColor = colors.background.disabledSubtle
            )
        }

    @Composable
    internal fun Thumb(
        interactionSource: MutableInteractionSource,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        thumbSize: DpSize = DpSize(
            dimensions.components.slider.thumbSize,
            dimensions.components.slider.thumbSize
        ),
    ) {
        val interactions = remember { mutableStateListOf<Interaction>() }
        LaunchedEffect(interactionSource) {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> interactions.add(interaction)
                    is PressInteraction.Release -> interactions.remove(interaction.press)
                    is PressInteraction.Cancel -> interactions.remove(interaction.press)
                    is DragInteraction.Start -> interactions.add(interaction)
                    is DragInteraction.Stop -> interactions.remove(interaction.start)
                    is DragInteraction.Cancel -> interactions.remove(interaction.start)
                }
            }
        }

        val elevation = if (interactions.isNotEmpty()) {
            6.dp
        } else {
            1.dp
        }

        val shape = shapes.ellipse
        val thumbColor = if (enabled) {
            if (interactions.isNotEmpty() && interactions.last() is DragInteraction.Start) {
                colors.background.primaryActive
            } else
                warpSliderColors.thumbColor
        } else warpSliderColors.disabledThumbColor

        @Suppress("DEPRECATION_ERROR")
        Spacer(
            modifier
                .size(thumbSize)
                .indication(
                    interactionSource = interactionSource,
                    indication = androidx.compose.material.ripple.rememberRipple(
                        bounded = false,
                        radius = 40.dp / 2
                    )
                )
                .hoverable(interactionSource = interactionSource)
                .shadow(if (enabled) elevation else 0.dp, shape, clip = false)
                .background(
                    thumbColor,
                    shape
                )
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun Track(
        state: WarpRangeSliderState,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
    ) {
        val inactiveTrackColor = trackColor(warpSliderColors, enabled, false)
        val activeTrackColor = trackColor(warpSliderColors, enabled, true)
        val inactiveTrackStrokeWidth = dimensions.components.slider.inactiveTrackHeight
        val activeTrackStrokeWidth = dimensions.components.slider.activeTrackHeight

        Canvas(
            modifier
                .fillMaxWidth()
                .height(dimensions.components.slider.activeTrackHeight)
        ) {
            drawTrack(
                startThumbWidth = state.startThumbWidth,
                endThumbWidth = state.endThumbWidth,
                state.activeRangeStart,
                state.activeRangeEnd,
                inactiveTrackColor,
                activeTrackColor,
                inactiveTrackStrokeWidth,
                activeTrackStrokeWidth
            )
        }
    }

    private fun DrawScope.drawTrack(
        startThumbWidth: Float,
        endThumbWidth: Float,
        activeRangeStart: Float,
        activeRangeEnd: Float,
        inactiveTrackColor: Color,
        activeTrackColor: Color,
        inactiveTrackStrokeWidth: Dp,
        activeTrackStrokeWidth: Dp
    ) {
        val isRtl = layoutDirection == LayoutDirection.Rtl
        val sliderLeft = Offset(0f, center.y)
        val sliderRight = Offset(size.width, center.y)
        val sliderStart = if (isRtl) sliderRight else sliderLeft
        val sliderEnd = if (isRtl) sliderLeft else sliderRight
        drawLine(
            inactiveTrackColor,
            sliderStart,
            sliderEnd,
            inactiveTrackStrokeWidth.toPx(),
            StrokeCap.Round
        )

        val sliderValueStart = Offset(
            sliderStart.x + activeRangeStart + startThumbWidth / 2,
            center.y
        )
        val sliderValueEnd = Offset(
            sliderStart.x + activeRangeEnd - endThumbWidth / 2,
            center.y
        )

        drawLine(
            activeTrackColor,
            sliderValueStart,
            sliderValueEnd,
            activeTrackStrokeWidth.toPx(),
            StrokeCap.Round
        )
    }

    @Stable
    internal fun trackColor(colors: SliderColors, enabled: Boolean, active: Boolean): Color =
        if (enabled) {
            if (active) colors.activeTrackColor else colors.inactiveTrackColor
        } else {
            if (active) colors.disabledActiveTrackColor else colors.disabledInactiveTrackColor
        }
}


private enum class WarpRangeSliderComponents {
    ENDTHUMB,
    STARTTHUMB,
    TRACK,
    STARTTOOLTIP,
    ENDTOOLTIP
}


/**
 * Class that holds information about [WarpRangeSlider]'s active state.
 *
 * @param initialStartItem [Any] indicates the initial
 * start item of the active range of the slider.
 * @param initialEndItem [Any] indicates the initial
 * start item of the active range of the slider. If outside of [valueRange]
 * provided, value will be coerced to this range.
 * @param items if greater than 0, specifies the items that the slider can consume. These are evenly distributed
 * in two lists of items from the left and right thumb. The list must have at least 1 value.
 * @param onValueChangeFinished lambda to be invoked when value change has ended. This callback
 * is used when the user has completed selecting a new value by ending a drag or a click.
 * @param onLeftValueChanged emits a new value when the left thumb is moved. This can be used to update
 * state elsewhere, for example a text input view.
 * @param onRightValueChanged emits a new value when the right thumb is moved. This can be used to update
 * state elsewhere, for example a text input view.
 * @param resetAtStartTerminal if true, the slider will reset to an unselected state when the left thumb
 * reaches the start of the slider.
 * @param resetAtEndTerminal if true, the slider will reset to an unselected state when the right thumb
 * reaches the end of the slider.
 */


@Stable
@ExperimentalMaterial3Api
internal data class WarpRangeSliderState(
    private val initialStartItem: Any,
    private val initialEndItem: Any,
    private val items: List<Any>,
    private val resetAtStartTerminal: Boolean = false,
    private val resetAtEndTerminal: Boolean = false,
    val onValueChangeFinished: (() -> Unit) = {},
    val onLeftValueChanged: ((Any) -> Unit) = {},
    val onRightValueChanged: ((Any) -> Unit) = {},
) {

    init {
        require(items.isNotEmpty()) { "Items must have at least one value" }
    }

    var blockDrag by mutableStateOf(false)

    private data class ItemCoordinate(val xPosition: Float)

    private val sliderValues = when {
        resetAtStartTerminal && resetAtEndTerminal -> {
            listOf(Any()) + items.map { it } + Any()
        }

        resetAtStartTerminal && !resetAtEndTerminal -> {
            listOf(Any()) + items.map { it }
        }

        !resetAtStartTerminal && resetAtEndTerminal -> {
            items.map { it } + Any()
        }

        else -> {
            items.map { it }
        }
    }

    private val leftSliderEndStepLimit =
        if (resetAtEndTerminal) sliderValues.lastIndex - 1 else sliderValues.lastIndex
    private val rightSliderStartStepLimit = if (resetAtStartTerminal) 1 else 0

    internal var valueRange by mutableStateOf(0f..1f)
    private var activeStartStepIndexState by mutableIntStateOf(0)
    private var activeEndStepIndexState by mutableIntStateOf(0)

    private var activeRangeStartState by mutableFloatStateOf(0f)
    private var activeRangeEndState by mutableFloatStateOf(1f)

    internal var currentLeftItem by mutableStateOf(initialStartItem)
    internal var currentRightItem by mutableStateOf(initialEndItem)

    private var leftItemCoordinates by mutableStateOf<List<ItemCoordinate>>(emptyList())
    private var rightItemCoordinates by mutableStateOf<List<ItemCoordinate>>(emptyList())

    /**
     * [Int] that indicates the start step of the current active range for the [WarpRangeSlider].
     */

    private var activeStartStepIndex: Int
        set(newVal) {
            activeStartStepIndexState = newVal
        }
        get() = activeStartStepIndexState

    internal var activeRangeStart: Float
        set(newVal) {
            activeRangeStartState = newVal
        }
        get() = activeRangeStartState

    /**
     * [Int] that indicates the end step of the current active range for the [WarpRangeSlider].
     */

    private var activeEndStepIndex: Int
        set(newVal) {
            activeEndStepIndexState = newVal
        }
        get() = activeEndStepIndexState

    internal var activeRangeEnd: Float
        set(newVal) {
            activeRangeEndState = newVal
        }
        get() = activeRangeEndState

    private fun setClosestStartItem(offset: Float, fromSlider: Boolean) {
        val closestStartStep =
            leftItemCoordinates.map { it.xPosition }.toFloatArray().findClosestValueIndex(offset)
        if (closestStartStep > leftSliderEndStepLimit) return
        if (closestStartStep <= activeEndStepIndex) {
            activeStartStepIndex = closestStartStep
            currentLeftItem = sliderValues[activeStartStepIndex]
            activeRangeStartState = leftItemCoordinates[closestStartStep].xPosition
            if (fromSlider) {
                onLeftValueChanged(currentLeftItem)
            }
        } else {
            // Ensure the left thumb can reach the exact step of the right thumb but not go past it
            activeStartStepIndex = activeEndStepIndex
            currentLeftItem = sliderValues[activeStartStepIndex]
            activeRangeStartState = leftItemCoordinates[activeStartStepIndex].xPosition
            if (fromSlider) {
                onLeftValueChanged(currentLeftItem)
            }
        }
    }

    private fun setClosestEndItem(offset: Float, fromSlider: Boolean) {
        val closestEndStep =
            rightItemCoordinates.map { it.xPosition }.toFloatArray().findClosestValueIndex(offset)
        if (closestEndStep < rightSliderStartStepLimit) return
        if (closestEndStep >= activeStartStepIndex) {
            activeEndStepIndex = closestEndStep
            currentRightItem = sliderValues[activeEndStepIndex]
            activeRangeEndState = rightItemCoordinates[closestEndStep].xPosition
            if (fromSlider) {
                onRightValueChanged(currentRightItem)
            }
        } else {
            // Ensure the right thumb can reach the exact step of the left thumb
            activeEndStepIndex = activeStartStepIndex
            currentRightItem = sliderValues[activeEndStepIndex]
            activeRangeEndState = rightItemCoordinates[activeEndStepIndex].xPosition
            if (fromSlider) {
                onRightValueChanged(currentRightItem)
            }
        }
    }

    internal var startThumbWidth by mutableFloatStateOf(0f)
    internal var endThumbWidth by mutableFloatStateOf(0f)
    internal var totalWidth by mutableIntStateOf(0)
    internal var rawOffsetStart by mutableFloatStateOf(0f)
    internal var rawOffsetEnd by mutableFloatStateOf(0f)

    internal var isRtl by mutableStateOf(false)

    internal val gestureEndAction: () -> Unit = {
        onValueChangeFinished()
    }

    internal fun onDrag(isStart: Boolean, offset: Float) {
        if (isStart) {
            rawOffsetStart = (rawOffsetStart + offset)
            rawOffsetEnd = leftItemCoordinates.last().xPosition

            val offsetEnd = rawOffsetEnd
            val offsetStart =
                rawOffsetStart.coerceIn(leftItemCoordinates.first().xPosition, offsetEnd)
            setClosestStartItem(offsetStart, true)
        } else {
            rawOffsetEnd = (rawOffsetEnd + offset)
            rawOffsetStart = rightItemCoordinates.first().xPosition

            val offsetStart = rawOffsetStart
            val offsetEnd =
                rawOffsetEnd.coerceIn(offsetStart, rightItemCoordinates.last().xPosition)
            setClosestEndItem(offsetEnd, true)
        }
    }

    internal val coercedActiveRangeStartAsFraction
        get() = calcFraction(
            valueRange.start,
            valueRange.endInclusive,
            activeRangeStart
        )

    internal val coercedActiveRangeEndAsFraction
        get() = calcFraction(
            valueRange.start,
            valueRange.endInclusive,
            activeRangeEnd
        )

    internal val startSteps
        get() = floor(leftItemCoordinates.size * coercedActiveRangeEndAsFraction).toInt()

    internal val endSteps
        get() = floor(rightItemCoordinates.size * (1f - coercedActiveRangeStartAsFraction)).toInt()

    private fun FloatArray.findClosestValueIndex(target: Float): Int {
        if (this.isEmpty()) throw IllegalArgumentException("Array cannot be empty")

        return this.foldIndexed(0 to this[0]) { index, closest, element ->
            if (abs(element - target) < abs(closest.second - target)) index to element else closest
        }.first
    }

    // scales float userValue within valueRange.start..valueRange.end to within minPx..maxPx
    private fun scaleToOffset(minPx: Float, maxPx: Float, userValue: Float) =
        scale(valueRange.start, valueRange.endInclusive, userValue, minPx, maxPx)

    internal fun setUpSteps() {
        if (leftItemCoordinates.isEmpty() && rightItemCoordinates.isEmpty()) {
            val stepCount = sliderValues.size

            val leftStartPx = startThumbWidth / 2
            val rightStartPx = leftStartPx + (endThumbWidth + endThumbWidth / 2)
            val rightEndPx = totalWidth - endThumbWidth / 2
            val leftEndPx = rightEndPx - (endThumbWidth + endThumbWidth / 2)

            when {
                resetAtStartTerminal && !resetAtEndTerminal -> {
                    val leftStepPxFloatArray = FloatArray(stepCount) { index ->
                        lerp(leftStartPx, leftEndPx, index / (stepCount - 1).toFloat())
                    }
                    val leftStepDifference = (leftStepPxFloatArray[1] - leftStepPxFloatArray[0]) / 2
                    val rightStartAdjustedPx = rightStartPx + leftStepDifference
                    val rightStepPxFloatArray = FloatArray(stepCount) { index ->
                        lerp(rightStartAdjustedPx, rightEndPx, index / (stepCount - 1).toFloat())
                    }
                    leftItemCoordinates = List(sliderValues.size) { index ->
                        ItemCoordinate(leftStepPxFloatArray[index])
                    }

                    rightItemCoordinates = List(sliderValues.size) { index ->
                        ItemCoordinate(rightStepPxFloatArray[index])
                    }
                }

                !resetAtStartTerminal && resetAtEndTerminal -> {
                    val rightStepPxFloatArray = FloatArray(stepCount) { index ->
                        lerp(rightStartPx, rightEndPx, index / (stepCount - 1).toFloat())
                    }
                    val rightStepDistance =
                        (rightStepPxFloatArray[1] - rightStepPxFloatArray[0]) / 2
                    val leftEndAdjustedPx = leftEndPx - rightStepDistance
                    val leftStepPxFloatArray = FloatArray(stepCount) { index ->
                        lerp(leftStartPx, leftEndAdjustedPx, index / (stepCount - 1).toFloat())
                    }
                    leftItemCoordinates = List(sliderValues.size) { index ->
                        ItemCoordinate(leftStepPxFloatArray[index])
                    }

                    rightItemCoordinates = List(sliderValues.size) { index ->
                        ItemCoordinate(rightStepPxFloatArray[index])
                    }
                }

                else -> {
                    val rightStepPxFloatArray = FloatArray(stepCount) { index ->
                        lerp(rightStartPx, rightEndPx, index / (stepCount - 1).toFloat())
                    }
                    val leftStepPxFloatArray = FloatArray(stepCount) { index ->
                        lerp(leftStartPx, leftEndPx, index / (stepCount - 1).toFloat())
                    }
                    leftItemCoordinates = List(sliderValues.size) { index ->
                        ItemCoordinate(leftStepPxFloatArray[index])
                    }

                    rightItemCoordinates = List(sliderValues.size) { index ->
                        ItemCoordinate(rightStepPxFloatArray[index])
                    }
                }
            }

            activeStartStepIndexState = sliderValues.indexOf(currentLeftItem)
            activeRangeStart = leftItemCoordinates[activeStartStepIndex].xPosition

            activeEndStepIndexState = sliderValues.indexOf(currentRightItem)
            activeRangeEnd = rightItemCoordinates[activeEndStepIndex].xPosition

            valueRange =
                leftItemCoordinates.first().xPosition..rightItemCoordinates.last().xPosition

            rawOffsetStart = scaleToOffset(
                leftItemCoordinates.first().xPosition,
                leftItemCoordinates.last().xPosition,
                activeRangeStart
            )
            rawOffsetEnd = scaleToOffset(
                rightItemCoordinates.first().xPosition,
                rightItemCoordinates.last().xPosition,
                activeRangeEnd
            )
        }
    }
}

/**
 * Immutable float range for [WarpRangeSlider]
 *
 * Used in [WarpRangeSlider] to determine the active track range for the component.
 * The range is as follows: SliderRange.start..SliderRange.endInclusive.
 */
@Immutable
@JvmInline
internal value class WarpSliderRange(
    val packedValue: Long,
) {
    /**
     * start of the [WarpSliderRange]
     */
    @Stable
    val start: Float
        get() {
            // Explicitly compare against packed values to avoid auto-boxing of Size.Unspecified
            check(this.packedValue != Unspecified.packedValue) {
                "SliderRange is unspecified"
            }
            return unpackFloat1(packedValue)
        }

    /**
     * End (inclusive) of the [WarpSliderRange]
     */
    @Stable
    val endInclusive: Float
        get() {
            // Explicitly compare against packed values to avoid auto-boxing of Size.Unspecified
            check(this.packedValue != Unspecified.packedValue) {
                "SliderRange is unspecified"
            }
            return unpackFloat2(packedValue)
        }

    companion object {
        /**
         * Represents an unspecified [WarpSliderRange] value, usually a replacement for `null`
         * when a primitive value is desired.
         */
        @Stable
        val Unspecified = WarpSliderRange(Float.NaN, Float.NaN)
    }

    /**
     * String representation of the [WarpSliderRange]
     */
    override fun toString() = if (isSpecified) {
        "$start..$endInclusive"
    } else {
        "FloatRange.Unspecified"
    }
}

/**
 * Creates a [WarpSliderRange] from a given start and endInclusive float.
 * It requires endInclusive to be >= start.
 *
 * @param start float that indicates the start of the range
 * @param endInclusive float that indicates the end of the range
 */
@Stable
internal fun WarpSliderRange(start: Float, endInclusive: Float): WarpSliderRange {
    val isUnspecified = start.isNaN() && endInclusive.isNaN()
    require(isUnspecified || start <= endInclusive) {
        "start($start) must be <= endInclusive($endInclusive)"
    }
    return WarpSliderRange(packFloats(start, endInclusive))
}

/**
 * Check for if a given [WarpSliderRange] is not [WarpSliderRange.Unspecified].
 */
@Stable
internal val WarpSliderRange.isSpecified: Boolean
    get() =
        packedValue != WarpSliderRange.Unspecified.packedValue