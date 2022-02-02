package com.example.projekt.fragments.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projekt.MainActivity
import com.example.projekt.MyApplication
import com.example.projekt.R
import com.example.projekt.repository.Repository
import com.example.projekt.viewmodels.ListViewModel
import com.example.projekt.viewmodels.ListViewModelFactory
import com.example.projekt.viewmodels.LoginViewModel
import com.example.projekt.viewmodels.LoginViewModelFactory
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var listViewModel: ListViewModel
    lateinit var btnLogin:Button
    lateinit var btnSignIn:Button
    lateinit var btnForgetPassword:TextView
    lateinit var userName:EditText
    lateinit var inputPassword:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = LoginViewModelFactory(this.requireContext(), Repository())
        val factory2 = ListViewModelFactory( Repository())
        loginViewModel = ViewModelProvider(requireActivity(), factory).get(LoginViewModel::class.java)
        listViewModel = ViewModelProvider(requireActivity(), factory2).get(ListViewModel::class.java)
        loginViewModel.showBottomNav.value = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_login, container, false)
        loginViewModel.showBottomNav.value = false
        if( view != null) {
            initializeView(view)
            login()
            register()
            forgetPassword()
        }

        return view
    }

    private fun register(){
        btnSignIn.setOnClickListener{
            this.findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun forgetPassword(){
        btnForgetPassword.setOnClickListener{
            this.findNavController().navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
        }
    }

    private fun login() {
        btnLogin.setOnClickListener{
            if(userName.length() == 0) {
                Toast.makeText(activity, "Kérem töltse ki a név mezöt", Toast.LENGTH_SHORT).show()
            } else if(inputPassword.length() == 0){
                Toast.makeText(activity, "Kérem töltse ki a jelszo mezöt", Toast.LENGTH_SHORT).show()
            }else{
                loginViewModel.user.value.let {
                    if (it != null) {
                        it.username = userName.text.toString()
                        MyApplication.userName = userName.text.toString()
                    }
                    if (it != null) {
                        it.password = inputPassword.text.toString()
                    }
                }
                lifecycleScope.launch {
                    loginViewModel.login()
                }
                loginViewModel.token.observe(viewLifecycleOwner){
                    Log.d("xxx", "navigate to list")
                    this.findNavController().navigate(R.id.action_loginFragment_to_listFragment)
                }
            }

        }
    }



    private fun initializeView(view: View) {
        view.apply {
        btnLogin = findViewById(R.id.btnLogin)
        btnSignIn = findViewById(R.id.btnSignUp)
        btnForgetPassword = findViewById(R.id.btnForgetPassword)
        userName = findViewById(R.id.editTextUserName)
        inputPassword = findViewById(R.id.editTextPassword)
        }
    }
}