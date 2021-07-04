package com.example.testapplication.api

import com.example.testapplication.api.request.RequestToken
import com.example.testapplication.api.response.ResponseToken
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkService {

    @POST
    suspend fun getToken(@Body requestToken: RequestToken): ResponseToken
}