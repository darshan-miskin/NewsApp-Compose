package com.darshanmiskin.newsapp.ui.newssources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darshanmiskin.newsapp.data.model.network.Source
import com.darshanmiskin.newsapp.data.model.network.SourcesResponse
import com.darshanmiskin.newsapp.data.repository.NewsRepository
import com.darshanmiskin.newsapp.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourceViewModel @Inject constructor(val newsRepository: NewsRepository): ViewModel() {
    private val _flow = MutableStateFlow<UiState<ArrayList<Source>>>(UiState.Loading)
    val flow = _flow.asStateFlow()

    init {
        getNewsSources()
    }

    fun getNewsSources(){
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getSources().collect {
                when(it){
                    is UiState.Error -> _flow.emit(it)
                    UiState.Loading -> _flow.emit(UiState.Loading)
                    is UiState.Success<SourcesResponse> -> {
                        if (it.data.status.lowercase() == "ok")
                            _flow.emit(UiState.Success(ArrayList(it.data.sources)))
                        else
                            _flow.emit(UiState.Error(it.data.message, code = it.data.code))
                    }
                }
            }
        }
    }
}