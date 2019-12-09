package com.abuenoben.challenge.setup.network

import android.content.Context
import com.abuenoben.challenge.R
import com.abuenoben.challenge.setup.extensions.fromJson
import com.abuenoben.data.model.ErrorBean
import com.abuenoben.data.utils.Either
import com.abuenoben.data.utils.NetworkExceptionController
import com.abuenoben.data.utils.ResponseResult
import java.net.SocketTimeoutException
import java.net.UnknownHostException

//HELPERS TO HANDLE ERRORS

class NetworkExceptionControllerImpl(private val context: Context) :
    NetworkExceptionController {

    override fun <T : Any> checkException(e: Exception): Either<ResponseResult.Failure, ResponseResult.Success<T>> {
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

    override fun <T : Any> checkResponse(
        responseCode: Int,
        responseBody: T?,
        responseErrorBody: String?
    ): Either<ResponseResult.Failure, ResponseResult.Success<T>> {
        print("--> responseCode $responseCode")
        return when (responseCode) {
            204 -> Either.Left(ResponseResult.Failure.NotContent)
            in 200..299 ->
                if (responseBody != null) {
                    Either.Right(ResponseResult.Success(responseBody))
                } else {
                    responseErrorBody?.let {
                        val responseResult = it.fromJson<ErrorBean>()

                        Either.Left(ResponseResult.Failure.Error(responseResult.message))
                    } ?: Either.Left(defaultOk())
                }
            403 -> Either.Left(ResponseResult.Failure.Forbidden)
            503 -> Either.Left(
                ResponseResult.Failure.Error(
                    context.getString(
                        R.string.network_down
                    )
                )
            )
            else -> responseErrorBody?.let {
                val responseResult = it.fromJson<ErrorBean>()
                Either.Left(ResponseResult.Failure.Error(responseResult.message))
            } ?: Either.Left(defaultError())
        }
    }
}
