package com.imxie.core.util

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.LayoutRes

object ViewUtil {

    @JvmStatic
    @JvmOverloads
    fun inflateAsRoot(
        context: Context, @LayoutRes id: Int,
        root: ViewGroup = FrameLayout(context)
    ): View {
        return LayoutInflater.from(context).inflate(id, root, false)
    }

    @JvmStatic
    fun inflateAsRoot(@LayoutRes id: Int, root: ViewGroup): View {
        return LayoutInflater.from(root.context).inflate(id, root, false)
    }

    // todo call place replace with fun inflateWithRoot(@LayoutRes id: Int, root: ViewGroup): View
    @JvmStatic
    fun inflateWithRoot(context: Context, @LayoutRes id: Int, root: ViewGroup): View {
        return LayoutInflater.from(context).inflate(id, root)
    }

    @JvmStatic
    fun inflateWithRoot(@LayoutRes id: Int, root: ViewGroup): View {
        return LayoutInflater.from(root.context).inflate(id, root)
    }

    /**
     * 获取当前 View 在屏幕的矩形，多用于动画相关
     */
    @JvmStatic
    fun getViewRect(view: View): Rect {
        val rect = Rect()
        view.getGlobalVisibleRect(rect)
        return rect
    }

    @JvmStatic
    fun removeViewFromWindowSafely(windowManager: WindowManager, view: View) {
        try {
            windowManager.removeView(view)
        } catch (ignore: Exception) {
        }
    }

}
