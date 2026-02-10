package com.darshanmiskin.newsapp.data.model.network
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ArticlesResponse(
    @SerializedName("articles")
    val articles: ArrayList<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String
)