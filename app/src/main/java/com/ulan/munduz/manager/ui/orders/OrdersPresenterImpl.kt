package com.ulan.munduz.manager.ui.orders

import com.ulan.munduz.manager.listeners.OrdersListCallback
import com.ulan.munduz.manager.data.models.Order
import com.ulan.munduz.manager.data.repository.Repository

class OrdersPresenterImpl(view: OrdersView, repository: Repository) : OrdersPresenter{

    private var mView: OrdersView? = view
    private var mRepository: Repository = repository

    companion object{
        const val ORDERS = "Заказы"
    }

    override fun detachView() {
       this.mView = null
    }

    override fun showToolbar() {
        mView?.initToolbar(ORDERS)
    }

    override fun loadCategories() {
        mRepository.getOrders(object : OrdersListCallback{
            override fun onCallback(value: MutableList<Order>) {
                mView?.showOrders(value)
            }
        })
    }
}