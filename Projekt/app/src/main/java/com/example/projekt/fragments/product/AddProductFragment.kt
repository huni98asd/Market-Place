package com.example.projekt.fragments.product

import android.app.Activity
import android.content.Intent
import android.graphics.Color
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
import kotlinx.android.synthetic.main.fragment_add_product.*
import kotlinx.coroutines.launch
import kotlin.math.PI

class AddProductFragment : Fragment() {
    lateinit var adddProductViewMoldel: AddProductViewModel
    private lateinit var loginViewModel: LoginViewModel
    lateinit var imageSwitcher: ImageSwitcher

    lateinit var editTextTitleProduct:EditText
    lateinit var editTextDescriptionProduct:EditText
    lateinit var editTextPriceProduct:EditText
    lateinit var editTextAvailableProduct:EditText


    lateinit var btnPreviewMyFair:Button
    lateinit var btnLaunchMyFair:Button

    lateinit var switchActivation: Switch
    lateinit var adapter: Horizontal_RecyclerView
    val PICK_IMAGE_CODE = 0


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
/*

        val amount_type = resources.getStringArray(R.array.amountType)
        val amount_price_type = resources.getStringArray(R.array.amountPrice)

        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,amount_type)
        val arrayAdapter2 = ArrayAdapter(requireContext(),R.layout.dropdown_item,amount_price_type)


        autoCompleteTextView.setAdapter(arrayAdapter)
        autoCompleteTextView.setAdapter(arrayAdapter2)
*/

        if(view!=null){
           initializeView(view)
           setImages()
           addProduct()
        }
        imageSwitcher.setFactory { ImageView(context) }

        return view
    }

    private fun pickImagesIntent(){
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Select Image(s)"),PICK_IMAGE_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_CODE){
            if(resultCode == Activity.RESULT_OK){
                if(data!!.clipData != null){
                    val count = data.clipData!!.itemCount
                    for(i in 0 until count){
                        val imageUri = data.clipData!!.getItemAt(i).uri
                        adddProductViewMoldel.images!!.add(imageUri)
                    }
                    imageSwitcher.setImageURI(adddProductViewMoldel.images!![0])
                }
                else{
                    val imageUri = data.data
                    imageSwitcher.setImageURI(imageUri)
                }
            }
        }
    }

    private fun setImages(){
        imageSwitcher.setOnClickListener{
            pickImagesIntent()

        }
    }


    private fun addProduct(){
        var switchCheck = false


        if(switchActivation.isChecked){ switchCheck = true }
        btnLaunchMyFair.setOnClickListener{

            adddProductViewMoldel.product.value?.let {
                if( editTextTitleProduct.length() != 0 && editTextDescriptionProduct.length() != 0 && editTextAvailableProduct.length() != 0 && editTextPriceProduct.length() != 0 ) {

                    it.title = editTextTitleProduct.text.toString()
                    it.amount_type = "kg"
                    it.description = editTextDescriptionProduct.text.toString()
                    it.price_type = "10"
                    it.is_active = switchCheck
                    it.price_per_unit = editTextPriceProduct.text.toString()
                    it.units = editTextAvailableProduct.text.toString()
                    it.rating = 5.5
                }else{
                    Toast.makeText(context,"Kérem töltse ki a hiányzo részeket",Toast.LENGTH_SHORT).show()
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
            imageSwitcher=findViewById(R.id.imageViewAddView)
            editTextTitleProduct=findViewById(R.id.editTextTitleProduct)
            editTextDescriptionProduct=findViewById(R.id.editTextDescriptionProduct)
            editTextPriceProduct=findViewById(R.id.editTextPriceProduct)
            editTextAvailableProduct=findViewById(R.id.editTextAvailableProduct)
            btnPreviewMyFair=findViewById(R.id.btnPreviewMyFair)
            btnLaunchMyFair=findViewById(R.id.btnLaunchMyFair)
            switchActivation=findViewById(R.id.switchAOI)
        }
    }
}