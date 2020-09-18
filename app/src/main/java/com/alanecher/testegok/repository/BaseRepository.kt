package com.alanecher.testegok.repository

import com.alanecher.testegok.repository.remote.BaseResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

object BaseRepository{
    fun <T : Any> handleError(resp: Response<T>): BaseResponse.Error {
        val error = resp.errorBody()
        if(error != null)
            return BaseResponse.Error(error.charStream().readText())
        return BaseResponse.Error("")
    }

    fun <T : Any> handleSuccess(response: Response<T>): BaseResponse<T> {
        response.body()?.let {
            return BaseResponse.Success(it)
        } ?: return handleError(response)
    }

}