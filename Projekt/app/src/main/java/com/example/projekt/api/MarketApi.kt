package com.example.projekt.api

import android.provider.SyncStateContract
import com.example.projekt.data.LoginRequest
import com.example.projekt.data.LoginResponse
import com.example.projekt.model.ProductResponse
import com.example.projekt.utils.Constants
import com.example.projekt.utils.Constants.LOGIN_URL
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface MarketApi {
    @POST(LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse
}