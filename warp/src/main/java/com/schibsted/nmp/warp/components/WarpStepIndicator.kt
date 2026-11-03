package com.schibsted.nmp.warp.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.StepIndicatorDimensions
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.typography
import java.lang.Float.min
import kotlin.math.floor


/**
 * A vertical component to show a user where they are in a user journey
 * For more info [see](https://warp-ds.github.io/tech-docs/components/steps/)
 * @param modifier Modifier applied to the component
 * @param steps The steps, in order. Must contain more than 1 step. The track after a step is
 * filled when the step is [WarpStepState.Complete]
 * @param highestEnabledStep Index of the last clickable step, counting from 0. Defaults to the
 * last step
 * @param type Whether the indicators show a dot or the step number. See [WarpStepIndicatorType]
 * @param onStepClicked Invoked with the index of the clicked step, counting from 0
 * @param stepContent Composable containing the content of each step, given its index counting
 * from 0
 */
@Composable
fun VerticalWarpStepIndicator(
    modifier: Modifier = Modifier,
    steps: List<WarpStep>,
    highestEnabledStep: Int = steps.lastIndex,
    type: WarpStepIndicatorType = WarpStepIndicatorType.Default,
    onStepClicked: ((Int) -> Unit)? = null,
    stepContent: @Composable (Int) -> Unit
) {
    require(steps.size > 1) { "StepIndicator must have more than 1 step" }

    val colors = stepIndicatorStyle()

    val states = remember(steps) { steps.map { it.state } }
    val progress = states.progress()
    val animatedStep =
        animateFloatAsState(targetValue = progress.toFloat(), label = "lineAnimation").value
    val componentDimensions = dimensions.components.stepIndicator

    val checkIcon = rememberStepIcon(R.drawable.step_indicator_checkmark)
    val errorIcon = rememberStepIcon(R.drawable.step_indicator_cross)
    val numbers = rememberStepNumbers(steps.size, type)

    val thresholds = remember(steps.size) { FloatArray(steps.size - 1) { 1.0f } }

    Layout(
        measurePolicy = customVerticalMeasurePolicy(
            dimensions = componentDimensions,
            trackGap = dimensions.space05,
            stepSpacing = dimensions.space3 + dimensions.space05
        ),
        modifier = modifier.semantics { isTraversalGroup = true },
        content = {
            for (i in 0 until steps.lastIndex) {
                Canvas(
                    modifier = Modifier.layoutId(StepIndicatorIds.TRACK)
                ) {
                    // In the vertical configuration the tracks have different length so we need to
                    // calculate the break point for each individually
                    thresholds[i] =
                        1 - (componentDimensions.indicatorSize.toPx() / (componentDimensions.indicatorSize.toPx() + size.height))

                    if (i < floor(animatedStep.toDouble())) {
                        // The track index is below the currently animated one so it is completed
                        drawLine(
                            color = colors.backgroundTrackActive,
                            start = Offset(size.width / 2, 0f),
                            end = Offset(size.width / 2, size.height),
                            strokeWidth = componentDimensions.trackWidth.toPx()
                        )
                    } else if (i > floor(animatedStep.toDouble())) {
                        // The track index is greater than the currently animated one, so it follows
                        // the state of the step before it
                        drawLine(
                            color = if (states[i] == WarpStepState.Complete) colors.backgroundTrackActive else colors.border,
                            start = Offset(size.width / 2, 0f),
                            end = Offset(size.width / 2, size.height),
                            strokeWidth = componentDimensions.trackWidth.toPx()
                        )
                    } else {
                        // The track index is the one currently being animated

                        // Calculate the current fraction of the animation for the entire section
                        val frac = animatedStep - i
                        // Calculate the fraction for the track part
                        val tFrac = frac / thresholds[i]
                        // Calculate the height of the part of the track that is "active". Since the
                        // animation covers the indicator as well as the track the value will
                        // eventually be greater than the height we take the smaller value
                        val fracHeight = min(tFrac * size.height, size.height)

                        // Some part of the track should be "active"
                        if (fracHeight > 0.0f) {
                            drawLine(
                                color = colors.backgroundTrackActive,
                                start = Offset(size.width / 2, 0f),
                                end = Offset(size.width / 2, fracHeight),
                                strokeWidth = componentDimensions.trackWidth.toPx()
                            )
                        }
                        // Some part of the track should be "inactive"
                        if (fracHeight < size.height) {
                            drawLine(
                                color = colors.border,
                                start = Offset(size.width / 2, fracHeight),
                                end = Offset(size.width / 2, size.height),
                                strokeWidth = componentDimensions.trackWidth.toPx()
                            )
                        }
                    }
                }
            }

            for (i in steps.indices) {
                Canvas(
                    modifier = Modifier
                        .layoutId(StepIndicatorIds.INDICATOR)
                        .clip(CircleShape)
                        .screenReaderOrder(3f * i) // Indicator, label and content of each step in turn
                        .stepIndicatorSemantics(
                            step = i,
                            steps = states,
                            highestEnabledStep = highestEnabledStep,
                            onStepClicked = onStepClicked
                        )
                ) {
                    drawStepIndicator(
                        step = i,
                        state = states[i],
                        animate = states[i] == defaultStepState(i, progress),
                        animatedStep = animatedStep,
                        threshold = if (i > 0) thresholds[i - 1] else 1.0f,
                        orientation = StepIndicatorOrientation.Vertical,
                        colors = colors,
                        dimensions = componentDimensions,
                        checkIcon = checkIcon,
                        errorIcon = errorIcon,
                        number = numbers?.get(i)
                    )
                }
            }
            for (i in steps.indices) {
                Column(
                    modifier = Modifier
                        .layoutId(StepIndicatorIds.LABEL)
                        .padding(start = dimensions.space1)
                        .screenReaderOrder(3f * i + 1), // Label after its indicator
                    verticalArrangement = Arrangement.spacedBy(dimensions.space05)
                ) {
                    steps[i].title?.let {
                        WarpText(
                            it,
                            color = colors.labelColor(states[i]),
                            style = WarpTextStyle.BodyStrong
                        )
                    }
                    steps[i].description?.let {
                        WarpText(
                            it,
                            color = colors.labelColor(states[i]),
                            style = WarpTextStyle.Caption
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .layoutId(StepIndicatorIds.CONTENT)
                        .padding(start = dimensions.space1)
                        .screenReaderOrder(3f * i + 2) // Content after its label
                ) {
                    stepContent(i)
                }
            }
        }
    )
}

private fun customVerticalMeasurePolicy(
    dimensions: StepIndicatorDimensions,
    trackGap: Dp,
    stepSpacing: Dp
) = MeasurePolicy { measurables, constraints ->
    val width = constraints.maxWidth
    // First measure the indicators, their size is determined by the theme and not by available space
    val indicatorConstraint = Constraints(
        minWidth = dimensions.indicatorSize.roundToPx(),
        maxWidth = dimensions.indicatorSize.roundToPx(),
        minHeight = dimensions.indicatorSize.roundToPx(),
        maxHeight = dimensions.indicatorSize.roundToPx()
    )
    val indicatorPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.INDICATOR }.map {
        it.measure(indicatorConstraint)
    }

    val indicatorSize = indicatorPlaceables.first().height

    // Next up is the text, the text can use the entire width except for that of the indicator
    val textConstraints =
        constraints.copy(minWidth = 0, maxWidth = constraints.maxWidth - indicatorSize)
    val labelPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.LABEL }
        .map { it.measure(textConstraints) }
    val descriptionPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.CONTENT }
        .map { it.measure(textConstraints) }

    val indicatorPlacements = mutableListOf<Pair<Placeable, IntOffset>>()
    val descriptionPlacements = mutableListOf<Pair<Placeable, IntOffset>>()
    val labelPlacements = mutableListOf<Pair<Placeable, IntOffset>>()
    val linePlacements = mutableListOf<Pair<Placeable, IntOffset>>()
    var offset = IntOffset(0, 0)

    // First place out indicators, titles and descriptions. The indicator and the title are top
    // aligned, like in the Figma spec, and the next step starts below both after the step spacing
    val spacing = stepSpacing.roundToPx()
    indicatorPlaceables.forEachIndexed { index, indicator ->
        val label = labelPlaceables[index]
        val description = descriptionPlaceables[index]

        indicatorPlacements.add(indicator to offset)
        labelPlacements.add(label to offset.copy(x = indicatorSize))
        descriptionPlacements.add(
            description to offset.copy(x = indicatorSize, y = offset.y + label.height)
        )
        val stepBottom = maxOf(
            offset.y + label.height + description.height,
            offset.y + indicator.height
        )
        offset = offset.copy(y = stepBottom + spacing)
    }

    val lines = measurables.filter { it.layoutId == StepIndicatorIds.TRACK }

    // Not that indicators have been placed the lines can be measured.
    indicatorPlacements.windowed(2).mapIndexed { index, pairs ->
        val measurable = lines[index]
        // The track leaves a gap to the indicators on both ends, like in the Figma spec
        val gap = trackGap.roundToPx()
        val h = (pairs.last().second.y - pairs.first().second.y - indicatorSize - 2 * gap)
            .coerceAtLeast(0)

        val line = measurable.measure(
            Constraints(
                minHeight = h,
                maxHeight = h,
                minWidth = dimensions.trackWidth.roundToPx(),
                maxWidth = dimensions.trackWidth.roundToPx()
            )
        )

        val x = pairs.first().second.x + indicatorSize / 2 - line.width / 2
        val y = pairs.first().second.y + indicatorSize + gap
        linePlacements.add(line to IntOffset(x, y))
    }

    val height = maxOf(
        descriptionPlacements.last().second.y + descriptionPlacements.last().first.height,
        indicatorPlacements.last().second.y + indicatorSize
    )
    layout(width, height) {
        indicatorPlacements.forEach { (placeable, offset) ->
            placeable.place(offset)
        }
        labelPlacements.forEach { (placeable, offset) ->
            placeable.place(offset)
        }
        descriptionPlacements.forEach { (placeable, offset) ->
            placeable.place(offset)
        }
        linePlacements.forEach { (placeable, offset) ->
            placeable.place(offset)
        }

    }
}

/**
 * A component for showing progress through a series of steps.
 * @param modifier Modifier to be applied to the component
 * @param steps The steps, in order. Must contain more than 1 step. The track after a step is
 * filled when the step is [WarpStepState.Complete]. When the steps don't fit without breaking a
 * word, the step indicator scrolls horizontally and keeps the active step centered
 * @param highestEnabledStep Index of the last clickable step, counting from 0. Defaults to the
 * last step
 * @param type Whether the indicators show a dot or the step number. See [WarpStepIndicatorType]
 * @param onStepClicked Optional callback invoked with the index of the clicked step, counting
 * from 0
 */
@Composable
fun HorizontalWarpStepIndicator(
    modifier: Modifier = Modifier,
    steps: List<WarpStep>,
    highestEnabledStep: Int = steps.lastIndex,
    type: WarpStepIndicatorType = WarpStepIndicatorType.Default,
    onStepClicked: ((Int) -> Unit)? = null
) {
    require(steps.size > 1) { "StepIndicator must have more than 1 step" }

    val colors = stepIndicatorStyle()

    val states = remember(steps) { steps.map { it.state } }
    val progress = states.progress()
    val animatedStep =
        animateFloatAsState(targetValue = progress.toFloat(), label = "lineAnimation").value
    val dimensions = dimensions.components.stepIndicator

    val checkIcon = rememberStepIcon(R.drawable.step_indicator_checkmark)
    val errorIcon = rememberStepIcon(R.drawable.step_indicator_cross)
    val numbers = rememberStepNumbers(steps.size, type)

    var threshold by remember { mutableFloatStateOf(1.0f) }

    val scrollState = rememberScrollState()
    var contentWidth by remember { mutableIntStateOf(0) }
    var scrolledToActiveStep by remember { mutableStateOf(false) }

    BoxWithConstraints(modifier = modifier) {
        val viewportWidth = if (constraints.hasBoundedWidth) constraints.maxWidth else 0

        // When the steps don't fit, keep the active step centered. Jump there when first shown and
        // animate when the active step changes
        val activeStep = progress.coerceAtMost(steps.lastIndex)
        LaunchedEffect(activeStep, contentWidth, viewportWidth) {
            if (contentWidth <= viewportWidth) return@LaunchedEffect
            val slot = contentWidth / steps.size
            val target = (slot * activeStep + slot / 2 - viewportWidth / 2)
                .coerceIn(0, contentWidth - viewportWidth)
            if (scrolledToActiveStep) {
                scrollState.animateScrollTo(target)
            } else {
                scrollState.scrollTo(target)
                scrolledToActiveStep = true
            }
        }

        Layout(
            measurePolicy = customHorizontalMeasurePolicy(
                steps = steps.size,
                viewportWidth = viewportWidth,
                dimensions = dimensions,
                labelGap = WarpTheme.dimensions.space1,
                trackGap = WarpTheme.dimensions.space05
            ),
            modifier = Modifier
                .horizontalScroll(scrollState)
                .onSizeChanged { contentWidth = it.width }
                .semantics { isTraversalGroup = true },
            content = {
                // Draw up the lines between the dots
                for (i in 0 until steps.lastIndex) {
                    Canvas(
                        Modifier
                            .layoutId(StepIndicatorIds.TRACK)
                            .fillMaxWidth()
                    ) {
                        val indicatorSize = dimensions.indicatorSize.toPx()
                        // set the threshold now that we have the size
                        threshold = 1 - (indicatorSize / (indicatorSize + size.width))
                        if (i < floor(animatedStep.toDouble())) {
                            // The line is between two visited dots so it should be in the "active" color
                            drawLine(
                                color = colors.backgroundTrackActive,
                                start = Offset(0f, size.height / 2),
                                end = Offset(size.width, size.height / 2),
                                strokeWidth = dimensions.trackWidth.toPx()
                            )

                        } else if (i > floor(animatedStep.toDouble())) {
                            // The line is past the animated step, so it follows the state of the dot before it
                            drawLine(
                                color = if (states[i] == WarpStepState.Complete) colors.backgroundTrackActive else colors.border,
                                start = Offset(0f, size.height / 2),
                                end = Offset(size.width, size.height / 2),
                                strokeWidth = dimensions.trackWidth.toPx()
                            )

                        } else {
                            // The line is between the previously active and the next active
                            val frac = animatedStep - i
                            val tFrac = frac / threshold
                            val fracWidth = min((tFrac) * size.width, size.width)

                            // A portion of the line should be in the active color
                            if (fracWidth > 0.0f) {
                                drawLine(
                                    color = colors.backgroundTrackActive,
                                    start = Offset(0f, size.height / 2),
                                    end = Offset(fracWidth, size.height / 2),
                                    strokeWidth = dimensions.trackWidth.toPx()
                                )
                            }
                            // A portion should be in the regular color
                            if (fracWidth < size.width) {
                                drawLine(
                                    color = colors.border,
                                    start = Offset(fracWidth, size.height / 2),
                                    end = Offset(size.width, size.height / 2),
                                    strokeWidth = dimensions.trackWidth.toPx()
                                )
                            }
                        }
                    }
                }

                // Draw the dots
                for (i in steps.indices) {
                    Canvas(
                        Modifier
                            .layoutId(StepIndicatorIds.INDICATOR)
                            .clip(CircleShape)
                            .screenReaderOrder(2f * i) // Indicator and label of each step in turn
                            .stepIndicatorSemantics(
                                step = i,
                                steps = states,
                                highestEnabledStep = highestEnabledStep,
                                onStepClicked = onStepClicked
                            )
                    ) {
                        drawStepIndicator(
                            step = i,
                            state = states[i],
                            animate = states[i] == defaultStepState(i, progress),
                            animatedStep = animatedStep,
                            threshold = threshold,
                            orientation = StepIndicatorOrientation.Horizontal,
                            colors = colors,
                            dimensions = dimensions,
                            checkIcon = checkIcon,
                            errorIcon = errorIcon,
                            number = numbers?.get(i)
                        )
                    }
                }

                // Draw the labels
                for (i in steps.indices) {
                    Column(
                        modifier = Modifier
                            .layoutId(StepIndicatorIds.LABEL)
                            .screenReaderOrder(2f * i + 1) // Label after its indicator
                            .padding(horizontal = WarpTheme.dimensions.space1),
                        verticalArrangement = Arrangement.spacedBy(WarpTheme.dimensions.space05),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        steps[i].title?.let {
                            WarpText(
                                it,
                                color = colors.labelColor(states[i]),
                                textAlign = TextAlign.Center,
                                style = WarpTextStyle.BodyStrong
                            )
                        }

                        steps[i].description?.let {
                            WarpText(
                                it,
                                color = colors.labelColor(states[i]),
                                textAlign = TextAlign.Center,
                                style = WarpTextStyle.Caption
                            )
                        }
                    }
                }

            }
        )
    }

}

private fun customHorizontalMeasurePolicy(
    steps: Int,
    viewportWidth: Int,
    dimensions: StepIndicatorDimensions,
    labelGap: Dp,
    trackGap: Dp
) = MeasurePolicy { measurables, _ ->
    val labelMeasurables = measurables.filter { it.layoutId == StepIndicatorIds.LABEL }
    val indicatorSize = dimensions.indicatorSize.roundToPx()
    val gap = trackGap.roundToPx()

    // Every step gets a slot of the same width, like in the Figma spec, with its indicator and label
    // centered. The slots fill the available width, but are never narrower than the longest word
    // of a label, so words are not broken. When the slots don't fit, the step indicator scrolls
    val longestWord = labelMeasurables.maxOf { it.minIntrinsicWidth(Constraints.Infinity) }
    val slot = maxOf(viewportWidth / steps, longestWord, indicatorSize + 2 * gap)
    val width = maxOf(viewportWidth, slot * steps)
    // Center the slots in the pixels left over when dividing the available width
    val start = (width - slot * steps) / 2

    val labelPlaceables = labelMeasurables.map { it.measure(Constraints(maxWidth = slot)) }
    val indicatorPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.INDICATOR }.map {
        it.measure(Constraints.fixed(indicatorSize, indicatorSize))
    }
    // The tracks leave a gap to the indicators on both ends, like in the Figma spec
    val trackWidth = (slot - indicatorSize - 2 * gap).coerceAtLeast(0)
    val linePlaceables = measurables.filter { it.layoutId == StepIndicatorIds.TRACK }.map {
        it.measure(Constraints(minWidth = trackWidth, maxWidth = trackWidth))
    }

    val labelHeight = labelPlaceables.maxOf { it.height }
    // Only leave a gap below the labels when there are labels
    val indicatorY = if (labelHeight > 0) labelHeight + labelGap.roundToPx() else 0

    layout(width, indicatorY + indicatorSize) {
        fun slotX(index: Int) = start + slot * index
        fun indicatorX(index: Int) = slotX(index) + (slot - indicatorSize) / 2

        linePlaceables.forEachIndexed { index, placeable ->
            placeable.place(indicatorX(index) + indicatorSize + gap, indicatorY + indicatorSize / 2)
        }
        indicatorPlaceables.forEachIndexed { index, placeable ->
            placeable.place(indicatorX(index), indicatorY)
        }
        labelPlaceables.forEachIndexed { index, placeable ->
            placeable.place(slotX(index) + (slot - placeable.width) / 2, 0)
        }
    }
}

/**
 * What the step indicators show
 */
enum class WarpStepIndicatorType {
    /** Indicators show a dot */
    Default,

    /** Incomplete and in progress indicators show the step number */
    Numbered
}

/**
 * A single step in a step indicator
 * @param state The state of the step
 * @param title Optional title shown next to the indicator
 * @param description Optional description shown below the title
 */
@Immutable
data class WarpStep(
    val state: WarpStepState,
    val title: String? = null,
    val description: String? = null
)

/**
 * The state of a single step
 */
enum class WarpStepState {
    Incomplete,
    InProgress,
    Complete,
    Error
}

/**
 * Announces what the indicator draws: the position and state of the step. Clickable steps are
 * announced as disabled after [highestEnabledStep]
 */
@Composable
private fun Modifier.stepIndicatorSemantics(
    step: Int,
    steps: List<WarpStepState>,
    highestEnabledStep: Int,
    onStepClicked: ((Int) -> Unit)?
): Modifier {
    // The position also names clickable steps, the title is read right after the indicator
    val contentDescriptionText =
        stringResource(R.string.step_indicator_position, step + 1, steps.size)
    val stateDescriptionText = steps[step].description()
    return this
        .let {
            if (onStepClicked != null) {
                it.clickable(enabled = step <= highestEnabledStep) { onStepClicked(step) }
            } else {
                it
            }
        }
        .semantics {
            contentDescription = contentDescriptionText
            stateDescription = stateDescriptionText
        }
}

// The labels are laid out apart from the indicators, so screen readers need to be told to read
// the parts of each step together, in order
private fun Modifier.screenReaderOrder(index: Float) = semantics {
    isTraversalGroup = true
    traversalIndex = index
}

@Composable
private fun WarpStepState.description() = stringResource(
    when (this) {
        WarpStepState.Incomplete -> R.string.step_indicator_state_incomplete
        WarpStepState.InProgress -> R.string.step_indicator_state_in_progress
        WarpStepState.Complete -> R.string.step_indicator_state_complete
        WarpStepState.Error -> R.string.step_indicator_state_error
    }
)

internal fun linearStepStates(steps: Int, activeStep: Int): List<WarpStepState> =
    List(steps) { defaultStepState(it, activeStep) }

private fun defaultStepState(step: Int, activeStep: Int) = when {
    step < activeStep -> WarpStepState.Complete
    step == activeStep -> WarpStepState.InProgress
    else -> WarpStepState.Incomplete
}

// The first step that is not complete. The tracks animate up to it, later tracks follow the state
// of the step before them
internal fun List<WarpStepState>.progress(): Int =
    indexOfFirst { it != WarpStepState.Complete }.takeIf { it >= 0 } ?: size

private enum class StepIndicatorOrientation { Horizontal, Vertical }

// The glyphs are drawn on a canvas the size of the indicator, like in the Figma spec
@Composable
private fun rememberStepIcon(@DrawableRes id: Int) =
    rememberVectorPainter(ImageVector.vectorResource(id))

@Composable
private fun rememberStepNumbers(steps: Int, type: WarpStepIndicatorType): List<TextLayoutResult>? {
    if (type != WarpStepIndicatorType.Numbered) return null
    val textMeasurer = rememberTextMeasurer()
    val style = typography.detailStrong
    // The indicator has a fixed size, so the number ignores the font scale to stay inside it
    val density = Density(LocalDensity.current.density, fontScale = 1f)
    return remember(steps, style, textMeasurer, density) {
        List(steps) { textMeasurer.measure("${it + 1}", style, density = density) }
    }
}

/**
 * Draws a single indicator. Steps whose state matches their position relative to the progress
 * animate between states, other steps are drawn as is.
 */
private fun DrawScope.drawStepIndicator(
    step: Int,
    state: WarpStepState,
    animate: Boolean,
    animatedStep: Float,
    threshold: Float,
    orientation: StepIndicatorOrientation,
    colors: WarpStepIndicatorColors,
    dimensions: StepIndicatorDimensions,
    checkIcon: Painter,
    errorIcon: Painter,
    number: TextLayoutResult?
) {
    val iconSize = size.minDimension
    // Keep the whole stroke inside the indicator, which is clipped to a circle
    val ringRadius = size.minDimension / 2f - dimensions.borderWidth.toPx() / 2
    val ring = Stroke(dimensions.borderWidth.toPx())

    if (state == WarpStepState.Error) {
        drawCircle(colors.border, style = ring, radius = ringRadius)
        drawCenteredIcon(errorIcon, iconSize, colors.iconError)
        return
    }

    if (!animate) {
        when (state) {
            WarpStepState.Complete -> {
                drawCircle(colors.backgroundActive)
                drawCircle(colors.borderActive, style = ring, radius = ringRadius)
                drawCenteredIcon(checkIcon, iconSize, colors.icon)
            }

            WarpStepState.InProgress -> {
                drawCircle(colors.borderActive, style = ring, radius = ringRadius)
                number?.let { drawCenteredText(it, colors.text) }
            }

            else -> {
                drawCircle(colors.border, style = ring, radius = ringRadius)
                number?.let { drawCenteredText(it, colors.text) }
            }
        }
        return
    }

    when {
        // Indicator is already passed so it should have the active color and an icon
        (step < floor(animatedStep.toDouble())) -> {
            drawCircle(colors.backgroundActive)
            drawCircle(colors.borderActive, style = ring, radius = ringRadius)
            drawCenteredIcon(checkIcon, iconSize, colors.icon)
        }
        // Indicator is in progress, or the one we just passed so fade in the fill and icon by
        // adjusting the alpha
        (step.toDouble() == floor(animatedStep.toDouble())) -> {
            val frac = animatedStep - step
            drawCircle(colors.backgroundActive, alpha = frac)
            drawCircle(colors.borderActive, style = ring, radius = ringRadius)
            number?.let { drawCenteredText(it, colors.text, alpha = 1 - frac) }
            drawCenteredIcon(checkIcon, iconSize, colors.icon, alpha = frac)
        }
        // Indicator index is greater than the one we are animating towards
        (step > floor(animatedStep.toDouble() + 1)) -> {
            drawCircle(colors.border, style = ring, radius = ringRadius)
            number?.let { drawCenteredText(it, colors.text) }
        }
        // Indicator is the one we are currently animating towards
        else -> {
            if (orientation == StepIndicatorOrientation.Horizontal && threshold == 0.0f) return
            val frac = animatedStep - (step - 1)
            // First draw a regular indicator
            drawCircle(colors.border, style = ring, radius = ringRadius)
            // Check if the animation is above the threshold for when the indicator
            // should start its animation
            if (frac > threshold) {
                // Calculate the progress of the indicator part
                val indicatorFraction = (frac - threshold) / (1 - threshold)
                // Draw the active ring over the current fraction of the indicator
                val tinted = when (orientation) {
                    StepIndicatorOrientation.Horizontal -> size.copy(width = size.width * indicatorFraction)
                    StepIndicatorOrientation.Vertical -> size.copy(height = size.height * indicatorFraction)
                }
                clipRect(right = tinted.width, bottom = tinted.height) {
                    drawCircle(colors.borderActive, style = ring, radius = ringRadius)
                }
            }
            // Draw the number on top of the ring
            number?.let { drawCenteredText(it, colors.text) }
        }
    }
}

private fun DrawScope.drawCenteredIcon(
    icon: Painter,
    iconSize: Float,
    color: Color,
    alpha: Float = 1f
) {
    translate(
        left = size.width / 2 - iconSize / 2,
        top = size.height / 2 - iconSize / 2,
    ) {
        with(icon) {
            draw(
                size = Size(iconSize, iconSize),
                alpha = alpha,
                colorFilter = ColorFilter.tint(color)
            )
        }
    }
}

private fun DrawScope.drawCenteredText(
    text: TextLayoutResult,
    color: Color,
    alpha: Float = 1f
) {
    drawText(
        textLayoutResult = text,
        color = color,
        topLeft = Offset(
            x = size.width / 2 - text.size.width / 2,
            y = size.height / 2 - text.size.height / 2
        ),
        alpha = alpha
    )
}

private object StepIndicatorIds {
    const val TRACK = "track"
    const val LABEL = "label"
    const val INDICATOR = "indicator"
    const val CONTENT = "content"
}

data class WarpStepIndicatorColors(
    val background: Color,
    val backgroundActive: Color,
    val backgroundTrack: Color,
    val backgroundTrackActive: Color,
    val border: Color,
    val borderActive: Color,
    val icon: Color,
    val iconError: Color,
    val text: Color,
    val textSubtle: Color
)

private fun WarpStepIndicatorColors.labelColor(state: WarpStepState) =
    if (state == WarpStepState.Incomplete) textSubtle else text

@Composable
fun stepIndicatorStyle() = WarpStepIndicatorColors(
    background = colors.background.disabled,
    backgroundActive = colors.background.primary,
    backgroundTrack = colors.background.disabled,
    backgroundTrackActive = colors.background.primary,
    border = colors.border.default,
    borderActive = colors.border.primary,
    icon = colors.icon.inverted,
    iconError = colors.icon.default,
    text = colors.text.default,
    textSubtle = colors.text.subtle
)

@Preview
@Composable
fun HorizontalPreview() {
    var active by remember { mutableStateOf(0) }
    Column {
        HorizontalWarpStepIndicator(
            modifier = Modifier,
            steps = linearStepStates(steps = 5, activeStep = active).mapIndexed { i, state ->
                WarpStep(state = state, title = "$i", description = "Description $i")
            },
            onStepClicked = { active = it }
        )

        WarpButton(
            onClick = { active = (active + 1).mod(6) },
            style = WarpButtonStyle.Primary
        ) {
            WarpText("Next")
        }
    }
}

@Preview
@Composable
internal fun VerticalPreview() {
    var active by remember { mutableStateOf(0) }

    Column {
        VerticalWarpStepIndicator(
            modifier = Modifier,
            steps = linearStepStates(steps = 5, activeStep = active).mapIndexed { i, state ->
                WarpStep(state = state, title = "Step ${i + 1}")
            },
            onStepClicked = { active = it },
            stepContent = { WarpText("Content", style = WarpTextStyle.Caption) }
        )
        WarpButton(
            onClick = { active = (active + 1).mod(6) },
            style = WarpButtonStyle.Primary
        ) {
            WarpText("Next")
        }
    }

}
