package com.imxie.core.ktx.util

import android.util.SparseArray
import java.text.DecimalFormat

private val digitsFormatterCache = SparseArray<DecimalFormat>()

/**
 * eg: digits=2
 * 0.12 -> "0.12"
 * 0.10 -> "0.1"
 * 1.00 -> "1"
 */
fun Float.toString(digits: Int): String {
    var formatter = digitsFormatterCache.get(digits)
    if (formatter == null) {
        formatter = DecimalFormat("#." + "#".repeat(digits))
        digitsFormatterCache.put(digits, formatter)
    }
    return formatter.format(this)
}