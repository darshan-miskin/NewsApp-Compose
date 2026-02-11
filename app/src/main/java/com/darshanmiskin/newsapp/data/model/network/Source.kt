package com.darshanmiskin.newsapp.data.model.network

import androidx.annotation.Keep
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

@Keep
data class Source(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)