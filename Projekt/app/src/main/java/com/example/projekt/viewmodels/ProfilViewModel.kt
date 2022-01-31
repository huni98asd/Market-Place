package com.example.projekt.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekt.MyApplication
import com.example.projekt.data.User
import com.example.projekt.repository.Repository
import kotlinx.coroutines.launch

class ProfilViewModel(private val repository: Repository) : ViewModel() {
    var user = MutableLiveData<User>()
    var token: MutableLiveData<String> = MutableLiveData()


    init{
       // user.value = User( )
        getProfil()
    }


    fun getProfil() {
        viewModelScope.launch {
            try {
                val result = repository.getProfil(MyApplication.token)
                user.value!!.token = result.token
                Log.d("xxx", "ListViewModel - #products:  ${result}")
            }catch(e: Exception){
                Log.d("xxx", "ListViewMofdel exception: ${e.toString()}")
            }
        }
    }
}