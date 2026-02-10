package com.darshanmiskin.newsapp.ui.languages

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.local.Language
import com.darshanmiskin.newsapp.data.model.local.LanguageDiffUtil
import com.darshanmiskin.newsapp.databinding.LayoutNamesListBinding

class LanguagesAdapter(private val onClick: () -> Unit) :
    ListAdapter<Language, LanguagesAdapter.LanguagesHolder>(
        LanguageDiffUtil()
    ) {

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LanguagesHolder {
        context = parent.context
        val binding = DataBindingUtil.inflate<LayoutNamesListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_names_list,
            parent,
            false
        )
        return LanguagesHolder(binding)
    }

    override fun onBindViewHolder(
        holder: LanguagesHolder,
        position: Int
    ) {
        holder.binding.name = getItem(position).name
        holder.binding.color = ContextCompat.getColor(context, R.color.card_languages)
    }

    inner class LanguagesHolder(val binding: LayoutNamesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick()
            }
        }
    }
}