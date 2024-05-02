package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.vectorResource
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpPill
import com.schibsted.nmp.warp.components.WarpPillStyle
import com.schibsted.nmp.warp.components.ext.getTextFromIdOrString
import com.schibsted.nmp.warp.theme.WarpTheme
import org.koin.java.KoinJavaComponent

class WarpPillView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    val theme: LegacyWarpTheme by KoinJavaComponent.inject(LegacyWarpTheme::class.java)

    var text = ""
        set(value) {
            field = value
            disposeComposition()
        }

    var style = WarpPillStyle.Suggestion
        set(value) {
            field = value
            disposeComposition()
        }

    var closable = false
        set(value) {
            field = value
            disposeComposition()
        }

    var pillSelected = false
        set(value) {
            field = value
            disposeComposition()
        }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        pillSelected = selected
    }

    var iconContentDescription : String? = null
        set(value) {
            field = value
            disposeComposition()
        }

    @DrawableRes
    var icon: Int = 0
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

    private var clickListener = OnClickListener { }

    override fun setOnClickListener(onCLick: OnClickListener?) {
        onCLick?.let { clickListener = it }
    }

    private var stylesList = listOf(
        WarpPillStyle.Suggestion,
        WarpPillStyle.Filter
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpPill) {
            val styleInt = getInteger(R.styleable.WarpPill_warpPillStyle, 0)
            style = stylesList[styleInt]
            text = getTextFromIdOrString(R.styleable.WarpPill_pillText, context) ?: ""
            icon = getResourceId(R.styleable.WarpPill_pillIcon, 0)
            iconColor = getColor(R.styleable.WarpPill_pillIconColor, 0)
            iconContentDescription =
                getTextFromIdOrString(R.styleable.WarpPill_pillIconContentDescr, context)
            pillSelected = getBoolean(R.styleable.WarpPill_selected, false)
            closable = getBoolean(R.styleable.WarpPill_closable, false)
        }
    }

    @Composable
    override fun Content() {
        theme {
            val icon: @Composable () -> Unit = {
                if (icon == 0) null else {
                    val iconTintColor =
                        if (iconColor == 0) WarpTheme.colors.icon.default else Color(iconColor)
                    Icon(
                        imageVector = ImageVector.vectorResource(id = icon),
                        contentDescription = iconContentDescription,
                        tint = iconTintColor,
                        modifier = Modifier.size(WarpTheme.dimensions.components.pillIcon)
                    )
                }
            }
            WarpPill(
                text = text,
                onClick = { clickListener.onClick(this@WarpPillView) },
                style = style,
                closable = closable,
                icon = icon,
                selected = pillSelected,
                iconContentDescription = iconContentDescription
            )
        }
    }
}
