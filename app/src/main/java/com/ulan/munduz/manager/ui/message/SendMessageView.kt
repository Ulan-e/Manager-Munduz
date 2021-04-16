package com.ulan.munduz.manager.ui.message

import com.ulan.munduz.manager.data.models.Message

interface SendMessageView {

    fun getInputMessage(): Message
    fun sendMessage(message: Message)
    fun cancelDialog()
    fun isEmptyFields() : Boolean
    fun showEmptyFields()
}