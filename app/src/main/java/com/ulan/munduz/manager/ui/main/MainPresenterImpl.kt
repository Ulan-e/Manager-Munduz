package com.ulan.munduz.manager.ui.main

class MainPresenterImpl: MainPresenter {

    private var mView: ManagerView? = null

    constructor(mView: ManagerView?) {
        this.mView = mView
    }

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