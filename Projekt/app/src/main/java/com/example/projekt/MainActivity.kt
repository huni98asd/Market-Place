package com.example.projekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.projekt.repository.Repository
import com.example.projekt.viewmodels.ListViewModel
import com.example.projekt.viewmodels.ListViewModelFactory
import com.example.projekt.viewmodels.LoginViewModel
import com.example.projekt.viewmodels.LoginViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    lateinit var btnNav:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = ListViewModelFactory( Repository())
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        btnNav=findViewById(R.id.bottom_navigation)

        //btnNav.visibility = View.GONE
        loginViewModel.showBottomNav.observe(this){

            if(it == true){
                btnNav.visibility = View.VISIBLE
            }else{
                btnNav.visibility = View.GONE
            }
        }
        btnNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.timeline -> {
                    Navigation.findNavController(this, R.id.nav_host).navigate(R.id.listFragment)
                    true
                }
                R.id.myMarket -> {
                    Navigation.findNavController(this, R.id.nav_host).navigate(R.id.myMarketListFragment)
                    true
                }
                R.id.myFares -> {
                    Navigation.findNavController(this, R.id.nav_host).navigate(R.id.myFaresListFragment)
                true
                }
                else->false
            }
        }
    }



}