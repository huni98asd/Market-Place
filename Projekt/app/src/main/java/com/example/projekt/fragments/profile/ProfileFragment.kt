package com.example.projekt.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt.R
import com.example.projekt.adapters.DataAdapter
import com.example.projekt.data.User
import com.example.projekt.model.Product
import com.example.projekt.repository.Repository
import com.example.projekt.viewmodels.*


class ProfileFragment : Fragment() {
    lateinit var profilViewModel: ProfilViewModel
    private lateinit var adapter: DataAdapter
    lateinit var fav: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        //setHasOptionsMenu(true)
        //listViewModel.showBottomNav.value = true
        super.onCreate(savedInstanceState)
        val factory = ListViewModelFactory(Repository())
        profilViewModel = ViewModelProvider(this, factory).get(ProfilViewModel::class.java)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profilViewModel.user.observe(viewLifecycleOwner){
            adapter.setData(profilViewModel.user.value as ArrayList<Product>)
            adapter.notifyDataSetChanged()
        }
        return view
    }


}