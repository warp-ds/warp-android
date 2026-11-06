@file:Suppress("DEPRECATION")

package com.schibsted.nmp.warp.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalDimensions
import com.schibsted.nmp.warp.theme.LocalShapes
import com.schibsted.nmp.warp.theme.LocalTypography
import com.schibsted.nmp.warp.theme.Transparent
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes
import com.schibsted.nmp.warp.theme.WarpTheme.typography

// Frozen renderer for the deprecated WarpButtonStyle values. Do not restyle: apps keep this look
// until they migrate, and the snapshot/button legacy snapshots pin it.

@Composable
internal fun LegacyWarpLabelButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    style: WarpButtonStyle,
    maxLines: Int,
    loading: Boolean,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
    small: Boolean,
    interactionSource: MutableInteractionSource?,
) {
    LegacyWarpButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = style,
        loading = loading,
        small = small,
        interactionSource = interactionSource,
    ) {
        val textStyle = if (small) typography.detailStrong else typography.title4

        leadingIcon?.let {
            it()
        }
        Text(
            text = text,
            modifier = Modifier
                .semantics(properties = { contentDescription = text }),
            overflow = TextOverflow.Ellipsis,
            maxLines = maxLines,
            style = textStyle,
            textAlign = TextAlign.Center
        )
        trailingIcon?.let {
            it()
        }
    }
}

@Composable
internal fun LegacyWarpDrawableButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    style: WarpButtonStyle,
    maxLines: Int,
    loading: Boolean,
    @DrawableRes leadingIcon: Int?,
    leadingIconContentDescr: String?,
    @DrawableRes trailingIcon: Int?,
    trailingIconContentDescr: String?,
    small: Boolean,
    iconModifier: Modifier,
    interactionSource: MutableInteractionSource?,
) {
    LegacyWarpButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = style,
        loading = loading,
        small = small,
        interactionSource = interactionSource,
    ) {
        val textStyle = if (small) typography.detailStrong else typography.title4

        val leadingIconModifier = if (small)
            Modifier
                .scale(0.7f)
                .padding(end = dimensions.space05) else
            Modifier
                .padding(end = dimensions.space1)

        val trailingIconModifier = if (small)
            Modifier
                .scale(0.7f)
                .padding(start = dimensions.space05) else
            Modifier
                .padding(start = dimensions.space1)

        leadingIcon?.let {
            Icon(
                modifier = leadingIconModifier.then(iconModifier),
                imageVector = ImageVector.vectorResource(leadingIcon),
                contentDescription = leadingIconContentDescr
            )
        }
        Text(
            text = text,
            modifier = Modifier
                .semantics(properties = { contentDescription = text }),
            overflow = TextOverflow.Ellipsis,
            maxLines = maxLines,
            style = textStyle,
        )
        trailingIcon?.let {
            Icon(
                modifier = trailingIconModifier.then(iconModifier),
                imageVector = ImageVector.vectorResource(trailingIcon),
                contentDescription = trailingIconContentDescr
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LegacyWarpButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    style: WarpButtonStyle,
    loading: Boolean,
    small: Boolean,
    interactionSource: MutableInteractionSource?,
    content: @Composable RowScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalShapes provides shapes,
        LocalTypography provides typography,
        LocalDimensions provides dimensions
    ) {
        val warpButtonColors: WarpButtonStyleColors = when (style) {
            WarpButtonStyle.Utility -> buttonStyleUtility()
            WarpButtonStyle.UtilityOverlay -> buttonStyleUtilityOverlay()
            WarpButtonStyle.UtilityQuiet -> buttonStyleUtilityQuiet()
            else -> error("${style.name} is rendered by WarpButtonSurface, not LegacyWarpButton")
        }

        val buttonColors = ButtonDefaults.buttonColors(
            containerColor = warpButtonColors.background.default,
            contentColor = warpButtonColors.text,

            disabledContainerColor = colors.background.disabled,
            disabledContentColor = colors.text.inverted
        )

        val loadingColors = ButtonDefaults.buttonColors(
            containerColor = Transparent,
            contentColor = colors.text.default,
            disabledContainerColor = colors.background.disabled,
            disabledContentColor = colors.text.inverted
        )

        val defaultBorder = warpButtonColors.border?.let {
            BorderStroke(
                dimensions.space025,
                it.default
            )
        }

        val buttonShape = shapes.components.button
        val focusedBorder = BorderStroke(dimensions.space025, colors.border.focus)
        var borderColor by remember { mutableStateOf(defaultBorder) }

        val elevation =
            if (!loading && style == WarpButtonStyle.UtilityOverlay) dimensions.shadowSmall else 0.dp

        val colors = if (loading) loadingColors else buttonColors
        //disable click when button is in loading state
        val clickAction = if (loading) {
            {}
        } else onClick

        val paddingVertical = if (small) 0.dp else dimensions.space2
        val paddingHorizontal = if (small) dimensions.space15 else dimensions.space2
        val buttonModifier = if (small) {
            val smallModifier = Modifier
                .height(dimensions.components.smallButtonHeight)
            if (loading) modifier
                .loadingAnimation()
                .then(smallModifier) else modifier.then(smallModifier)
        } else {
            if (loading) modifier.loadingAnimation() else modifier
        }

        val rippleConfiguration = RippleConfiguration(
            color = warpButtonColors.background.active,
            rippleAlpha = RippleAlpha(0f, 0f, 0f, 0.5f)
        )
        CompositionLocalProvider(
            LocalRippleConfiguration provides rippleConfiguration
        ) {
            Button(
                modifier = buttonModifier
                    .onFocusChanged {
                        borderColor = if (it.isFocused) focusedBorder else defaultBorder
                    }
                    .let { if (!enabled) it.focusable() else it },
                onClick = clickAction,
                enabled = enabled,
                shape = buttonShape,
                colors = colors,
                border = borderColor,
                content = content,
                contentPadding = PaddingValues(
                    horizontal = paddingHorizontal,
                    vertical = paddingVertical
                ),
                elevation = ButtonDefaults.buttonElevation(elevation),
                interactionSource = interactionSource,
            )
        }
    }
}
