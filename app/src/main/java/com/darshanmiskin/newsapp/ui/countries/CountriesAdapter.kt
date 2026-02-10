package com.darshanmiskin.newsapp.ui.countries

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.local.Country
import com.darshanmiskin.newsapp.data.model.local.CountryDiffUtil
import com.darshanmiskin.newsapp.databinding.LayoutNamesListBinding

class CountriesAdapter(private val onClick:(value: String) -> Unit) : ListAdapter<Country, CountriesAdapter.CountriesHolder>(CountryDiffUtil()) {

    private lateinit var context: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountriesHolder {
        context = parent.context
        val binding = DataBindingUtil.inflate<LayoutNamesListBinding>(
            LayoutInflater.from(context),
            R.layout.layout_names_list,
            parent,
            false
        )
        return CountriesHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CountriesHolder,
        position: Int
    ) {
        holder.binding.name = getItem(position).name
        holder.binding.color = ContextCompat.getColor(context, R.color.card_countries)
    }

    inner class CountriesHolder(val binding: LayoutNamesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick(getItem(absoluteAdapterPosition).code)
            }
        }
    }
}