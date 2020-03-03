package com.imxie.core.util

import android.graphics.drawable.GradientDrawable
import androidx.annotation.ColorInt
import group.infotech.drawable.dsl.corners
import group.infotech.drawable.dsl.shapeDrawable
import group.infotech.drawable.dsl.solidColor
import group.infotech.drawable.dsl.stroke

object DrawableDecorator {

    fun rect(
        @ColorInt solidColor: Int = 0,
        corner: Float = 0f,
        strokeWidth: Int = 0,
        @ColorInt strokeColor: Int = 0
    ) =
        shapeDrawable {
            this.solidColor = solidColor
            shape = GradientDrawable.RECTANGLE
            corners {
                radius = corner
            }

            if (strokeWidth > 0) {
                stroke {
                    width = strokeWidth
                    color = strokeColor
                }
            }
        }

    fun oval(@ColorInt color: Int) =
        shapeDrawable {
            shape = GradientDrawable.OVAL
            solidColor = color
        }

    fun gradualRect(
        @ColorInt colors: IntArray, cornerDp: Float = 0f,
        orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT
    ) = shapeDrawable {
        this.colors = colors
        corners { radius = cornerDp }
        this.orientation = orientation
    }
}