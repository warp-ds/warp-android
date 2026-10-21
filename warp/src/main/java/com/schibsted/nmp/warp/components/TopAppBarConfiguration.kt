package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors

/**
 * Everything a [WarpTopAppBar] shows. The same configuration can be passed to a [WarpBottomSheet].
 *
 * @param title The title text.
 * @param subtitle Secondary text below the title; empty shows none.
 * @param navigation Optional leading button.
 * @param actions Trailing actions, in order.
 * @param style Layout and collapse style.
 * @param titleCollapsible Whether the title collapses on scroll. Ignored for
 * [WarpAppBarStyle.MediumFlexible].
 * @param search Optional integrated search field.
 * @param tabs Optional integrated tab row.
 */
data class TopAppBarConfiguration(
    val title: String,
    val subtitle: String = "",
    val navigation: TopAppBarNavigation? = null,
    val actions: List<TopAppBarAction> = emptyList(),
    val style: WarpAppBarStyle = WarpAppBarStyle.Default,
    val titleCollapsible: Boolean = false,
    val search: SearchConfiguration? = null,
    val tabs: TabConfiguration? = null,
)

/**
 * The leading button of a [WarpTopAppBar]. Screen readers announce its purpose, e.g. "Back",
 * before the icon's own description.
 */
sealed interface TopAppBarNavigation {
    val onClick: () -> Unit

    /** A back arrow. */
    data class Back(override val onClick: () -> Unit) : TopAppBarNavigation

    /** A close button. */
    data class Close(override val onClick: () -> Unit) : TopAppBarNavigation
}

/**
 * A trailing action of a [WarpTopAppBar].
 */
sealed interface TopAppBarAction {

    /**
     * An icon button.
     *
     * @param label Read by screen readers before the icon's own description, e.g. "Share".
     */
    data class Icon(
        val icon: WarpIconResource,
        val label: String? = null,
        val onClick: () -> Unit,
    ) : TopAppBarAction

    /**
     * An icon button that switches between two states, e.g. favourite. Screen readers announce
     * whether it's checked.
     *
     * @param icon Shown while unchecked.
     * @param checkedIcon Shown while checked.
     * @param label Read by screen readers before the icon's own description, e.g. "Favourite".
     */
    data class Toggle(
        val icon: WarpIconResource,
        val checkedIcon: WarpIconResource,
        val checked: Boolean,
        val label: String? = null,
        val onCheckedChange: (Boolean) -> Unit,
    ) : TopAppBarAction

    /**
     * A quiet text button.
     *
     * @param destructive Shows the action as a quiet negative button, e.g. for Delete.
     */
    data class Text(
        val text: String,
        val destructive: Boolean = false,
        val onClick: () -> Unit,
    ) : TopAppBarAction

    /** A "more options" button that opens a menu of [items]. */
    data class Menu(val items: List<TopAppBarMenuItem>) : TopAppBarAction
}

/**
 * An item in a [TopAppBarAction.Menu].
 *
 * @param checked Shows a checkmark, e.g. for the current choice in a sort menu.
 */
data class TopAppBarMenuItem(
    val text: String,
    val checked: Boolean = false,
    val onClick: () -> Unit,
)

@Composable
internal fun TopAppBarNavigationButton(navigation: TopAppBarNavigation, enabled: Boolean) {
    val (icon, label) = when (navigation) {
        is TopAppBarNavigation.Back -> icons.arrowLeft to stringResource(R.string.top_app_bar_back)
        is TopAppBarNavigation.Close -> icons.close to stringResource(R.string.top_app_bar_close)
    }
    // Merged before the icon's own description, so it reads e.g. "Back, Arrow pointing left".
    IconButton(
        onClick = navigation.onClick,
        enabled = enabled,
        modifier = Modifier.semantics { contentDescription = label },
    ) {
        WarpIcon(icon = icon, color = iconColor(enabled))
    }
}

@Composable
internal fun RowScope.TopAppBarActions(actions: List<TopAppBarAction>, enabled: Boolean) {
    actions.forEach { action ->
        when (action) {
            is TopAppBarAction.Icon -> IconButton(
                onClick = action.onClick,
                enabled = enabled,
                modifier = action.label.labelSemantics(),
            ) {
                WarpIcon(icon = action.icon, color = iconColor(enabled))
            }

            is TopAppBarAction.Toggle -> IconToggleButton(
                checked = action.checked,
                onCheckedChange = action.onCheckedChange,
                enabled = enabled,
                modifier = action.label.labelSemantics(),
            ) {
                WarpIcon(
                    icon = if (action.checked) action.checkedIcon else action.icon,
                    color = iconColor(enabled),
                )
            }

            is TopAppBarAction.Text -> WarpButton(
                text = action.text,
                onClick = action.onClick,
                enabled = enabled,
                style = if (action.destructive) WarpButtonStyle.NegativeQuiet else WarpButtonStyle.Quiet,
            )

            is TopAppBarAction.Menu -> TopAppBarMenu(action.items, enabled)
        }
    }
}

@Composable
private fun TopAppBarMenu(items: List<TopAppBarMenuItem>, enabled: Boolean) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        IconButton(
            onClick = { expanded = true },
            enabled = enabled,
            modifier = stringResource(R.string.top_app_bar_more_options).labelSemantics(),
        ) {
            WarpIcon(icon = icons.dotsVertical, color = iconColor(enabled))
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = colors.background.default,
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { WarpText(text = item.text) },
                    onClick = {
                        expanded = false
                        item.onClick()
                    },
                    trailingIcon = if (item.checked) {
                        { WarpIcon(icon = icons.check) }
                    } else {
                        null
                    },
                    modifier = Modifier.semantics { if (item.checked) selected = true },
                )
            }
        }
    }
}

private fun String?.labelSemantics(): Modifier =
    if (this == null) Modifier else Modifier.semantics { contentDescription = this@labelSemantics }

@Composable
private fun iconColor(enabled: Boolean) = if (enabled) colors.icon.default else colors.icon.disabled
