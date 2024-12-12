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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.ViewConfiguration
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setProgress
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
import com.schibsted.nmp.warp.theme.WarpTheme.colors
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
 *
 * [WarpRangeSlider] adaptation of Material 3 RangeSlider.
 *
 * The two values are bound by the value range but they also cannot cross each other.
 *
 * It uses the provided startThumb for the slider's start thumb and endThumb for the
 * slider's end thumb. It also uses the provided track for the slider's track. If nothing is
 * passed for these parameters, it will use [WarpSliderDefaults.Thumb] and [WarpSliderDefaults.Track]
 * for the thumbs and track.
 *
 * Unlike Material 3 RangeSlider, [WarpRangeSlider] acts on a list of items in [WarpRangeSliderState].
 * This is used to create a discrete slider with the number of steps provided, each step corresponds to
 * an index in the list of values passed to the state.
 *
 * [WarpRangeSlider] maps the steps of each thump separately. This means that the start thumb
 * and end thumb can be limited to current the step of the other and never overlap, which is an constraint that is
 * not overridable in Material 3 RangeSlider.
 *
 * @param state [WarpRangeSliderState] which contains the current values of the RangeSlider.
 * @param modifier modifiers for the Range Slider layout
 * @param enabled whether or not component is enabled and can we interacted with or not.
 * parts in different state. See [WarpSliderDefaults.colors] to customize.
 * @param startInteractionSource the [MutableInteractionSource] representing the stream of
 * [Interaction]s for the start thumb. You can create and pass in your own
 * `remember`ed instance to observe.
 * @param endInteractionSource the [MutableInteractionSource] representing the stream of
 * [Interaction]s for the end thumb. You can create and pass in your own
 * `remember`ed instance to observe.
 * @param startThumb the start thumb to be displayed on the Range Slider. The lambda receives a
 * [WarpRangeSliderState] which is used to obtain the current startThumb x position.
 * @param endThumb the end thumb to be displayed on the Range Slider. The lambda receives a
 * [WarpRangeSliderState] which is used to obtain the current endThumb x position.
 * @param track the track to be displayed on the range slider, it is placed underneath the thumb.
 * The lambda receives a [WarpRangeSliderState] which is used to obtain the current active track.
 */

@Composable
@ExperimentalMaterial3Api
fun WarpRangeSlider(
    modifier: Modifier = Modifier,
    state: WarpRangeSliderState,
    enabled: Boolean = true,
    blockDrag: Boolean = false,
    startInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    endInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
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
    val track: @Composable (WarpRangeSliderState) -> Unit = { rangeSliderState ->
        WarpSliderDefaults.Track(
            enabled = enabled,
            WarpRangeSliderState = rangeSliderState
        )
    }

    LaunchedEffect(blockDrag) {
        state.blockDrag = blockDrag
    }

    WarpRangeSliderImpl(
        modifier = modifier,
        state = state,
        enabled = enabled,
        startInteractionSource = startInteractionSource,
        endInteractionSource = endInteractionSource,
        startThumb = startThumb,
        endThumb = endThumb,
        track = track
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
    track: @Composable ((WarpRangeSliderState) -> Unit)
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

    Layout(
        {
            Box(modifier = Modifier
                .layoutId(WarpRangeSliderComponents.STARTTHUMB)
                .semantics(mergeDescendants = true) {
                    contentDescription = startContentDescription
                }
                .focusable(enabled, startInteractionSource)
                .then(startThumbSemantics)
            ) { startThumb(state) }
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
                minWidth = 20.dp,
                minHeight = 20.dp
            )
            .then(pressDrag)
    ) { measurables, constraints ->

        val startThumbPlaceable = measurables.fastFirst {
            it.layoutId == WarpRangeSliderComponents.STARTTHUMB
        }.measure(
            constraints
        )

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

        layout(
            sliderWidth,
            sliderHeight
        ) {
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

/**
 * Object to hold defaults used by [WarpSliderRange]
 */
@Stable
object WarpSliderDefaults {

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

    /**
     * The Default thumb for [WarpRangeSlider].
     *
     * @param interactionSource the [MutableInteractionSource] representing the stream of
     * [Interaction]s for this thumb. You can create and pass in your own `remember`ed
     * instance to observe
     * @param modifier the [Modifier] to be applied to the thumb.
     * @param colors [SliderColors] that will be used to resolve the colors used for this thumb in
     * different states. See [SliderDefaults.colors].
     * @param enabled controls the enabled state of this slider. When `false`, this component will
     * not respond to user input, and it will appear visually disabled and disabled to
     * accessibility services.
     */
    @Composable
    fun Thumb(
        interactionSource: MutableInteractionSource,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        thumbSize: DpSize = ThumbSize,
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
            ThumbPressedElevation
        } else {
            ThumbDefaultElevation
        }

        val shape = shapes.roundedMedium

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
                    if (enabled) warpSliderColors.thumbColor else warpSliderColors.disabledThumbColor,
                    shape
                )
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Track(
        WarpRangeSliderState: WarpRangeSliderState,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
    ) {
        val inactiveTrackColor = trackColor(warpSliderColors, enabled, false)
        val activeTrackColor = trackColor(warpSliderColors, enabled, true)

        Canvas(
            modifier
                .fillMaxWidth()
                .height(TrackHeight)
        ) {
            drawTrack(
                endThumbWidth = WarpRangeSliderState.endThumbWidth,
                WarpRangeSliderState.activeRangeStart,
                WarpRangeSliderState.activeRangeEnd,
                inactiveTrackColor,
                activeTrackColor
            )
        }
    }

    private fun DrawScope.drawTrack(
        endThumbWidth: Float,
        activeRangeStart: Float,
        activeRangeEnd: Float,
        inactiveTrackColor: Color,
        activeTrackColor: Color,
    ) {
        val isRtl = layoutDirection == LayoutDirection.Rtl
        val sliderLeft = Offset(0f, center.y)
        val sliderRight = Offset(size.width, center.y)
        val sliderStart = if (isRtl) sliderRight else sliderLeft
        val sliderEnd = if (isRtl) sliderLeft else sliderRight
        val trackStrokeWidth = TrackHeight.toPx()
        drawLine(
            inactiveTrackColor,
            sliderStart,
            sliderEnd,
            trackStrokeWidth,
            StrokeCap.Round
        )

        val sliderValueEnd = Offset(
            sliderStart.x + (activeRangeEnd - endThumbWidth / 2),
            center.y
        )

        val sliderValueStart = Offset(
            sliderStart.x + activeRangeStart,
            center.y
        )

        drawLine(
            activeTrackColor,
            sliderValueStart,
            sliderValueEnd,
            trackStrokeWidth,
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

private val ThumbWidth = 28.dp
private val ThumbHeight = 28.dp
private val ThumbSize = DpSize(ThumbWidth, ThumbHeight)
private val ThumbDefaultElevation = 1.dp
private val ThumbPressedElevation = 6.dp

private val TrackHeight = 4.0.dp

private enum class WarpRangeSliderComponents {
    ENDTHUMB,
    STARTTHUMB,
    TRACK
}

/**
 * Items passed to this class must implement this interface. This forces the user to think about
 * the values that the slider will be using. This was chosen over Any to guard against wrong data items
 * being passed to setLeftSliderFromItem and setRightSliderFromItem in [WarpRangeSliderState].
 *
 * The developer must handle the retrieval of any value before passing them to the
 * [WarpRangeSliderState].
 */

interface WarpSliderValue
data object ResetStart : WarpSliderValue
data object ResetEnd : WarpSliderValue
data class SliderValue(val value: Any) : WarpSliderValue

/**
 * Class that holds information about [WarpRangeSlider]'s active state.
 *
 * @param initialStartItem [WarpSliderValue] indicates the initial
 * start item of the active range of the slider.
 * @param initialEndItem [WarpSliderValue] indicates the initial
 * start item of the active range of the slider. If outside of [valueRange]
 * provided, value will be coerced to this range.
 * @param items if greater than 0, specifies the items that the slider can consume. These are evenly distributed
 * in two lists of items from the left and right thumb. The list must have at least 1 value.
 * @param onValueChangeFinished lambda to be invoked when value change has ended. This callback
 * is used when the user has completed selecting a new value by ending a drag or a click.
 * @param onLeftValueChanged emits a new value when the left thumb is moved. This can be used to update
 * state elsewhere, for example a text input view.
 * and [onRightValueChanged] emits a new value when the right thumb is moved. This can be used to update
 * state elsewhere, for example a text input view.
 * @param resetAtStartTerminal if true, the slider will reset to an unselected state when the left thumb
 * reaches the start of the slider.
 * @param resetAtEndTerminal if true, the slider will reset to an unselected state when the right thumb
 * reaches the end of the slider.
 */


@Stable
@ExperimentalMaterial3Api
data class WarpRangeSliderState(
    private val initialStartItem: WarpSliderValue,
    private val initialEndItem: WarpSliderValue,
    private val items: List<Any>,
    private val resetAtStartTerminal: Boolean = false,
    private val resetAtEndTerminal: Boolean = false,
    val onValueChangeFinished: (() -> Unit) = {},
    val onLeftValueChanged: ((WarpSliderValue) -> Unit) = {},
    val onRightValueChanged: ((WarpSliderValue) -> Unit) = {},
) {

    init {
        require(items.isNotEmpty()) { "Items must have at least one value" }
    }

    var blockDrag by mutableStateOf(false)

    private data class ItemCoordinate(val xPosition: Float)

    private val sliderValues = when {
        resetAtStartTerminal && resetAtEndTerminal -> {
            listOf(ResetStart) + items.map { SliderValue(it) } + ResetEnd
        }

        resetAtStartTerminal && !resetAtEndTerminal -> {
            listOf(ResetStart) + items.map { SliderValue(it) }
        }

        !resetAtStartTerminal && resetAtEndTerminal -> {
            items.map { SliderValue(it) } + ResetEnd
        }

        else -> {
            items.map { SliderValue(it) }
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

    private var currentLeftItem by mutableStateOf<WarpSliderValue>(
        initialStartItem
    )

    private var currentRightItem by mutableStateOf<WarpSliderValue>(initialEndItem)

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

    fun setLeftSliderFromItem(targetItem: WarpSliderValue) {
        //Get the coordinate value of the target item
        val targetItemIndex = sliderValues.indexOf(targetItem)
        val targetItemCoordinate = leftItemCoordinates[targetItemIndex].xPosition
        setClosestStartItem(targetItemCoordinate, fromSlider = false)
    }

    fun setRightSliderFromItem(targetItem: WarpSliderValue) {
        //Get the coordinate value of the target item
        val targetItemIndex = sliderValues.indexOf(targetItem)
        val targetItemCoordinate = rightItemCoordinates[targetItemIndex].xPosition
        setClosestEndItem(targetItemCoordinate, fromSlider = false)
    }

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