package com.schibsted.nmp.warp.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpDimensions.adaptDpToFontScale
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions


/**
 * A component that can hide and show content. This version handles the expanded state internally
 * @param modifier Modifier applied to the view
 * @param title Text displayed next to the button
 * @param initiallyExpanded state the view will start in
 * @param type Type of the view. Default is [WarpExpandableType.Default]
 * @param expandedContent The content to display when the view is expanded
 */
@Composable
fun WarpExpandable(
    modifier: Modifier = Modifier,
    title: String,
    initiallyExpanded: Boolean = false,
    type: WarpExpandableType = WarpExpandableType.Default,
    expandedContent: @Composable () -> Unit,
) {
    var expanded by remember { mutableStateOf(initiallyExpanded) }

    val transition = updateTransition(targetState = expanded, label = "expansionTransition")
    val rotation by transition.animateFloat(label = "iconRotation") { exp ->
        if (exp) -180f else 0f
    }
    val hArrangement: Arrangement.Horizontal
    when (type) {
        WarpExpandableType.Box -> {
            hArrangement = Arrangement.SpaceBetween
            WarpBox(boxStyle = WarpBoxStyle.Neutral) {
                ExpandableContent(
                    modifier = modifier,
                    hArrangement = hArrangement,
                    title = title,
                    onExpandButtonClicked = { expanded = !expanded },
                    rotation = rotation,
                    transition = transition,
                    expandedContent = expandedContent
                )
            }
        }

        WarpExpandableType.Default -> {
            hArrangement = Arrangement.spacedBy(0.dp)
            ExpandableContent(
                modifier = modifier,
                hArrangement = hArrangement,
                title = title,
                onExpandButtonClicked = { expanded = !expanded },
                rotation = rotation,
                transition = transition,
                expandedContent = expandedContent
            )
        }
    }
}

@Composable
private fun ExpandableContent(
    modifier: Modifier,
    hArrangement: Arrangement.Horizontal,
    title: String,
    onExpandButtonClicked: () -> Unit,
    rotation: Float,
    transition: Transition<Boolean>,
    expandedContent: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = hArrangement
        ) {
            WarpText(text = title, style = WarpTextStyle.BodyStrong)
            IconButton(onClick = onExpandButtonClicked) {
                WarpIcon(
                    icon = icons.chevronDown,
                    size = adaptDpToFontScale(dimensions.icon.small),
                    color = WarpTheme.colors.icon.default,
                    modifier = Modifier.rotate(rotation)
                )
            }
        }
        Box {
            transition.AnimatedContent { exp ->
                if (exp) {
                    expandedContent()
                }
            }
        }
    }
}

enum class WarpExpandableType {
    Default,
    Box
}

@Preview
@Composable
private fun ExpandablePreview() {
    Column {
        WarpExpandable(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensions.space1),
            initiallyExpanded = false,
            title = "title",
            expandedContent = { WarpText("Expanded content") }
        )

        WarpExpandable(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensions.space1),
            initiallyExpanded = true,
            title = "Title",
            expandedContent = { WarpText("Expanded content") },
        )
    }
}
