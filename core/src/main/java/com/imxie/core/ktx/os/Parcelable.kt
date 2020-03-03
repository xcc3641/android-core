package io.iftech.android.sdk.ktx.os

import android.os.Build
import android.os.Parcel
import android.os.Parcelable

fun <T : Parcelable> Parcelable.copy(): T? {
    val parcel = Parcel.obtain()
    parcel.writeValue(this)
    parcel.setDataPosition(0)
    @Suppress("UNCHECKED_CAST")
    val copied = parcel.readValue(this::class.java.classLoader) as? T
    parcel.recycle()
    return copied
}

inline fun <reified T : Parcelable> Parcel.readMutableList(
    list: MutableList<T>,
    classLoader: ClassLoader?
) {
    if (Build.VERSION.SDK_INT >= 29) {
        readParcelableList(list, classLoader)
    } else {
        readList(list as List<*>, classLoader)
    }
}

inline fun <reified T : Parcelable> Parcel.writeMutableList(list: MutableList<T>, flags: Int) {
    if (Build.VERSION.SDK_INT >= 29) {
        writeParcelableList(list, flags)
    } else {
        writeList(list as List<*>)
    }
}