package com.example.projekt.api

import com.example.projekt.data.*
import com.example.projekt.model.AddOrder
import com.example.projekt.model.ProductResponse
import com.example.projekt.model.addProduct
import com.example.projekt.model.addProductResponse
import com.example.projekt.utils.Constants
import com.example.projekt.utils.Constants.ADD_ORDER
import com.example.projekt.utils.Constants.ADD_PRODUCT
import com.example.projekt.utils.Constants.GET_ORDERS
import com.example.projekt.utils.Constants.LOGIN_URL
import com.example.projekt.utils.Constants.REGISTER_URL
import com.example.projekt.utils.Constants.USERINFO_URL
import okhttp3.RequestBody
import retrofit2.http.*


interface MarketApi {
    @POST(LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getMyPruducts(@Header("token")token: String,@Header("filter")filter: String):ProductResponse

    @POST(REGISTER_URL)
    suspend fun register(@Body request: Register):RegisterResponse

    @GET(USERINFO_URL)
    suspend fun profil(@Header("token") token: String ):LoginResponse

    @POST(ADD_ORDER)
    suspend fun addOrder(@Body request: AddOrder): ProductResponse

    @GET(GET_ORDERS)
    suspend fun getOrders(@Header ("token")token: String):ProductResponse

   // @POST(ADD_PRODUCT)
    //suspend fun addProduct(@Header("token")token: String, @Body request: addProduct): addProductResponse

    @Multipart
    @POST(Constants.ADD_PRODUCT)
    suspend fun addProduct(
            @Header("token") token:String,
            @Part("title") title: RequestBody,
            @Part("description") description:String,
            @Part("price_per_unit") price_per_unit:String,
            @Part("units") units:String,
            @Part("is_active") is_active:Boolean,
            @Part("rating") rating:Double,
            @Part("amount_type") amount_type:String,
            @Part("price_type") price_type:String
    ) : addProductResponse


}