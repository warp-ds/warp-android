package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpSwitch
import org.koin.java.KoinJavaComponent.inject

class WarpSwitchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    val theme: LegacyWarpTheme by inject(LegacyWarpTheme::class.java)

    var switchEnabled = true
        set(value) {
            field = value
            disposeComposition()
        }

    var switchChecked = false
        set(value) {
            field = value
            disposeComposition()
        }

    private var checkedChangeListener: ((Boolean) -> Unit) = { }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        switchEnabled = enabled
        disposeComposition()
    }

    fun setChecked(isChecked: Boolean) {
        this.switchChecked = isChecked
        disposeComposition()
    }

    fun setOnCheckedChangeListener(onCheckedChangeListener: ((Boolean) -> Unit)) {
        this.checkedChangeListener = onCheckedChangeListener
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpSwitch) {
            switchEnabled = getBoolean(R.styleable.WarpSwitch_switchEnabled, true)
            switchChecked = getBoolean(R.styleable.WarpSwitch_switchChecked, false)
        }
    }

    @Composable
    override fun Content() {
        theme {
            WarpSwitch(
                checked = switchChecked,
                onCheckedChange = checkedChangeListener,
                enabled = switchEnabled
            )
        }
    }
}
