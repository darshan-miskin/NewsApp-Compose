package com.darshanmiskin.newsapp.data.repository

import com.darshanmiskin.newsapp.data.api.NetworkService
import com.darshanmiskin.newsapp.utils.callApi
import javax.inject.Inject

class NewsRepository @Inject constructor(val newsService: NetworkService) {

    fun getTopHeadlines() = newsService.topHeadlines("us").callApi()

    fun getTopHeadlinesBy(
        source: String? = null,
        country: String? = null,
        language: String? = null
    ) = newsService.topHeadlines(
        source = source,
        country = country,
        language = language
    ).callApi()

//    fun getTopHeadlinesBySource(source: String) =
//        newsService.topHeadlines(source = source).callApi()
//
//    fun getTopHeadlinesByCountry(country: String) =
//        newsService.topHeadlines(country = country).callApi()
//
//    fun getTopHeadlinesByLanguage(language: String) =
//        newsService.topHeadlines(language = language).callApi()

    fun getSources() = newsService.sources().callApi()

    fun getSearchResults(searchQuery: String) = newsService.everything(searchQuery).callApi()
}