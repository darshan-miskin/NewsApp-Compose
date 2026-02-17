package com.darshanmiskin.newsapp.ui.newssources

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.network.Source
import com.darshanmiskin.newsapp.databinding.ActivityNewsSourceBinding
import com.darshanmiskin.newsapp.ui.base.BaseActivity
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesActivity
import com.darshanmiskin.newsapp.utils.gone
import com.darshanmiskin.newsapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsSourceActivity : BaseActivity<ActivityNewsSourceBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_news_source

    private val viewModel: NewsSourceViewModel by viewModels()

    private val adapter by lazy {
        SourcesAdapter {
            val intent = TopHeadlinesActivity.createIntent(
                TopHeadlinesActivity.Filter.SOURCE,
                it
            )
            startActivity(TopHeadlinesActivity::class.java, intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = ContextCompat.getString(this, R.string.news_sources)
        layoutProgress.btnTryAgain.setOnClickListener {
            viewModel.getNewsSources()
        }

        binding.rvCountries.adapter = adapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.flow.collect {
                    when (it) {
                        is UiState.Error -> {
                            binding.rvCountries.gone()
                            layoutProgress.cProgress.gone()
                            layoutProgress.tvMessage.gone()
                            layoutProgress.clError.visible()
                        }

                        UiState.Loading -> {
                            binding.rvCountries.gone()
                            layoutProgress.cProgress.visible()
                            layoutProgress.tvMessage.gone()
                            layoutProgress.clError.gone()
                        }

                        is UiState.Success<ArrayList<Source>> -> {
                            layoutProgress.cProgress.gone()
                            layoutProgress.tvMessage.gone()
                            layoutProgress.clError.gone()
                            if (it.data.isEmpty())
                                layoutProgress.tvMessage.visible()
                            else
                                adapter.submitList(it.data)
                            binding.rvCountries.visible()
                        }

                        UiState.Initial -> {

                        }
                    }
                }
            }
        }
    }
}