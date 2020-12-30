package com.mars.qualityvisualization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mars.qualityvisualization.views.polarChartView.PolarChartView
import com.mars.qualityvisualization.views.polarChartView.models.PolarCoordinates
import java.lang.Math.PI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<PolarChartView>(R.id.viewPolarChart).coordinates = listOf(
            PolarCoordinates(100f, (PI/2).toFloat()),
            PolarCoordinates(100f, (PI).toFloat()),
            PolarCoordinates(100f, 0f),
            )
    }
}