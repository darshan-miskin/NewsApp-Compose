package com.darshanmiskin.newsapp.data.model.network
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SourcesResponse(
    @SerializedName("sources")
    val sources: List<Source>,
    @SerializedName("status")
    val status: String
)