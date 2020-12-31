package com.mars.qualityvisualization.data.marksRepository.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExpertMarksGroup(
    val groupName: String,
    val marks: Map<String, ExpertMark>,
    val groupWeight: Int
) : Parcelable