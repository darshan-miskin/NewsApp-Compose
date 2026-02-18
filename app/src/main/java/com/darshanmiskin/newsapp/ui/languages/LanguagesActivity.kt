package com.darshanmiskin.newsapp.ui.languages

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.local.Language
import com.darshanmiskin.newsapp.databinding.ActivityLanguagesBinding
import com.darshanmiskin.newsapp.ui.base.BaseActivityXml
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesActivity
import com.darshanmiskin.newsapp.utils.gone
import com.darshanmiskin.newsapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LanguagesActivity : BaseActivityXml<ActivityLanguagesBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_languages

    private val viewModel: LanguagesViewModel by viewModels()
    private val adapter by lazy {
        LanguagesAdapter {
            val intent = TopHeadlinesActivity.createIntent(TopHeadlinesActivity.Filter.LANGUAGE, it)
            startActivity(TopHeadlinesActivity::class.java, intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = ContextCompat.getString(this, R.string.languages)
        binding.rvCountries.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.flow.collect {
                    when (it) {
                        is UiState.Error -> {
                            binding.rvCountries.gone()
                            layoutProgress.cProgress.gone()
                        }

                        UiState.Loading -> {
                            binding.rvCountries.gone()
                            layoutProgress.cProgress.visible()
                        }

                        is UiState.Success<ArrayList<Language>> -> {
                            layoutProgress.cProgress.gone()

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