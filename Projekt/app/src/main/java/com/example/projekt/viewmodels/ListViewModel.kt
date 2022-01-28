package com.example.projekt.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekt.MyApplication
import com.example.projekt.data.LoginRequest
import com.example.projekt.model.AddOrder
import com.example.projekt.model.Product
import com.example.projekt.repository.Repository
import kotlinx.coroutines.launch


class ListViewModel(private val repository: Repository) : ViewModel() {
    var products: MutableLiveData<List<Product>> = MutableLiveData()
    var myFares: MutableLiveData<List<Product>> = MutableLiveData()
    var myMarket: MutableLiveData<List<Product>> = MutableLiveData()

    var listOrder =  mutableListOf<String>()


    init{
        Log.d("xxx", "ListViewModel constructor - Token: ${MyApplication.token}")
        getProducts()
        //addOrder()
        //getMyMareket()
        //getOrders()
    }

    fun getIndex(){

    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                val result = repository.getProducts(MyApplication.token)
                products.value = result.products
                Log.d("xxx", "ListViewModel - #products:  ${result.item_count}")
            }catch(e: Exception){
                Log.d("xxx", "ListViewMofdel exception: ${e.toString()}")
            }
        }
    }

    fun getMyMareket(){
        viewModelScope.launch {
            try {
                val result = repository.getProducts(MyApplication.token)
                result.products.filter { it.username == "huni21" }.forEach{
                    products.value = result.products
                }
                Log.d("xxx", "ListViewModel - #products:  ${result.item_count}")
            }catch(e: Exception){
                Log.d("xxx", "ListViewMofdel exception: ${e.toString()}")
            }
        }
    }

    /*suspend fun addOrder(position: Int){
        val request = AddOrder( token = products.value?.get(position)!!.product_id )
        try{
            val result = repository.addOrder(request)


        }catch (e: Exception){
            Log.d("xxx", "LoginViewModel - exception: ${e.toString()}")
        }
    }*/

    fun getOrders(){
        viewModelScope.launch {
            try{
                val result = repository.getOrders(MyApplication.token)
                myFares.value = result.products
                Log.d("xxx", "ListViewModel - #products:  ${result.item_count}")
            }catch(e: Exception){
                Log.d("xxx", "ListViewMofdel exception: ${e.toString()}")
            }
        }
    }

}