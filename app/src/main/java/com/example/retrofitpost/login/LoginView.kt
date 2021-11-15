package com.example.retrofitpost.login

import com.hannesdorfmann.mosby3.mvp.MvpView

interface LoginView: MvpView {
    fun navigateToSecondActivity()
    fun errorMessage(message: String?)
    fun validationMessage(messageId: Int)
    fun validateFields(): Boolean
}