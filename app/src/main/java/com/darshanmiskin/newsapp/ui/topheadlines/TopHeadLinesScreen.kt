package com.darshanmiskin.newsapp.ui.topheadlines

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.darshanmiskin.newsapp.data.model.network.Article
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.ui.base.theme.Error
import com.darshanmiskin.newsapp.ui.base.theme.Loading
import com.darshanmiskin.newsapp.ui.base.theme.NewsItem
import com.darshanmiskin.newsapp.ui.base.theme.NoDataFound

@Composable
fun TopHeadLinesScreen(viewModel: TopHeadlineViewModel, paddingValues: PaddingValues, onClick:(String) -> Unit){
    val uiState by viewModel.flow.collectAsStateWithLifecycle()
    when(val state = uiState){
        is UiState.Error -> Error { viewModel.getArticles() }
        UiState.Initial -> {}
        UiState.Loading -> Loading()
        is UiState.Success<List<Article>> -> {
            if (state.data.isEmpty())
                NoDataFound()
            else
                TopHeadLinesList(state.data, paddingValues, onClick)
        }
    }
}

@Composable
fun TopHeadLinesList(list: List<Article>, paddingValues: PaddingValues, onClick:(String) -> Unit){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        LazyColumn {
            items(list, key = {it.url}){
                NewsItem(onClick,it)
            }
        }
    }
}