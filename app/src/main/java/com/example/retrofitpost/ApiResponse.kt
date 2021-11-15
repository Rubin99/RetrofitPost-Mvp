package com.example.retrofitpost

import com.example.retrofitpost.model.LoginResponseData

interface ApiResponse<T> {
    fun onSuccess(response: T)
    fun onFaliure(message:String?)
}