package com.example.testapplication.base

import com.example.testapplication.helper.ActionResult
import com.example.testapplication.api.response.ResponseBaseBody

abstract class BaseRepository() {

    protected suspend fun <RESPONSE : ResponseBaseBody> getActionResult(call: suspend () -> RESPONSE): ActionResult<RESPONSE> {
        return try {
            val response = call.invoke()
            return if (response.success == "true") {
                ActionResult.Success(response)
            } else {
                val errorCode = response.error?.error?.errorMsg ?: "null"
                ActionResult.Failed(errorCode)
            }
        } catch (exception: java.lang.Exception) {
            ActionResult.Failed("Network error")
        }
    }

}