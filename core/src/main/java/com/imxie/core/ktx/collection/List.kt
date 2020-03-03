package com.imxie.core.ktx.collection

import java.util.*

fun <T> MutableList<T>.replace(list: List<T>) {
    if (list != this) {
        clear()
        addAll(list)
    }
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    Collections.swap(this, index1, index2)
}

fun <T> MutableList<T>.move(index1: Int, index2: Int) {
    val pre = removeAt(index1)
    add(index2, pre)
}


fun <T> MutableList<T>.addOrUpdate(item: T): MutableList<T> {
    val index = indexOf(item)
    if (index > -1) {
        set(index, item)
    } else {
        add(item)
    }
    return this
}

fun <T> MutableList<T>.addIfAbsent(item: T): MutableList<T> {
    if (item !in this) {
        add(item)
    }
    return this
}

/**
 * Iterate the receiver [List] backwards using an index.
 *
 * @f an action to invoke on each list element (index, element).
 */
inline fun <T> List<T>.forEachReversedWithIndex(f: (Int, T) -> Unit) {
    var i = size - 1
    while (i >= 0) {
        f(i, get(i))
        i--
    }
}