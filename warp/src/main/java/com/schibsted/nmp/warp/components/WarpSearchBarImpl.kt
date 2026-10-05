package com.schibsted.nmp.warp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpIconResources
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import kotlinx.coroutines.flow.drop

private fun LayoutCoordinates.rootXRange(): ClosedFloatingPointRange<Float> {
    val startX = positionInRoot().x
    return startX..(startX + size.width.toFloat())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun WarpSearchBarImpl(
    textFieldState: TextFieldState,
    clearContentDescription: String,
    modifier: Modifier = Modifier,
    hint: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    onInputClick: (() -> Unit)? = null,
    onSearch: ((String) -> Unit)? = null,
    onQueryChange: ((String) -> Unit)? = null,
    showClearButton: Boolean = false,
    onClearClick: (() -> Unit)? = null,
    disabledTextColor: Color = colors.text.disabled,
    secondaryAction: WarpSearchBarAction? = null,
    leadingAction: WarpSearchBarAction? = null,
    requestInitialFocus: Boolean = false,
    onFocusChanged: (Boolean) -> Unit = {},
    focusRequester: FocusRequester? = null,
) {
    val isButton = readOnly && onInputClick != null
    val interactionSource = remember { MutableInteractionSource() }
    var inputFieldRootX by remember { mutableFloatStateOf(0f) }
    var leadingIconRootRange by remember { mutableStateOf<ClosedFloatingPointRange<Float>?>(null) }
    var trailingIconRootRange by remember { mutableStateOf<ClosedFloatingPointRange<Float>?>(null) }
    val internalFocusRequester = remember { FocusRequester() }
    val inputFocusRequester = focusRequester ?: internalFocusRequester
    var inputIsFocused by remember { mutableStateOf(false) }
    val focusesInputOnEntry = requestInitialFocus && !isButton
    val inputFieldModifier = if (isButton) {
        Modifier
            .fillMaxWidth()
            .clip(SearchBarDefaults.inputFieldShape)
            .onGloballyPositioned { inputFieldRootX = it.positionInRoot().x }
            .pointerInput(onInputClick) {
                awaitEachGesture {
                    val down = awaitFirstDown(pass = PointerEventPass.Initial)
                    val downRootX = inputFieldRootX + down.position.x
                    val overLeadingIcon = leadingIconRootRange?.contains(downRootX) == true
                    val overTrailingIcon = trailingIconRootRange?.contains(downRootX) == true
                    if (!overTrailingIcon && !overLeadingIcon) {
                        down.consume()
                        val press = PressInteraction.Press(down.position)
                        interactionSource.tryEmit(press)
                        val up = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                        up?.consume()
                        interactionSource.tryEmit(
                            if (up != null) {
                                PressInteraction.Release(press)
                            } else {
                                PressInteraction.Cancel(press)
                            }
                        )
                        if (up != null) onInputClick?.invoke()
                    }
                }
            }
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(),
                role = Role.Button,
                onClick = { onInputClick?.invoke() },
            )
            .focusProperties { canFocus = false }
    } else {
        Modifier
            .focusRequester(inputFocusRequester)
            .onFocusChanged { focusState ->
                if (focusState.isFocused != inputIsFocused) {
                    inputIsFocused = focusState.isFocused
                    onFocusChanged(focusState.isFocused)
                }
            }
    }

    LaunchedEffect(focusesInputOnEntry) {
        if (focusesInputOnEntry) {
            inputFocusRequester.requestFocus()
        }
    }

    val latestOnQueryChange by rememberUpdatedState(onQueryChange)
    LaunchedEffect(textFieldState, isButton) {
        if (isButton) return@LaunchedEffect
        snapshotFlow { textFieldState.text.toString() }
            .drop(1)
            .collect { latestOnQueryChange?.invoke(it) }
    }

    val placeholder: @Composable () -> Unit = {
        WarpText(
            text = hint,
            color = colors.text.placeholder,
            style = WarpTextStyle.Body,
            maxLines = 1,
        )
    }

    val leadingIcon: @Composable () -> Unit = if (leadingAction == null) {
        {
            WarpIcon(
                icon = WarpIconResources.search,
                size = dimensions.icon.small,
            )
        }
    } else {
        {
            IconButton(
                onClick = leadingAction.onClick,
                modifier = Modifier
                    .onGloballyPositioned { leadingIconRootRange = it.rootXRange() }
                    .semantics {
                        contentDescription = leadingAction.contentDescription
                    },
            ) {
                WarpIcon(
                    icon = leadingAction.icon,
                    size = dimensions.icon.small,
                )
            }
        }
    }

    val trailingIcon: (@Composable () -> Unit)? = if (showClearButton || secondaryAction != null) {
        {
            Row(
                modifier = Modifier.onGloballyPositioned { trailingIconRootRange = it.rootXRange() },
            ) {
                if (showClearButton) {
                    IconButton(
                        onClick = { onClearClick?.invoke() },
                        modifier = Modifier.semantics {
                            contentDescription = clearContentDescription
                        },
                    ) {
                        WarpIcon(
                            icon = WarpIconResources.close,
                            size = dimensions.icon.small,
                        )
                    }
                }
                secondaryAction?.let { action ->
                    IconButton(
                        onClick = action.onClick,
                        modifier = Modifier.semantics {
                            contentDescription = action.contentDescription
                        },
                    ) {
                        WarpIcon(
                            icon = action.icon,
                            size = dimensions.icon.small,
                        )
                    }
                }
            }
        }
    } else {
        null
    }

    val textFieldColors = TextFieldDefaults.colors(
        focusedTextColor = colors.text.default,
        focusedContainerColor = colors.background.subtle,
        unfocusedTextColor = colors.text.default,
        unfocusedContainerColor = colors.background.subtle,
        disabledTextColor = disabledTextColor,
        disabledPlaceholderColor = colors.text.placeholder,
        disabledContainerColor = colors.background.subtle,
        focusedPlaceholderColor = colors.text.placeholder,
        unfocusedPlaceholderColor = colors.text.placeholder,
        cursorColor = colors.icon.default,
    )

    SearchBar(
        inputField = {
            if (isButton) {
                SearchBarDefaults.InputField(
                    modifier = inputFieldModifier,
                    state = textFieldState,
                    onSearch = {},
                    expanded = false,
                    onExpandedChange = {},
                    enabled = true,
                    readOnly = true,
                    placeholder = placeholder,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    colors = textFieldColors,
                )
            } else {
                SearchBarDefaults.InputField(
                    modifier = inputFieldModifier,
                    state = textFieldState,
                    onSearch = { submittedQuery -> onSearch?.invoke(submittedQuery) },
                    expanded = false,
                    onExpandedChange = { onInputClick?.invoke() },
                    enabled = enabled,
                    placeholder = placeholder,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    colors = textFieldColors,
                )
            }
        },
        expanded = false,
        onExpandedChange = { onInputClick?.invoke() },
        modifier = modifier.fillMaxWidth(),
        colors = SearchBarDefaults.colors(
            containerColor = colors.background.subtle,
        ),
        content = {},
        windowInsets = WindowInsets(
            left = 0.dp,
            top = 0.dp,
            right = 0.dp,
            bottom = 0.dp,
        ),
    )
}
