package com.ulan.munduz.manager.ui.products

import com.ulan.app.munduz.ui.Product
import com.ulan.munduz.manager.listeners.ProductsListCallback
import com.ulan.munduz.manager.data.repository.Repository
import javax.inject.Inject

class ProductsPresenterImpl @Inject constructor(view: ProductsView, repository: Repository) :
    ProductsPresenter {

    private var mView: ProductsView? = view
    private var mRepository: Repository = repository

    override fun loadProducts(){
        mRepository.getProducts(object :
            ProductsListCallback{
            override fun onCallback(value: ArrayList<Product>) {
                mView?.hideProgressBar()
                mView?.showProducts(value)
            }
        })
    }

    override fun setToolbarTitle() {
        val title = "Список"
        mView?.setToolbar(title)
    }

    override fun detachView() {
        this.mView = null
    }
}