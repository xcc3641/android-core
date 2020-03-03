package com.imxie.core.log

import timber.log.Timber

internal fun Any.logTag(): String? = (this as? LogTag)?.tag()

/**
 *  Any
 */
fun Any.logD(message: String, tag: String? = null) {
    CLog.t(tag ?: logTag()).d(message)
}

fun Any.logI(message: String, tag: String? = null) {
    CLog.t(tag ?: logTag()).i(message)
}

fun Any.logV(message: String, tag: String? = null) {
    CLog.t(tag ?: logTag()).v(message)
}

fun Any.logW(message: String? = null, cause: Throwable? = null, tag: String? = null) {
    CLog.t(tag ?: logTag()).w(message, cause)
}

fun Any.logE(message: String? = null, cause: Throwable? = null, tag: String? = null) {
    CLog.t(tag ?: logTag()).e(message, cause)
}

/**
 *  Throwable
 */

fun Throwable.logE(tag: String? = null) {
    CLog.t(tag ?: logTag()).e(cause = this)
}

fun Throwable.logW(message: String? = null, tag: String? = null) {
    CLog.t(tag ?: logTag()).w(message, this)
}


typealias LogCallback = ((priority: Int, tag: String?, message: String, t: Throwable?) -> Unit)?

object CLog {

    private const val STACK_OFFSET = 9

    private val DELEGATE = Delegate()

    @JvmStatic
    @JvmOverloads
    fun install(
        debug: Boolean, tag: String?,
        logCallback: LogCallback = null
    ) {
        if (debug) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? = tag

                override fun log(priority: Int, t: Throwable, message: String?, vararg args: Any?) {
                    super.log(priority, t, message + currentThreadName() + stackInfo(), *args)
                }

                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    logCallback?.invoke(
                        priority,
                        tag,
                        message + currentThreadName() + stackInfo(),
                        t
                    )
                    super.log(priority, tag, message + currentThreadName() + stackInfo(), t)
                }

                private fun currentThreadName(): String = " ${Thread.currentThread().name}"

                private fun stackInfo(): String {
                    var offset = STACK_OFFSET
                    val stacks = Thread.currentThread().stackTrace
                    var stack: StackTraceElement
                    while (true) {
                        stack = stacks[offset]
                        val canonicalName = CLog::class.java.canonicalName
                        if (arrayOf(
                                canonicalName,
                                "${canonicalName}Kt"
                            ).contains(stack.className).not()
                        ) {
                            break
                        }
                        offset++
                    }

                    return " -> ${stack.methodName}(${stack.fileName}:${stack.lineNumber})"
                }
            })
        } else {
            Timber.plant(object : ReleaseTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    super.log(priority, tag, message, t)
                    logCallback?.invoke(priority, tag, message, t)
                }
            })
        }
    }

    /**
     * 设置只能用一次的 tag
     */
    @JvmStatic
    fun t(tag: String?): Delegate {
        return tag?.let { DELEGATE.t(it) } ?: DELEGATE
    }

    @JvmStatic
    fun v(message: String, vararg args: Any?) {
        DELEGATE.v(message, *args)
    }

    @JvmStatic
    fun d(message: String, vararg args: Any?) {
        DELEGATE.d(message, *args)
    }

    @JvmStatic
    fun i(message: String, vararg args: Any?) {
        DELEGATE.i(message, *args)
    }

    @JvmStatic
    fun w(message: String, vararg args: Any?) {
        DELEGATE.w(message, *args)
    }

    @JvmStatic
    @JvmOverloads
    fun e(message: String? = null, cause: Throwable? = null) {
        DELEGATE.e(message, cause)
    }

    class Delegate internal constructor() {

        fun t(tag: String): Delegate {
            Timber.tag(tag)
            return this
        }

        fun v(message: String, vararg args: Any?) {
            Timber.v(nonEmptyMessage(message), *args)
        }

        fun d(message: String, vararg args: Any?) {
            Timber.d(nonEmptyMessage(message), *args)
        }

        fun i(message: String, vararg args: Any?) {
            Timber.i(nonEmptyMessage(message), *args)
        }

        fun w(message: String, vararg args: Any?) {
            Timber.w(nonEmptyMessage(message), *args)
        }

        fun w(message: String?, cause: Throwable? = null) {
            when {
                message != null && cause != null -> Timber.w(cause, nonEmptyMessage(message))
                cause != null -> Timber.w(cause)
                message != null -> Timber.w(nonEmptyMessage(message))
            }
        }

        fun e(message: String? = null, cause: Throwable? = null) {
            when {
                message != null && cause != null -> Timber.e(cause, nonEmptyMessage(message))
                message != null -> Timber.e(nonEmptyMessage(message))
                cause != null -> Timber.e(cause)
            }
        }

        private fun nonEmptyMessage(message: String): String {
            if (message.isEmpty()) {
                return "empty msg"
            }
            return message
        }
    }


}