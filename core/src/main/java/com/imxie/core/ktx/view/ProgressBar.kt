package com.imxie.core.ktx.view

import android.animation.ObjectAnimator
import android.widget.ProgressBar

fun ProgressBar.animateProgress(progress: Int, duration: Int) {
    ObjectAnimator.ofInt(this, "progress", getProgress(), progress).apply {
        this.duration = duration.toLong()
        setAutoCancel(true)
        start()
    }
}

fun ProgressBar.animateSecondaryProgress(progress: Int, duration: Int) {
    ObjectAnimator.ofInt(this, "secondaryProgress", secondaryProgress, progress).apply {
        this.duration = duration.toLong()
        setAutoCancel(true)
        start()
    }
}