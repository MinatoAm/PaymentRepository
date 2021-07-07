package com.example.testapplication

import android.content.Context
import com.example.testapplication.helper.showLoginFailedDialog

fun showError(
    context: Context, errorMassage: String
) {
    showLoginFailedDialog(context, errorMassage)
}