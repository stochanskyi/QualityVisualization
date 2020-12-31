package com.mars.qualityvisualization.presentation.fragments.groupInfo.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mars.qualityvisualization.presentation.fragments.groupInfo.models.MarkViewModel

class MarksAdapter : RecyclerView.Adapter<MarkViewHolder>() {

    private val items: MutableList<MarkViewModel> = mutableListOf()

    fun updateItems(items: List<MarkViewModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkViewHolder {
        return MarkViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MarkViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}