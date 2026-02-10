package com.darshanmiskin.newsapp.ui.topheadlines

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.network.Article
import com.darshanmiskin.newsapp.data.model.network.ArticleDiffUtil
import com.darshanmiskin.newsapp.databinding.LayoutArticleBinding

class TopHeadlinesAdapter(val onClick: (String) -> Unit) :
    ListAdapter<Article, TopHeadlinesAdapter.TopHeadlinesViewHolder>(ArticleDiffUtil())
{
    private lateinit var context: Context
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TopHeadlinesViewHolder {
        context = parent.context
        val binding = DataBindingUtil.inflate<LayoutArticleBinding>(
            LayoutInflater.from(context), R.layout.layout_article, parent, false
        )
        return TopHeadlinesViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TopHeadlinesViewHolder, position: Int
    ) {
        holder.binding.article = getItem(position)
    }

    inner class TopHeadlinesViewHolder(val binding: LayoutArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick(getItem(absoluteAdapterPosition).url)
            }
        }
    }
}