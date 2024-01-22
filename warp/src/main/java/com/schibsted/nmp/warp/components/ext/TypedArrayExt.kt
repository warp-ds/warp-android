package com.schibsted.nmp.warp.components.ext

import android.content.Context
import android.content.res.TypedArray
import android.util.TypedValue

fun TypedArray.getTextFromIdOrString(index: Int, context: Context): String? {
    return if (this.hasValue(index)) {
        when (this.getType(index)) {
            TypedValue.TYPE_STRING -> {
                this.getString(index)
            }
            TypedValue.TYPE_REFERENCE -> {
                val resourceId = this.getResourceId(index, 0)
                if (resourceId != 0) {
                    context.getString(resourceId)
                } else { null }
            }
            else -> { null }
        }
    } else { null }
}
