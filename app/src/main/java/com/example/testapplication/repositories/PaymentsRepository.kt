package com.example.testapplication.repositories

import com.example.testapplication.api.NetworkService
import com.example.testapplication.api.response.ResponsePaymentsList
import com.example.testapplication.base.BaseRepository
import com.example.testapplication.helper.ActionResult

interface PaymentsRepository{
    suspend fun getPaymentsList(): ActionResult<ResponsePaymentsList>
}

class PaymentsRepositoryImpl(private val networkService: NetworkService): BaseRepository(), PaymentsRepository {
    override suspend fun getPaymentsList(): ActionResult<ResponsePaymentsList> =
        getActionResult { networkService.getPaymentsList() }
}