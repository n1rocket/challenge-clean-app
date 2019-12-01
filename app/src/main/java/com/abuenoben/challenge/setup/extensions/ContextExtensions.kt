package com.abuenoben.challenge.setup.extensions

import android.content.Context
import com.abuenoben.challenge.setup.network.ResponseResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

fun Context.getJsonFromResource(res: Int): String {
    return resources.openRawResource(res)
        .bufferedReader().use { it.readText() }
}

inline fun <reified T : Any> T.json(): String = Gson().toJson(this, T::class.java)
inline fun <reified T : Any> String.fromJson(): T = Gson().fromJson(this, T::class.java)

inline fun <reified T : Any> Context.getMockListResponse(raw: Int): Response<List<T>> {
    val json = this.getJsonFromResource(raw)
    val response: List<T> = Gson().fromJson(json, Array<T>::class.java).toList()
    return Response.success(response)
}

inline fun <reified T : Any> Context.getMockResponse(raw: Int): Response<T> {
    val json = this.getJsonFromResource(raw)
    val response: T = json.fromJson()
    return Response.success(response)
}

inline fun <reified T : Any> Context.getMockResult(raw: Int): T {
    val json = this.getJsonFromResource(raw)
    return json.fromJson()
}

inline fun <reified T : Any> Context.getMockResponseResult(raw: Int): ResponseResult.Success<T> {
    return ResponseResult.Success(this.getMockResult(raw))
}

//convert a data class to a map
fun <T> T.serializeToMap(): Map<String, Any> {
    return convert()
}

//convert a map to a data class
inline fun <reified T> Map<String, Any>.toDataClass(): T {
    return convert()
}

//convert an object of type I to type O
inline fun <I, reified O> I.convert(): O {
    val json = Gson().toJson(this)
    return Gson().fromJson(json, object : TypeToken<O>() {}.type)
}