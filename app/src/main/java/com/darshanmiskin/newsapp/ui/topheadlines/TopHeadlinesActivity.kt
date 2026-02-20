package com.darshanmiskin.newsapp.ui.topheadlines

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.network.Article
import com.darshanmiskin.newsapp.databinding.ActivityTopHeadlinesBinding
import com.darshanmiskin.newsapp.ui.base.BaseActivity
import com.darshanmiskin.newsapp.ui.base.BaseActivityXml
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.ui.base.theme.NewsApplicationTheme
import com.darshanmiskin.newsapp.utils.gone
import com.darshanmiskin.newsapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopHeadlinesActivity : BaseActivity() {

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

    val filter by lazy {
        Filter.valueOf(
            intent.extras?.getString("filter") ?: Filter.TOP_HEADLINES.name
        )
    }
    val value by lazy { intent.extras?.getString("value") ?: "us" }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Type: $filter Value: $value"

        setContent {
            NewsApplicationTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Type: $filter Value: $value")
                            }
                        )
                    },
                    content = { paddingValues ->
                        TopHeadLinesScreen(viewModel, paddingValues) {
                            TopHeadlinesAdapter {
                                CustomTabsIntent.Builder().build().launchUrl(this, it.toUri())
                            }
                        }
                    }
                )
            }
        }
    }
}