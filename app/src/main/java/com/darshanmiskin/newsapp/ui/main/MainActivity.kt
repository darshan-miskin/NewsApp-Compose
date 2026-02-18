package com.darshanmiskin.newsapp.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.ui.base.BaseActivity
import com.darshanmiskin.newsapp.ui.base.theme.NewsApplicationTheme
import com.darshanmiskin.newsapp.ui.countries.CountriesActivity
import com.darshanmiskin.newsapp.ui.languages.LanguagesActivity
import com.darshanmiskin.newsapp.ui.main.model.MenuItem
import com.darshanmiskin.newsapp.ui.newssources.NewsSourceActivity
import com.darshanmiskin.newsapp.ui.search.SearchActivity
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesActivity

class MainActivity : BaseActivity() {

    private val mainMenu by lazy {
        listOf(
            MenuItem(
                getString(R.string.top_headlines),
                { startActivity(TopHeadlinesActivity::class.java) }),
            MenuItem(
                getString(R.string.news_sources),
                { startActivity(NewsSourceActivity::class.java) }),
            MenuItem(
                getString(R.string.countries),
                { startActivity(CountriesActivity::class.java) }),
            MenuItem(
                getString(R.string.languages),
                { startActivity(LanguagesActivity::class.java) }),
            MenuItem(
                getString(R.string.search),
                { startActivity(SearchActivity::class.java) }),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsApplicationTheme {
                Scaffold(content = { paddingValues ->
                    MainScreen(mainMenu, paddingValues)
                })
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMainScreen() {
        val menu = listOf(
            MenuItem("1", {}),
            MenuItem("2", {}),
            MenuItem("3", {}),
            MenuItem("4", {}),
            MenuItem("5", {}),
        )
        MainScreen(menu, PaddingValues())
    }
}