package com.mars.qualityvisualization.presentation.fragments.groupInfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mars.qualityvisualization.R
import com.mars.qualityvisualization.data.marksRepository.models.ExpertMark
import com.mars.qualityvisualization.data.marksRepository.models.ExpertMarksGroup
import com.mars.qualityvisualization.presentation.activities.main.AppNavigator
import com.mars.qualityvisualization.presentation.fragments.groupInfo.adapter.MarksAdapter
import com.mars.qualityvisualization.presentation.fragments.groupInfo.models.MarkViewModel
import kotlinx.android.synthetic.main.fragment_group_info.*

class GroupInfoFragment : Fragment(R.layout.fragment_group_info) {

    companion object {
        private const val DATA_KEY = "key_input_data"

        fun newInstance(data: ExpertMarksGroup): GroupInfoFragment {
            return GroupInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DATA_KEY, data)
                }
            }
        }
    }

    private lateinit var data: ExpertMarksGroup

    private var appNavigator: AppNavigator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        appNavigator = activity as AppNavigator
        retrieveData()
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun retrieveData() {
        data = arguments!!.getParcelable(DATA_KEY)!!
    }

    private fun initViews() {
        textTitle.text = data.groupName

        recyclerMarks.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MarksAdapter().apply {
                updateItems(data.marks.toViewModels())
            }
        }

        buttonDraw.setOnClickListener { _ ->
            appNavigator?.openPolarScreen(
                ExpertMarksGroup(
                    data.groupName,
                    data.marks.mapValues {
                        ExpertMark(
                            it.value.resolveComplexMark(),
                            it.value.weight
                        )
                    }
                )
            )
        }
    }

    override fun onDestroy() {
        appNavigator = null
        super.onDestroy()
    }

    private fun Map<String, ExpertMark>.toViewModels(): List<MarkViewModel> {
        return this.map { MarkViewModel(it.key, it.value.resolveComplexMark()) }
    }

    private fun ExpertMark.resolveComplexMark(): Int = mark * weight
}