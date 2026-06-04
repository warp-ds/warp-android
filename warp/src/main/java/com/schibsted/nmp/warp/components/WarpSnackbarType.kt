package com.schibsted.nmp.warp.components

import com.schibsted.nmp.warp.theme.WarpIconResource

sealed class WarpSnackbarType {
    data object Positive : WarpSnackbarType()
    data object Negative : WarpSnackbarType()
    data object Warning : WarpSnackbarType()
    data object Info : WarpSnackbarType()
    data class Neutral(val customIcon: WarpIconResource? = null) : WarpSnackbarType()
}
