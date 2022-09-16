package com.example.jobreadiness.ui

sealed class ApiResult<T> {

    data class Success<T>(val result: T): ApiResult<T>()

    data class Error<T>(val error: Throwable): ApiResult<T>()
}