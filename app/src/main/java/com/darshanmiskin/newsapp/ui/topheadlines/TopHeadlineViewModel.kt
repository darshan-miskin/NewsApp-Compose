package com.darshanmiskin.newsapp.ui.topheadlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darshanmiskin.newsapp.data.model.network.Article
import com.darshanmiskin.newsapp.data.repository.NewsRepository
import com.darshanmiskin.newsapp.ui.base.UiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = TopHeadlineViewModel.FilterFactory::class)
class TopHeadlineViewModel @AssistedInject constructor(
    private val newsRepository: NewsRepository,
    @Assisted private val filter: TopHeadlinesActivity.Filter,
    @Assisted private val value: String
) : ViewModel() {
    @AssistedFactory
    interface FilterFactory {
        fun create(
            filter: TopHeadlinesActivity.Filter = TopHeadlinesActivity.Filter.TOP_HEADLINES,
            value: String = "us"
        ): TopHeadlineViewModel
    }

    private val _flow = MutableStateFlow<UiState<ArrayList<Article>>>(UiState.Loading)
    val flow = _flow.asStateFlow()

    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            val filteredApi = when (filter) {
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