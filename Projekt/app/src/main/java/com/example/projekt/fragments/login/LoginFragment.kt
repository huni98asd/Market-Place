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
import com.example.projekt.R
import com.example.projekt.repository.Repository
import com.example.projekt.viewmodels.LoginViewModel
import com.example.projekt.viewmodels.LoginViewModelFactory
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    lateinit var btnLogin:Button
    lateinit var btnSignIn:Button
    lateinit var btnForgetPassword:TextView
    lateinit var userName:EditText
    lateinit var inputPassword:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = LoginViewModelFactory(this.requireContext(), Repository())
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_login, container, false)

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
            if(userName.length() == 0 && inputPassword.length() == 0){
                Toast.makeText(activity,"Kérem töltse ki a mezöket",Toast.LENGTH_SHORT).show()
            }else{
                loginViewModel.user.value.let {
                    if (it != null) {
                        it.username = userName.text.toString()
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