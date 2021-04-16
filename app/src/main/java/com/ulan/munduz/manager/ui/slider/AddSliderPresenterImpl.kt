package com.ulan.munduz.manager.ui.slider

import android.net.Uri
import com.ulan.munduz.manager.data.models.SliderImage
import com.ulan.munduz.manager.data.repository.Repository
import com.ulan.munduz.manager.data.repository.Storage
import javax.inject.Inject

class AddSliderPresenterImpl @Inject constructor(
    mView: AddSliderView,
    private var mRepository: Repository,
    private var mStorage: Storage
) : AddSliderPresenter{

    private var mView: AddSliderView? = mView

    override fun setToolbar() {
        mView?.showToolbar()
    }

    override fun setSliderImage(filePath: Uri?) {
        mStorage.insertSliderImage(filePath!!)
    }

    override fun insertToDatabase(name: String){
        mRepository.insertSliderImage(name, getSliderImage())
    }

    private fun getSliderImage(): SliderImage {
        return mStorage.getInsertedSliderImage()
    }

    override fun detachView() {
        mView = null
    }
}