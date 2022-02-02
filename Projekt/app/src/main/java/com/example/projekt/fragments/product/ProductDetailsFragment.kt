package com.example.projekt.fragments.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.projekt.MainActivity.Companion.products
import com.example.projekt.MyApplication
import com.example.projekt.R
import com.example.projekt.model.Product
import com.example.projekt.repository.Repository
import com.example.projekt.viewmodels.ListViewModel
import com.example.projekt.viewmodels.ListViewModelFactory
import com.example.projekt.viewmodels.LoginViewModel
import com.example.projekt.viewmodels.LoginViewModelFactory


class ProductDetailsFragment : Fragment() {
    lateinit var sellerName: TextView
    lateinit var productName: TextView
    lateinit var productPrice: TextView
    lateinit var productDetalis: TextView
    lateinit var priceType: TextView
    lateinit var amountType: TextView

    lateinit var listViewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ListViewModelFactory(Repository())
        listViewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)

        if(view != null){
            initializeView(view)
            loadProduct()
        }

        return view
    }

    fun loadProduct(){

        //Toast.makeText(context, products.value!![MyApplication.position].username,Toast.LENGTH_SHORT).show()
        sellerName.text = products.value!![MyApplication.position].username
        productName.text = products.value!![MyApplication.position].title
        productDetalis.text = products.value!![MyApplication.position].description
        //productPrice.text = products.value!![MyApplication.position].price_per_unit
        //priceType.text = products.value!![MyApplication.position].price_type
        //amountType.text = products.value!![MyApplication.position].amount_type
        productPrice.text = products.value!![MyApplication.position].price_per_unit + " " + products.value!![MyApplication.position].price_type + " / " + products.value!![MyApplication.position].amount_type
    }

    fun initializeView(view: View){
        view.apply {
            sellerName = findViewById(R.id.sellerName)
            productName = findViewById(R.id.productName)
            productPrice = findViewById(R.id.productPrice)
            productDetalis = findViewById(R.id.productDescription)
            //priceType = findViewById(R.id.priceType)
            //amountType = findViewById(R.id.amountType)
        }
    }

}