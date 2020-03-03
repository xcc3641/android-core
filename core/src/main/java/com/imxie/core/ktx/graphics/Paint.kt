package com.imxie.core.ktx.graphics

import android.graphics.Paint

fun Paint.setAlpha(value: Float) {
    alpha = (value * 255).toInt()
}

