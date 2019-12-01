package com.abuenoben.challenge.setup.network

import com.abuenoben.challenge.setup.extensions.logd
import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor : Interceptor {

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        logd("Interceptor - intercept()")
        var request = chain.request()

        request = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(request)
    }
}