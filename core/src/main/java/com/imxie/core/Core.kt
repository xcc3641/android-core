package com.imxie.core

import android.app.Application
import android.content.Context
import com.imxie.core.arch.AppLifecycle

object Core {

    lateinit var context: Context

    fun init(context: Context) {
        this.context = context
        AppLifecycle.install(context as Application)
    }
}