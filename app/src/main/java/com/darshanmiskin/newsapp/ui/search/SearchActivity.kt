package com.darshanmiskin.newsapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darshanmiskin.newsapp.NewsApplication
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.network.Article
import com.darshanmiskin.newsapp.databinding.ActivitySearchBinding
import com.darshanmiskin.newsapp.databinding.LayoutLoadingBinding
import com.darshanmiskin.newsapp.di.component.DaggerActivityComponent
import com.darshanmiskin.newsapp.di.module.ActivityModule
import com.darshanmiskin.newsapp.ui.base.BaseActivity
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesAdapter
import com.darshanmiskin.newsapp.utils.gone
import com.darshanmiskin.newsapp.utils.visible
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_search

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()

        val layoutProgress = LayoutLoadingBinding.bind(binding.root)
        val adapter = TopHeadlinesAdapter {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        binding.rvSearch.adapter = adapter
        layoutProgress.btnTryAgain.setOnClickListener {
            binding.rvSearch.gone()
            layoutProgress.cProgress.visible()
            layoutProgress.tvMessage.gone()
            layoutProgress.clError.gone()
            viewModel.search(viewModel.query.value)
        }

        binding.searchView.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                viewModel.search(s.toString())
            }
        })

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Error -> {
                            binding.rvSearch.gone()
                            layoutProgress.cProgress.gone()
                            layoutProgress.tvMessage.gone()
                            layoutProgress.clError.visible()
                        }

                        UiState.Loading -> {
                            binding.rvSearch.gone()
                            layoutProgress.cProgress.visible()
                            layoutProgress.tvMessage.gone()
                            layoutProgress.clError.gone()
                        }

                        is UiState.Success<ArrayList<Article>> -> {
                            layoutProgress.cProgress.gone()
                            layoutProgress.clError.gone()
                            layoutProgress.tvMessage.gone()
                            if (it.data.isEmpty())
                                layoutProgress.tvMessage.visible()
                            else
                                adapter.submitList(it.data)
                            binding.rvSearch.visible()
                        }

                        UiState.Initial -> {
                            binding.rvSearch.visible()
                            layoutProgress.cProgress.gone()
                            layoutProgress.clError.gone()
                            layoutProgress.tvMessage.gone()
                        }
                    }
                }
            }
        }
    }

    fun inject() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }
}