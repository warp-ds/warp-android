package com.schibsted.nmp.warp.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.schibsted.nmp.warp.theme.WarpBrandedTheme
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import java.lang.Float.max
import java.lang.Float.min
import kotlin.math.floor
import kotlin.math.max



/**
 * A vertical component to show a user where they are in a user journey
 * For more info [see](https://warp-ds.github.io/tech-docs/components/steps/)
 * @param modifier Modifier applied to the component
 * @param steps Amount of steps
 * @param activeStep Currently active step
 * @param onStepClicked will be invoked if a step is clicked
 * @param stepTitle Composable that will be drawn with the step
 * @param stepDescription Composable that will be drawn below the title
 */
@Composable
fun VerticalWarpStepIndicator(
    modifier: Modifier,
    steps: Int = 3,
    activeStep: Int = 0,
    onStepClicked: ((Int) -> Unit)?,
    stepTitle: @Composable (Int) -> Unit,
    stepDescription: @Composable (Int) -> Unit
) {
    val completedColor = WarpTheme.colors.components.stepIndicator.completed
    val unvisitedColor = WarpTheme.colors.components.stepIndicator.unvisited
    val animatedStep = animateFloatAsState(targetValue = activeStep.toFloat(), label = "lineAnimation").value
    val dotSize = 24.0f

    val thresholds = remember { FloatArray(steps - 1) { 1.0f } }

    Layout(
        measurePolicy = customVerticalMeasurePolicy(),
        modifier = modifier,
        content = {
            for (i in 0 until steps - 1) {
                Canvas(
                    modifier = Modifier.layoutId(StepIndicatorIds.LINE)
                ) {
                    thresholds[i] = 1 - (dotSize / (dotSize + size.height))
                    if (i < floor(animatedStep.toDouble())) {
                        drawLine(completedColor, Offset(size.width / 2, 0f), Offset(size.width / 2, size.height), strokeWidth = 4.dp.toPx())
                    } else if (i > floor(animatedStep.toDouble())) {
                        drawLine(unvisitedColor, Offset(size.width / 2, 0f), Offset(size.width / 2, size.height), strokeWidth = 4.dp.toPx())
                    } else {
                        val frac = animatedStep - i
                        val tFrac = frac / thresholds[i]
                        val fracHeight = min(tFrac * size.height, size.height)
                        if (fracHeight > 0.0f) {
                            drawLine(
                                completedColor,
                                Offset(size.width / 2, 0f),
                                Offset(size.width / 2, fracHeight),
                                strokeWidth = 4.dp.toPx()
                            )
                        }
                        if (fracHeight < size.height) {
                            drawLine(
                                unvisitedColor,
                                Offset(size.width / 2, fracHeight),
                                Offset(size.width / 2, size.height),
                                strokeWidth = 4.dp.toPx()
                            )
                        }
                    }
                }
            }

            val p = rememberVectorPainter(image = Icons.Filled.Check)
            val backgroundColor = WarpTheme.colors.background

            for (i in 0 until steps) {
                Canvas(
                    modifier = Modifier
                        .layoutId(StepIndicatorIds.DOT)
                        .clip(CircleShape)
                        .clickable { onStepClicked?.let { it(i) } }
                ) {
                    when {
                        (i < floor(animatedStep.toDouble())) -> {
                            drawCircle(completedColor)
                            with(p) {
                                draw(size, colorFilter = ColorFilter.tint(backgroundColor))
                            }
                        }
                        (i.toDouble() == floor(animatedStep.toDouble())) -> {
                            val frac = animatedStep - i
                            drawCircle(completedColor)
                            with(p) {
                                draw(
                                    size,
                                    alpha = frac,
                                    colorFilter = ColorFilter.tint(backgroundColor))
                            }
                        }
                        (i > floor(animatedStep.toDouble() + 1)) -> {
                            drawCircle(unvisitedColor, style = Stroke(6f), radius = size.minDimension/2f - 3f)
                        }
                        else -> {
                            if (thresholds[i - 1] == 0.0f) return@Canvas
                            val frac = animatedStep - (i - 1)
                            drawCircle(unvisitedColor, style = Stroke(6f), radius = size.minDimension/2f - 3f)
                            if (frac > thresholds[i - 1]) {
                                val tFrac = (frac - thresholds[i - 1]) / (1 - thresholds[i - 1])
                                drawRect(completedColor, Offset.Zero, size.copy(height = size.height * tFrac), blendMode = BlendMode.SrcAtop)
                            }
                        }
                    }
                }
            }
            for (i in 0 until steps) {
                Box(
                    modifier = Modifier.layoutId(StepIndicatorIds.LABEL)
                ) {
                    stepTitle(i)
                }
                Box(
                    modifier = Modifier.layoutId(StepIndicatorIds.DESCRIPTION)
                ) {
                    stepDescription(i)
                }
            }
        }
    )
}

private fun customVerticalMeasurePolicy() = MeasurePolicy { measurables, constraints ->
    val width = constraints.maxWidth
    val dotPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.DOT}.map {
        it.measure(
            Constraints(
                minWidth = 24.dp.roundToPx(),
                maxWidth = 24.dp.roundToPx(),
                minHeight = 24.dp.roundToPx(),
                maxHeight = 24.dp.roundToPx()
            )
        )
    }
    val dotSize = dotPlaceables.first().height
    val textConstraints = constraints.copy(minWidth = 0, maxWidth = constraints.maxWidth - dotSize)
    val labelPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.LABEL }.map { it.measure(textConstraints) }
    val descriptionPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.DESCRIPTION }.map { it.measure(textConstraints) }

    val lines = measurables.filter { it.layoutId == StepIndicatorIds.LINE }

    val dotPlacements = mutableListOf<Pair<Placeable, IntOffset>>()
    val descriptionPlacements = mutableListOf<Pair<Placeable, IntOffset>>()
    val labelPlacements = mutableListOf<Pair<Placeable, IntOffset>>()
    val linePlacements = mutableListOf<Pair<Placeable, IntOffset>>()
    var offset = IntOffset(0, 0)
    dotPlaceables.mapIndexed { index, dot ->
        val label = labelPlaceables[index]
        val description = descriptionPlaceables[index]
        if (dot.height > label.height) {
            dotPlacements.add(dot to offset)
            val ly = offset.y + dot.height / 2 - label.height / 2
            labelPlacements.add(label to offset.copy(x = dotSize, y = ly))
            descriptionPlacements.add(description to offset.copy(x = dotSize, y = ly + label.height))
            offset = offset.copy(y = ly + label.height + description.height)
        } else {
            labelPlacements.add(label to offset.copy(x = dotSize))
            val dotY = offset.y + label.height / 2 - dot.height / 2
            dotPlacements.add(dot to offset.copy(y = dotY))
            descriptionPlacements.add(description to offset.copy(x = dotSize, y = offset.y + label.height))
            offset = offset.copy(y = offset.y + label.height + description.height)
        }
    }
    dotPlacements.windowed(2).mapIndexed { index, pairs ->
        val measurable = lines[index]
        val h = pairs.last().second.y - pairs.first().second.y - dotSize + 2

        val line = measurable.measure(Constraints(minHeight = h, maxHeight = h, minWidth = 4.dp.roundToPx(), maxWidth = 4.dp.roundToPx()))
        val x = pairs.first().second.x + dotSize / 2 - line.width / 2
        val y = pairs.first().second.y + dotSize - 1
        linePlacements.add(line to IntOffset(x, y))
    }

    val height = descriptionPlacements.last().second.y + descriptionPlacements.last().first.height
    layout(width, height) {
        dotPlacements.forEach { (placeable, offset) ->
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
private fun customHorizontalMeasurePolicy(
    steps: Int
) = MeasurePolicy { measurables, constraints ->
    val width = constraints.maxWidth
    val labelPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.LABEL }.map { it.measure(constraints) }
    val dotPlaceables = measurables.filter { it.layoutId == StepIndicatorIds.DOT}.map {
        it.measure(
            Constraints(
                minWidth = 24.dp.roundToPx(),
                maxWidth = 24.dp.roundToPx(),
                minHeight = 24.dp.roundToPx(),
                maxHeight = 24.dp.roundToPx()
            )
        )
    }
    val dotWidth = dotPlaceables.first().width

    val maxLabel = max(labelPlaceables.first().width, labelPlaceables.last().width) / 2 - dotWidth / 2
    val endSpace = max(dotWidth / 2, maxLabel)


    val space = (width - dotWidth * steps - 2 * endSpace) / (steps - 1)
    val linePlaceables = measurables.filter { it.layoutId == StepIndicatorIds.LINE}.map {
        it.measure(constraints.copy(minWidth = space + 2, maxWidth = space + 2))
    }


    val labelHeight = labelPlaceables.maxByOrNull { it.height }?.height ?: 0


    val height = dotPlaceables.first().height + labelHeight

    layout(width, height) {

        linePlaceables.forEachIndexed { index, placeable ->
            val xPos = endSpace + space * index + dotWidth * (index + 1) - 1
            placeable.place(xPos, labelHeight + dotWidth / 2)
        }
        dotPlaceables.forEachIndexed { index, placeable ->
            val xPos = endSpace + space * index + dotWidth * index
            placeable.place(xPos, labelHeight)
        }
        labelPlaceables.forEachIndexed { index, placeable ->
            val xPos = space * index + dotWidth * index
            placeable.place(xPos, 0)
        }

    }
}

@Composable
fun HorizontalWarpStepIndicator(
    modifier: Modifier = Modifier,
    steps: Int = 3,
    activeStep: Int = 0,
    onStepClicked: ((Int) -> Unit)?,
    label: @Composable ((Int) -> Unit)? = null
) {
    val completedColor = WarpTheme.colors.components.stepIndicator.completed
    val unvisitedColor = WarpTheme.colors.components.stepIndicator.unvisited
    val animatedStep = animateFloatAsState(targetValue = activeStep.toFloat(), label = "lineAnimation").value
    val dotSize = 24.0f

    var threshold by remember { mutableStateOf(1.0f) }

    Layout(
        measurePolicy = customHorizontalMeasurePolicy(
            steps
        ),
        modifier = modifier,
        content = {

            // Draw up the lines between the dots
            for (i in 0 until steps - 1) {
                Canvas(
                    Modifier
                        .layoutId(StepIndicatorIds.LINE)
                        .fillMaxWidth()) {
                    threshold = 1 - (dotSize / (dotSize + size.width))
                    if (i < floor(animatedStep.toDouble())) {
                        // The line is between two visited dots so it should be completedColor
                        drawLine(completedColor, Offset(0f, size.height/2), Offset(size.width, size.height/2), strokeWidth = 4.dp.toPx())

                    } else if (i > floor(animatedStep.toDouble())) {
                        // The line is between two dots that haven't been visited so it should be unvisitedColor
                        drawLine(unvisitedColor, Offset(0f, size.height/2), Offset(size.width, size.height/2), strokeWidth = 4.dp.toPx())

                    } else {
                        // The line is between the previously active and the next active
                        if (threshold == 0.0f) return@Canvas // just making sure that we do not get any division by 0
                        val frac = animatedStep - i
                        val tFrac = frac / threshold
                        val fracWidth = min((tFrac) * size.width, size.width)

                        // A portion of the line should be in the completed color
                        if (fracWidth > 0.0f) {
                            drawLine(
                                color = completedColor,
                                Offset(0f, size.height / 2),
                                Offset(fracWidth, size.height / 2),
                                strokeWidth = 4.dp.toPx()
                            )
                        }
                        // A portion should be in the unvisited color
                        if (fracWidth < size.width) {
                            drawLine(
                                color = unvisitedColor,
                                Offset(fracWidth, size.height / 2),
                                Offset(size.width , size.height / 2),
                                strokeWidth = 4.dp.toPx()
                            )
                        }
                    }
                }
            }

            val p = rememberVectorPainter(image = Icons.Filled.Check)
            val backgroundColor = WarpTheme.colors.background
            // Draw the dots
            for (i in 0 until steps) {
                Canvas(
                    Modifier
                        .layoutId(StepIndicatorIds.DOT)
                        .clip(CircleShape)
                        .clickable { onStepClicked?.let { it(i) } }
                ) {
                    when {
                        (i < floor(animatedStep.toDouble())) -> {
                            drawCircle(completedColor)
                            with(p) {
                                draw(size, colorFilter = ColorFilter.tint(backgroundColor))
                            }
                        }
                        (i.toDouble() == floor(animatedStep.toDouble())) -> {
                            val frac = animatedStep - i
                            drawCircle(completedColor)
                            with(p) {
                                draw(
                                    size,
                                    alpha = frac,
                                    colorFilter = ColorFilter.tint(backgroundColor))
                            }
                        }
                        (i > floor(animatedStep.toDouble() + 1)) -> {
                            drawCircle(unvisitedColor, style = Stroke(6f), radius = size.minDimension/2f - 3f)
                        }
                        else -> {
                            if (threshold == 0.0f) return@Canvas
                            val frac = animatedStep - (i - 1)
                            drawCircle(unvisitedColor, style = Stroke(6f), radius = size.minDimension/2f - 3f)
                            if (frac > threshold) {
                                val tFrac = (frac - threshold) / (1 - threshold)
                                drawRect(completedColor, Offset.Zero, size.copy(width = size.width * tFrac), blendMode = BlendMode.SrcAtop)
                            }
                        }
                    }
                }
            }

            // Draw the labels
            label?.let {
                for (i in 1..steps) {
                    Box(modifier = Modifier.layoutId(StepIndicatorIds.LABEL)) {
                        label(i)
                    }
                }
            }
        }
    )
}

private object StepIndicatorIds {
    const val LINE = "line"
    const val LABEL = "label"
    const val DOT = "dot"
    const val DESCRIPTION = "description"
}

@Preview
@Composable
fun HorizontalPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String,
) {
    var active by remember { mutableStateOf(0) }
    WarpBrandedTheme(flavor = flavor, darkTheme = isSystemInDarkTheme()) {

        Column {
            HorizontalWarpStepIndicator(
                Modifier.padding(16.dp),
                5,
                active,
                { active = it },
                { Text("step: $it") }
            )

            WarpButton(onClick = { active = (active + 1).mod(6)  }, buttonStyle = WarpButtonStyle.Primary) {
                Text("Next")
            }
        }
    }
}

@Preview
@Composable
internal fun VerticalPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String,
) {
    var active by remember { mutableStateOf(0) }
    WarpBrandedTheme(
        flavor,
        isSystemInDarkTheme()
    ) {
        Column {
            VerticalWarpStepIndicator(
                modifier = Modifier,
                steps = 5,
                activeStep = active,
                onStepClicked = { active = it },
                stepTitle = { Text("Step ${it + 1}", style = WarpTheme.typography.title4) },
                stepDescription = { Text("Description", style = WarpTheme.typography.caption) }
            )
            WarpButton(onClick = { active = (active + 1).mod(6)  }, buttonStyle = WarpButtonStyle.Primary) {
                Text("Next")
            }
        }
    }
}
