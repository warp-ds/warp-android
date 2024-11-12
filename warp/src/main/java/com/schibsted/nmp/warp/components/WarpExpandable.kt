package com.schibsted.nmp.warp.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
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
import com.schibsted.nmp.warp.theme.WarpTheme


/**
 * A component that can hide and show content. This version handles the expanded state internally
 * @param modifier Modifier applied to the view
 * @param initiallyExpanded state the view will start in
 * @param title Text displayed next to the button
 * @param expandedContent The content to display when the view is expanded
 */
@Composable
fun WarpExpandable(
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
    title: String,
    expandedContent: @Composable () -> Unit,
) {
    var expanded by remember { mutableStateOf(initiallyExpanded) }
    WarpExpandable(
        modifier = modifier,
        expanded = expanded,
        title = { WarpText(title, style = WarpTextStyle.Title4) },
        expandedContent = expandedContent,
        onExpandButtonClicked = { expanded = !expanded },
    )
}

/**
 * A version of Expandable that gives the user more control over the content and flow
 * @param modifier Modifier applied to the view
 * @param expanded State which the view should be in
 * @param title Content displayed next to the button
 * @param expandedContent Content displayed when expanded
 * @param onExpandButtonClicked Invoked when the button to expand is clicked
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WarpExpandable(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    title: @Composable () -> Unit,
    expandedContent: @Composable () -> Unit,
    onExpandButtonClicked: () -> Unit
) {
    val transition = updateTransition(targetState = expanded, label = "expansionTransition")
    val rotation by transition.animateFloat(label = "iconRotation") { exp ->
        if (exp) -180f else 0f
    }
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            title()
            IconButton(onClick = onExpandButtonClicked) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    tint = WarpTheme.colors.icon.default,
                    contentDescription = "TODO content description",
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

@Preview
@Composable
private fun ExpandablePreview() {
    Column {

        WarpExpandable(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), false, "title"
        ) {
            WarpText("body text")
        }
        var expanded by remember { mutableStateOf(false) }
        WarpExpandable(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            expanded = expanded,
            title = { WarpText("Here we can control the title more", style = WarpTextStyle.Title3) },
            expandedContent = { WarpText("body text") },
            onExpandButtonClicked = { expanded = !expanded}
        )
    }
}