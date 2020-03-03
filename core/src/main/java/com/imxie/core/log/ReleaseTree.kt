package com.imxie.core.log

import android.util.Log
import timber.log.Timber


open class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.ASSERT, Log.VERBOSE, Log.DEBUG, Log.INFO -> return
        }

    }
}