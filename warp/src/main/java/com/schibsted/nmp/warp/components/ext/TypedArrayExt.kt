package com.schibsted.nmp.warp.components.ext

import android.content.Context
import android.content.res.TypedArray
import android.util.TypedValue
import com.schibsted.nmp.warp.R

fun TypedArray.getTextFromIdOrString(index: Int, context: Context): String {
    return if (this.hasValue(R.styleable.WarpText_text)) {
        when (this.getType(R.styleable.WarpText_text)) {
            TypedValue.TYPE_STRING -> {
                this.getString(R.styleable.WarpText_text) ?: ""
            }

            TypedValue.TYPE_REFERENCE -> {
                val resourceId = this.getResourceId(R.styleable.WarpText_text, 0)
                if (resourceId != 0) {
                    context.getString(resourceId)
                } else {
                    ""
                }
            }

            else -> ""
        }
    } else {
        ""
    }
}