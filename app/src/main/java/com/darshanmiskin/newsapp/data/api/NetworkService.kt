package com.darshanmiskin.newsapp.data.api

import com.darshanmiskin.newsapp.BuildConfig
import com.darshanmiskin.newsapp.data.model.SourcesResponse
import com.darshanmiskin.newsapp.data.model.ArticlesListResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkService {

    @Headers("X-Api-Key: ${BuildConfig.API_KEY}")
    @GET("everything")
    fun everything(@Query("q") query: String): ArticlesListResponse

    @Headers("X-Api-Key: ${BuildConfig.API_KEY}")
    @GET("top-headlines")
    fun topHeadlines(@Query("source") source: String): ArticlesListResponse

    @Headers("X-Api-Key: ${BuildConfig.API_KEY}")
    @GET("top-headlines/sources")
    fun sources(): SourcesResponse

}