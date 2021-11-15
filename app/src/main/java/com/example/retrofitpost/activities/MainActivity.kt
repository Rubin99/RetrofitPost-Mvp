package com.example.retrofitpost.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.retrofitpost.login.LoginPresenter
import com.example.retrofitpost.login.LoginView
import com.example.retrofitpost.R
import com.example.retrofitpost.model.LoginRequest
import com.hannesdorfmann.mosby3.mvp.MvpActivity

class MainActivity : MvpActivity<LoginView, LoginPresenter>(), LoginView {

    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var btnLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin?.setOnClickListener {
           loginUser()
        }

    }

    private fun loginUser() {
        if (validateFields()){
            presenter.callLoginApi(LoginRequest(etEmail?.text.toString(), etPassword?.text.toString()))
        }
    }

    /*private fun implementLoginApi() {
        val json = GsonBuilder().create().toJson(LoginRequest(etEmail?.text.toString(), etPassword?.text.toString()))
        val applicationJson = "application/json".toMediaTypeOrNull()
        val request = json.toRequestBody(applicationJson)

        RetrofitHelper.getApiInterface().login(request).enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {

                SharedPrefManager.getInstance(applicationContext)
                    .saveUser(response.body()!!)

                startActivity(Intent(this@MainActivity, SecondActivity::class.java))
                finish()
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "THIS NOT WORKING", Toast.LENGTH_LONG).show()
            }
        })
    }*/

    override fun validateFields(): Boolean {
        if (etEmail?.text.isNullOrEmpty()){
            Toast.makeText(this, "Please type in the Email", Toast.LENGTH_LONG).show()
            return false
        }
        else if(etPassword?.text.isNullOrEmpty()){
            Toast.makeText(this, "Please type in the password", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    /*override fun onStart() {
        super.onStart()

        if (SharedPrefManager.getInstance(this).isLoggedIn) {
            val  intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }
    }*/

    override fun createPresenter() = LoginPresenter()

    override fun navigateToSecondActivity() {
        startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        finish()
    }

    override fun errorMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun validationMessage(messageId: Int) {
        Toast.makeText(this, getString(messageId), Toast.LENGTH_SHORT).show()
    }
}