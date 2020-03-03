package com.imxie.core.ktx.content

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat

fun Context.color(@ColorRes id: Int): Int = ContextCompat.getColor(this, id)

fun Context.dimensionPixelSize(@DimenRes id: Int): Int = resources.getDimensionPixelSize(id)

fun Context.bool(@BoolRes id: Int): Boolean = resources.getBoolean(id)

fun Context.stringArray(@ArrayRes id: Int): Array<String> = resources.getStringArray(id)

fun Context.drawable(@DrawableRes id: Int): Drawable = drawableOrNull(id)
    ?: throw IllegalArgumentException("invalid drawable id $id")

fun Context.drawableOrNull(@DrawableRes id: Int): Drawable? = ContextCompat.getDrawable(this, id)