package com.mars.qualityvisualization.presentation.fragments.chart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mars.qualityvisualization.R
import com.mars.qualityvisualization.data.marksRepository.models.ExpertMarksGroup
import com.mars.qualityvisualization.presentation.views.polarChartView.models.PolarCoordinates
import kotlinx.android.synthetic.main.fragment_chart.*
import kotlin.math.PI

class ChartFragment : Fragment(R.layout.fragment_chart) {

    companion object {
        private const val DATA_KEY = "key_data"

        fun newInstance(data: ExpertMarksGroup): ChartFragment {
            return ChartFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DATA_KEY, data)
                }
            }
        }
    }

    private lateinit var data: ExpertMarksGroup

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fetchData()
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun fetchData() {
        data = arguments?.getParcelable(DATA_KEY)!!
    }

    private fun initViews() {
        val angles = processAngles()
        val coordinatess = createVectors(angles)

        viewPolarChart.apply {
            setSectorBounds(angles)
            coordinates = coordinatess
        }
    }

    private fun processAngles(): List<Float> {
        val angles = mutableListOf<Float>()
        val marks = data.marks.values.toList()
        val weightsSum = marks.sumBy { it.weight }

        if (marks.isEmpty()) return angles.toList()

        angles += (2 * PI * marks.first().weight / weightsSum / 2).toFloat()

        for (i in 1 until marks.size) {
            angles += angles.last() + (2 * PI * marks[i].weight / weightsSum).toFloat()
        }

        return angles.toList()
    }

    private fun createVectors(angles: List<Float>): List<PolarCoordinates> {
        val coordinates = mutableListOf<PolarCoordinates>()
        val marks = data.marks.values.toMutableList()

        if(angles.isEmpty()) return coordinates

        coordinates += PolarCoordinates(
            marks.first().mark.toFloat(),
            0f
        )
        marks.removeFirst()

        for (i in 0 until marks.size) {
            coordinates += PolarCoordinates(
                marks[i].mark.toFloat(),
                angles[i] + (angles[i + 1] - angles[i]) / 2
            )
        }

        return coordinates.toList()
    }


}