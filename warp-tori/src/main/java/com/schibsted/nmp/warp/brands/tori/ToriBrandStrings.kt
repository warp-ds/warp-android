package com.schibsted.nmp.warp.brands.tori

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.schibsted.nmp.warp.theme.WarpStrings

object ToriBrandStrings : WarpStrings {
    @Composable
    override fun getVerifyIdentityButtonText(): String {
        return stringResource(R.string.tori_verify_identity_button)
    }
}
