package com.darshanmiskin.newsapp.ui.search

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.core.net.toUri
import com.darshanmiskin.newsapp.ui.base.BaseActivity
import com.darshanmiskin.newsapp.ui.base.theme.NewsApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseActivity() {
    private val viewModel: SearchViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsApplicationTheme {
                Scaffold { paddingValues ->
                    SearchScreen(viewModel, paddingValues) {
                        CustomTabsIntent.Builder().build()
                            .launchUrl(
                                this@SearchActivity,
                                it.toUri()
                            )
                    }
                }
            }
        }
    }
}