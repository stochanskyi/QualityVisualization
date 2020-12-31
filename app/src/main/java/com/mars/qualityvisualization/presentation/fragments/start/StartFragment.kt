package com.mars.qualityvisualization.presentation.fragments.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mars.qualityvisualization.R
import com.mars.qualityvisualization.data.marksRepository.MarksRepository
import com.mars.qualityvisualization.data.marksRepository.models.ExpertMark
import com.mars.qualityvisualization.data.marksRepository.models.ExpertMarksGroup
import com.mars.qualityvisualization.presentation.activities.main.AppNavigator
import kotlinx.android.synthetic.main.fragment_start.*
import kotlin.math.roundToInt

class StartFragment : Fragment(R.layout.fragment_start) {

    companion object {
        fun newInstance(): StartFragment = StartFragment()
    }

    private var appNavigator: AppNavigator? = null

    private val repository = MarksRepository()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews() {
        val data = loadData()

        appNavigator = activity as AppNavigator

        buttonFirst.setOnClickListener { appNavigator?.openCategoryScreen(data[0]) }
        buttonSecond.setOnClickListener { appNavigator?.openCategoryScreen(data[1]) }
        buttonThird.setOnClickListener { appNavigator?.openCategoryScreen(data[2]) }
        buttonFourth.setOnClickListener { appNavigator?.openCategoryScreen(data[3]) }
        buttonFifth.setOnClickListener { appNavigator?.openCategoryScreen(data[4]) }
    }

    private fun loadData(): List<ExpertMarksGroup>{
        val workingData = repository.getMarksGroups().toMutableList()

        val weightsSum = workingData.sumBy { it.groupWeight }

//        val average = workingData.mapIndexed { index, expertMarksGroup ->
//            val mark = expertMarksGroup.marks.values.toList()[index]
//            mark.mark * mark.weight * expertMarksGroup.groupWeight
//            expertMarksGroup
//        }

        val aver = workingData.map { it.marks.values.toList() }

        val m = mutableMapOf<String, ExpertMark>()

        val factors = workingData.first().marks.keys.toList()

        val groupWeightsSum = workingData.sumBy { it.groupWeight }.toFloat()

        for (i in aver.first().indices) {
            val s = aver.mapIndexed { index, list ->  list[i].mark * list[i].weight * workingData[index].groupWeight }.sum()
            m += factors[i] to ExpertMark(s / groupWeightsSum.roundToInt(), 1)
        }

        workingData.add(ExpertMarksGroup(
            "Узагальнені оцінки",
            m,
            1
        ))

        return workingData.toList()
    }
    override fun onDestroy() {
        appNavigator = null
        super.onDestroy()
    }
}