package com.example.testapplication.repositories

import com.example.testapplication.api.NetworkService
import com.example.testapplication.base.BaseRepository
import com.example.testapplication.helper.ActionResult
import com.example.testapplication.api.request.RequestToken
import com.example.testapplication.api.response.ResponseToken

interface LoginRepository {
    suspend fun getAccessToken(requestToken: RequestToken): ActionResult<ResponseToken>
}

class LoginRepositoryImpl(private val networkService: NetworkService) : BaseRepository(),
    LoginRepository {
    override suspend fun getAccessToken(requestToken: RequestToken): ActionResult<ResponseToken> =
        getActionResult { networkService.getToken(requestToken) }
}