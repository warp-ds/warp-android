package com.schibsted.nmp.warp.brands.finn

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.schibsted.nmp.warp.theme.WarpStrings

object FinnBrandStrings : WarpStrings {
    @Composable
    override fun getVerifyIdentityButtonText(): String {
        return stringResource(R.string.finn_verify_identity_button)
    }
}
