package com.imxie.core.ktx.view

import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.Px
import com.imxie.core.ktx.content.drawable

internal enum class Direction {
    LEFT, TOP, RIGHT, BOTTOM
}

@JvmOverloads
fun TextView.setDrawableLeft(drawable: Drawable?, @Px sizePx: Int? = null, @Px drawablePadding: Int? = null) {
    setDrawable(drawable, Direction.LEFT, sizePx, drawablePadding)
}

@JvmOverloads
fun TextView.setDrawableLeft(@DrawableRes drawable: Int, @Px sizePx: Int? = null, @Px drawablePadding: Int? = null) {
    setDrawableLeft(context.drawable(drawable), sizePx, drawablePadding)
}

@JvmOverloads
fun TextView.setDrawableTop(drawable: Drawable?, @Px sizePx: Int? = null, @Px drawablePadding: Int? = null) {
    setDrawable(drawable, Direction.TOP, sizePx, drawablePadding)
}

@JvmOverloads
fun TextView.setDrawableTop(@DrawableRes drawable: Int, @Px sizePx: Int? = null, @Px drawablePadding: Int? = null) {
    setDrawableTop(context.drawable(drawable), sizePx, drawablePadding)
}

@JvmOverloads
fun TextView.setDrawableRight(drawable: Drawable?, @Px sizePx: Int? = null, @Px drawablePadding: Int? = null) {
    setDrawable(drawable, Direction.RIGHT, sizePx, drawablePadding)
}

@JvmOverloads
fun TextView.setDrawableRight(@DrawableRes drawable: Int, @Px sizePx: Int? = null, @Px drawablePadding: Int? = null) {
    setDrawableRight(context.drawable(drawable), sizePx, drawablePadding)
}

internal fun TextView.setDrawable(drawable: Drawable?, direction: Direction, @Px sizePx: Int? = null, @Px drawablePadding: Int? = null) {
    val oldDrawables = compoundDrawables
    drawable?.apply {
        setBounds(0, 0, sizePx ?: minimumWidth, sizePx ?: minimumHeight)
    }
    setCompoundDrawables(if (direction == Direction.LEFT) drawable else oldDrawables[0],
            if (direction == Direction.TOP) drawable else oldDrawables[1],
            if (direction == Direction.RIGHT) drawable else oldDrawables[2],
            if (direction == Direction.BOTTOM) drawable else oldDrawables[3])
    drawablePadding?.let { compoundDrawablePadding = it }
}

@Suppress("DEPRECATION")
fun TextView.createStaticLayout(source: CharSequence, provideWidth: Int? = null): StaticLayout {
    val w = provideWidth ?: width
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        StaticLayout.Builder.obtain(source, 0, source.length, paint, w).build()
    } else {
        StaticLayout(source, paint, w, Layout.Alignment.ALIGN_NORMAL,
                lineSpacingMultiplier, lineSpacingExtra, includeFontPadding)
    }
}
