package com.mars.qualityvisualization.presentation.fragments.groupInfo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mars.qualityvisualization.R
import com.mars.qualityvisualization.presentation.fragments.groupInfo.models.MarkViewModel
import kotlinx.android.synthetic.main.view_holder_mark.view.*

class MarkViewHolder private constructor(view: View): RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup) : MarkViewHolder {
            return MarkViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_mark, parent, false)
            )
        }
    }

    fun bind(model: MarkViewModel) {
        with(itemView) {
            textTitle.text = model.title
            textMark.text = model.mark.toString()
        }
    }
}