package com.imxie.core

import android.content.Context

object Core {

    lateinit var context: Context

    fun init(context: Context) {
        this.context = context
    }
}