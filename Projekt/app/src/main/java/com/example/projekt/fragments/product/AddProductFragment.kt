package com.example.projekt.fragments.product

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.projekt.R
import com.example.projekt.repository.Repository
import com.example.projekt.viewmodels.ListViewModel
import com.example.projekt.viewmodels.ListViewModelFactory
import com.example.projekt.viewmodels.LoginViewModel
import com.example.projekt.viewmodels.LoginViewModelFactory
import kotlinx.android.synthetic.main.fragment_add_product.*

class AddProductFragment : Fragment() {
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory2 = LoginViewModelFactory(this.requireContext(), Repository())
        loginViewModel = ViewModelProvider(requireActivity(), factory2).get(LoginViewModel::class.java)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)

        /* val amountPrice = resources.getString(R.array.amountPrice)
        val availableAmount = resources.getString(R.array.AvailableAmount)*/

       // val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, amountPrice)
        //autoCompleteTextView.setAdapter(arrayAdapter)

        loginViewModel.showFloatingBottom.value = false
        return view
    }


}