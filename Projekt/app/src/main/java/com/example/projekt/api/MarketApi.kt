package com.example.projekt.api

import com.example.projekt.data.*
import com.example.projekt.model.ProductResponse
import com.example.projekt.utils.Constants
import com.example.projekt.utils.Constants.LOGIN_URL
import com.example.projekt.utils.Constants.REGISTER_URL
import com.example.projekt.utils.Constants.USERINFO_URL
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface MarketApi {
    @POST(LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse

    @POST(REGISTER_URL)
    suspend fun register(@Body request: Register):RegisterResponse

    @GET(USERINFO_URL)
    suspend fun profil(@Header("token") response: String):User
}