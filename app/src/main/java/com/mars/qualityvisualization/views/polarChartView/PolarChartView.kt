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
        private const val DEFAULT_MAX_RADIUS = 100
    }

    private val circlePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val coordinatesPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val polygonFillPaint: Paint = Paint()
    private val polygonStrokeColor: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val vectorPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val sectorPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var polygonPath: Path = Path()

    private val maxCoordinatesSize
        get() = min(
            width - paddingStart - paddingEnd,
            height - paddingTop - paddingBottom
        )

    init {
        initPaints()
        initAttrs(attrs, defStyleAttr, defStyleRes)
    }

    private fun initPaints() {
        circlePaint.apply {
            style = Paint.Style.STROKE
            color = Color.parseColor("#CCCCCC")
            strokeWidth = 1f
        }

        polygonStrokeColor.apply {
            style = Paint.Style.STROKE
            color = Color.BLACK
            strokeWidth = 5f
        }

        polygonFillPaint.apply {
            style = Paint.Style.FILL
            color = Color.parseColor("#AA00FF00")
            strokeWidth = 1f
        }

        vectorPaint.apply {
            style = Paint.Style.STROKE
            color = Color.RED
            strokeWidth = 3f
        }

        coordinatesPaint.apply {
            style = Paint.Style.STROKE
            color = Color.parseColor("#336600")
            strokeWidth = 1f
        }

        sectorPaint.apply {
            style = Paint.Style.STROKE
            color = Color.BLACK
            strokeWidth = 2f
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

        val maxCircleRadius = maxCoordinatesSize / 2

        val step = maxCircleRadius / circlesCount

        circlesRadius.add(maxCircleRadius)

        for (i in 1..circlesCount) {
            circlesRadius.add(circlesRadius.last() - step)
        }

        circlesRadius.add(1)
    }

    override fun onDraw(canvas: Canvas) {
        val viewCenterX = width.toFloat() / 2
        val viewCenterY = height.toFloat() / 2

        drawCircles(canvas, viewCenterX, viewCenterY)
        drawCoordinates(canvas, viewCenterX, viewCenterY)

        canvas.drawPath(polygonPath, polygonFillPaint)
        drawVectors(canvas, viewCenterX, viewCenterY)
        canvas.drawPath(polygonPath, polygonStrokeColor)

        drawSectors(canvas, viewCenterX, viewCenterY)

        super.onDraw(canvas)
    }

    private fun drawCircles(canvas: Canvas, centerX: Float, centerY: Float) {
        circlesRadius.forEach {
            canvas.drawCircle(centerX, centerY, it.toFloat(), circlePaint)
        }
    }

    private fun drawCoordinates(canvas: Canvas, centerX: Float, centerY: Float) {
        canvas.drawLine(centerX, 0f, centerX, height.toFloat(), coordinatesPaint)
        canvas.drawLine(0f, centerY, width.toFloat(), centerY, coordinatesPaint)
    }

    private fun drawVectors(canvas: Canvas, centerX: Float, centerY: Float) {
        coordinates.forEach {
            val coordinate = it.toDrawableCoordinates(width, height)
            canvas.drawLine(centerX, centerY, coordinate.x, coordinate.y, vectorPaint)
        }
    }

    private fun drawSectors(canvas: Canvas, centerX: Float, centerY: Float) {
        sectorBounds.forEach{
            val coordinate = it.toDrawableCoordinates(width, height)
            canvas.drawLine(centerX, centerY, coordinate.x, coordinate.y, sectorPaint)
        }
    }

    private fun PolarCoordinates.scaleCoordinates(): PolarCoordinates {
        return PolarCoordinates(
            scaleCoordinate(radius),
            angle
        )
    }

    private fun scaleCoordinate(coordinate: Float): Float =
        coordinate * maxCoordinatesSize / 2 / DEFAULT_MAX_RADIUS

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
    get() {
        return field.map { it.scaleCoordinates() }
    }

    private var sectorBounds: List<PolarCoordinates> = listOf()
        get () = field.map { it.scaleCoordinates() }

    fun setSectorBounds(boundsAngles: List<Float>) {
        sectorBounds = boundsAngles.map { PolarCoordinates(DEFAULT_MAX_RADIUS.toFloat(), it) }
        invalidate()
    }
}