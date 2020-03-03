package com.imxie.core.ktx.content

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.annotation.StyleRes

fun View.useAttrs(attrs: AttributeSet?, styleableRes: IntArray, block: TypedArray.() -> Unit) {
    attrs?.also {
        val ta = context.obtainStyledAttributes(it, styleableRes)
        ta.block()
        ta.recycle()
    }
}

fun Context.useAttrs(@StyleRes styleRes: Int, styleableRes: IntArray, block: TypedArray.() -> Unit) {
    val ta = obtainStyledAttributes(styleRes, styleableRes)
    ta.block()
    ta.recycle()
}