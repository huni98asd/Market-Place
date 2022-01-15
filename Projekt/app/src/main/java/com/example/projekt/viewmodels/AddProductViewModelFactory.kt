package com.example.projekt.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projekt.repository.Repository

class AddProductViewModelFactory(private val context: Context, private val repository: Repository) : ViewModelProvider.Factory   {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddProductViewModel(context,repository) as T
    }
}