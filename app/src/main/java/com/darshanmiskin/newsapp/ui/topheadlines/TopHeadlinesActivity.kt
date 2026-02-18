package com.darshanmiskin.newsapp.ui.topheadlines

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.network.Article
import com.darshanmiskin.newsapp.databinding.ActivityTopHeadlinesBinding
import com.darshanmiskin.newsapp.ui.base.BaseActivityXml
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.utils.gone
import com.darshanmiskin.newsapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopHeadlinesActivity : BaseActivityXml<ActivityTopHeadlinesBinding>() {

    companion object {
        fun createIntent(filter: Filter, value: String) =
            Intent().putExtra("filter", filter.name).putExtra("value", value)
    }

    enum class Filter {
        COUNTRY, LANGUAGE, SOURCE, TOP_HEADLINES
    }

    private val viewModel: TopHeadlineViewModel by viewModels(
        extrasProducer = {
            defaultViewModelCreationExtras.withCreationCallback<TopHeadlineViewModel.FilterFactory> { factory ->
                factory.create(filter, value)
            }
        }
    )

    override val layoutId: Int
        get() = R.layout.activity_top_headlines

    val filter by lazy {
        Filter.valueOf(
            intent.extras?.getString("filter") ?: Filter.TOP_HEADLINES.name
        )
    }
    val value by lazy { intent.extras?.getString("value") ?: "us" }

    private val adapter by lazy {
        TopHeadlinesAdapter {
            CustomTabsIntent.Builder().build().launchUrl(this, it.toUri())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Type: $filter Value: $value"

        binding.rvTopHeadlines.adapter = adapter
        layoutProgress.btnTryAgain.setOnClickListener {
            viewModel.getArticles()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.flow.collect {
                    when (it) {
                        is UiState.Error -> {
                            binding.rvTopHeadlines.gone()
                            layoutProgress.cProgress.gone()
                            layoutProgress.tvMessage.gone()
                            layoutProgress.clError.visible()
                        }

                        UiState.Loading -> {
                            binding.rvTopHeadlines.gone()
                            layoutProgress.cProgress.visible()
                            layoutProgress.tvMessage.gone()
                            layoutProgress.clError.gone()
                        }

                        is UiState.Success<ArrayList<Article>> -> {
                            layoutProgress.cProgress.gone()
                            layoutProgress.clError.gone()
                            layoutProgress.tvMessage.gone()
                            if (it.data.isEmpty()) layoutProgress.tvMessage.visible()
                            else adapter.submitList(it.data)
                            binding.rvTopHeadlines.visible()
                        }

                        UiState.Initial -> {

                        }
                    }
                }
            }
        }
    }
}