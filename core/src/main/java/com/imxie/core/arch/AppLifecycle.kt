package com.imxie.core.arch

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.util.*

class AppLifecycle private constructor(app: Application) : SimpleActivityLifecycleCallbacks {

    private val activityStack: Stack<Activity> = Stack()

    private fun peekActivity(stack: Stack<Activity>, index: Int): Activity {
        val size = stack.size
        if (size < index) {
            throw IndexOutOfBoundsException("stack size error")
        }
        val activity: Activity
        for (i in size - index downTo 0) {
            if (i < size) {
                activity = stack[i]
                return activity
            }
        }
        throw IllegalStateException()
    }

    val preActivity
        get() = peekActivity(activityStack, 2)

    val curActivity
        get() = peekActivity(activityStack, 1)

    init {
        app.registerActivityLifecycleCallbacks(this)
    }


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activityStack.add(activity)
    }

    override fun onActivityDestroyed(activity: Activity) {
        activityStack.remove(activity)
    }

    fun getPreActivityOf(activity: Activity): Activity? {
        val preActivityIndex = activityStack.indexOf(activity) - 1
        return if (preActivityIndex < 0) {
            null
        } else activityStack[preActivityIndex]
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private lateinit var instance: AppLifecycle

        @JvmStatic
        internal fun install(app: Application) {
            instance = AppLifecycle(app)
        }

        @JvmStatic
        fun instance(): AppLifecycle = instance
    }

}

internal interface SimpleActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {}

    override fun onActivityDestroyed(activity: Activity) {}
}
