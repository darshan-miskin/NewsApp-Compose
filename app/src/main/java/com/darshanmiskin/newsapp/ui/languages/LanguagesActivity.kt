package com.darshanmiskin.newsapp.ui.languages

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
import com.darshanmiskin.newsapp.data.model.local.Language
import com.darshanmiskin.newsapp.ui.base.BaseActivity
import com.darshanmiskin.newsapp.ui.base.theme.NewsApplicationTheme
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguagesActivity : BaseActivity() {
    private val viewModel: LanguagesViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsApplicationTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(stringResource(R.string.languages))
                        })
                    },
                    content = { paddingValues ->
                        LanguagesScreen(viewModel, paddingValues) {
                            val intent = TopHeadlinesActivity.createIntent(
                                TopHeadlinesActivity.Filter.LANGUAGE,
                                it
                            )
                            startActivity(TopHeadlinesActivity::class.java, intent)
                        }
                    }
                )
            }
        }
    }

    @Preview
    @Composable
    fun PreviewLanguagesScreen() {
        val list = listOf(
            Language("A", "a"),
            Language("A", "b"),
            Language("A", "c"),
            Language("A", "d"),
        )
        LanguagesList(list, PaddingValues()) { }
    }
}