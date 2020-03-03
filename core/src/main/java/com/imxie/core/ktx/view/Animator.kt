package com.imxie.core.ktx.view

import android.animation.Animator
import android.animation.ValueAnimator

inline fun <reified T> ValueAnimator.doOnUpdate(crossinline action: (Animator, T) -> Unit): ValueAnimator.AnimatorUpdateListener {
    val listener = ValueAnimator.AnimatorUpdateListener {
        action(it, it.animatedValue as T)
    }
    addUpdateListener(listener)
    return listener
}

fun Animator?.isRunning(): Boolean = this?.isRunning == true
