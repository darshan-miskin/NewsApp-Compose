package com.darshanmiskin.newsapp.data.model.local

import androidx.recyclerview.widget.DiffUtil

data class Country(val name: String, val code: String)

class CountryDiffUtil : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(
        oldItem: Country,
        newItem: Country
    ): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: Country,
        newItem: Country
    ): Boolean {
        return oldItem == newItem
    }

}