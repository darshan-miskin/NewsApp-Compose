package com.darshanmiskin.newsapp.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

fun <T> Response<T>.callApi() = flow {
    emit(Loading)
    val api = this@callApi
    try {
        if (api.isSuccessful)
            emit(Success(api.body()))
        else
            emit(Error(api.errorBody().toString(), code = api.code()))
    }
    catch (e: Exception){
        emit(Error(e.message, e))
    }
}.flowOn(Dispatchers.IO)