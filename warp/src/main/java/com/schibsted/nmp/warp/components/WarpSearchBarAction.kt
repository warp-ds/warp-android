package com.schibsted.nmp.warp.components

import com.schibsted.nmp.warp.theme.WarpIconResource

data class WarpSearchBarAction(
    val icon: WarpIconResource,
    val contentDescription: String,
    val onClick: () -> Unit,
)
