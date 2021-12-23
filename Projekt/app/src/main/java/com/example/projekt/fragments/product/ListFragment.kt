package com.example.projekt.fragments.product

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt.R
import com.example.projekt.adapters.DataAdapter
import com.example.projekt.model.Product
import com.example.projekt.repository.Repository
import com.example.projekt.viewmodels.ListViewModel
import com.example.projekt.viewmodels.ListViewModelFactory
import com.example.projekt.viewmodels.LoginViewModel
import com.example.projekt.viewmodels.LoginViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class ListFragment : Fragment() , DataAdapter.OnItemClickListener, DataAdapter.OnItemLongClickListener,DataAdapter.ClickOrderButton {
    lateinit var listViewModel: ListViewModel
    lateinit var loginViewModel:LoginViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: DataAdapter
    lateinit var fav: MenuItem


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        //listViewModel.showBottomNav.value = true
        super.onCreate(savedInstanceState)
        val factory = ListViewModelFactory(Repository())
        val factory2 = LoginViewModelFactory(this.requireContext(), Repository())
        listViewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)
        loginViewModel = ViewModelProvider(requireActivity(), factory2).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)
        recycler_view = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        listViewModel.products.observe(viewLifecycleOwner){
            adapter.setData(listViewModel.products.value as ArrayList<Product>)
            adapter.notifyDataSetChanged()
        }
        loginViewModel.showBottomNav.value = true
        loginViewModel.showFloatingBottom.value = false
        return view
    }

    private fun setupRecyclerView(){
        adapter = DataAdapter(ArrayList<Product>(), this.requireContext(), this, this, this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context)
        recycler_view.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler_view.setHasFixedSize(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_navigation_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_search -> Toast.makeText(context, "dsds", Toast.LENGTH_SHORT).show()
            R.id.nav_filter -> Toast.makeText(context, "dsds", Toast.LENGTH_SHORT).show()
            R.id.nav_profil -> Navigation.findNavController(context as Activity,R.id.nav_host).navigate(R.id.profileFragment)
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onItemClick(position: Int) {
        this.findNavController().navigate(R.id.action_listFragment_to_productDetailsFragment)
        //Toast.makeText(context,position.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(position: Int) {
//        TODO("Not yet implemented")
    }

    override fun addOrder(position: Int) {
        listViewModel.listOrder.add(listViewModel.products.value?.get(position)?.product_id.toString())
    }

}