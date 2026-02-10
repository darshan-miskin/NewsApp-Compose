package com.darshanmiskin.newsapp.utils

import com.darshanmiskin.newsapp.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

fun <Resp, Data> Response<Resp>.callApi(
    validateResponse: suspend (response: Resp, flow: FlowCollector<UiState<Data>>) -> Unit
) =
    flow {
        emit(UiState.Loading)
        val api = this@callApi
        try {
            if (api.isSuccessful && api.body() != null) {
                UiState.Success(validateResponse(api.body()!!, this))
            } else
                emit(UiState.Error(api.errorBody().toString(), code = api.code().toString()))
        } catch (e: Exception) {
            emit(UiState.Error(message = e.message ?: "-", e))
        }
    }.flowOn(Dispatchers.IO)