package com.example.retrofitpost.login

import com.example.retrofitpost.ApiResponse
import com.example.retrofitpost.model.LoginRequest
import com.example.retrofitpost.model.LoginResponse
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class LoginPresenter : MvpBasePresenter<LoginView>() {
    private var loginInteractor: LoginInteractor? = null

    override fun attachView(view: LoginView) {
        super.attachView(view)
        loginInteractor = LoginInteractor()
    }

    override fun detachView() {
        super.detachView()
        loginInteractor = null
    }

    fun callLoginApi(loginRequest: LoginRequest){
        ifViewAttached { view ->
            if (!validateFields(loginRequest, view)){
                return@ifViewAttached
            }
            loginInteractor?.callLogin(loginRequest, object : ApiResponse<LoginResponse> {
                override fun onSuccess(response: LoginResponse) {
                    view.navigateToSecondActivity()
                }

                override fun onFaliure(message: String?) {
                    view.errorMessage(message)
                }
            })
        }
    }

    private fun validateFields(loginRequest: LoginRequest, view: LoginView): Boolean {
        if (loginRequest.email.isNullOrEmpty()) {
            view.validationMessage(0)
            return false
        } else if (loginRequest.password.isNullOrEmpty()){
            view.validationMessage(1)
            return false
        }
        return true

    }
}