package com.alanecher.testegok.repository

import com.alanecher.testegok.repository.remote.BaseResponse
import retrofit2.Response

object BaseRepository{
    fun <T : Any> handleError(resp: Response<T>): BaseResponse.Error {
        val error = resp.errorBody()
        return BaseResponse.Error(error.toString())
    }

    fun <T : Any> handleSuccess(response: Response<T>): BaseResponse<T> {
        response.body()?.let {
            return BaseResponse.Success(it)
        } ?: return handleError(response)
    }

}