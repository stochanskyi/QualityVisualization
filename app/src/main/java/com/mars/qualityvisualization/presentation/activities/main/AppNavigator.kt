package com.mars.qualityvisualization.presentation.activities.main

import com.mars.qualityvisualization.data.marksRepository.models.ExpertMarksGroup

interface AppNavigator {
    fun openCategoryScreen(data: ExpertMarksGroup)
    fun openPolarScreen(data: ExpertMarksGroup)
}