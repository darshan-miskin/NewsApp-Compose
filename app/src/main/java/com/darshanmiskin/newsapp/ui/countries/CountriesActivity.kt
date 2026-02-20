package com.darshanmiskin.newsapp.ui.countries

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.data.model.local.Country
import com.darshanmiskin.newsapp.ui.base.BaseActivity
import com.darshanmiskin.newsapp.ui.base.theme.NewsApplicationTheme
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesActivity : BaseActivity() {
    private val viewModel: CountriesViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsApplicationTheme {
                Scaffold(topBar = {
                    TopAppBar(
                        { Text(stringResource(R.string.countries)) })
                }, content = { paddingValues ->
                    CountriesScreen(viewModel, paddingValues, onClick = {
                        val intent = TopHeadlinesActivity.createIntent(
                            TopHeadlinesActivity.Filter.COUNTRY, it
                        )
                        startActivity(TopHeadlinesActivity::class.java, intent)
                    })
                })
            }
        }
    }

    @Preview
    @Composable
    fun PreviewCountriesScreen() {
        val list = listOf(
            Country("A", "A"),
            Country("B", "B"),
            Country("C", "C"),
        )
        ListScreen(list, PaddingValues(), {})
    }
}