package com.darshanmiskin.newsapp.ui.countries

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.local.Country
import com.darshanmiskin.newsapp.databinding.ActivityCountriesBinding
import com.darshanmiskin.newsapp.ui.base.BaseActivityXml
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesActivity
import com.darshanmiskin.newsapp.utils.gone
import com.darshanmiskin.newsapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CountriesActivity : BaseActivityXml<ActivityCountriesBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_countries

    private val viewModel: CountriesViewModel by viewModels()
    private val adapter by lazy {
        CountriesAdapter {
            val intent = TopHeadlinesActivity.createIntent(
                TopHeadlinesActivity.Filter.COUNTRY,
                it
            )
            startActivity(TopHeadlinesActivity::class.java, intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = ContextCompat.getString(this, R.string.countries)
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

                        is UiState.Success<ArrayList<Country>> -> {
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