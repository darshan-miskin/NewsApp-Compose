package com.darshanmiskin.newsapp.data.model.local

import androidx.recyclerview.widget.DiffUtil

data class Language(val name: String, val code: String)

class LanguageDiffUtil: DiffUtil.ItemCallback<Language>(){
    override fun areItemsTheSame(
        oldItem: Language,
        newItem: Language
    ): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: Language,
        newItem: Language
    ): Boolean {
        return oldItem == newItem
    }

}