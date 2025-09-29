package com.schibsted.nmp.warp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * A page indicator in the warp design system.
 * @param modifier The modifier to be applied to the page indicator.
 * @param pageCount The total number of pages.
 * @param currentPage The current page index. Defaults to 0.
 */
@Composable
fun WarpPageIndicator(
    modifier: Modifier = Modifier,
    pageCount: Int,
    currentPage: Int = 0
) {
    Row(
        Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .then(modifier)
    ) {
        repeat(pageCount) { iteration ->
            val color =
                if (currentPage == iteration) colors.icon.selected else colors.background.disabledSubtle
            Box(
                modifier = Modifier
                    .padding(dimensions.space05)
                    .clip(CircleShape)
                    .size(dimensions.components.indicatorDotSize)
                    .background(color)
            )
        }
    }
}
