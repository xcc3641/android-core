package com.imxie.core.ktx.os

import android.os.Build
import androidx.annotation.IntRange

object Sdk {
    fun ge(@IntRange(from = 21) version: Int) = Build.VERSION.SDK_INT >= version
    fun eq(@IntRange(from = 21) version: Int) = Build.VERSION.SDK_INT == version
    fun ge23() = ge(23)
    fun ge24() = ge(24)
    fun ge26() = ge(26)
    fun ge28() = ge(28)
    fun ge29() = ge(29)
}
