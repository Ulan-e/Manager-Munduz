package com.ulan.munduz.manager.ui.detail

import android.net.Uri
import com.ulan.app.munduz.ui.Product
import com.ulan.munduz.manager.data.models.Picture
import com.ulan.munduz.manager.data.repository.Repository
import com.ulan.munduz.manager.data.repository.Storage
import javax.inject.Inject

class DetailsPresenterImpl @Inject constructor(
    view: DetailsView,
    repository: Repository,
    storage: Storage
) : DetailsPresenter{

    companion object{
        const val ABOUT_PRODUCT = "О Продукте"
    }

    private var mView: DetailsView? = view
    private var mRepository: Repository = repository
    private var mStorage: Storage = storage

    override fun initCategory() {
        val categories = mRepository.getCategories()
        mView?.setCategories(categories)
    }

    override fun findCategoryPosition(category: String): Int {
        val categories = mRepository.getCategories()
        var position = 0
        for ((index, value) in categories.withIndex()){
            if(category == value){
                position =  index
            }
        }
        return position
    }

    override fun setToolbar() {
        mView?.initToolbar(ABOUT_PRODUCT)
    }

    override fun showProduct(product: Product) {
        mView?.setProduct(product)
    }

    override fun getPictureUrl(filePath: Uri?): Picture {
        return mStorage.insertImage(filePath!!)
    }

    override fun updateButtonClicked() {
        val product = mView!!.getInputProduct()
        mRepository.updateProduct(product)
    }

    override fun deleteButtonClicked(product: Product) {
        mRepository.removeProduct(product.id)
    }

    override fun chooseImageButtonClicked() {
        mView?.chooseImage()
    }

    override fun detachView() {
        mView = null
    }
}