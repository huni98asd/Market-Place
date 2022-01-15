package com.example.projekt.fragments.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projekt.R
import com.example.projekt.repository.Repository
import com.example.projekt.viewmodels.*
import kotlinx.coroutines.launch


class  RegistrationFragment : Fragment() {
    private lateinit var registerViewModel: RegisterViewModel
    lateinit var userName:EditText
    lateinit var phonNumber:EditText
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var btnRegister:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = RegisterViewModelFactory(this.requireContext(), Repository())
        registerViewModel = ViewModelProvider(requireActivity(), factory).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        if( view != null) {
            initializeView(view)
            registration()
        }

        return view
    }

    private fun registration(){
        btnRegister.setOnClickListener {

            if (userName.length() == 0) {
                Toast.makeText(activity, "Kérem töltse ki a név mezöt", Toast.LENGTH_SHORT).show()
            } else if (phonNumber.length() == 0) {
                Toast.makeText(activity, "Kérem töltse ki a telefon mezöt", Toast.LENGTH_SHORT).show()
            } else if(email.length() == 0){
                Toast.makeText(activity, "Kérem töltse ki az email mezöt", Toast.LENGTH_SHORT).show()
            }else if(password.length() ==0){
                Toast.makeText(activity, "Kérem töltse ki a jelszo mezöt", Toast.LENGTH_SHORT).show()
            }else{

                registerViewModel.user.value.let {
                    if (it != null) {
                        it.username = userName.text.toString()
                    }
                    if (it != null) {
                        it.phone_number = phonNumber.text.toString()
                    }
                    if(it != null){
                        it.email = email.text.toString()
                    }
                    if(it != null){
                        it.password = password.text.toString()
                    }
                }
                lifecycleScope.launch {
                    registerViewModel.registration()
                }
                registerViewModel.token.observe(viewLifecycleOwner){
                    Log.d("xxxy", "registration valid")
                }
            }
        }
    }


    private fun initializeView(view: View) {
        view.apply {
            userName=findViewById(R.id.editTextUserName)
            phonNumber=findViewById(R.id.editTextPhoneNumber)
            email=findViewById(R.id.editTextTextEmailAddress)
            password=findViewById(R.id.editTextPassword)
            btnRegister=findViewById(R.id.buttonRegister)
        }
    }

}