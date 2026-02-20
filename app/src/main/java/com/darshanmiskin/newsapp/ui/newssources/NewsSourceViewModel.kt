package com.darshanmiskin.newsapp.ui.newssources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darshanmiskin.newsapp.data.model.network.Source
import com.darshanmiskin.newsapp.data.repository.NewsRepository
import com.darshanmiskin.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsSourceViewModel @Inject constructor(val newsRepository: NewsRepository): ViewModel() {
    private val _flow = MutableStateFlow<UiState<List<Source>>>(UiState.Loading)
    val flow = _flow.asStateFlow()

    init {
        getNewsSources()
    }

    fun getNewsSources(){
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getSources().collect {
                _flow.emit(it)
            }
        }
    }
}