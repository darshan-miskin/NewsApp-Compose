package com.darshanmiskin.newsapp.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.ui.base.BaseActivity
import com.darshanmiskin.newsapp.ui.base.theme.NewsApplicationTheme
import com.darshanmiskin.newsapp.ui.countries.CountriesActivity
import com.darshanmiskin.newsapp.ui.languages.LanguagesActivity
import com.darshanmiskin.newsapp.ui.main.model.MenuItem
import com.darshanmiskin.newsapp.ui.newssources.NewsSourceActivity
import com.darshanmiskin.newsapp.ui.search.SearchActivity
import com.darshanmiskin.newsapp.ui.search.SearchScreen
import com.darshanmiskin.newsapp.ui.search.SearchViewModel
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadLinesScreen
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlineViewModel
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesActivity
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesActivity.Filter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val mainMenu by lazy {
        listOf(
//            MenuItem(
//                R.string.top_headlines,
//                { startActivity(TopHeadlinesActivity::class.java) }),
            MenuItem(
                R.string.news_sources,
                { startActivity(NewsSourceActivity::class.java) }),
            MenuItem(
                R.string.countries,
                { startActivity(CountriesActivity::class.java) }),
            MenuItem(
                R.string.languages,
                { startActivity(LanguagesActivity::class.java) }),
//            MenuItem(
//                R.string.search,
//                { startActivity(SearchActivity::class.java) }),
        )
    }

    private val viewModelTopHeadLines: TopHeadlineViewModel by viewModels(
        extrasProducer = {
            defaultViewModelCreationExtras.withCreationCallback<TopHeadlineViewModel.FilterFactory> { factory ->
                factory.create(Filter.TOP_HEADLINES, "us")
            }
        }
    )
    private val viewModelSearch: SearchViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsApplicationTheme {
                MainScreen(mainMenu){ paddingValues, destination ->
                    when (destination) {
                        Destination.TOPHEADLINES -> {
                            TopHeadLinesScreen(viewModelTopHeadLines, paddingValues) { url ->
                                CustomTabsIntent.Builder().build().launchUrl(this, url.toUri())
                            }
                        }
                        Destination.SEARCH -> {
                            SearchScreen(viewModelSearch, paddingValues) {
                                CustomTabsIntent.Builder().build()
                                    .launchUrl(
                                        this,
                                        it.toUri()
                                    )
                            }
                        }
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMainScreen() {
        val menu = listOf(
            MenuItem(R.string.top_headlines, {}),
            MenuItem(R.string.news_sources, {}),
            MenuItem(R.string.countries, {}),
            MenuItem(R.string.languages, {}),
            MenuItem(R.string.search, {}),
        )
        MainScreen(menu){paddingValues, destination -> }
    }
}