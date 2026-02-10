package com.darshanmiskin.newsapp.data.api

import com.darshanmiskin.newsapp.data.model.network.ArticlesResponse
import com.darshanmiskin.newsapp.data.model.network.SourcesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("everything")
    suspend fun everything(@Query("q") query: String): Response<ArticlesResponse>

    @GET("top-headlines")
    suspend fun topHeadlines(
        @Query("country") country: String? = null,
        @Query("source") source: String? = null,
        @Query("language") language: String? = null
    ): Response<ArticlesResponse>

    @GET("top-headlines/sources")
    suspend fun sources(): Response<SourcesResponse>

}