package com.example.testapplication.api.response

import com.google.gson.annotations.SerializedName

data class ResponseToken(
    @SerializedName("token")
    val token: String?
): ResponseBaseBody()
