package com.example.projekt.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projekt.MyApplication
import com.example.projekt.data.LoginRequest
import com.example.projekt.data.Register
import com.example.projekt.data.User
import com.example.projekt.repository.Repository

class RegisterViewModel(val context: Context, val repository: Repository) : ViewModel()  {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()

    init{
        Log.d("xxx", "ListViewModel constructor - Token: ${MyApplication.token}")
        user.value = User()
    }


    suspend fun registration() {
        val request =
                Register(username = user.value!!.username, phone_number = user.value!!.phone_number, email = user.value!!.email ,password = user.value!!.password)
        try {
            val result = repository.register(request)
            MyApplication.token = result.mesege
            token.value = result.mesege
            Log.d("xxx", "MyApplication - token:  ${MyApplication.token}")
        } catch (e: Exception) {
            Log.d("xxx", "LoginViewModel - exception: ${e.toString()}")
        }
    }
}