package com.example.projekt.repository

import com.example.projekt.api.RetrofitInstance
import com.example.projekt.data.*
import com.example.projekt.model.ProductResponse

class  Repository {
    suspend fun login(request: LoginRequest): LoginResponse {
        return RetrofitInstance.api.login(request)
    }

    suspend fun getProducts(token: String): ProductResponse {
        return RetrofitInstance.api.getProducts(token)
    }

    suspend fun getProfil(token: String):User{
        return RetrofitInstance.api.profil(token)
    }

    suspend fun register(request: Register): RegisterResponse {
        return RetrofitInstance.api.register(request)
    }
}