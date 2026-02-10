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
import kotlin.random.Random

class NewsRepository @Inject constructor(val newsService: NetworkService) {

    suspend fun getTopHeadlinesBy(
        source: String? = null,
        country: String? = null,
        language: String? = null
    ) = newsService.topHeadlines(
        source = source,
        country = country,
        language = language
    ).callApi { response, flow ->
        if (response.status.lowercase() == "ok")
            flow.emit(UiState.Success(response.articles))
        else
            flow.emit(UiState.Error(message = response.message, code = response.code))
    }

    fun getCountries() = flow {
        emit(UiState.Loading)
        delay(Random.nextLong(200, 800))
        emit(UiState.Success(countriesList))
    }.flowOn(Dispatchers.IO)

    fun getLanguages() = flow {
        emit(UiState.Loading)
        delay(Random.nextLong(200, 800))
        emit(UiState.Success(languagesList))
    }.flowOn(Dispatchers.IO)

    suspend fun getSources() = newsService.sources().callApi { response, flow ->
        if (response.status.lowercase() == "ok")
            flow.emit(UiState.Success(response.sources))
        else
            flow.emit(UiState.Error(message = response.message, code = response.code))
    }

    suspend fun getSearchResults(searchQuery: String) = newsService.everything(searchQuery).callApi { response, flow ->
            if (response.status.lowercase() == "ok")
                flow.emit(UiState.Success(response.articles))
            else
                flow.emit(UiState.Error(message = response.message, code = response.code))
        }
}