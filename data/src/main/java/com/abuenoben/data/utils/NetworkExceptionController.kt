package com.abuenoben.data.utils

//HELPERS TO HANDLE ERRORS

interface NetworkExceptionController {
    fun <T : Any> checkException(e: Exception): Either<ResponseResult.Failure, ResponseResult.Success<T>>
    fun <T : Any> checkResponse(
        responseCode: Int,
        responseBody: T?,
        responseErrorBody: String?
    ): Either<ResponseResult.Failure, ResponseResult.Success<T>>
}
