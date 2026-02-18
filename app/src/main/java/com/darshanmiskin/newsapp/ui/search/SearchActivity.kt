package com.darshanmiskin.newsapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.network.Article
import com.darshanmiskin.newsapp.databinding.ActivitySearchBinding
import com.darshanmiskin.newsapp.ui.base.BaseActivityXml
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesAdapter
import com.darshanmiskin.newsapp.utils.gone
import com.darshanmiskin.newsapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : BaseActivityXml<ActivitySearchBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_search

    private val viewModel: SearchViewModel by viewModels()
    private val adapter by lazy {
        TopHeadlinesAdapter {
            CustomTabsIntent.Builder().build().launchUrl(this, it.toUri())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvSearch.adapter = adapter
        layoutProgress.btnTryAgain.setOnClickListener {
            viewModel.search(viewModel.query.value)
        }

        binding.searchView.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

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
//                            binding.rvSearch.gone()
                            layoutProgress.cProgress.visible()
                            layoutProgress.tvMessage.gone()
                            layoutProgress.clError.gone()
                        }

                        is UiState.Success<ArrayList<Article>> -> {
                            layoutProgress.cProgress.gone()
                            layoutProgress.clError.gone()
                            layoutProgress.tvMessage.gone()
                            if (it.data.isEmpty()) {
                                binding.rvSearch.gone()
                                layoutProgress.tvMessage.visible()
                            } else
                                adapter.submitList(it.data) {
//                                    Log.d("my_log", "submitList")
                                    binding.rvSearch.doOnPreDraw {
                                        binding.rvSearch.visible()
//                                        Log.d("my_log", "doOnPreDraw")
                                    }
                                }

//                            Log.d("my_log", "=========================================================")
//                            binding.rvSearch.doOnLayout { Log.d("my_log", "doOnLayout") }
//                            binding.rvSearch.doOnAttach { Log.d("my_log", "doOnAttach") }
//                            binding.rvSearch.doOnNextLayout { Log.d("my_log", "doOnNextLayout") }
////                            binding.rvSearch.doOnPreDraw { Log.d("my_log", "doOnPreDraw") }
//                            Log.d("my_log", "=========================================================")
                        }

                        UiState.Initial -> {
                            binding.rvSearch.visible()
                            layoutProgress.cProgress.gone()
                            layoutProgress.clError.gone()
                            layoutProgress.tvMessage.gone()
                        }
                    }
                    hideKeyboard()
                }
            }
        }
    }
}