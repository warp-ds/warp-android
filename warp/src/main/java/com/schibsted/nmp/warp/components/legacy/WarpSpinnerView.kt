package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpSpinner
import com.schibsted.nmp.warp.components.WarpSpinnerSize
import org.koin.java.KoinJavaComponent.inject

class WarpSpinnerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    val theme: LegacyWarpTheme by inject(LegacyWarpTheme::class.java)

    var size: WarpSpinnerSize = WarpSpinnerSize.Default
        set(value) {
            field = value
            disposeComposition()
        }

    private var spinnerSizes = listOf(
        WarpSpinnerSize.Small,
        WarpSpinnerSize.Default,
        WarpSpinnerSize.Large
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpSpinner) {
            size = spinnerSizes[getInteger(R.styleable.WarpSpinner_spinnerSize, 1)]
        }
    }

    @Composable
    override fun Content() {
        theme {
            WarpSpinner(size = size)
        }
    }

}