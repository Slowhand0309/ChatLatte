package com.github.slowhand.chatlatte.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.ImageView

class RoundedImageView: ImageView {

    var isOutgoing = true
    private val path = Path()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun initialize() {

        val r = context.resources.displayMetrics.density * 10.0f
        val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
        val radii = if (isOutgoing) {
            floatArrayOf(r, r, r, r, 0f, 0f, r, r)
        } else {
            floatArrayOf(r, r, r, r, r, r, 0f, 0f)
        }

        path.addRoundRect(rect, radii, Path.Direction.CW)
    }

    override fun onDraw(canvas: Canvas?) {
        initialize()
        canvas?.clipPath(path)
        super.onDraw(canvas)
    }
}