package com.example.projekt.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projekt.MyApplication
import com.example.projekt.MyApplication.Companion.token
import com.example.projekt.data.User
import com.example.projekt.model.Product
import com.example.projekt.model.addProduct
import com.example.projekt.repository.Repository

class AddProductViewModel(val context: Context, val repository: Repository) : ViewModel() {
    var product = MutableLiveData<Product>()

    suspend fun addNewProduct() {
        val request =
            addProduct(title = product.value!!.username, description = product.value!!.description,product.value!!.price_per_unit,
                units = product.value!!.units,is_active = product.value!!.is_active,rating = product.value!!.rating,
                amount_type = product.value!!.amount_type,price_type = product.value!!.price_type)
        try {
            val result = repository.addProduct(request)
            MyApplication.token = result.item_count.toString()
            token = result.toString()
            Log.d("xxx", "MyApplication - token:  ${MyApplication.token}")
        } catch (e: Exception) {
            Log.d("xxx", "LoginViewModel - exception: ${e.toString()}")
            Toast.makeText(context,"Helytelen adatok", Toast.LENGTH_SHORT).show()

        }
    }

}
