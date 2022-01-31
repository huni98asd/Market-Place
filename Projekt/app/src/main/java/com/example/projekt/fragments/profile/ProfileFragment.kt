package com.example.projekt.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.projekt.R
import com.example.projekt.repository.Repository
import com.example.projekt.viewmodels.LoginViewModel
import com.example.projekt.viewmodels.ProfilViewModel
import com.example.projekt.viewmodels.ProfilViewModelFactory
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment:Fragment() {

    lateinit var profilViewModel: ProfilViewModel
    lateinit var userEmail:TextView
    lateinit var phoneNumber:TextView
    lateinit var nickName: TextView
    lateinit var changeProfilleBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProfilViewModelFactory(Repository())
        profilViewModel = ViewModelProvider(requireActivity(),factory).get(ProfilViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile,container,false)

        if(view != null){
            initializeView(view)
            goToUpdateProfile()
            setData()
        }

        return view
    }

    fun setData(){
        userEmail.text = profilViewModel.user.value!!.email
        phoneNumber.text = profilViewModel.user.value!!.phone_number
        nickName.text = profilViewModel.user.value!!.username

    }

    fun goToUpdateProfile(){
        changeProfilleBtn.setOnClickListener{
            this.findNavController().navigate(R.id.action_profileFragment_to_updatingProfileFragment)
            //Toast.makeText(context,"aaha",Toast.LENGTH_SHORT).show()
        }
    }

    fun initializeView(view: View){
        view.apply {
            userEmail = findViewById(R.id.username)
            phoneNumber = findViewById(R.id.phonNumber)
            nickName = findViewById(R.id.clientEmail)
            changeProfilleBtn = findViewById(R.id.changeProfileButton)
        }
    }

}