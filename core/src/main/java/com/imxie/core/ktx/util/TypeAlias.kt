package com.imxie.core.ktx.util

/**
 * 常用的 lambda 定义
 */

typealias Action0 = () -> Unit

typealias Action1<T> = (T) -> Unit

typealias Action2<T, F> = (T, F) -> Unit

typealias Func0<T> = () -> T
typealias Func1<T1, R> = (T1) -> R
typealias Func2<T1, T2, R> = (T1, T2) -> R
