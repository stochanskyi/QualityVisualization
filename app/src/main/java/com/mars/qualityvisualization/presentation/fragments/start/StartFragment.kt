package com.mars.qualityvisualization.presentation.fragments.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mars.qualityvisualization.R
import com.mars.qualityvisualization.data.marksRepository.MarksRepository
import com.mars.qualityvisualization.presentation.activities.main.AppNavigator
import kotlinx.android.synthetic.main.fragment_start.*

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
        val data = repository.getMarksGroups()

        appNavigator = activity as AppNavigator

        buttonFirst.setOnClickListener { appNavigator?.openCategoryScreen(data[0]) }
        buttonSecond.setOnClickListener { appNavigator?.openCategoryScreen(data[1]) }
        buttonThird.setOnClickListener { appNavigator?.openCategoryScreen(data[2]) }
        buttonFourth.setOnClickListener { appNavigator?.openCategoryScreen(data[3]) }
    }

    override fun onDestroy() {
        appNavigator = null
        super.onDestroy()
    }
}