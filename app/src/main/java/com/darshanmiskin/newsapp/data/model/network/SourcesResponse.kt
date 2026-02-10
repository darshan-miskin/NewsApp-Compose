package com.darshanmiskin.newsapp.data.model.network
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SourcesResponse(
    @SerializedName("sources")
    val sources: ArrayList<Source>,
    @SerializedName("status")
    val status: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String
)