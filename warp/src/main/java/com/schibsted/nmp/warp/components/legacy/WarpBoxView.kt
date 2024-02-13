package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.vectorResource
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpBox
import com.schibsted.nmp.warp.components.WarpBoxStyle
import com.schibsted.nmp.warp.components.ext.getTextFromIdOrString
import com.schibsted.nmp.warp.theme.WarpTheme
import org.koin.java.KoinJavaComponent.inject

class WarpBoxView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context = context, attrs = attrs, defStyleAttr = defStyleAttr) {

    val theme: LegacyWarpTheme by inject(LegacyWarpTheme::class.java)

    var boxStyle: WarpBoxStyle = WarpBoxStyle.Neutral
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

    var iconContentDescription: String? = null
        set(value) {
            field = value
            disposeComposition()
        }

    var heading: String? = null
        set(value) {
            field = value
            disposeComposition()
        }

    var text: String? = null
        set(value) {
            field = value
            disposeComposition()
        }

    var link: String? = null
        set(value) {
            field = value
            disposeComposition()
        }

    private var linkAction: OnClickListener? = null

    fun setLinkOnClickListener(onClick: OnClickListener?) {
        linkAction = OnClickListener { }
        onClick?.let { linkAction = it }
    }

    var buttonText: String? = null
        set(value) {
            field = value
            disposeComposition()
        }

    private var buttonAction: OnClickListener? = null

    fun setButtonOnClickListener(onClick: OnClickListener?) {
        buttonAction = OnClickListener { }
        onClick?.let { buttonAction = it }
    }

    private var stylesList = listOf(
        WarpBoxStyle.Neutral,
        WarpBoxStyle.Bordered,
        WarpBoxStyle.Info
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpBox) {
            boxStyle = stylesList[getInt(R.styleable.WarpBox_boxStyle, 0)]
            icon = getResourceId(R.styleable.WarpBox_boxIcon, 0)
            iconColor = getColor(R.styleable.WarpBox_boxIconColor, 0)
            iconContentDescription =
                getTextFromIdOrString(R.styleable.WarpBox_boxIconContentDescr, context)
            heading = getTextFromIdOrString(R.styleable.WarpBox_boxHeading, context)
            text = getTextFromIdOrString(R.styleable.WarpBox_boxText, context)
            link = getTextFromIdOrString(R.styleable.WarpBox_boxLink, context)
            buttonText = getTextFromIdOrString(R.styleable.WarpBox_boxButtonText, context)
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
                        tint = iconTintColor
                    )
                }
            }
            WarpBox(
                boxStyle = boxStyle,
                icon = icon,
                heading = heading,
                text = text,
                link = link,
                linkAction = { linkAction?.onClick(this@WarpBoxView) },
                buttonText = buttonText,
                buttonAction = { buttonAction?.onClick(this@WarpBoxView) }
            )
        }
    }
}