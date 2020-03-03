package com.imxie.core.ktx.util

import java.io.File


fun File.mkdirsIfNeed() {
    if (exists() && !isDirectory) {
        deleteRecursively()
    }

    if (!exists()) {
        mkdirs()
    }
}

fun File.child(name: String) = File(this, name)
fun File.child(vararg names: String) = File(arrayOf(absolutePath, *names).joinToString(File.separator))

fun File.ensure(): File = apply { mkdirsIfNeed() }