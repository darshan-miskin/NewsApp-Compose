package com.darshanmiskin.newsapp.ui.languages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darshanmiskin.newsapp.data.model.local.Language
import com.darshanmiskin.newsapp.data.repository.NewsRepository
import com.darshanmiskin.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguagesViewModel @Inject constructor(val newsRepository: NewsRepository): ViewModel() {
    private val _flow = MutableStateFlow<UiState<ArrayList<Language>>>(UiState.Loading)
    val flow = _flow.asStateFlow()

    init {
        getLanguages()
    }

    fun getLanguages(){
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getLanguages().collect {
                _flow.emit(it)
            }
        }
    }
}