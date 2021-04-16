package com.ulan.munduz.manager.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulan.munduz.manager.helpers.convertLongToTime
import com.ulan.munduz.manager.R
import com.ulan.munduz.manager.data.models.Order

class OrdersAdapter(
    private var context: Context
) : RecyclerView.Adapter<OrdersAdapter.CategoryViewHolder>() {

    private lateinit var orders: MutableList<Order>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.orders_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val order = orders.get(position)
        holder.body.text = order.body
        holder.phoneNumber.text = order.phoneNumber.toString()
        holder.time.text = order.time.convertLongToTime(order.time)
    }

    fun setCategories(categories: MutableList<Order>) {
        this.orders = categories
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val body: TextView = itemView.findViewById(R.id.order_body)
        val phoneNumber: TextView = itemView.findViewById(R.id.order_phone_number)
        val time: TextView = itemView.findViewById(R.id.order_time)
    }
}