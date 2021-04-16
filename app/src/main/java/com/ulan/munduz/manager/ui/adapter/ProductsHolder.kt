package com.ulan.munduz.manager.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulan.app.munduz.ui.Product
import com.ulan.munduz.manager.R
import com.ulan.munduz.manager.listeners.OnItemClickListener

class ProductsHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {

    val itemName: TextView = itemView.findViewById(R.id.item_name)
    val itemCategory: TextView = itemView.findViewById(R.id.item_category)
    val itemCost: TextView = itemView.findViewById(R.id.item_cost)

    fun bind(product: Product?, onItemClickListener: OnItemClickListener?){
        itemView.setOnClickListener{
            onItemClickListener!!.onItemClick(product)
        }
    }
}
