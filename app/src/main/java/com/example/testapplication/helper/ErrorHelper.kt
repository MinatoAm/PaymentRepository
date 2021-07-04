package com.example.testapplication

import android.content.Context
import com.example.testapplication.R

fun showError(
    context: Context, errorMassage: String
) {
    showLoginFailedDialog(context, R.string.error_login_failed)
}