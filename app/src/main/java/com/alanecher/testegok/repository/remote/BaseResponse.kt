package com.alanecher.testegok.repository.remote

sealed class BaseResponse<out T> {

    data class Success<out T>(val data: T) : BaseResponse<T>()
    class Error(
        val message: String
    ) : BaseResponse<Nothing>()
}