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
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions


/**
 * A component that can hide and show content. This version handles the expanded state internally
 * @param modifier Modifier applied to the view
 * @param initiallyExpanded state the view will start in
 * @param title Text displayed next to the button
 * @param type Type of the view. Default is [WarpExpandableType.Default]
 * @param expandedContent The content to display when the view is expanded
 */
@Composable
fun WarpExpandable(
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
    title: String,
    type: WarpExpandableType = WarpExpandableType.Default,
    expandedContent: @Composable () -> Unit,
) {
    var expanded by remember { mutableStateOf(initiallyExpanded) }
    WarpExpandable(
        modifier = modifier,
        expanded = expanded,
        title = title,
        type = type,
        expandedContent = expandedContent,
        onExpandButtonClicked = { expanded = !expanded },
    )
}

/**
 * A version of Expandable that gives the user more control over the content and flow
 * @param modifier Modifier applied to the view
 * @param expanded State which the view should be in
 * @param title Text displayed next to the button
 * @param type Type of the view. Default is [WarpExpandableType.Default]
 * @param expandedContent Content displayed when expanded
 * @param onExpandButtonClicked Invoked when the button to expand is clicked
 */
@Composable
fun WarpExpandable(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    title: String,
    type: WarpExpandableType = WarpExpandableType.Default,
    expandedContent: @Composable () -> Unit,
    onExpandButtonClicked: () -> Unit
) {
    val transition = updateTransition(targetState = expanded, label = "expansionTransition")
    val rotation by transition.animateFloat(label = "iconRotation") { exp ->
        if (exp) -180f else 0f
    }
    var hArrangement: Arrangement.HorizontalOrVertical
    when (type) {
        WarpExpandableType.Box -> {
            hArrangement = Arrangement.SpaceBetween
            WarpBox(boxStyle = WarpBoxStyle.Neutral) {
                ExpandableContent(
                    modifier,
                    hArrangement,
                    title,
                    onExpandButtonClicked,
                    rotation,
                    transition,
                    expandedContent
                )
            }
        }

        WarpExpandableType.Default -> {
            hArrangement = Arrangement.spacedBy(0.dp)
            ExpandableContent(
                modifier,
                hArrangement,
                title,
                onExpandButtonClicked,
                rotation,
                transition,
                expandedContent
            )
        }
    }

}

@Composable
private fun ExpandableContent(
    modifier: Modifier,
    hArrangement: Arrangement.HorizontalOrVertical,
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
                    size = dimensions.icon.small,
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
            expandedContent = { WarpText("body text") }
        )

        var expanded by remember { mutableStateOf(false) }
        WarpExpandable(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensions.space1),
            expanded = !expanded,
            title = "Title",
            expandedContent = { WarpText("body text") },
            onExpandButtonClicked = { expanded = !expanded }
        )
    }
}