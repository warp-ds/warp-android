package com.schibsted.nmp.warp.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class FlavorPreviewProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("finn", "tori")

}