package com.mars.qualityvisualization.presentation.views.polarChartView.transformer

import com.mars.qualityvisualization.presentation.views.polarChartView.models.CartesianCoordinates
import com.mars.qualityvisualization.presentation.views.polarChartView.models.PolarCoordinates
import kotlin.math.cos
import kotlin.math.sin

internal object CoordinatesTransformer {

    fun PolarCoordinates.toCartesianCoordinates(): CartesianCoordinates {
        return CartesianCoordinates(
            radius * cos(angle),
            radius * sin(angle)
        )
    }

    fun PolarCoordinates.toDrawableCoordinates(width: Int, height: Int): CartesianCoordinates {
        return toCartesianCoordinates().toDrawableCoordinates(width, height)
    }

    fun CartesianCoordinates.toDrawableCoordinates(width: Int, height: Int): CartesianCoordinates {
        return CartesianCoordinates(
            x + width / 2,
            height / 2 - y
        )
    }
}