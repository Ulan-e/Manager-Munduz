package com.ulan.munduz.manager.ui.orders

import com.ulan.munduz.manager.data.models.Order

interface OrdersView {

    fun initToolbar(title: String)
    fun showProgress()
    fun hideProgress()
    fun showOrders(category: MutableList<Order>)
}