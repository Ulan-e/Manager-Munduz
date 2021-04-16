package com.ulan.munduz.manager.listeners

import com.ulan.app.munduz.ui.Product

interface ProductsListCallback {

    fun onCallback(value: ArrayList<Product>)
}