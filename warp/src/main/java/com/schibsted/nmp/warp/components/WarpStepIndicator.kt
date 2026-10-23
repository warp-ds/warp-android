package com.schibsted.nmp.warp.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.StepIndicatorDimensions
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.typography
import java.lang.Float.min
import kotlin.math.floor


/**
 * A vertical component to show a user where they are in a user journey
 * For more info [see](https://warp-ds.github.io/tech-docs/components/steps/)
 * @param modifier Modifier applied to the component
 * @param steps Amount of steps. Must be greater than 1
 * @param activeStep Currently active step
 * @param type Whether the indicators show a dot or the step number. See [WarpStepIndicatorType]
 * @param stepState Function to provide the state of each step. Defaults to steps before
 * [activeStep] being complete, [activeStep] being in progress and the rest being incomplete
 * @param onStepClicked will be invoked if a step is clicked
 * @param stepContentDescription Function to provide content descriptions for the steps, this will
 * only be used if onStepClicked is not null
 * @param stepTitle Function to provide titles for the steps
 * @param stepDescription Function to provide descriptions for the steps
 * @param stepContent Composable containing the content of each step
 */
@Composable
fun VerticalWarpStepIndicator(
    modifier: Modifier = Modifier,
    steps: Int,
    activeStep: Int = 0,
    type: WarpStepIndicatorType = WarpStepIndicatorType.Default,
    stepState: (Int) -> WarpStepState = { defaultStepState(it, activeStep) },
    onStepClicked: ((Int) -> Unit)? = null,
    stepContentDescription: (@Composable (Int) -> String)? = null,
    stepTitle: ((Int) -> String)? = null,
    stepDescription: ((Int) -> String)? = null,
    stepContent: @Composable (Int) -> Unit
) {
    require(steps > 1) { "StepIndicator must have more than 1 step" }

    val colors = stepIndicatorStyle()

    val animatedStep =
        animateFloatAsState(targetValue = activeStep.toFloat(), label = "lineAnimation").value
    val componentDimensions = dimensions.components.stepIndicator

    val checkIcon = rememberStepIcon(R.drawable.step_indicator_checkmark)
    val errorIcon = rememberStepIcon(R.drawable.step_indicator_cross)
    val numbers = rememberStepNumbers(steps, type)

    val thresholds = remember { FloatArray(steps - 1) { 1.0f } }

    Layout(
        measurePolicy = customVerticalMeasurePolicy(componentDimensions),
        modifier = modifier,
        content = {
            for (i in 0 until steps - 1) {
                Canvas(
                    modifier = Modifier.layoutId(StepIndicatorIds.TRACK)
                ) {
                    // In the vertical configuration the tracks have different length so we need to
                    // calculate the break point for each individually
                    thresholds[i] =
                        1 - (componentDimensions.placeholderIndicatorSize.toPx() / (componentDimensions.placeholderIndicatorSize.toPx() + size.height))

                    if (i < floor(animatedStep.toDouble())) {
                        // The track index is below the currently animated one so it is completed
                        drawLine(
                            color = colors.backgroundTrackActive,
                            start = Offset(size.width / 2, 0f),
                            end = Offset(size.width / 2, size.height),
                            strokeWidth = componentDimensions.placeholderTrackWidth.toPx()
                        )
                    } else if (i > floor(animatedStep.toDouble())) {
                        // The track index is greater than the currently animated one so it is inactive
                        drawLine(
                            color = colors.border,
                            start = Offset(size.width / 2, 0f),
                            end = Offset(size.width / 2, size.height),
                            strokeWidth = componentDimensions.placeholderTrackWidth.toPx()
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
                                strokeWidth = componentDimensions.placeholderTrackWidth.toPx()
                            )
                        }
                        // Some part of the track should be "inactive"
                        if (fracHeight < size.height) {
                            drawLine(
                                color = colors.border,
                                start = Offset(size.width / 2, fracHeight),
                                end = Offset(size.width / 2, size.height),
                                strokeWidth = componentDimensions.placeholderTrackWidth.toPx()
                            )
                        }
                    }
                }
            }

            for (i in 0 until steps) {
                Canvas(
                    modifier = Modifier
                        .layoutId(StepIndicatorIds.INDICATOR)
                        .clip(CircleShape)
                        .let {
                            if (onStepClicked != null) {
                                val mod = it.clickable {
                                    onStepClicked(i)
                                }
                                if (stepContentDescription != null) {
                                    val contentDescription = stepContentDescription(i)
                                    mod.semantics {
                                        this.contentDescription = contentDescription
                                    }
                                } else {
                                    mod
                                }
                            } else {
                                it
                            }
                        }
                ) {
                    drawStepIndicator(
                        step = i,
                        state = stepState(i),
                        animate = stepState(i) == defaultStepState(i, activeStep),
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
            for (i in 0 until steps) {
                Column(
                    modifier = Modifier
                        .layoutId(StepIndicatorIds.LABEL)
                        .padding(start = dimensions.space3)
                ) {
                    stepTitle?.let {
                        WarpText(
                            it(i),
                            color = colors.labelColor(stepState(i)),
                            style = WarpTextStyle.BodyStrong
                        )
                    }
                    stepDescription?.let {
                        WarpText(
                            it(i),
                            color = colors.labelColor(stepState(i)),
                            style = WarpTextStyle.Caption
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .layoutId(StepIndicatorIds.CONTENT)
                        .padding(start = dimensions.space3)
                ) {
                    stepContent(i)
                }
            }
        }
    )
}

private fun customVerticalMeasurePolicy(
    dimensions: StepIndicatorDimensions
) = MeasurePolicy { measurables, constraints ->
    val width = constraints.maxWidth
    // First measure the indicators, their size is determined by the theme and not by available space
    val indicatorConstraint = Constraints(
        minWidth = dimensions.placeholderIndicatorSize.roundToPx(),
        maxWidth = dimensions.placeholderIndicatorSize.roundToPx(),
        minHeight = dimensions.placeholderIndicatorSize.roundToPx(),
        maxHeight = dimensions.placeholderIndicatorSize.roundToPx()
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

    // First place out indicators, titles and descriptions
    indicatorPlaceables.mapIndexed { index, indicator ->
        val label = labelPlaceables[index]
        val description = descriptionPlaceables[index]

        // If indicator is bigger than the label then the indicator decides placement,
        // otherwise the label does
        if (indicator.height > label.height) {
            indicatorPlacements.add(indicator to offset)
            val labelY = offset.y + indicator.height / 2 - label.height / 2
            labelPlacements.add(label to offset.copy(x = indicatorSize, y = labelY))
            descriptionPlacements.add(
                description to offset.copy(
                    x = indicatorSize,
                    y = labelY + label.height
                )
            )
            // Adjust the offset for the next round
            offset = offset.copy(y = labelY + label.height + description.height)
        } else {
            labelPlacements.add(label to offset.copy(x = indicatorSize))
            val indicatorY = offset.y + label.height / 2 - indicator.height / 2
            indicatorPlacements.add(indicator to offset.copy(y = indicatorY))
            descriptionPlacements.add(
                description to offset.copy(
                    x = indicatorSize,
                    y = offset.y + label.height
                )
            )
            // Adjust the offset for the next round
            offset = offset.copy(y = offset.y + label.height + description.height)
        }
    }

    val lines = measurables.filter { it.layoutId == StepIndicatorIds.TRACK }

    // Not that indicators have been placed the lines can be measured.
    indicatorPlacements.windowed(2).mapIndexed { index, pairs ->
        val measurable = lines[index]
        // The height should be just a little bigger than the space between the indicators so we do
        // not get a gap from the curvature
        val h = pairs.last().second.y - pairs.first().second.y - indicatorSize + 2

        val line = measurable.measure(
            Constraints(
                minHeight = h,
                maxHeight = h,
                minWidth = dimensions.placeholderTrackWidth.roundToPx(),
                maxWidth = dimensions.placeholderTrackWidth.roundToPx()
            )
        )

        val x = pairs.first().second.x + indicatorSize / 2 - line.width / 2
        val y = pairs.first().second.y + indicatorSize - 1
        linePlacements.add(line to IntOffset(x, y))
    }

    val height = descriptionPlacements.last().second.y + descriptionPlacements.last().first.height
    layout(width, height) {
        indicatorPlacements.forEach { (placeable, offset) ->
            // The dots have a different z index to be on the top because the line is a bit larger
            // than the space so we do not gaps.
            placeable.place(offset, 1f)
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
 * @param steps Number of steps to display in the component. Must be greater than 1
 * @param highestEnabledStep Steps with an index greater than this number will not be clickable.
 * Defaults to steps - 1
 * @param activeStep The currently active step
 * @param type Whether the indicators show a dot or the step number. See [WarpStepIndicatorType]
 * @param stepState Function to provide the state of each step. Defaults to steps before
 * [activeStep] being complete, [activeStep] being in progress and the rest being incomplete
 * @param onStepClicked Optional callback for when a step is clicked
 * @param stepContentDescription Optional function to provide content descriptions for each step
 * @param stepTitle Function to provide titles for each step
 * @param stepDescription Optional function to provide descriptions for each step
 */
@Composable
fun HorizontalWarpStepIndicator(
    modifier: Modifier = Modifier,
    steps: Int,
    activeStep: Int = 0,
    highestEnabledStep: Int = steps - 1,
    type: WarpStepIndicatorType = WarpStepIndicatorType.Default,
    stepState: (Int) -> WarpStepState = { defaultStepState(it, activeStep) },
    onStepClicked: ((Int) -> Unit)? = null,
    stepContentDescription: (@Composable (Int) -> String)? = null,
    stepTitle: ((Int) -> String)? = null,
    stepDescription: ((Int) -> String)? = null
) {
    require(steps > 1) { "StepIndicator must have more than 1 step" }

    val colors = stepIndicatorStyle()

    val animatedStep =
        animateFloatAsState(targetValue = activeStep.toFloat(), label = "lineAnimation").value
    val dimensions = dimensions.components.stepIndicator

    val checkIcon = rememberStepIcon(R.drawable.step_indicator_checkmark)
    val errorIcon = rememberStepIcon(R.drawable.step_indicator_cross)
    val numbers = rememberStepNumbers(steps, type)

    var threshold by remember { mutableFloatStateOf(1.0f) }

    Layout(
        measurePolicy = customHorizontalMeasurePolicy(
            steps,
            dimensions
        ),
        modifier = modifier,
        content = {
            // Draw up the lines between the dots
            for (i in 0 until steps - 1) {
                Canvas(
                    Modifier
                        .layoutId(StepIndicatorIds.TRACK)
                        .fillMaxWidth()
                ) {
                    val indicatorSize = dimensions.placeholderIndicatorSize.toPx()
                    // set the threshold now that we have the size
                    threshold = 1 - (indicatorSize / (indicatorSize + size.width))
                    if (i < floor(animatedStep.toDouble())) {
                        // The line is between two visited dots so it should be in the "active" color
                        drawLine(
                            color = colors.backgroundTrackActive,
                            start = Offset(0f, size.height / 2),
                            end = Offset(size.width, size.height / 2),
                            strokeWidth = dimensions.placeholderTrackWidth.toPx()
                        )

                    } else if (i > floor(animatedStep.toDouble())) {
                        // The line is between two dots that haven't been visited so it should be the regular color
                        drawLine(
                            color = colors.border,
                            start = Offset(0f, size.height / 2),
                            end = Offset(size.width, size.height / 2),
                            strokeWidth = dimensions.placeholderTrackWidth.toPx()
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
                                strokeWidth = dimensions.placeholderTrackWidth.toPx()
                            )
                        }
                        // A portion should be in the regular color
                        if (fracWidth < size.width) {
                            drawLine(
                                color = colors.border,
                                start = Offset(fracWidth, size.height / 2),
                                end = Offset(size.width, size.height / 2),
                                strokeWidth = dimensions.placeholderTrackWidth.toPx()
                            )
                        }
                    }
                }
            }

            // Draw the dots
            for (i in 0 until steps) {
                Canvas(
                    Modifier
                        .layoutId(StepIndicatorIds.INDICATOR)
                        .clip(CircleShape)
                        .let {
                            if (onStepClicked != null && i <= highestEnabledStep) {
                                val mod = it.clickable {
                                    onStepClicked(i)
                                }
                                if (stepContentDescription != null) {
                                    val contentDescription = stepContentDescription(i)
                                    mod.semantics {
                                        this.contentDescription = contentDescription
                                    }
                                } else {
                                    mod
                                }
                            } else {
                                it
                            }
                        }
                ) {
                    drawStepIndicator(
                        step = i,
                        state = stepState(i),
                        animate = stepState(i) == defaultStepState(i, activeStep),
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
            for (i in 0 until steps) {
                Column(
                    modifier = Modifier.layoutId(StepIndicatorIds.LABEL),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    stepTitle?.let {
                        WarpText(
                            it(i),
                            color = colors.labelColor(stepState(i)),
                            textAlign = TextAlign.Center,
                            style = WarpTextStyle.BodyStrong
                        )
                    }

                    stepDescription?.let {
                        WarpText(
                            it(i),
                            color = colors.labelColor(stepState(i)),
                            textAlign = TextAlign.Center,
                            style = WarpTextStyle.Caption
                        )
                    }
                }
            }

        }
    )

}

private fun customHorizontalMeasurePolicy(
    steps: Int,
    dimensions: StepIndicatorDimensions
) = MeasurePolicy { measurables, constraints ->
    val width = constraints.maxWidth
    // First measure labels and indicators
    val labelConstraint = constraints.copy(
        minWidth = 0,
        maxWidth = constraints.maxWidth / steps
    )
    val labelPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.LABEL }
        .map { it.measure(labelConstraint) }
    val indicatorPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.INDICATOR }.map {
        it.measure(
            Constraints(
                minWidth = dimensions.placeholderIndicatorSize.roundToPx(),
                maxWidth = dimensions.placeholderIndicatorSize.roundToPx(),
                minHeight = dimensions.placeholderIndicatorSize.roundToPx(),
                maxHeight = dimensions.placeholderIndicatorSize.roundToPx()
            )
        )
    }
    val indicatorWidth = indicatorPlaceables.first().width

    // Check if the labels or indicators on the end are widest

    val maxLabel = labelPlaceables.maxBy { it.width }.width
    val (labelPadding, indicatorPadding) = if (maxLabel > indicatorWidth) {
        0 to ((maxLabel - indicatorWidth) / 2)
    } else {
        (indicatorWidth / 2) to 0
    }


    val space = (width - indicatorWidth * steps - indicatorPadding * 2) / (steps - 1)
    val linePlaceables = measurables.filter { it.layoutId == StepIndicatorIds.TRACK }.map {
        // Adjust the width just a little so we do not get gaps from the curvature of the indicators
        it.measure(constraints.copy(minWidth = space + 2, maxWidth = space + 2))
    }

    val labelHeight = labelPlaceables.maxByOrNull { it.height }?.height ?: 0

    val height = indicatorPlaceables.first().height + labelHeight

    layout(width, height) {

        linePlaceables.forEachIndexed { index, placeable ->
            val padding = if (indicatorPadding > 0) indicatorPadding else 0
            val xPos = padding + space * index + indicatorWidth * (index + 1) - 1
            placeable.place(xPos, labelHeight + indicatorWidth / 2)
        }
        indicatorPlaceables.forEachIndexed { index, placeable ->
            val padding = if (indicatorPadding > 0) indicatorPadding else 0
            val xPos = padding + space * index + indicatorWidth * index
            placeable.place(xPos, labelHeight)
        }
        labelPlaceables.forEachIndexed { index, placeable ->
            val padding = if (labelPadding > 0) labelPadding - placeable.width / 2 else 0
            val xPos = padding + space * index + indicatorWidth * index
            placeable.place(xPos, 0)
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
 * The state of a single step
 */
enum class WarpStepState {
    Incomplete,
    InProgress,
    Complete,
    Error
}

internal fun defaultStepState(step: Int, activeStep: Int) = when {
    step < activeStep -> WarpStepState.Complete
    step == activeStep -> WarpStepState.InProgress
    else -> WarpStepState.Incomplete
}

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
    return remember(steps, style, textMeasurer) {
        List(steps) { textMeasurer.measure("${it + 1}", style) }
    }
}

/**
 * Draws a single indicator. Steps that are in the state given by [activeStep][defaultStepState]
 * animate between states, steps with a custom state are drawn as is.
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
    val ringRadius = when (orientation) {
        StepIndicatorOrientation.Horizontal -> size.minDimension / 2f - dimensions.placeholderBorderWidth.toPx() / 2
        StepIndicatorOrientation.Vertical -> size.minDimension / 2f
    }
    val ring = Stroke(dimensions.placeholderBorderWidth.toPx())

    if (state == WarpStepState.Error) {
        drawCircle(colors.border, style = ring, radius = ringRadius)
        drawCenteredIcon(errorIcon, iconSize, colors.iconError)
        return
    }

    if (!animate) {
        when (state) {
            WarpStepState.Complete -> {
                drawCircle(colors.backgroundActive)
                drawCenteredIcon(checkIcon, iconSize, colors.icon)
            }

            WarpStepState.InProgress -> {
                drawCircle(colors.backgroundActive, style = ring, radius = ringRadius)
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
            drawCenteredIcon(checkIcon, iconSize, colors.icon)
        }
        // Indicator is in progress, or the one we just passed so fade in the fill and icon by
        // adjusting the alpha
        (step.toDouble() == floor(animatedStep.toDouble())) -> {
            val frac = animatedStep - step
            drawCircle(colors.backgroundActive, style = ring, radius = ringRadius)
            drawCircle(colors.backgroundActive, alpha = frac)
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
                // Just paint over the current fraction with BlendMode.SrcAtop to tint the indicator
                val tinted = when (orientation) {
                    StepIndicatorOrientation.Horizontal -> size.copy(width = size.width * indicatorFraction)
                    StepIndicatorOrientation.Vertical -> size.copy(height = size.height * indicatorFraction)
                }
                drawRect(
                    color = colors.backgroundActive,
                    topLeft = Offset.Zero,
                    size = tinted,
                    blendMode = BlendMode.SrcAtop
                )
            }
            // Draw the number last so it is not tinted
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
            steps = 5,
            activeStep = active,
            onStepClicked = { active = it },
            stepTitle = { "$it" },
            stepDescription = { "Description $it" }
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
            steps = 5,
            activeStep = active,
            onStepClicked = { active = it },
            stepTitle = { "Step ${it + 1}" },
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
