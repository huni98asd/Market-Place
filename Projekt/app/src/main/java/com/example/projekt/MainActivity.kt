package com.example.projekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.projekt.repository.Repository
import com.example.projekt.viewmodels.ListViewModel
import com.example.projekt.viewmodels.ListViewModelFactory
import com.example.projekt.viewmodels.LoginViewModel
import com.example.projekt.viewmodels.LoginViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    lateinit var btnNav:BottomNavigationView
    lateinit var btnFA:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = ListViewModelFactory( Repository())
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        btnNav=findViewById(R.id.bottom_navigation)
        btnFA=findViewById(R.id.floatingActionButton)

        //btnNav.visibility = View.GONE
        loginViewModel.showBottomNav.observe(this){
            if(it == true){
                btnNav.visibility = View.VISIBLE
            }else{
                btnNav.visibility = View.GONE
            }
        }
        loginViewModel.showFloatingBottom.observe(this){
            if(it == true){
                btnFA.visibility = View.VISIBLE
            }else{
                btnFA.visibility = View.GONE
            }
        }
        btnFA.setOnClickListener{
            Navigation.findNavController(this,R.id.nav_host).navigate(R.id.action_myMarketListFragment_to_addProductFragment)
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