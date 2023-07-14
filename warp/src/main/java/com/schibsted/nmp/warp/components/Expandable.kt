package com.schibsted.nmp.warp.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.schibsted.nmp.warp.theme.WarpBrandedTheme
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Expandable(
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
    title: String,
    expandedContent: @Composable () -> Unit,
) {
    var state by remember { mutableStateOf(initiallyExpanded) }
    val transition = updateTransition(targetState = state, label = "expansionTransition")
    val rotation by transition.animateFloat(label = "iconRotation") { exp ->
        if (exp) -180f else 0f
    }
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, style = WarpTheme.typography.title4)
            IconButton(onClick = { state = !state }) {
                Icon(
                     Icons.Filled.KeyboardArrowDown,
                    "TODO content description",
                    modifier = Modifier.rotate(rotation)
                )
            }
        }
        Box() {
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
private fun ExpandablePreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String,
) {
    WarpBrandedTheme(
        flavor,
        isSystemInDarkTheme()
    ) {
        Expandable(modifier = Modifier.fillMaxWidth(), false, "title") {
            Text("body text")
        }
    }
}