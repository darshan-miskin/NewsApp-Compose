package com.darshanmiskin.newsapp.ui.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.darshanmiskin.newsapp.data.model.network.Article
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.ui.base.theme.Error
import com.darshanmiskin.newsapp.ui.base.theme.Loading
import com.darshanmiskin.newsapp.ui.base.theme.NewsItem
import com.darshanmiskin.newsapp.ui.base.theme.NoDataFound

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    paddingValues: PaddingValues,
    onClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val startSearch: (String) -> Unit = { query ->
        viewModel.search(query)
    }
    val keyboard = LocalSoftwareKeyboardController.current
    LaunchedEffect(uiState) {
        if (uiState is UiState.Error || uiState is UiState.Loading || uiState is UiState.Success<*>) {
            keyboard?.hide()
        }
    }
    SearchPage(paddingValues, startSearch) {
        when (val state = uiState) {
            is UiState.Error -> Error { startSearch(viewModel.query.value) }
            UiState.Initial -> {}
            UiState.Loading -> Loading()
            is UiState.Success<List<Article>> ->
                if (state.data.isEmpty())
                    NoDataFound()
                else {
                    LazyColumn {
                        items(state.data, { it.url }) { article ->
                            NewsItem(onClick, article)
                        }
                    }
                }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(
    paddingValues: PaddingValues,
    startSearch: (String) -> Unit,
    uiToLoad: @Composable () -> Unit
) {
    val textFieldState = rememberTextFieldState()
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    BackHandler(enabled = isExpanded) {
        isExpanded = false
    }
    Box(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter),
            inputField = {
                SearchBarDefaults.InputField(
                    query = textFieldState.text.toString(),
                    onQueryChange = {
                        textFieldState.edit {
                            replace(
                                0,
                                length,
                                it
                            )
                        }
                        startSearch(textFieldState.text.toString())
                    },
                    onSearch = {
                        isExpanded = true
                    },
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = it },
                    placeholder = { Text("Search") }
                )
            },
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it },
        ) {
            uiToLoad()
        }
    }
}