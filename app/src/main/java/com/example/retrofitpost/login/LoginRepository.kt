package com.example.retrofitpost.login

import com.example.retrofitpost.ApiResponse
import com.example.retrofitpost.api.RetrofitHelper
import com.example.retrofitpost.model.LoginRequest
import com.example.retrofitpost.model.LoginResponse
import com.google.gson.GsonBuilder
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LoginRepository {
    fun callLogin(loginRequest: LoginRequest, apiResponse: ApiResponse<LoginResponse>){
        val json = GsonBuilder().create().toJson(loginRequest)
        val applicationJson = "application/json".toMediaTypeOrNull()
        val requestBody = json.toRequestBody(applicationJson)
        RetrofitHelper.getApiInterface().login(requestBody).enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                apiResponse.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                apiResponse.onFaliure(t.message)
            }
        })
    }
}