package com.example.projekt.fragments.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt.R
import com.example.projekt.adapters.Horizontal_RecyclerView
import com.example.projekt.repository.Repository
import com.example.projekt.viewmodels.*
import kotlinx.coroutines.launch

class AddProductFragment : Fragment() {
    lateinit var adddProductViewMoldel: AddProductViewModel
    private lateinit var loginViewModel: LoginViewModel
    lateinit var productImage:ImageView
    lateinit var editTextTitleProduct:EditText
    lateinit var editTextDescriptionProduct:EditText
    lateinit var editTextPriceProduct:EditText
    lateinit var editTextAvailableProduct:EditText
    lateinit var editTextPersonName:EditText
    lateinit var editTextEmail:EditText
    lateinit var editTextPhoneNumber:EditText
    lateinit var btnPreviewMyFair:Button
    lateinit var btnLaunchMyFair:Button
    lateinit var recyckerView: RecyclerView
    lateinit var adapter: Horizontal_RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = AddProductViewModelFactory(this.requireContext(), Repository())
        val factory2 = LoginViewModelFactory(this.requireContext(), Repository())
        adddProductViewMoldel = ViewModelProvider(requireActivity(), factory).get(AddProductViewModel::class.java)
        loginViewModel = ViewModelProvider(requireActivity(), factory2).get(LoginViewModel::class.java)
        loginViewModel.showFloatingBottom.value=false


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)

       if(view!=null){
           initializeView(view)
           addProduct()
           addPersonInfo()
       }
        adapter = Horizontal_RecyclerView()

        recyckerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyckerView.adapter = adapter

        return view
    }

    private fun setupRecyclerView(){

    }

    private fun addPersonInfo(){

    }

    private fun addProduct(){
        btnLaunchMyFair.setOnClickListener{
            adddProductViewMoldel.product.value.let {
                if(it != null){
                    it.title = editTextTitleProduct.text.toString()
                }
                if(it != null){
                    it.title = editTextDescriptionProduct.text.toString()
                }
                if(it != null){
                    it.title = editTextPriceProduct.text.toString()
                }
                if(it != null){
                    it.title = editTextAvailableProduct.text.toString()
                }


            }
            lifecycleScope.launch{
                adddProductViewMoldel.addNewProduct()
            }
            adddProductViewMoldel.token.observe(viewLifecycleOwner){
                Log.d("xxxy", "new product added")
            }
        }
    }

    private fun initializeView(view: View) {
        view.apply {
            recyckerView=findViewById(R.id.horizontalRecyclerViewAddImage)
            editTextTitleProduct=findViewById(R.id.editTextTitleProduct)
            editTextDescriptionProduct=findViewById(R.id.editTextDescriptionProduct)
            editTextPriceProduct=findViewById(R.id.editTextPriceProduct)
            editTextAvailableProduct=findViewById(R.id.editTextAvailableProduct)
            editTextPersonName=findViewById(R.id.editTextPersonName)
            editTextEmail=findViewById(R.id.editTextEmail)
            editTextPhoneNumber=findViewById(R.id.editTextPhoneNumber)
            btnPreviewMyFair=findViewById(R.id.btnPreviewMyFair)
            btnLaunchMyFair=findViewById(R.id.btnLaunchMyFair)
        }
    }
}