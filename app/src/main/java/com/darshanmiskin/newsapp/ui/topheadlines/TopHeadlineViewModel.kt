package com.darshanmiskin.newsapp.ui.topheadlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darshanmiskin.newsapp.data.model.network.Article
import com.darshanmiskin.newsapp.data.repository.NewsRepository
import com.darshanmiskin.newsapp.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopHeadlineViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val filter: TopHeadlinesActivity.Filter,
    private val value: String
): ViewModel() {
    private val _flow = MutableStateFlow<UiState<ArrayList<Article>>>(UiState.Loading)
    val flow = _flow.asStateFlow()

    init {
        getArticles()
    }

    fun getArticles(){
        viewModelScope.launch(Dispatchers.IO) {
            val filteredApi = when(filter){
                TopHeadlinesActivity.Filter.TOP_HEADLINES,
                TopHeadlinesActivity.Filter.COUNTRY -> newsRepository.getTopHeadlinesBy(country = value)
                TopHeadlinesActivity.Filter.LANGUAGE -> newsRepository.getTopHeadlinesBy(language = value)
                TopHeadlinesActivity.Filter.SOURCE -> newsRepository.getTopHeadlinesBy(source = value)
            }
            filteredApi.collect {
                _flow.emit(it)
            }
        }
    }
}