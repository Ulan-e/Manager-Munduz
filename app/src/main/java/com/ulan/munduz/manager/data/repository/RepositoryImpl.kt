package com.ulan.munduz.manager.data.repository

import android.content.Context
import com.google.firebase.database.*
import com.ulan.munduz.manager.R
import com.ulan.munduz.manager.helpers.Constants.Companion.ORDERS_DATA
import com.ulan.app.munduz.ui.Product
import com.ulan.munduz.manager.helpers.Constants.Companion.PRODUCTS_DATA
import com.ulan.munduz.manager.helpers.Constants.Companion.SLIDER_IMAGE_DATA
import com.ulan.app.munduz.helpers.showErrorReadFromDatabase
import com.ulan.munduz.manager.listeners.OrdersListCallback
import com.ulan.munduz.manager.listeners.ProductsListCallback
import com.ulan.munduz.manager.data.models.Order
import com.ulan.munduz.manager.data.models.SliderImage
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val context: Context) : Repository {

    private val database: FirebaseDatabase
    private val ref: DatabaseReference

    init {
        database = FirebaseDatabase.getInstance()
        ref = database.reference
    }

    override fun insertProduct(product: Product) {
        val key = ref.push().key ?: return
        product.id = key
        ref.child(PRODUCTS_DATA).child(key).setValue(product)
    }

    override fun updateProduct(product: Product) {
        val key = product.id
        ref.child(PRODUCTS_DATA).child(key).setValue(product)
    }

    override fun getProducts(callback: ProductsListCallback){
        val products = ArrayList<Product>()
        ref.child(PRODUCTS_DATA).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                products.clear()
                for (item: DataSnapshot in p0.children) {
                    val product: Product? = item.getValue(Product::class.java)
                    products.add(product!!)
                }
                callback.onCallback(products)
            }

            override fun onCancelled(p0: DatabaseError) {
                showErrorReadFromDatabase(context)
            }
        })
    }

    override fun removeProduct(key: String) {
        ref.child(PRODUCTS_DATA).child(key).removeValue()
    }

    override fun getOrders(callback: OrdersListCallback) {
        val orders = mutableListOf<Order>()
        ref.child(ORDERS_DATA).addListenerForSingleValueEvent(object : ValueEventListener{

            override fun onDataChange(p0: DataSnapshot) {
                for(item: DataSnapshot in p0.children){
                    val order = item.getValue(Order::class.java)
                    orders.add(order!!)
                }
                callback.onCallback(orders)
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO()
            }
        })
    }

    override fun getCategories(): MutableList<String> {
        val result = context.resources.getStringArray(R.array.category)
        return result.toMutableList()
    }

    override fun insertSliderImage(name: String, sliderImage: SliderImage) {
        sliderImage.name = name
        ref.child(SLIDER_IMAGE_DATA).child(name).setValue(sliderImage)
    }

    override fun getSliderImage(queryName: String): SliderImage {
        var sliderImage = SliderImage()
        val query = ref.child(SLIDER_IMAGE_DATA).orderByChild("name").equalTo(queryName)
        query.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO()
            }

            override fun onDataChange(p0: DataSnapshot) {
                sliderImage = p0.getValue(SliderImage::class.java)!!
            }

        })
        return sliderImage
    }
}