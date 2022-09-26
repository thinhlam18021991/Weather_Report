package com.assignment.base.divider

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView

class SeparatorDecoration(
    private val context: Context,
    @ColorRes color: Int,
    heightDp: Float
    ): RecyclerView.ItemDecoration() {
    private var paint: Paint = Paint()

    init {
        paint = Paint()
        paint.color = context.getColor(color)
        val thickness = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                heightDp, context.resources.displayMetrics
        )
        paint.strokeWidth = thickness
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val params = view.layoutParams as RecyclerView.LayoutParams
        val position = params.viewAdapterPosition
        if (position < state.itemCount) {
            outRect[0, 0, 0] = paint.strokeWidth.toInt() ?: 0 // left, top, right, bottom
        } else {
            outRect.setEmpty() // 0, 0, 0, 0
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val offset = (paint.strokeWidth / 2)
        for (i in 0 until parent.childCount) {
            val view: View =
                parent.getChildAt(i)
            val params = view.layoutParams as RecyclerView.LayoutParams
            val position = params.viewAdapterPosition
            if (position < state.itemCount) {
                c.drawLine(
                    view.left.toFloat(),
                    (view.bottom + offset),
                    view.right.toFloat(), (view.bottom + offset), paint
                )
            }
        }
    }
}