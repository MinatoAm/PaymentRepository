package com.example.testapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.testapplication.api.AuthInterceptor
import com.example.testapplication.api.request.RequestToken
import com.example.testapplication.api.response.ResponseBaseBody
import com.example.testapplication.helper.ActionResult
import com.example.testapplication.LoginRepository
import kotlinx.coroutines.Dispatchers

open class LoginViewModel(private val loginRepository: LoginRepository, private val authInterceptor: AuthInterceptor) : ViewModel() {



    fun createRequestToken(login: String, password: String) = liveData<ActionResult<ResponseBaseBody>>(Dispatchers.IO) {
        emit(ActionResult.Loading)

        val requestToken = RequestToken(login, password)
        emit(loginRepository.getAccessToken(requestToken))
    }
}