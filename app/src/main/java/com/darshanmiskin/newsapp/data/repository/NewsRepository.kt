package com.darshanmiskin.newsapp.data.repository

import com.darshanmiskin.newsapp.data.api.NetworkService
import com.darshanmiskin.newsapp.data.local.countriesList
import com.darshanmiskin.newsapp.data.local.languagesList
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.utils.callApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepository @Inject constructor(val newsService: NetworkService) {

    fun getTopHeadlinesBy(
        source: String = "us",
        country: String? = null,
        language: String? = null
    ) = newsService.topHeadlines(
        source = source,
        country = country,
        language = language
    ).callApi()

    fun getCountries() = flow {
        emit(UiState.Loading)
        delay(100)
        emit(UiState.Success(countriesList))
    }.flowOn(Dispatchers.IO)

    fun getLanguages() = flow {
        emit(UiState.Loading)
        delay(100)
        emit(UiState.Success(languagesList))
    }.flowOn(Dispatchers.IO)

    suspend fun getSources() = newsService.sources().callApi()

    fun getSearchResults(searchQuery: String) = newsService.everything(searchQuery).callApi()
}