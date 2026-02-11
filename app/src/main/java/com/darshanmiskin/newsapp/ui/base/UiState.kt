package com.darshanmiskin.newsapp.ui.base

sealed interface UiState<out T>{
    object Loading : UiState<Nothing>

    object Initial : UiState<Nothing>

    data class Success<T>(val data: T) : UiState<T>

    data class Error(
        val message: String,
        val exception: Throwable? = null,
        val code: String? = null
    ) : UiState<Nothing>
}
