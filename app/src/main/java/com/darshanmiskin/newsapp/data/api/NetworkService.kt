package com.darshanmiskin.newsapp.data.api

import com.darshanmiskin.newsapp.data.model.network.ArticlesListResponse
import com.darshanmiskin.newsapp.data.model.network.SourcesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("everything")
    fun everything(@Query("q") query: String): Response<ArticlesListResponse>

    @GET("top-headlines")
    fun topHeadlines(
        @Query("country") country: String? = null,
        @Query("source") source: String? = null,
        @Query("language") language: String? = null
    ): Response<ArticlesListResponse>

    @GET("top-headlines/sources")
    suspend fun sources(): Response<SourcesResponse>

}