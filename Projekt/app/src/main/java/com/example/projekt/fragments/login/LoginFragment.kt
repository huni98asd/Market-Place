package com.example.projekt.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.projekt.R


class LoginFragment : Fragment() {

    lateinit var btnLogin:Button
    lateinit var btnSignIn:Button
    lateinit var btnForgetPassword:Button
    lateinit var inputEmail:EditText
    lateinit var inputPassword:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_login, container, false)

        if( view != null) {
            initializeView(view)
        }

        return view
    }


    private fun initializeView(view: View) {
        view.apply {
        btnLogin = findViewById(R.id.btnLogin)
        btnSignIn = findViewById(R.id.btnSignUp)
        btnForgetPassword = findViewById(R.id.btnForgetPassword)
        inputEmail = findViewById(R.id.editTextEmailAddress)
        inputPassword = findViewById(R.id.editTextPassword)
        }
    }
}