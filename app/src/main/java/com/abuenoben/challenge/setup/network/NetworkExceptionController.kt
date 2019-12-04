package com.abuenoben.challenge.setup.network

import android.content.Context
import com.abuenoben.challenge.R
import com.abuenoben.challenge.setup.extensions.fromJson
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

//HELPERS TO HANDLE ERRORS

class NetworkExceptionController(private val context: Context) {

    fun checkException(e: Exception): ResponseResult.Error {
        print("--> checkException $e")
        return when (e) {
            is UnknownHostException -> ResponseResult.Error(context.getString(R.string.network_down), e)
            is SocketTimeoutException -> ResponseResult.Error(context.getString(R.string.network_down_timeout), e)
            else -> defaultError(e)
        }
    }

    private fun defaultError(e: Exception? = null): ResponseResult.Error {
        print("--> defaultError $e")
        return ResponseResult.Error(context.getString(R.string.network_error), e)
    }

    private fun defaultOk(): ResponseResult.Error {
        return ResponseResult.Error(context.getString(R.string.operation_ok))
    }

    fun <T : Any> checkResponse(response: Response<T>): ResponseResult<T> {
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
                    } ?: defaultOk()
                }
            response.code() == 403 -> ResponseResult.Forbidden("403")
            response.code() == 503 -> ResponseResult.Error(context.getString(R.string.network_down))
            else -> response.errorBody()?.string()?.let {
                val responseResult = it.fromJson<ErrorBean>()
                ResponseResult.Error(responseResult.message)
            } ?: defaultError()
        }
    }
}
