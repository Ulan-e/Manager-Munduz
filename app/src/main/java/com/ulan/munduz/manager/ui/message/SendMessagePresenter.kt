package com.ulan.munduz.manager.ui.message

interface SendMessagePresenter {

    // откпрепить View
    fun detachView()

    // клик на оправку
    fun sendButtonClicked()

    // клик на отмену отправки
    fun cancelButtonClicked()
}