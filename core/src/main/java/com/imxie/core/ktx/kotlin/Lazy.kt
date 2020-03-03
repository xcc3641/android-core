package com.imxie.core.ktx.kotlin


/**
 * 非线程安全的 lazy, 不加锁效率稍高
 */
fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)