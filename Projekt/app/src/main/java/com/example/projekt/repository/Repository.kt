package com.example.projekt.repository

import com.example.projekt.MainActivity
import com.example.projekt.MyApplication
import com.example.projekt.MyApplication.Companion.token
import com.example.projekt.api.RetrofitInstance
import com.example.projekt.data.*
import com.example.projekt.model.AddOrder
import com.example.projekt.model.ProductResponse
import com.example.projekt.model.addProduct
import com.example.projekt.model.addProductResponse
import com.example.projekt.utils.Constants
import okhttp3.MediaType
import okhttp3.RequestBody
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

    suspend fun getMyProducts(token: String):ProductResponse{
        return RetrofitInstance.api.getMyPruducts(token,"{\"username\":\"${ MyApplication.userName}\"}")
    }

    suspend fun getProfil(token: String): LoginResponse{
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

    suspend fun addProduct(token: String,
                           title: String,
                           description:String,
                           price_per_unit:String,
                           units:String,
                           is_active:Boolean,
                           rating:Double,
                           amount_type:String,
                           price_type:String): addProductResponse{
        return RetrofitInstance.api.addProduct(token, RequestBody.create(MediaType.parse("text/plain"),title), description, price_per_unit, units, is_active, rating, amount_type, price_type)
        //RequestBody.create(MediaType.parse("text/plain"),title)
    }
}