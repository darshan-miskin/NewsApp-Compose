package com.darshanmiskin.newsapp.ui.newssources

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.network.Source
import com.darshanmiskin.newsapp.ui.base.BaseActivity
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.ui.base.theme.NewsApplicationTheme
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsSourceActivity : BaseActivity() {
    private val viewModel: NewsSourceViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsApplicationTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = stringResource(R.string.news_sources))
                            }
                        )
                    },
                    content = { paddingValues ->
                        NewsSourcesScreen(viewModel, paddingValues) {
                            val intent = TopHeadlinesActivity.createIntent(
                                TopHeadlinesActivity.Filter.SOURCE,
                                it
                            )
                            startActivity(TopHeadlinesActivity::class.java, intent)
                        }
                    }
                )
            }
        }
    }
}