package com.imxie.core.ktx.graphics

import android.graphics.Canvas
import android.graphics.Paint

fun Canvas.drawVerticalLine(x: Float, yRange: ClosedFloatingPointRange<Float>, paint: Paint) {
    drawLine(x, yRange.start, x, yRange.endInclusive, paint)
}

fun Canvas.drawVerticalLine(x: Int, yRange: IntRange, paint: Paint) {
    drawLine(x.toFloat(), yRange.first.toFloat(), x.toFloat(), yRange.last.toFloat(), paint)
}
