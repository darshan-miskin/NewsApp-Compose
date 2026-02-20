package com.darshanmiskin.newsapp.ui.countries

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.darshanmiskin.newsapp.data.model.local.Country
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.ui.base.theme.Error
import com.darshanmiskin.newsapp.ui.base.theme.Loading
import com.darshanmiskin.newsapp.ui.base.theme.SimpleListItem

@Composable
fun CountriesScreen(
    viewModel: CountriesViewModel,
    paddingValues: PaddingValues,
    onClick: (String) -> Unit
) {
    val list by viewModel.flow.collectAsStateWithLifecycle()
    CountriesContent(list, paddingValues, { viewModel.getCountries() }) { onClick(it) }
}

@Composable
fun CountriesContent(
    uiState: UiState<List<Country>>,
    paddingValues: PaddingValues,
    onRetry: () -> Unit,
    onClick: (String) -> Unit
) {
    when (val currentState = uiState) {
        is UiState.Error -> Error { onRetry() }
        UiState.Initial -> {}
        UiState.Loading -> Loading()
        is UiState.Success -> ListScreen(currentState.data, paddingValues, onClick)
    }
}

@Composable
fun ListScreen(list: List<Country>, paddingValues: PaddingValues, onClick: (String) -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(paddingValues)
    ) {
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(list, key = { county -> county.code }) {
                SimpleListItem(onClick, it.code, it.name)
            }
        }
    }
}