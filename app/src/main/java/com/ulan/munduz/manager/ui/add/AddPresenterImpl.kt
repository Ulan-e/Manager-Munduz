package com.ulan.munduz.manager.ui.add

import android.net.Uri
import com.ulan.app.munduz.ui.Product
import com.ulan.munduz.manager.data.models.Picture
import com.ulan.munduz.manager.data.repository.Repository
import com.ulan.munduz.manager.data.repository.Storage
import javax.inject.Inject

class AddPresenterImpl @Inject constructor(
    view: AddView,
    repository: Repository,
    storage: Storage
) : AddPresenter {

    private var mView: AddView? = view
    private var mRepository: Repository = repository
    private var mStorage: Storage = storage
    private lateinit var mProduct: Product
    private lateinit var mPicture: Picture

    override fun setToolbar() {
        mView!!.initToolbar("Добавить")
    }

    override fun initCategory() {
        val categories = mRepository.getCategories()
        mView?.setCategories(categories)
    }

    override fun insert(product: Product) {
        mRepository.insertProduct(product)
        mView?.clearFields()
    }

    override fun getPictureUrl(filePath: Uri?): Picture {
        mPicture = mStorage.insertImage(filePath!!)
        return mPicture
    }

    override fun addButtonClicked() {
        mProduct = mView?.getInputProduct()!!
        mView?.checkForNull()
        insert(mProduct)
        mView?.clearFields()
    }

    override fun chooseImageButtonClicked() {
        mView?.chooseImage()
    }

    override fun detachView() {
        this.mView = null
    }
}