package com.darshanmiskin.newsapp.ui.newssources

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.darshanmiskin.newsapp.data.model.network.Source
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.ui.base.theme.Error
import com.darshanmiskin.newsapp.ui.base.theme.Loading
import com.darshanmiskin.newsapp.ui.base.theme.NoDataFound
import com.darshanmiskin.newsapp.ui.base.theme.Pink80
import com.darshanmiskin.newsapp.ui.base.theme.SimpleListItem

@Composable
fun NewsSourcesScreen(
    viewModel: NewsSourceViewModel,
    paddingValues: PaddingValues,
    onClick: (String) -> Unit
) {
    val uiState by viewModel.flow.collectAsStateWithLifecycle()
    when (val state = uiState) {
        is UiState.Error -> Error { viewModel.getNewsSources() }
        UiState.Initial -> {}
        UiState.Loading -> Loading()
        is UiState.Success<List<Source>> -> {
            if (state.data.isEmpty())
                NoDataFound()
            else
                NewsSourceList(state.data, paddingValues, onClick)
        }
    }
}

@Composable
fun NewsSourceList(list: List<Source>, paddingValues: PaddingValues, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier.padding(paddingValues)
    ) {
        LazyColumn(
            modifier = Modifier.padding(8.dp)
        ) {
            items(list, key = { it.id }) {
                SimpleListItem(onClick, it.id, it.name, Pink80)
            }
        }
    }
}