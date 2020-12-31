package com.mars.qualityvisualization.presentation.activities.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mars.qualityvisualization.R
import com.mars.qualityvisualization.data.marksRepository.models.ExpertMarksGroup
import com.mars.qualityvisualization.presentation.fragments.groupInfo.GroupInfoFragment
import com.mars.qualityvisualization.presentation.fragments.start.StartFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AppNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(fragmentContainer.id, StartFragment.newInstance())
            .commit()
    }

    override fun openCategoryScreen(data: ExpertMarksGroup) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(fragmentContainer.id, GroupInfoFragment.newInstance(data))
            .commit()
    }

    override fun openPolarScreen() {
        //TODO
    }
}