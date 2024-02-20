package com.schibsted.nmp.warp.components.legacy

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView
import androidx.core.content.withStyledAttributes
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.HorizontalWarpStepIndicator
import com.schibsted.nmp.warp.components.VerticalWarpStepIndicator
import org.koin.java.KoinJavaComponent

class WarpStepIndicatorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context = context, attrs = attrs, defStyleAttr = defStyleAttr) {

    val theme: LegacyWarpTheme by KoinJavaComponent.inject(LegacyWarpTheme::class.java)

    var orientation: WarpStepIndicatorOrientation = WarpStepIndicatorOrientation.Horizontal
        set(value) {
            field = value
            disposeComposition()
        }

    var steps: Int = 0
        set(value) {
            field = value
            disposeComposition()
        }

    var activeStep: Int by mutableIntStateOf(0)

    var stepTitle: ((Int) -> String)? = null
        set(value) {
            field = value
            disposeComposition()
        }

    var stepContentDescription: @Composable ((Int) -> String)? = null
        set(value) {
            field = value
            disposeComposition()
        }

    var stepDescription: ((Int) -> String)? = null
        set(value) {
            field = value
            disposeComposition()
        }

    var stepContent: @Composable ((Int) -> Unit)? = null
        set(value) {
            require(orientation == WarpStepIndicatorOrientation.Vertical) { "Content cannot be set for horizontal orientation" }
            field = value
            disposeComposition()
        }

    private var onStepClicked: ((Int) -> Unit)? = null

    fun setOnStepClickedListener(onClick: (Int) -> Unit) {
        this.onStepClicked = onClick
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.WarpStepIndicator) {
            steps = getInt(R.styleable.WarpStepIndicator_steps, 0)
            activeStep = getInt(R.styleable.WarpStepIndicator_activeStep, 0)
            orientation = when (getInt(R.styleable.WarpStepIndicator_stepIndicatorOrientation, 0)) {
                0 -> WarpStepIndicatorOrientation.Horizontal
                1 -> WarpStepIndicatorOrientation.Vertical
                else -> {
                    throw IllegalArgumentException("Invalid orientation")
                }
            }
        }
    }

    @Composable
    override fun Content() {
        theme {
            if (orientation == WarpStepIndicatorOrientation.Horizontal) {
                HorizontalWarpStepIndicator(
                    steps = steps,
                    activeStep = activeStep,
                    onStepClicked = {
                        activeStep = it
                        onStepClicked?.invoke(it)
                    },
                    stepContentDescription = stepContentDescription,
                    stepTitle = stepTitle,
                    stepDescription = stepDescription,

                    )
            } else if (orientation == WarpStepIndicatorOrientation.Vertical) {
                VerticalWarpStepIndicator(
                    modifier = Modifier,
                    steps = steps,
                    activeStep = activeStep,
                    onStepClicked = {
                        activeStep = it
                        onStepClicked?.invoke(it)
                    },
                    stepContentDescription = stepContentDescription,
                    stepTitle = stepTitle,
                    stepDescription = stepDescription
                ) {
                    stepContent?.invoke(it)
                }
            }
        }
    }
}

enum class WarpStepIndicatorOrientation(val value: Int) {
    Horizontal(0),
    Vertical(1)
}
