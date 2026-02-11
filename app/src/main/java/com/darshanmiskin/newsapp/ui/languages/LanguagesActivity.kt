package com.darshanmiskin.newsapp.ui.languages

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darshanmiskin.newsapp.NewsApplication
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.local.Language
import com.darshanmiskin.newsapp.databinding.ActivityLanguagesBinding
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

class LanguagesActivity : BaseActivity<ActivityLanguagesBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_languages

    @Inject
    lateinit var viewModel: LanguagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()

        title = ContextCompat.getString(this, R.string.languages)

        val layoutProgress = LayoutLoadingBinding.bind(binding.root)

        val adapter = LanguagesAdapter{
            val intent = TopHeadlinesActivity.createIntent(TopHeadlinesActivity.Filter.LANGUAGE, it)
            startActivity(TopHeadlinesActivity::class.java, intent)
        }
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

    fun inject(){
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }
}