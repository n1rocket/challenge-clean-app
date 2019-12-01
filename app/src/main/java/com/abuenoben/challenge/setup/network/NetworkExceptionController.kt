package com.abuenoben.challenge.setup.network

import android.content.Context
import com.abuenoben.challenge.R
import com.abuenoben.challenge.setup.extensions.fromJson
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

//HELPERS TO HANDLE ERRORS

object NetworkExceptionController {

    fun checkException(context: Context, e: Exception): ResponseResult.Error {
        print("--> checkException $e")
        return when (e) {
            is UnknownHostException -> ResponseResult.Error(context.getString(R.string.network_down), e)
            is SocketTimeoutException -> ResponseResult.Error(context.getString(R.string.network_down_timeout), e)
            else -> defaultError(context, e)
        }
    }

    fun defaultError(context: Context, e: Exception? = null): ResponseResult.Error {
        print("--> defaultError $e")
        return ResponseResult.Error(context.getString(R.string.network_error), e)
    }

    fun defaultOk(context: Context): ResponseResult.Error {
        return ResponseResult.Error(context.getString(R.string.operation_ok))
    }

    fun <T : Any> checkResponse(context: Context, response: Response<T>): ResponseResult<T> {
        print("--> response $response")
        return when {
            response.code() == 204 -> ResponseResult.NotContent("204")
            response.code() in 200..299 ->
                if (response.body() != null) {
                    ResponseResult.Success(response.body() as T)
                } else {
                    response.errorBody()?.string()?.let {
                        val responseResult = it.fromJson<ErrorBean>()
                        ResponseResult.Error(responseResult.message)
                    } ?: defaultOk(context)
                }
            response.code() == 403 -> ResponseResult.Forbidden("403")
            response.code() == 503 -> ResponseResult.Error(context.getString(R.string.network_down))
            else -> response.errorBody()?.string()?.let {
                val responseResult = it.fromJson<ErrorBean>()
                ResponseResult.Error(responseResult.message)
            } ?: defaultError(context)
        }
    }
}
