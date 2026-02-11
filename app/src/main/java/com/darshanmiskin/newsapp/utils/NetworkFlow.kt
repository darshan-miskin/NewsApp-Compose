package com.darshanmiskin.newsapp.utils

import com.darshanmiskin.newsapp.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

@OptIn(FlowPreview::class)
fun <Resp, Data> toUiState(
    apiCall: suspend () -> Response<Resp>,
    validateResponse: suspend (response: Resp, flow: FlowCollector<UiState<Data>>) -> Unit
) =
    flow {
        emit(UiState.Loading)
        val api = apiCall()
        try {
            if (api.isSuccessful && api.body() != null) {
                validateResponse(api.body()!!, this)
            } else {
                emit(UiState.Error(api.errorBody().toString(), code = api.code().toString()))
            }
        } catch (e: Exception) {
            emit(UiState.Error(message = e.message ?: "-", e))
        }
    }.flowOn(Dispatchers.IO)