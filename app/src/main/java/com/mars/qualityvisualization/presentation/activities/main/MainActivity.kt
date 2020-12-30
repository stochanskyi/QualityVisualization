package com.mars.qualityvisualization.presentation.activities.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mars.qualityvisualization.R
import com.mars.qualityvisualization.presentation.fragments.MenuFragment
import com.mars.qualityvisualization.presentation.views.polarChartView.PolarChartView
import com.mars.qualityvisualization.presentation.views.polarChartView.models.PolarCoordinates
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.PI

class MainActivity : AppCompatActivity(), AppNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(fragmentContainer.id, MenuFragment.newInstance())
            .commit()
    }

    override fun openCategoryScreen() {
        TODO("Not yet implemented")
    }

    override fun openPolarScreen() {
        TODO("Not yet implemented")
    }
}