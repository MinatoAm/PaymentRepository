package com.example.testapplication.helper

sealed class ActionResult<out RESPONSE> {

    data class Success<RESPONSE>(val response: RESPONSE) : ActionResult<RESPONSE>()
    data class Failed(val error: String) : ActionResult<Nothing>()
    object Loading : ActionResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[response = $response]"
            is Failed -> "Failed[error = $error]"
            is Loading -> "Loading"
        }
    }
}