package com.example.testapplication.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    var token: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        // DONT INCLUDE API KEYS IN YOUR SOURCE CODE
        token?.apply {
            val url =
                req.url.newBuilder().addQueryParameter("token", token).build() // TODO set token
            req = req.newBuilder().url(url).build()
        }
        return chain.proceed(req)
    }
}