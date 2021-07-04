package com.example.testapplication.base

import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.testapplication.showError
import com.google.android.material.button.MaterialButton

abstract class BaseFragment: Fragment(), IBaseFragment {

    override fun showErrorMessage(errorMessage : String) {
        val ctx = context ?: return
        showError(ctx, errorMessage)
    }

    protected fun showLoading(button: Button) {
        button.isEnabled = false
    }

    protected fun hideLoading(button: MaterialButton){
        button.isEnabled = true
    }
}