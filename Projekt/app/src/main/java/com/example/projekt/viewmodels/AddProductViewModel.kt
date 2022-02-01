package com.example.projekt.viewmodels

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projekt.MyApplication
import com.example.projekt.model.Product
import com.example.projekt.model.addProduct
import com.example.projekt.repository.Repository

class AddProductViewModel(val context: Context, val repository: Repository) : ViewModel() {
    var product : MutableLiveData<Product> = MutableLiveData()
    var token: MutableLiveData<String> = MutableLiveData()
    var images:ArrayList<Uri?>? = ArrayList()

    init {
        product.value = Product()

    }


    suspend fun addNewProduct() {
        try {
            val result = repository.addProduct(token = MyApplication.token,title = product.value!!.title,
                    description = product.value!!.description,price_per_unit =  product.value!!.price_per_unit,
                    units = product.value!!.units,is_active = product.value!!.is_active,rating = product.value!!.rating,
                    amount_type = product.value!!.amount_type,price_type = product.value!!.price_type)
            MyApplication.token = result.title
            token.value = result.toString()
            Log.d("xxx", "MyApplication - token:  ${MyApplication.token}")
            Toast.makeText(context,"${result.title}",Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Log.d("xxx", "LoginViewModel - exception: ${e.toString()}")
            Toast.makeText(context,"${e.toString()}",Toast.LENGTH_SHORT).show()
        }
    }

}
