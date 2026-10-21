package com.schibsted.nmp.warp.components

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.schibsted.nmp.warp.R

/**
 * A read-only search field that acts as a button, for entry points that open a separate search screen.
 * Use this instead of [WarpSearchBar] with `searchIsEnabled = false`, which TalkBack reads as a disabled text field.
 */
@Composable
fun WarpSearchBarButton(
    text: String,
    modifier: Modifier = Modifier,
    clearContentDescription: String = stringResource(R.string.clear_field),
    hint: String = "",
    onClick: () -> Unit = {},
    onClearClick: (() -> Unit)? = null,
    secondaryAction: WarpSearchBarAction? = null,
    leadingAction: WarpSearchBarAction? = null,
) {
    val textFieldState = remember(text) { TextFieldState(text) }

    WarpSearchBarImpl(
        textFieldState = textFieldState,
        clearContentDescription = clearContentDescription,
        modifier = modifier,
        hint = hint,
        readOnly = true,
        onInputClick = onClick,
        showClearButton = text.isNotEmpty() && onClearClick != null,
        onClearClick = onClearClick,
        secondaryAction = secondaryAction,
        leadingAction = leadingAction,
    )
}
