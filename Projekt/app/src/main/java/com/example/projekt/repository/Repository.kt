package com.example.projekt.repository

import com.example.projekt.api.RetrofitInstance
import com.example.projekt.data.*
import com.example.projekt.model.AddOrder
import com.example.projekt.model.ProductResponse
import com.example.projekt.model.addProduct
import com.example.projekt.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

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

    suspend fun addOrder(request: AddOrder): ProductResponse{
        return RetrofitInstance.api.addOrder(request)
    }

    suspend fun getOrders(token: String):ProductResponse{
        return RetrofitInstance.api.getOrders(token)
    }

    suspend fun addProduct(request: addProduct): ProductResponse{
        return RetrofitInstance.api.addProduct(request)
    }
}