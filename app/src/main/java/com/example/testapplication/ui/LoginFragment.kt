package com.example.testapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.testapplication.R
import com.example.testapplication.helper.ActionResult
import com.example.testapplication.base.BaseFragment
import com.example.testapplication.ui.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    private val loginViewModel: LoginViewModel by viewModel()

    private lateinit var etLogin: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etLogin = view.findViewById(R.id.et_login)
        etPassword = view.findViewById(R.id.et_password)
        btnLogin = view.findViewById(R.id.btn_login)
        initView()
    }


    private fun initView() {
        btnLogin.setOnClickListener {
            createRequestToken(
                etLogin.text.toString(),
                etPassword.text.toString()
            )
        }
    }

    private fun createRequestToken(login: String, password: String) {
        loginViewModel.createRequestToken(login, password).observe(viewLifecycleOwner, {
            when(it) {
                is ActionResult.Loading -> showLoading(btnLogin)
                is ActionResult.Failed -> showErrorMessage(it.error)
                is ActionResult.Success -> openPaymentsPage()

            }
        })
    }

    private fun openPaymentsPage() {
        findNavController().navigate(R.id.action_loginFragment_to_paymentsFragment)
    }
}