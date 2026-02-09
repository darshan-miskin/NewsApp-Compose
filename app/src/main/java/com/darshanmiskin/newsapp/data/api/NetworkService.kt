package com.darshanmiskin.newsapp.data.api

import com.darshanmiskin.newsapp.data.model.network.SourcesResponse
import com.darshanmiskin.newsapp.data.model.network.ArticlesListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("everything")
    fun everything(@Query("q") query: String): ArticlesListResponse

    @GET("top-headlines")
    fun topHeadlines(@Query("source") source: String): ArticlesListResponse

    @GET("top-headlines/sources")
    fun sources(): SourcesResponse

}