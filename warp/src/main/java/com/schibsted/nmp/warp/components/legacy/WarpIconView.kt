package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.unit.dp
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.ext.getTextFromIdOrString
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.koin.java.KoinJavaComponent

class WarpIconView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    val theme: LegacyWarpTheme by KoinJavaComponent.inject(LegacyWarpTheme::class.java)

    var customSize: Int = 0
        set(value) {
            field = value
            disposeComposition()
        }

    internal var iconSize = WarpIconSize.Standard
        set(value) {
            field = value
            disposeComposition()
        }

    var identifier: String? = null
        set(value) {
            field = value
            disposeComposition()
        }

    @ColorInt
    var iconColor: Int = 0
        set(value) {
            field = value
            disposeComposition()
        }

    @DrawableRes
    var iconDrawable: Int = 0
        set(value) {
            field = value
            disposeComposition()
        }

    private val iconSizeList = listOf(
        WarpIconSize.Small,
        WarpIconSize.Standard,
        WarpIconSize.Large
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpIcon, defStyleAttr) {
            customSize = getInteger(R.styleable.WarpIcon_iconCustomSize, 0)
            iconSize = iconSizeList[getInt(R.styleable.WarpIcon_size, 1)]
            identifier = getTextFromIdOrString(R.styleable.WarpIcon_iconId, context)
            iconColor = getColor(R.styleable.WarpIcon_iconColor, 0)
            iconDrawable = getResourceId(R.styleable.WarpIcon_iconDrawable, 0)
        }
    }

    @Composable
    override fun Content() {
        theme {
            val iconName = identifier ?: resources.getResourceName(iconDrawable)
            val size = if(customSize != 0) customSize.dp else when (iconSize) {
                WarpIconSize.Small -> dimensions.icon.small
                WarpIconSize.Standard -> dimensions.icon.default
                WarpIconSize.Large -> dimensions.icon.large
            }
            val iconTintColor = if (iconColor == 0) WarpTheme.colors.icon.default else Color(iconColor)
            WarpIcon(
                identifier = iconName,
                size = size,
                color = iconTintColor
            )
        }
    }
}

internal enum class WarpIconSize {
    Small,
    Standard,
    Large
}