package com.example.projekt

import android.app.Application

class MyApplication: Application(){
    companion object{
        var userName = ""
        var token: String =""
        var thisProduct: String =""
        var position:Int = 0
    }
}