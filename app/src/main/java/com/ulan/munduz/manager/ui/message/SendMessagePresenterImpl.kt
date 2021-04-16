package com.ulan.munduz.manager.ui.message

class SendMessagePresenterImpl(view: SendMessageView) : SendMessagePresenter {

    private var mView: SendMessageView? = view

    override fun detachView() {
        mView = null
    }

    override fun sendButtonClicked() {
        if(mView!!.isEmptyFields()){
            mView?.showEmptyFields()
        }else {
            mView?.sendMessage(mView?.getInputMessage()!!)
        }
    }

    override fun cancelButtonClicked() {
        mView?.cancelDialog()
    }
}