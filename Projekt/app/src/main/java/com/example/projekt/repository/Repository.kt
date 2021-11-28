package com.example.projekt.repository

import com.example.projekt.api.RetrofitInstance
import com.example.projekt.data.LoginRequest
import com.example.projekt.data.LoginResponse
import com.example.projekt.model.ProductResponse

class  Repository {
    suspend fun login(request: LoginRequest): LoginResponse {
        return RetrofitInstance.api.login(request)
    }

    suspend fun getProducts(token: String): ProductResponse {
        return RetrofitInstance.api.getProducts(token)
    }
}