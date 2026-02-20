package com.darshanmiskin.newsapp.ui.languages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.darshanmiskin.newsapp.data.model.local.Language
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.ui.base.theme.Error
import com.darshanmiskin.newsapp.ui.base.theme.Loading
import com.darshanmiskin.newsapp.ui.base.theme.Purple80
import com.darshanmiskin.newsapp.ui.base.theme.SimpleListItem

@Composable
fun LanguagesScreen(
    viewModel: LanguagesViewModel,
    paddingValues: PaddingValues,
    onClick: (String) -> Unit
) {
    val uiState by viewModel.flow.collectAsStateWithLifecycle()
    when (val state = uiState) {
        is UiState.Error -> Error { viewModel.getLanguages() }
        UiState.Initial -> {}
        UiState.Loading -> Loading()
        is UiState.Success<List<Language>> -> LanguagesList(state.data, paddingValues, onClick)
    }
}

@Composable
fun LanguagesList(list: List<Language>, paddingValues: PaddingValues, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(list, key = {it.code}){
                SimpleListItem(onClick, it.code, it.name, backgroundColor = Purple80)
            }
        }
    }
}