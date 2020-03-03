@file:Suppress("NOTHING_TO_INLINE")

package com.imxie.core.ktx.collection

inline fun Collection<Boolean>.allTrue(): Boolean = this.all { it }
inline fun Collection<Boolean>.anyTrue(): Boolean = this.any { it }

infix fun <T> Collection<T>.contentEquals(other: Collection<T>): Boolean {
    return size == other.size && zip(other) { a, b -> a == b }.allTrue()
}

