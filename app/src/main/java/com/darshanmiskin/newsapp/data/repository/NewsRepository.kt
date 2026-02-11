package com.darshanmiskin.newsapp.data.repository

import com.darshanmiskin.newsapp.data.api.NetworkService
import com.darshanmiskin.newsapp.data.local.countriesList
import com.darshanmiskin.newsapp.data.local.languagesList
import com.darshanmiskin.newsapp.ui.base.UiState
import com.darshanmiskin.newsapp.utils.toUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.random.Random

class NewsRepository @Inject constructor(val newsService: NetworkService) {

    fun getTopHeadlinesBy(
        source: String? = null,
        country: String? = null,
        language: String? = null
    ) = toUiState({
        newsService.topHeadlines(
            source = source,
            country = country,
            language = language
        )
    }) { response, flow ->
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

    suspend fun getSources() = toUiState({ newsService.sources() }) { response, flow ->
        if (response.status.lowercase() == "ok")
            flow.emit(UiState.Success(response.sources))
        else
            flow.emit(UiState.Error(message = response.message, code = response.code))
    }

    fun getSearchResults(searchQuery: String) =
        toUiState({ newsService.everything(searchQuery) }) { response, flow ->
            if (response.status.lowercase() == "ok")
                flow.emit(UiState.Success(response.articles))
            else
                flow.emit(UiState.Error(message = response.message, code = response.code))
        }
}