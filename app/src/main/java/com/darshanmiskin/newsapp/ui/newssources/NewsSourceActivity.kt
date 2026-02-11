package com.darshanmiskin.newsapp.ui.newssources

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darshanmiskin.newsapp.NewsApplication
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.network.Source
import com.darshanmiskin.newsapp.databinding.ActivityNewsSourceBinding
import com.darshanmiskin.newsapp.databinding.LayoutLoadingBinding
import com.darshanmiskin.newsapp.di.component.DaggerActivityComponent
import com.darshanmiskin.newsapp.di.module.ActivityModule
import com.darshanmiskin.newsapp.ui.base.BaseActivity
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesActivity
import com.darshanmiskin.newsapp.utils.gone
import com.darshanmiskin.newsapp.utils.visible
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourceActivity : BaseActivity<ActivityNewsSourceBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_news_source

    @Inject
    lateinit var viewModel: NewsSourceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()

        title = ContextCompat.getString(this, R.string.news_sources)

        val layoutProgress = LayoutLoadingBinding.bind(binding.root)
        val adapter = SourcesAdapter {
            val intent = TopHeadlinesActivity.createIntent(
                TopHeadlinesActivity.Filter.SOURCE,
                it
            )
            startActivity(TopHeadlinesActivity::class.java, intent)
        }

        layoutProgress.btnTryAgain.setOnClickListener {
            binding.rvCountries.gone()
            layoutProgress.cProgress.visible()
            layoutProgress.tvMessage.gone()
            layoutProgress.clError.gone()

            viewModel.getNewsSources()
        }

        binding.rvCountries.adapter = adapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
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
                            binding.rvCountries.visible()
                            layoutProgress.cProgress.gone()
                            layoutProgress.tvMessage.gone()
                            layoutProgress.clError.gone()
                            if (it.data.isEmpty())
                                layoutProgress.tvMessage.visible()
                            else
                                adapter.submitList(it.data)
                        }
                        UiState.Initial -> {

                        }
                    }
                }
            }
        }
    }

    fun inject(){
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }
}