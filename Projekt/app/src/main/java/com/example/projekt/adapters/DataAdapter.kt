package com.example.projekt.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projekt.R
import com.example.projekt.model.Product


class DataAdapter(
private var list: ArrayList<Product>,
private val context: Context,
private val listener: OnItemClickListener,
private val listener2: OnItemLongClickListener,
val listener3: ClickOrderButton
) :
RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener{
        fun onItemLongClick(position: Int)
    }

    interface ClickOrderButton{
        fun addOrder(position: Int)
    }

    // 1. user defined ViewHolder type - Embedded class!
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener, View.OnLongClickListener {
        val textView_name: TextView = itemView.findViewById(R.id.textView_name_item_layout)
        val textView_price: TextView = itemView.findViewById(R.id.textView_price_item_layout)
        val textView_seller: TextView = itemView.findViewById(R.id.textView_seller_item_layout)
        val imageView: ImageView = itemView.findViewById(R.id.imageView_item_layout)
        val btnAddOrder: Button = itemView.findViewById(R.id.btnOrder)

        init{
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }
        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition
            listener.onItemClick(currentPosition)
        }

        override fun onLongClick(p0: View?): Boolean {
            val currentPosition = this.adapterPosition
            listener2.onItemLongClick(currentPosition)
            return true
        }

        fun addOrder(position: Int) {
            listener3.addOrder(position)
        }

    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return DataViewHolder(itemView)
    }


    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        holder.textView_name.text = currentItem.title
        holder.textView_price.text = currentItem.price_per_unit
        holder.textView_seller.text = currentItem.username
        holder.btnAddOrder.setOnClickListener{
            listener3.addOrder(position)
        }
        val images = currentItem.images
        if( images != null && images.size > 0) {
            Log.d("xxx", "#num_images: ${images.size}")
        }
        Glide.with(this.context)
                .load(R.drawable.ic_user)
                .override(200, 200)
                .into(holder.imageView);
    }

    override fun getItemCount() = list.size

    // Update the list
    fun setData(newlist: ArrayList<Product>){
        list = newlist
    }
}
