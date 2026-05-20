package com.schibsted.nmp.warp.brands.dba

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.schibsted.nmp.warp.theme.WarpStrings

object DbaBrandStrings : WarpStrings {
    @Composable
    override fun getVerifyIdentityButtonText(): String {
        return stringResource(R.string.dba_verify_identity_button)
    }
}
