package com.mars.qualityvisualization.data.marksRepository.models

data class ExpertMarksGroup(
    val groupName: String,
    val marks: Map<String, ExpertMark>
)