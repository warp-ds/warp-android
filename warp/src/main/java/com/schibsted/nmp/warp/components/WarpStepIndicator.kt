package com.schibsted.nmp.warp.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalDimensions
import com.schibsted.nmp.warp.theme.LocalTypography
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
 * @param onStepClicked will be invoked if a step is clicked
 * @param stepTitle Function to provide titles for the steps
 * @param stepContent Composable containing the content of each step
 */
@Composable
fun VerticalWarpStepIndicator(
    modifier: Modifier,
    steps: Int,
    activeStep: Int,
    onStepClicked: ((Int) -> Unit)?,
    stepTitle: (Int) -> String,
    stepContent: @Composable (Int) -> Unit
) {
    require(steps > 1)
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalDimensions provides dimensions,
        LocalTypography provides typography
    ) {
        val colors = colors.components.stepIndicator

        val animatedStep =
            animateFloatAsState(targetValue = activeStep.toFloat(), label = "lineAnimation").value
        val componentDimensions = dimensions.components.stepIndicator


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
                            1 - (componentDimensions.placeholderIndicatorSize.dp.toPx() / (componentDimensions.placeholderIndicatorSize.dp.toPx() + size.height))

                        if (i < floor(animatedStep.toDouble())) {
                            // The track index is below the currently animated one so it is completed
                            drawLine(
                                color = colors.backgroundTrackActive,
                                start = Offset(size.width / 2, 0f),
                                end = Offset(size.width / 2, size.height),
                                strokeWidth = componentDimensions.placeholderTrackWidth.dp.toPx()
                            )
                        } else if (i > floor(animatedStep.toDouble())) {
                            // The track index is greater than the currently animated one so it is inactive
                            drawLine(
                                color = colors.backgroundTrack,
                                start = Offset(size.width / 2, 0f),
                                end = Offset(size.width / 2, size.height),
                                strokeWidth = componentDimensions.placeholderTrackWidth.dp.toPx()
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
                                    strokeWidth = componentDimensions.placeholderTrackWidth.dp.toPx()
                                )
                            }
                            // Some part of the track should be "inactive"
                            if (fracHeight < size.height) {
                                drawLine(
                                    color = colors.backgroundTrack,
                                    start = Offset(size.width / 2, fracHeight),
                                    end = Offset(size.width / 2, size.height),
                                    strokeWidth = componentDimensions.placeholderTrackWidth.dp.toPx()
                                )
                            }
                        }
                    }
                }

                val icon = rememberVectorPainter(image = Icons.Filled.Check)

                for (i in 0 until steps) {
                    Canvas(
                        modifier = Modifier
                            .layoutId(StepIndicatorIds.INDICATOR)
                            .clip(CircleShape)
                            .let {
                                if (onStepClicked != null) {
                                    it.clickable {
                                        onStepClicked(i)
                                    }
                                } else {
                                    it
                                }
                            }
                    ) {
                        when {
                            // Indicator is already passed so it should have the active color and an icon
                            (i < floor(animatedStep.toDouble())) -> {
                                drawCircle(colors.backgroundActive)
                                translate(
                                    left = size.width / 2 - componentDimensions.placeholderIconSize.dp.toPx() / 2,
                                    top = size.height / 2 - componentDimensions.placeholderIconSize.dp.toPx() / 2,
                                ) {
                                    with(icon) {
                                        draw(
                                            size = Size(
                                                componentDimensions.placeholderIconSize.dp.toPx(),
                                                componentDimensions.placeholderIconSize.dp.toPx()
                                            ),
                                            colorFilter = ColorFilter.tint(colors.icon)
                                        )
                                    }

                                }
                            }
                            // Indicator is the one we just passed so animate in the icon by adjusting the alpha
                            (i.toDouble() == floor(animatedStep.toDouble())) -> {
                                val frac = animatedStep - i
                                drawCircle(colors.backgroundActive)
                                translate(
                                    left = size.width / 2 - componentDimensions.placeholderIconSize.dp.toPx() / 2,
                                    top = size.height / 2 - componentDimensions.placeholderIconSize.dp.toPx() / 2,
                                ) {
                                    with(icon) {
                                        draw(
                                            size = Size(
                                                componentDimensions.placeholderIconSize.dp.toPx(),
                                                componentDimensions.placeholderIconSize.dp.toPx()
                                            ),
                                            alpha = frac,
                                            colorFilter = ColorFilter.tint(colors.icon)
                                        )
                                    }

                                }
                            }
                            // Indicator index is greater than the one we are animating towards
                            (i > floor(animatedStep.toDouble() + 1)) -> {
                                drawCircle(
                                    colors.background,
                                    style = Stroke(componentDimensions.placeholderBorderWidth.dp.toPx()),
                                    radius = size.minDimension / 2f // - dimensions.placeholderBorderWidth.dp.toPx()/2
                                )
                            }
                            // Indicator is the one we are currently animating towards
                            else -> {
                                val frac = animatedStep - (i - 1)
                                // First draw a regular indicator
                                drawCircle(
                                    color = colors.background,
                                    style = Stroke(componentDimensions.placeholderBorderWidth.dp.toPx()),
                                    radius = size.minDimension / 2f // - dimensions.placeholderBorderWidth.dp.toPx()/2
                                )
                                // Check if the animation is above the threshold for when the indicator
                                // should start its animation
                                if (frac > thresholds[i - 1]) {
                                    // Calculate the progress of the indicator part
                                    val indicatorFraction =
                                        (frac - thresholds[i - 1]) / (1 - thresholds[i - 1])
                                    // Just paint over the current fraction of the height with BlendMode.SrcAtop
                                    // to tint the indicator
                                    drawRect(
                                        color = colors.backgroundActive,
                                        topLeft = Offset.Zero,
                                        size = size.copy(height = size.height * indicatorFraction),
                                        blendMode = BlendMode.SrcAtop
                                    )
                                }
                            }
                        }
                    }
                }
                for (i in 0 until steps) {
                    Box(
                        modifier = Modifier.layoutId(StepIndicatorIds.LABEL).padding(start = dimensions.space3.dp)
                    ) {
                        Text(
                            stepTitle(i),
                            style = typography.bodyStrong
                        )
                    }
                    Box(
                        modifier = Modifier.layoutId(StepIndicatorIds.CONTENT).padding(start = dimensions.space3.dp)
                    ) {
                        stepContent(i)
                    }
                }
            }
        )
    }
}

private fun customVerticalMeasurePolicy(
    dimensions: StepIndicatorDimensions
) = MeasurePolicy { measurables, constraints ->
    val width = constraints.maxWidth
    // First measure the indicators, their size is determined by the theme and not by available space
    val indicatorConstraint = Constraints(
        minWidth = dimensions.placeholderIndicatorSize.dp.roundToPx(),
        maxWidth = dimensions.placeholderIndicatorSize.dp.roundToPx(),
        minHeight = dimensions.placeholderIndicatorSize.dp.roundToPx(),
        maxHeight = dimensions.placeholderIndicatorSize.dp.roundToPx()
    )
    val indicatorPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.INDICATOR}.map {
        it.measure(indicatorConstraint)
    }

    val indicatorSize = indicatorPlaceables.first().height

    // Next up is the text, the text can use the entire width except for that of the indicator
    val textConstraints = constraints.copy(minWidth = 0, maxWidth = constraints.maxWidth - indicatorSize)
    val labelPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.LABEL }.map { it.measure(textConstraints) }
    val descriptionPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.CONTENT }.map { it.measure(textConstraints) }

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
            descriptionPlacements.add(description to offset.copy(x = indicatorSize, y = labelY + label.height))
            // Adjust the offset for the next round
            offset = offset.copy(y = labelY + label.height + description.height)
        } else {
            labelPlacements.add(label to offset.copy(x = indicatorSize))
            val indicatorY = offset.y + label.height / 2 - indicator.height / 2
            indicatorPlacements.add(indicator to offset.copy(y = indicatorY))
            descriptionPlacements.add(description to offset.copy(x = indicatorSize, y = offset.y + label.height))
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
                minWidth = dimensions.placeholderTrackWidth.dp.roundToPx(),
                maxWidth = dimensions.placeholderTrackWidth.dp.roundToPx()
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
 * @param activeStep The currently active step
 * @param onStepClicked Optional callback for when a step is clicked
 * @param label Function to provide titles for each step
 * @param description Optional function to provide descriptions for each step
 */
@Composable
fun HorizontalWarpStepIndicator(
    modifier: Modifier = Modifier,
    steps: Int,
    activeStep: Int = 0,
    onStepClicked: ((Int) -> Unit)? = null,
    label: ((Int) -> String)? = null,
    description: ((Int) -> String)? = null,
) {
    require(steps > 1)

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalDimensions provides dimensions,
        LocalTypography provides typography
    ) {
        val colors = colors.components.stepIndicator

        val animatedStep = animateFloatAsState(targetValue = activeStep.toFloat(), label = "lineAnimation").value
        val dimensions = dimensions.components.stepIndicator

        var threshold by remember { mutableStateOf(1.0f) }

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
                        val indicatorSize = dimensions.placeholderIndicatorSize.dp.toPx()
                        // set the threshold now that we have the size
                        threshold = 1 - (indicatorSize / (indicatorSize + size.width))
                        if (i < floor(animatedStep.toDouble())) {
                            // The line is between two visited dots so it should be in the "active" color
                            drawLine(
                                color = colors.backgroundTrackActive,
                                start = Offset(0f, size.height / 2),
                                end = Offset(size.width, size.height / 2),
                                strokeWidth = dimensions.placeholderTrackWidth.dp.toPx()
                            )

                        } else if (i > floor(animatedStep.toDouble())) {
                            // The line is between two dots that haven't been visited so it should be the regular color
                            drawLine(
                                color = colors.backgroundTrack,
                                start = Offset(0f, size.height / 2),
                                end = Offset(size.width, size.height / 2),
                                strokeWidth = dimensions.placeholderTrackWidth.dp.toPx()
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
                                    strokeWidth = dimensions.placeholderTrackWidth.dp.toPx()
                                )
                            }
                            // A portion should be in the regular color
                            if (fracWidth < size.width) {
                                drawLine(
                                    color = colors.backgroundTrack,
                                    start = Offset(fracWidth, size.height / 2),
                                    end = Offset(size.width, size.height / 2),
                                    strokeWidth = dimensions.placeholderTrackWidth.dp.toPx()
                                )
                            }
                        }
                    }
                }

                val icon = rememberVectorPainter(image = Icons.Filled.Check)
                // Draw the dots
                for (i in 0 until steps) {
                    Canvas(
                        Modifier
                            .layoutId(StepIndicatorIds.INDICATOR)
                            .clip(CircleShape)
                            .clickable { onStepClicked?.let { it(i) } }
                    ) {
                        when {
                            (i < floor(animatedStep.toDouble())) -> {
                                drawCircle(colors.backgroundActive)
                                translate(
                                    left = size.width / 2 - dimensions.placeholderIconSize.dp.toPx() / 2,
                                    top = size.height / 2 - dimensions.placeholderIconSize.dp.toPx() / 2,
                                ) {
                                    with(icon) {
                                        draw(
                                            size = Size(
                                                dimensions.placeholderIconSize.dp.toPx(),
                                                dimensions.placeholderIconSize.dp.toPx()
                                            ),
                                            colorFilter = ColorFilter.tint(colors.icon)
                                        )
                                    }

                                }
                            }

                            (i.toDouble() == floor(animatedStep.toDouble())) -> {
                                val frac = animatedStep - i
                                drawCircle(colors.backgroundActive)
                                translate(
                                    left = size.width / 2 - dimensions.placeholderIconSize.dp.toPx() / 2,
                                    top = size.height / 2 - dimensions.placeholderIconSize.dp.toPx() / 2,
                                ) {
                                    with(icon) {
                                        draw(
                                            size = Size(
                                                dimensions.placeholderIconSize.dp.toPx(),
                                                dimensions.placeholderIconSize.dp.toPx()
                                            ),
                                            alpha = frac,
                                            colorFilter = ColorFilter.tint(colors.icon)
                                        )
                                    }

                                }
                            }

                            (i > floor(animatedStep.toDouble() + 1)) -> {
                                drawCircle(
                                    color = colors.background,
                                    style = Stroke(dimensions.placeholderBorderWidth.dp.toPx()),
                                    radius = size.minDimension / 2f - dimensions.placeholderBorderWidth.dp.toPx() / 2
                                )
                            }

                            else -> {
                                if (threshold == 0.0f) return@Canvas
                                val frac = animatedStep - (i - 1)
                                drawCircle(
                                    color = colors.background,
                                    style = Stroke(dimensions.placeholderBorderWidth.dp.toPx()),
                                    radius = size.minDimension / 2f - dimensions.placeholderBorderWidth.dp.toPx() / 2
                                )
                                if (frac > threshold) {
                                    val tFrac = (frac - threshold) / (1 - threshold)
                                    drawRect(
                                        colors.backgroundActive,
                                        Offset.Zero,
                                        size.copy(width = size.width * tFrac),
                                        blendMode = BlendMode.SrcAtop
                                    )
                                }
                            }
                        }
                    }
                }

                // Draw the labels
                for (i in 1..steps) {
                    Column(modifier = Modifier.layoutId(StepIndicatorIds.LABEL), horizontalAlignment = Alignment.CenterHorizontally) {
                        label?.let {
                            Text(
                                it(i),
                                textAlign = TextAlign.Center,
                                style = typography.bodyStrong
                            )
                        }

                        description?.let {
                            Text(
                                it(i),
                                textAlign = TextAlign.Center,
                                style = typography.caption
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
    dimensions: StepIndicatorDimensions
) = MeasurePolicy { measurables, constraints ->
    val width = constraints.maxWidth
    // First measure labels and indicators
    val labelConstraint = constraints.copy(
        minWidth = 0,
        maxWidth = constraints.maxWidth / steps
    )
    val labelPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.LABEL }.map { it.measure(labelConstraint) }
    val indicatorPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.INDICATOR}.map {
        it.measure(
            Constraints(
                minWidth = dimensions.placeholderIndicatorSize.dp.roundToPx(),
                maxWidth = dimensions.placeholderIndicatorSize.dp.roundToPx(),
                minHeight = dimensions.placeholderIndicatorSize.dp.roundToPx(),
                maxHeight = dimensions.placeholderIndicatorSize.dp.roundToPx()
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
    val linePlaceables = measurables.filter { it.layoutId == StepIndicatorIds.TRACK}.map {
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
            val padding = if (labelPadding > 0) labelPadding - placeable.width/2 else 0
            val xPos = padding + space * index + indicatorWidth * index
            placeable.place(xPos, 0)
        }
    }
}

private object StepIndicatorIds {
    const val TRACK = "track"
    const val LABEL = "label"
    const val INDICATOR = "indicator"
    const val CONTENT = "content"
}

@Preview
@Composable
fun HorizontalPreview() {
    var active by remember { mutableStateOf(0) }
        Column {
            HorizontalWarpStepIndicator(
                Modifier,
                5,
                active,
                { active = it },
                { "$it" },
                { "Description $it" }
            )

            WarpButton(onClick = { active = (active + 1).mod(6)  }, buttonStyle = WarpButtonStyle.Primary) {
                Text("Next")
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
                stepContent = { Text("Content", style = typography.caption) }
            )
            WarpButton(onClick = { active = (active + 1).mod(6)  }, buttonStyle = WarpButtonStyle.Primary) {
                Text("Next")
            }
        }

}
