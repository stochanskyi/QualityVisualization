package com.mars.qualityvisualization.data.marksRepository.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ExpertMark(
    val mark: Int,
    val weight: Int
) : Parcelable