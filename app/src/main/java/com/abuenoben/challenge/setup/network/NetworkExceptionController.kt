package com.abuenoben.challenge.setup.network

import android.content.Context
import com.abuenoben.challenge.R
import com.abuenoben.challenge.setup.extensions.fromJson
import com.abuenoben.challenge.setup.utils.Either
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

//HELPERS TO HANDLE ERRORS

class NetworkExceptionController(private val context: Context) {

    fun <T : Any> checkException(e: Exception): Either<ResponseResult.Failure, ResponseResult.Success<T>> {
        print("--> checkException $e")
        return Either.Left(
            when (e) {
                is UnknownHostException -> ResponseResult.Failure.Error(
                    context.getString(R.string.network_down),
                    e
                )
                is SocketTimeoutException -> ResponseResult.Failure.Error(
                    context.getString(R.string.network_down_timeout),
                    e
                )
                else -> defaultError(e)
            }
        )
    }

    private fun defaultError(e: Exception? = null): ResponseResult.Failure.Error {
        print("--> defaultError $e")
        return ResponseResult.Failure.Error(context.getString(R.string.network_error), e)
    }

    private fun defaultOk(): ResponseResult.Failure.Error {
        return ResponseResult.Failure.Error(context.getString(R.string.operation_ok))
    }

    fun <T : Any> checkResponse(response: Response<T>): Either<ResponseResult.Failure, ResponseResult.Success<T>> {
        print("--> response $response")
        return when {
            response.code() == 204 -> Either.Left(ResponseResult.Failure.NotContent)
            response.code() in 200..299 ->
                if (response.body() != null) {
                    Either.Right(ResponseResult.Success(response.body() as T))
                } else {
                    response.errorBody()?.string()?.let {
                        val responseResult = it.fromJson<ErrorBean>()

                        Either.Left(ResponseResult.Failure.Error(responseResult.message))
                    } ?: Either.Left(defaultOk())
                }
            response.code() == 403 -> Either.Left(ResponseResult.Failure.Forbidden)
            response.code() == 503 -> Either.Left(
                ResponseResult.Failure.Error(
                    context.getString(
                        R.string.network_down
                    )
                )
            )
            else -> response.errorBody()?.string()?.let {
                val responseResult = it.fromJson<ErrorBean>()
                Either.Left(ResponseResult.Failure.Error(responseResult.message))
            } ?: Either.Left(defaultError())
        }
    }
}
