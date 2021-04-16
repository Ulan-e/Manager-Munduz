package com.ulan.munduz.manager.ui.main

interface MainView {

    // инициализация тулбара
    fun initToolbar()

    // добавление продукта
    fun showAddActivity()

    // добавление рекламного поста
    fun showAddSliderImageActivity()

    // упраление продуктами
    fun showManageActivity()

    // отправление сообщения разработчику
    fun showSendMessageFragment()

    // заказ продукта
    fun showOrderActivity()
}