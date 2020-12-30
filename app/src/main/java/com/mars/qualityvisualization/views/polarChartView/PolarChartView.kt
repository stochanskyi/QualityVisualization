package com.mars.qualityvisualization.views.polarChartView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.use
import com.mars.qualityvisualization.R
import com.mars.qualityvisualization.views.polarChartView.models.PolarCoordinates
import com.mars.qualityvisualization.views.polarChartView.transformer.CoordinatesTransformer.toDrawableCoordinates
import kotlin.math.min

class PolarChartView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    companion object {
        private const val DEFAULT_CIRCLES_COUNT = 10
    }

    private val circlePaint: Paint = Paint()
    private val polygonPaint: Paint = Paint()

    private var polygonPath: Path = Path()

    init {
        initPaints()
        initAttrs(attrs, defStyleAttr, defStyleRes)
    }

    private fun initPaints() {
        circlePaint.apply {
            style = Paint.Style.STROKE
            color = Color.GRAY
            strokeWidth = 1f
        }

        polygonPaint.apply {
            style = Paint.Style.STROKE
            color = Color.RED
            strokeWidth = 1f
        }
    }

    private fun initAttrs(
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
    ) {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.PolarChartView,
            defStyleAttr,
            defStyleRes
        ).use {
            circlesCount =
                it.getInteger(R.styleable.PolarChartView_pc_circles_count, DEFAULT_CIRCLES_COUNT)
        }
    }


    private val circlesRadius: MutableList<Int> = mutableListOf()

    private fun updatePolygonPath() {
        if (coordinates.isEmpty()) return

        val drawableCoordinates = coordinates.map { it.toDrawableCoordinates(width, height) }

        polygonPath = Path().apply {
            moveTo(drawableCoordinates.first().x, drawableCoordinates.first().y)
            for (i in 1 until drawableCoordinates.size) {
                lineTo(drawableCoordinates[i].x, drawableCoordinates[i].y)
            }
            lineTo(drawableCoordinates.first().x, drawableCoordinates.first().y)
            close()
        }

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        resolveCirclesSizes()
        updatePolygonPath()
    }

    private fun resolveCirclesSizes() {
        circlesRadius.clear()

        if (circlesCount <= 0) return
        val viewClearWidth = width - paddingStart - paddingEnd
        val viewClearHeight = height - paddingTop - paddingBottom

        val maxCircleRadius = min(viewClearHeight, viewClearWidth) / 2

        val step = maxCircleRadius / circlesCount

        circlesRadius.add(maxCircleRadius)

        for (i in 1..circlesCount) {
            circlesRadius.add(circlesRadius.last() - step)
        }

        circlesRadius.add(1)
    }

    override fun onDraw(canvas: Canvas) {
        val viewCentreX = width.toFloat() / 2
        val viewCenterY = height.toFloat() / 2

        circlesRadius.forEach {
            canvas.drawCircle(viewCentreX, viewCenterY, it.toFloat(), circlePaint)
        }

        canvas.drawPath(polygonPath, polygonPaint)

        super.onDraw(canvas)
    }

    var circlesCount: Int = DEFAULT_CIRCLES_COUNT
        set(value) {
            field = value
            requestLayout()
        }

    var coordinates: List<PolarCoordinates> = listOf()
        set(value) {
            field = value
            updatePolygonPath()
            invalidate()
        }
}