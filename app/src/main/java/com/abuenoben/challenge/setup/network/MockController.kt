package com.abuenoben.challenge.setup.network

import android.content.Context
import com.abuenoben.challenge.setup.extensions.fromJson
import com.abuenoben.data.utils.ResponseResult
import com.google.gson.Gson
import retrofit2.Response

//HELPERS TO HANDLE ERRORS

class MockController(private val context: Context) {

    inline fun <reified T : Any> getMockListResponse(raw: Int): Response<List<T>> {
        val json = this.getJsonFromResource(raw)
        val response: List<T> = Gson().fromJson(json, Array<T>::class.java).toList()
        return Response.success(response)
    }

    inline fun <reified T : Any> getMockResponse(raw: Int): Response<T> {
        val json = this.getJsonFromResource(raw)
        val response: T = json.fromJson()
        return Response.success(response)
    }

    inline fun <reified T : Any> getMockResult(raw: Int): T {
        val json = this.getJsonFromResource(raw)
        return json.fromJson()
    }

    inline fun <reified T : Any> getMockResponseResult(raw: Int): ResponseResult.Success<T> {
        return ResponseResult.Success(getMockResult(raw))
    }

    fun getJsonFromResource(res: Int): String {
        return context.resources.openRawResource(res)
            .bufferedReader().use { it.readText() }
    }
}
