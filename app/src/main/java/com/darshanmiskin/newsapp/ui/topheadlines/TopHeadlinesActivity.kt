package com.darshanmiskin.newsapp.ui.topheadlines

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darshanmiskin.newsapp.NewsApplication
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.local.Country
import com.darshanmiskin.newsapp.data.model.network.Article
import com.darshanmiskin.newsapp.databinding.ActivityTopHeadlinesBinding
import com.darshanmiskin.newsapp.databinding.LayoutLoadingBinding
import com.darshanmiskin.newsapp.di.component.DaggerActivityComponent
import com.darshanmiskin.newsapp.di.module.ActivityModule
import com.darshanmiskin.newsapp.ui.base.BaseActivity
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.utils.gone
import com.darshanmiskin.newsapp.utils.visible
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopHeadlinesActivity : BaseActivity<ActivityTopHeadlinesBinding>() {

    companion object {
        fun createIntent(filter: Filter, value: String) = Intent().putExtra("filter", filter.name).putExtra("value", value)
    }
    enum class Filter {
        COUNTRY, LANGUAGE, SOURCE, TOP_HEADLINES
    }

    @Inject
    lateinit var viewModel: TopHeadlineViewModel

    override val layoutId: Int
        get() = R.layout.activity_top_headlines

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val filter = Filter.valueOf(intent.extras?.getString("filter")?:Filter.TOP_HEADLINES.name)
        val value = intent.extras?.getString("value")?:"us"
        title = "Type: $filter Value: $value"
        inject(filter, value)

        val layoutProgress = LayoutLoadingBinding.bind(binding.root)
        val adapter = TopHeadlinesAdapter{
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        binding.rvTopHeadlines.adapter = adapter
        layoutProgress.btnTryAgain.setOnClickListener {
            binding.rvTopHeadlines.gone()
            layoutProgress.cProgress.visible()
            layoutProgress.tvMessage.gone()
            layoutProgress.clError.gone()
            viewModel.getArticles()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
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
                            binding.rvTopHeadlines.visible()
                            layoutProgress.cProgress.gone()
                            layoutProgress.clError.gone()
                            layoutProgress.tvMessage.gone()
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

    fun inject(filter: Filter, value: String){
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this, filter, value))
            .build()
            .inject(this)
    }
}