package com.ulan.munduz.manager.ui.main

interface MainPresenter {

    // открепить View
    fun detachView()

    // вызов добавление продукта
    fun callAddActivity()

    // вызов добавление рекламного поста
    fun callAddSliderImageActivity()

    // вызов списка продуктов
    fun callProductActivity()

    // вызов заказа продукта
    fun callOrderActivity()

    // вызов отправки сообщению разработчику
    fun callSendMessageFragment()
}