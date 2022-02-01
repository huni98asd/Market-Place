package com.example.projekt.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(val _id: String="",
                 val image_id: String="",
                 val image_name: String="",
                 val image_path: String="")

@JsonClass(generateAdapter = true)
data class Product(
        var rating: Double=2.0,
        var amount_type: String="",
        var price_type: String="",
        var product_id: String="",
        var username: String="",
        var is_active: Boolean=true,
        var price_per_unit: String="",
        var units: String="",
        var description: String="",
        var title: String="",
        var images: List<Image> = emptyList() ,
        var creation_time: Long=2
)

@JsonClass(generateAdapter = true)
data class ProductResponse(
        val item_count: Int,
        val products: List<Product>,
        val timestamp: Long)

@JsonClass(generateAdapter = true)
data class AddOrder(
       val token:String
)

@JsonClass(generateAdapter = true)
data class addProduct(
    val title: String,
    val description: String,
    val price_per_unit: String,
    val units: String,
    val is_active: Boolean,
    val rating: Double,
    val amount_type: String,
    val price_type: String
)

@JsonClass(generateAdapter = true)
data class addProductResponse(
    val creation:String,
    val product_id:String,
    val username:String,
    val is_active:Boolean,
    val price_per_unit:String,
    val units:String,
    val description:String,
    val title:String,
    val rating:Double,
    val amount_type:String,
    val price_type:String,
    val images:List<Image> = emptyList(),
    val creation_time:Long
)
