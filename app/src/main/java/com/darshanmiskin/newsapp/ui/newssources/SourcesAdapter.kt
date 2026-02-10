package com.darshanmiskin.newsapp.ui.newssources

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.network.Source
import com.darshanmiskin.newsapp.data.model.network.SourceDiffUtil
import com.darshanmiskin.newsapp.databinding.LayoutNamesListBinding

class SourcesAdapter(private val onClick: () -> Unit) :
    ListAdapter<Source, SourcesAdapter.SourcesHolder>(
        SourceDiffUtil()
    ) {

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SourcesHolder {
        context = parent.context
        val binding = DataBindingUtil.inflate<LayoutNamesListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_names_list,
            parent,
            false
        )
        return SourcesHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SourcesHolder,
        position: Int
    ) {
        holder.binding.name = getItem(position).name
        holder.binding.color = ContextCompat.getColor(context, R.color.card_sources)
    }

    inner class SourcesHolder(val binding: LayoutNamesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick()
            }
        }
    }
}