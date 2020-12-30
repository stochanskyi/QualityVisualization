package com.mars.qualityvisualization.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mars.qualityvisualization.R
import com.mars.qualityvisualization.data.marksRepository.MarksRepository

class MenuFragment : Fragment(R.layout.fragment_start) {

    companion object {
        fun newInstance(): MenuFragment = MenuFragment()
    }

    private val repository = MarksRepository()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews() {

    }
}