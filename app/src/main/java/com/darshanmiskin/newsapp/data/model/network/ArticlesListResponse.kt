package com.darshanmiskin.newsapp.data.model.network
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ArticlesListResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)