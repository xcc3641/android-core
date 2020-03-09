package com.imxie.life

import android.app.Application
import com.imxie.core.Core


class App :Application(){

    override fun onCreate() {
        super.onCreate()
        Core.init(this)
    }
}