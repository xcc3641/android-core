package com.imxie.core.util

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.WindowManager
import com.imxie.core.Core
import com.imxie.core.ktx.content.dip
import com.imxie.core.ktx.kotlin.unsafeLazy

object EnvUtil {

    /** 获取的像素宽高不包含虚拟键所占空间 */
    private val screenMetrics: DisplayMetrics
        get() {
            val wm = Core.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics
        }

    @JvmStatic
    val screenWidth: Int
        get() = screenMetrics.widthPixels

    @JvmStatic
    val screenHeight: Int
        get() = screenMetrics.heightPixels

    /** 获取的像素宽高不包含虚拟键所占空间 */
    private val realScreenMetrics: DisplayMetrics
        get() {
            val wm = Core.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            wm.defaultDisplay.getRealMetrics(displayMetrics)
            return displayMetrics
        }

    @JvmStatic
    val realScreenHeight: Int
        get() = realScreenMetrics.heightPixels

    @JvmStatic
    val actionBarSize: Int by unsafeLazy {
        Core.context.dip(56f)
    }

    @JvmStatic
    val statusBarSize: Int by unsafeLazy {
        getInternalDimensionSize(Core.context, "status_bar_height")
    }

    @JvmStatic
    fun isInScreen(rect: Rect) =
        rect.left < screenWidth && rect.top < screenHeight && rect.right > 0 && rect.bottom > 0

    private fun getInternalDimensionSize(context: Context, key: String): Int {
        val resourceId = context.resources.getIdentifier(key, "dimen", "android")
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }
}
