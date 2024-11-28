package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpCheckbox
import com.schibsted.nmp.warp.components.WarpCheckboxStyle
import com.schibsted.nmp.warp.components.ext.getTextFromIdOrString
import org.koin.java.KoinJavaComponent.inject

class WarpCheckboxView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    val theme: LegacyWarpTheme by inject(LegacyWarpTheme::class.java)

    var style: WarpCheckboxStyle = WarpCheckboxStyle.Default
        set(value) {
            field = value
            disposeComposition()
        }

    var label = ""
        set(value) {
            field = value
            disposeComposition()
        }

    var extraText: String? = null
        set(value) {
            field = value
            disposeComposition()
        }

    var checkboxEnabled = true
        set(value) {
            field = value
            disposeComposition()
        }

    var checkboxChecked = false
        set(value) {
            field = value
            disposeComposition()
        }

    var isError = false
        set(value) {
            field = value
            disposeComposition()
        }

    var slot: @Composable (() -> Unit)? = null
        set(value) {
            field = value
            disposeComposition()
        }


    private var checkedChangeListener: ((Boolean) -> Unit) = {}

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        checkboxEnabled = enabled
        disposeComposition()
    }

    fun setOnCheckedChangeListener(onCheckedChangeListener: ((Boolean) -> Unit)) {
        this.checkedChangeListener = onCheckedChangeListener
    }

    fun setChecked(isChecked: Boolean) {
        this.checkboxChecked = isChecked
        disposeComposition()
    }

    private var stylesList = listOf(
        WarpCheckboxStyle.Default,
        WarpCheckboxStyle.Disabled,
        WarpCheckboxStyle.Negative
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpCheckbox) {
            val styleInt = getInteger(R.styleable.WarpCheckbox_warpCheckboxStyle, 0)
            style = stylesList[styleInt]
            label = getTextFromIdOrString(R.styleable.WarpCheckbox_checkboxLabel, context) ?: ""
            extraText = getTextFromIdOrString(R.styleable.WarpCheckbox_checkboxExtraText, context)
            checkboxEnabled = getBoolean(R.styleable.WarpCheckbox_checkboxEnabled, true)
            checkboxChecked = getBoolean(R.styleable.WarpCheckbox_checked, false)
            isError = getBoolean(R.styleable.WarpCheckbox_checkboxIsError, false)
        }
    }

    @Composable
    override fun Content() {
        theme {
            if (label.isEmpty()) {
                WarpCheckbox(
                    onCheckedChange = checkedChangeListener,
                    style = style,
                    enabled = checkboxEnabled,
                    checked = checkboxChecked,
                    isError = isError
                )
            } else {
                WarpCheckbox(
                    label = label,
                    extraText = extraText,
                    onCheckedChange = checkedChangeListener,
                    slot = slot,
                    style = style,
                    enabled = checkboxEnabled,
                    checked = checkboxChecked,
                    isError = isError
                )
            }
        }
    }

}