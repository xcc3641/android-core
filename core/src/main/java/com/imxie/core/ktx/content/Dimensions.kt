/*
 * Copyright 2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.imxie.core.ktx.content

import android.content.Context
import android.view.View
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment
import kotlin.math.roundToInt

fun Context.dip(value: Int): Int = dipF(value).roundToInt()
fun Context.dip(value: Float): Int = dipF(value).roundToInt()
fun Context.dipF(value: Int): Float = (value * resources.displayMetrics.density)
fun Context.dipF(value: Float): Float = (value * resources.displayMetrics.density)
fun Context.sp(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()
fun Context.sp(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()
fun Context.px2dip(px: Int): Float = px.toFloat() / resources.displayMetrics.density
fun Context.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity
fun Context.dimen(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)


inline fun View.dip(value: Int): Int = context.dip(value)
inline fun View.dip(value: Float): Int = context.dip(value)
inline fun View.dipF(value: Int): Float = context.dipF(value)
inline fun View.dipF(value: Float): Float = context.dipF(value)
inline fun View.sp(value: Int): Int = context.sp(value)
inline fun View.sp(value: Float): Int = context.sp(value)
inline fun View.px2dip(px: Int): Float = context.px2dip(px)
inline fun View.px2sp(px: Int): Float = context.px2sp(px)
inline fun View.dimen(@DimenRes resource: Int): Int = context.dimen(resource)

inline fun Fragment.dip(value: Int): Int = act.dip(value)
inline fun Fragment.dip(value: Float): Int = act.dip(value)
inline fun Fragment.dipF(value: Int): Float = act.dipF(value)
inline fun Fragment.dipF(value: Float): Float = act.dipF(value)
inline fun Fragment.sp(value: Int): Int = act.sp(value)
inline fun Fragment.sp(value: Float): Int = act.sp(value)
inline fun Fragment.px2dip(px: Int): Float = act.px2dip(px)
inline fun Fragment.px2sp(px: Int): Float = act.px2sp(px)
inline fun Fragment.dimen(@DimenRes resource: Int): Int = act.dimen(resource)
