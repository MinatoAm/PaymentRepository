package com.example.testapplication.api.response


import com.google.gson.annotations.SerializedName

data class ResponsePaymentsList(
    @SerializedName("response")
    val responsePayments: List<ResponsePayments>
): ResponseBaseBody() {
    data class ResponsePayments(
        @SerializedName("amount")
        val amount: Double?,
        @SerializedName("created")
        val created: Long?,
        @SerializedName("currency")
        val currency: String?,
        @SerializedName("desc")
        val desc: String?
    )
}