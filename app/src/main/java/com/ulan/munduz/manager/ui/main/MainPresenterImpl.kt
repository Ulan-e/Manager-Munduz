package com.ulan.munduz.manager.ui.main

import javax.inject.Inject

class MainPresenterImpl @Inject constructor(private var mView: MainView?) : MainPresenter {

    override fun detachView() {
        this.mView = null
    }

    override fun callAddActivity() {
        mView?.showAddActivity()
    }

    override fun callAddSliderImageActivity() {
        mView?.showAddSliderImageActivity()
    }

    override fun callProductActivity() {
        mView?.showManageActivity()
    }

    override fun callOrderActivity() {
        mView?.showOrderActivity()
    }

    override fun callSendMessageFragment() {
        mView?.showSendMessageFragment()
    }
}