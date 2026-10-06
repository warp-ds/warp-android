package com.schibsted.nmp.warp.components

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import com.schibsted.nmp.warp.R

@Composable
fun WarpSearchBar(
    textFieldState: TextFieldState,
    modifier: Modifier = Modifier,
    clearContentDescription: String = stringResource(R.string.clear_field),
    hint: String = "",
    searchIsEnabled: Boolean = true,
    onSearch: (String) -> Unit = {},
    onSearchChanged: (String) -> Unit = {},
    onClearClick: () -> Unit = { textFieldState.clearText() },
    secondaryAction: WarpSearchBarAction? = null,
    leadingAction: WarpSearchBarAction? = null,
    requestInitialFocus: Boolean = false,
    onFocusChanged: (Boolean) -> Unit = {},
    focusRequester: FocusRequester? = null,
) {
    WarpSearchBarImpl(
        textFieldState = textFieldState,
        clearContentDescription = clearContentDescription,
        modifier = modifier,
        hint = hint,
        enabled = searchIsEnabled,
        onSearch = onSearch,
        onQueryChange = onSearchChanged,
        showClearButton = textFieldState.text.isNotEmpty(),
        onClearClick = onClearClick,
        secondaryAction = secondaryAction,
        leadingAction = leadingAction,
        requestInitialFocus = requestInitialFocus,
        onFocusChanged = onFocusChanged,
        focusRequester = focusRequester,
    )
}
