package com.darshanmiskin.newsapp.ui.countries

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darshanmiskin.newsapp.NewsApplication
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.local.Country
import com.darshanmiskin.newsapp.databinding.ActivityCountriesBinding
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

class CountriesActivity : BaseActivity<ActivityCountriesBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_countries

    @Inject
    lateinit var viewModel: CountriesViewModel
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
//        injectDependencies(this)
        inject()

        title = ContextCompat.getString(this, R.string.countries)
        binding.rvCountries.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
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

    fun inject() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }
}