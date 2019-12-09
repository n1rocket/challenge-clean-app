package com.abuenoben.challenge.setup.network

sealed class ResponseResult<out T : Any> {
    data class Success<out T : Any>(val value: T) : ResponseResult<T>()
    sealed class Failure {
        data class Error(val message: String, val cause: Exception? = null) : Failure()
        object NotContent : Failure()
        object Forbidden : Failure()
    }
}