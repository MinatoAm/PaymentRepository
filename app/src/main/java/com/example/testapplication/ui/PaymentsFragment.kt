package com.example.testapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.api.response.ResponsePaymentsList
import com.example.testapplication.base.BaseFragment
import com.example.testapplication.helper.ActionResult
import com.example.testapplication.recyclerView.PaymentAdapter
import com.example.testapplication.viewModel.PaymentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentsFragment: BaseFragment() {

    private val paymentsViewModel: PaymentsViewModel by viewModel()

    private var adapter = PaymentAdapter()
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.payments_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPaymentsList()

        recyclerView = view.findViewById(R.id.recycler_view)

        btnLogout = view.findViewById(R.id.btn_logout)
        btnLogout.setOnClickListener { logout() }
    }

    private fun logout() {
        paymentsViewModel.setNullToToken()
        findNavController().navigate(R.id.action_paymentsFragment_to_loginFragment)
    }

    private fun getPaymentsList() {
        paymentsViewModel.getPaymentsList().observe(viewLifecycleOwner, {
            when(it) {
                is ActionResult.Loading -> showLoading(btnLogout)
                is ActionResult.Failed -> showErrorMessage(it.error)
                is ActionResult.Success -> createRecyclerView(it.response.responsePayments)
            }
            hideLoading(btnLogout)
        })
    }

    private fun createRecyclerView(paymentsList: List<ResponsePaymentsList.ResponsePayments>) {
        adapter.setPayments(paymentsList)
        recyclerView.adapter = adapter
    }
}