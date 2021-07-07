package com.example.testapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.testapplication.api.AuthInterceptor
import com.example.testapplication.api.request.RequestToken
import com.example.testapplication.helper.ActionResult
import com.example.testapplication.repositories.LoginRepository
import kotlinx.coroutines.Dispatchers

open class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {


    fun getToken(login: String, password: String) = liveData(Dispatchers.IO) {
        emit(ActionResult.Loading)

        val requestToken = RequestToken(login, password)
        emit(loginRepository.getAccessToken(requestToken))
    }

    fun setToken(token: String?){
        AuthInterceptor.token = token
    }
}   