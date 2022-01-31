package com.example.projekt.data

import com.squareup.moshi.JsonClass

data class User(
    var username: String="",
    var password: String="",
    var email: String="",
    var phone_number: String="",
    var token: String=""
)


@JsonClass(generateAdapter = true)
data class LoginRequest (
    var username: String,
    var password: String
)

@JsonClass(generateAdapter = true)
data class LoginResponse (
    var username: String,
    var email: String,
    var phone_number: Int = 0,
    var token: String,
    var creation_time: Long,
    var refresh_time: Long
)

@JsonClass(generateAdapter = true)
data class Register(
    var username: String,
    var email: String,
    var phone_number: String="",
    var password: String
)

@JsonClass(generateAdapter = true)
data class RegisterResponse (
        var number: String,
        var mesege: String,
        var time: Long
)



