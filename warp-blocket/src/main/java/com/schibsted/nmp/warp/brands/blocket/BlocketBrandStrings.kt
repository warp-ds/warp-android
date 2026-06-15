package com.schibsted.nmp.warp.brands.blocket

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.schibsted.nmp.warp.theme.WarpStrings

object BlocketBrandStrings : WarpStrings {
    @Composable
    override fun getVerifyIdentityButtonText(): String {
        return stringResource(R.string.blocket_verify_identity_button)
    }
}
