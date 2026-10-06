package com.schibsted.nmp.warp.components

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun WarpSearchBarButton(
    text: String,
    clearContentDescription: String,
    modifier: Modifier = Modifier,
    hint: String = "",
    onClick: () -> Unit = {},
    onClearClick: (() -> Unit)? = null,
    showClearButton: Boolean = true,
    secondaryAction: WarpSearchBarAction? = null,
    leadingAction: WarpSearchBarAction? = null,
) {
    val textFieldState = remember { TextFieldState(text) }

    LaunchedEffect(text) {
        if (textFieldState.text.toString() != text) {
            textFieldState.edit { replace(0, length, text) }
        }
    }

    WarpSearchBarImpl(
        textFieldState = textFieldState,
        clearContentDescription = clearContentDescription,
        modifier = modifier,
        hint = hint,
        readOnly = true,
        onInputClick = onClick,
        showClearButton = text.isNotEmpty() && showClearButton && onClearClick != null,
        onClearClick = onClearClick,
        secondaryAction = secondaryAction,
        leadingAction = leadingAction,
    )
}
