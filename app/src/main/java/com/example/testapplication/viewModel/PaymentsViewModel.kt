package com.example.testapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.testapplication.api.AuthInterceptor
import com.example.testapplication.helper.ActionResult
import com.example.testapplication.repositories.PaymentsRepository
import kotlinx.coroutines.Dispatchers

class PaymentsViewModel(private val paymentsRepository: PaymentsRepository): ViewModel() {

    fun getPaymentsList() = liveData(Dispatchers.IO) {
        emit(ActionResult.Loading)

        emit(paymentsRepository.getPaymentsList())
    }

    fun setNullToToken() {
        AuthInterceptor.token = null
    }
}