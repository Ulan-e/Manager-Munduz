package com.ulan.munduz.manager.listeners

import com.ulan.munduz.manager.data.models.Order

interface OrdersListCallback {

    fun onCallback(value: MutableList<Order>)
}