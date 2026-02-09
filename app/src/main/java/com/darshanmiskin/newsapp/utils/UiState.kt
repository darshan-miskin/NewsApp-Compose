package com.darshanmiskin.newsapp.utils

sealed class UiState

object Loading : UiState()
data class Success<T>(val data: T) : UiState()
data class Error(
    val message: String,
    val exception: Throwable? = null,
    val code: Int? = null
) : UiState()