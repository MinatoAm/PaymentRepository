package com.example.testapplication.api

import com.example.testapplication.api.request.RequestToken
import com.example.testapplication.api.response.ResponsePaymentsList
import com.example.testapplication.api.response.ResponseToken
import retrofit2.http.*

interface NetworkService {

    @POST("login")
    suspend fun getToken(@Body requestToken: RequestToken): ResponseToken

    @GET("payments")
    suspend fun getPaymentsList(): ResponsePaymentsList
}