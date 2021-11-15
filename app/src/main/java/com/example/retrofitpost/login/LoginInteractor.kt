package com.example.retrofitpost.login

import com.example.retrofitpost.ApiResponse
import com.example.retrofitpost.model.LoginRequest
import com.example.retrofitpost.model.LoginResponse

class LoginInteractor {
    fun callLogin(loginRequest: LoginRequest, apiResponse: ApiResponse<LoginResponse>) =
        LoginRepository.callLogin(loginRequest, apiResponse)
}