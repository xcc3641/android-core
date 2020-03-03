package com.imxie.core.ktx.view

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Outline
import android.view.*
import androidx.annotation.Px
import com.imxie.core.ktx.content.dip

fun View.doOnAttach(action: (view: View) -> Unit) = addOnAttachStateChangeListener(onAttach = action)
fun View.doOnDetach(action: (view: View) -> Unit) = addOnAttachStateChangeListener(onDetach = action)

fun View.addOnAttachStateChangeListener(
        onAttach: ((view: View) -> Unit)? = null,
        onDetach: ((view: View) -> Unit)? = null
): View.OnAttachStateChangeListener {
    val listener = object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(v: View) {
            onAttach?.invoke(v)
        }

        override fun onViewDetachedFromWindow(v: View) {
            onDetach?.invoke(v)
        }
    }
    addOnAttachStateChangeListener(listener)
    return listener
}

fun View.doOnScrollChanged(action: () -> Unit) {
    viewTreeObserver.addOnScrollChangedListener {
        action()
    }
}

/**
 * 如果 [block] 返回 true，View 为 VISIBLE，否则为 INVISIBLE (invisibleOrGone=true) 或者 GONE (invisibleOrGone=false)
 */
fun <T : View> T.takeIfVisible(invisibleOrGone: Boolean = false, block: () -> Boolean): T? {
    return if (
            if (invisibleOrGone) visibleOrInvisible(block)
            else visibleOrGone(block)) this
    else null
}

fun <T : View> T.visibleOrGone(block: () -> Boolean): Boolean =
        block().also { visibility = if (it) View.VISIBLE else View.GONE }

fun <T : View> T.visibleOrInvisible(block: () -> Boolean): Boolean =
        block().also { visibility = if (it) View.VISIBLE else View.INVISIBLE }

fun <T : View> List<T>.visibleOrGone(block: () -> Boolean): Boolean {
    val show = block()
    forEach { it.visibleOrGone { show } }
    return show
}


@JvmOverloads
fun View.screenshot(emptyBmpGet: (Int, Int) -> Bitmap? = { w, h ->
    try {
        Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    } catch (e: OutOfMemoryError) {
        null
    }
}): Bitmap? {
    return (width to height).takeIf { it.first > 0 && it.second > 0 }
            ?.let { emptyBmpGet(it.first, it.second) }
            ?.also {
                val canvas = Canvas(it)
                canvas.drawColor(Color.WHITE)
                draw(canvas)
            }
}

/**
 * 当 [width] 和 [height] 都大于 0 时执行 measure 和 layout 操作
 */
fun View.fakeLayout(width: Int, height: Int) {
    if (width > 0 && height > 0) {
        measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.AT_MOST))
        layout(0, 0, measuredWidth, measuredHeight)
    }
}

fun View.fakeLayoutFixedWidth(width: Int) {
    measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
    layout(0, 0, measuredWidth, measuredHeight)
}


fun View.setRoundCorner(radius: Float = context.dip(4f).toFloat()) {
    clipToOutline = true
    outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            val width = view.width
            val height = view.height
            if (width.coerceAtMost(height) >= radius * 2) {
                outline.setRoundRect(0, 0, width, height, radius)
            }
        }
    }
}

fun View.updateScale(sx: Float, sy: Float) {
    scaleX = sx
    scaleY = sy
}

fun View.updateAllScale(all: Float) {
    updateScale(all, all)
}


fun View.updateMargin(
        start: Int? = null,
        top: Int? = null,
        end: Int? = null,
        bottom: Int? = null
) {
    layoutParams = (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
        marginStart = start ?: marginStart
        topMargin = top ?: topMargin
        marginEnd = end ?: marginEnd
        bottomMargin = bottom ?: bottomMargin
    }
}

fun View.updateAllMargin(all: Int) {
    updateMargin(all, all, all, all)
}

fun View.updateAllPadding(@Px all: Int) {
    setPadding(all, all, all, all)
}

fun View.updateSize(width: Int? = null, height: Int? = null) {
    width?.also { layoutParams.width = it }
    height?.also { layoutParams.height = it }
    requestLayout()
}

fun View.doOnLayoutSizeChange(block: () -> Unit) {
    addOnLayoutChangeListener { _, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
        if (right - left != oldRight - oldLeft || bottom - top != oldBottom - oldTop) {
            block()
        }
    }
}

/**
 * Use this method only when you are not sure whether requestLayout directly work or not,
 * since there is additional check work.
 */
fun View.safeRequestLayout() {
    fun isSafeToRequestDirectly(): Boolean {
        return if (isInLayout) {
            // when isInLayout == true and isLayoutRequested == true,
            // means that this layout pass will layout current view which will
            // make currentView.isLayoutRequested == false, and this will let currentView
            // ignored in process handling requests called during last layout pass.
            isLayoutRequested.not()
        } else {
            var ancestorLayoutRequested = false
            var p: ViewParent? = parent
            while (p != null) {
                if (p.isLayoutRequested) {
                    ancestorLayoutRequested = true
                    break
                }
                p = p.parent
            }
            ancestorLayoutRequested.not()
        }
    }

    if (isSafeToRequestDirectly()) {
        requestLayout()
    } else {
        post { requestLayout() }
    }
}

fun View.doubleClick(block: () -> Unit) {
    val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent?): Boolean {
            block()
            return true
        }
    })
    isClickable = true
    setOnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }
}

