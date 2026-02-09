package com.darshanmiskin.newsapp.data.repository

import com.darshanmiskin.newsapp.data.api.NetworkService
import com.darshanmiskin.newsapp.utils.Error
import com.darshanmiskin.newsapp.utils.Loading
import com.darshanmiskin.newsapp.utils.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepository @Inject constructor(val newsService: NetworkService) {

    fun getTopHeadlines() = flow {
        emit(Loading)
        try {
            val response = newsService.topHeadlines("us")
            if (response.isSuccessful)
                emit(Success(response.body()))
            else
                emit(Error(response.errorBody().toString(), code = response.code()))
        } catch (e: Exception) {
            emit(Error(e.message, e))
        }
    }.flowOn(Dispatchers.IO)

    fun getTopHeadlinesBySource(source: String) = flow {
        emit(Loading)
        try {
            val response = newsService.topHeadlines(source = source)
            if (response.isSuccessful)
                emit(Success(response.body()))
            else
                emit(Error(response.errorBody().toString(), code = response.code()))
        } catch (e: Exception) {
            emit(Error(e.message, e))
        }
    }.flowOn(Dispatchers.IO)

    fun getTopHeadlinesByCountry(country: String) = flow {
        emit(Loading)
        try {
            val response = newsService.topHeadlines(country = country)
            if (response.isSuccessful)
                emit(Success(response.body()))
            else
                emit(Error(response.errorBody().toString(), code = response.code()))
        } catch (e: Exception) {
            emit(Error(e.message, e))
        }
    }.flowOn(Dispatchers.IO)

    fun getTopHeadlinesByLanguage(language: String) = flow {
        emit(Loading)
        try {
            val response = newsService.topHeadlines(language = language)
            if (response.isSuccessful)
                emit(Success(response.body()))
            else
                emit(Error(response.errorBody().toString(), code = response.code()))
        }catch (e: Exception){
            emit(Error(e.message, e))
        }
    }.flowOn(Dispatchers.IO)

    fun getSources() = flow {
        emit(Loading)
        try {
            val response = newsService.sources()
            if(response.isSuccessful)
                emit(Success(response.body()))
            else
                emit(Error(response.errorBody().toString(), code = response.code()))
        }catch (e: Exception){
            emit(Error(e.message, e))
        }
    }.flowOn(Dispatchers.IO)

    fun getSearchResults(searchQuery: String) = flow {
        emit(Loading)
        try {
            val response = newsService.everything(searchQuery)
            if (response.isSuccessful)
                emit(Success(response.body()))
            else
                emit(Error(response.errorBody().toString(), code = response.code()))
        }catch (e: Exception){
            emit(Error(e.message, e))
        }
    }.flowOn(Dispatchers.IO)
}