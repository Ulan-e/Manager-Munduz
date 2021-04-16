package com.ulan.munduz.manager.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ulan.munduz.manager.helpers.convertLongToTime
import com.ulan.app.munduz.ui.Product
import com.ulan.munduz.manager.R
import com.ulan.munduz.manager.listeners.OnItemClickListener
import javax.inject.Inject

class ProductsAdapter @Inject constructor(
    private val context: Context,
    listener: OnItemClickListener
) : RecyclerView.Adapter<ProductsHolder>(), Filterable {

    private val onItemClickListener: OnItemClickListener? = listener

    private lateinit var products: ArrayList<Product>
    private lateinit var filteredProducts: ArrayList<Product>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ProductsHolder {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.products_items, parent, false)
        return ProductsHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        val product: Product? = filteredProducts.get(position)
        holder.bind(product, onItemClickListener)
        holder.itemName.text = product!!.name
        holder.itemCategory.text = product!!.category
        holder.itemCost.text = product.date.convertLongToTime(product!!.date)
    }

    override fun getItemCount(): Int {
        return filteredProducts.size
    }

    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val pattern = p0.toString()

                if(pattern.isEmpty()){
                    filteredProducts = products
                }else{
                    val filteredList = ArrayList<Product>()
                    for(product: Product in products){
                        if(product.name.toLowerCase().contains(pattern)){
                            filteredList.add(product)
                        }
                    }
                    filteredProducts = filteredList
                }

                val results: FilterResults = FilterResults()
                results.values = filteredProducts
                return results

            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filteredProducts = p1!!.values as ArrayList<Product>
                notifyDataSetChanged()
            }
        }
    }

    fun setProducts(products: ArrayList<Product>){
        this.products = products
        this.filteredProducts = products
        this.filteredProducts.distinct()
    }
}