package com.darshanmiskin.newsapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.darshanmiskin.newsapp.data.model.local.Country
import com.darshanmiskin.newsapp.data.model.local.Language
import com.darshanmiskin.newsapp.data.model.network.Article
import com.darshanmiskin.newsapp.data.model.network.Source

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


class LanguageDiffUtil : DiffUtil.ItemCallback<Language>() {
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


class ArticleDiffUtil : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem == newItem
    }
}


class SourceDiffUtil : DiffUtil.ItemCallback<Source>() {
    override fun areItemsTheSame(
        oldItem: Source,
        newItem: Source
    ): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: Source,
        newItem: Source
    ): Boolean {
        return oldItem == newItem
    }
}