package com.ulan.munduz.manager.ui.add

import com.ulan.app.munduz.ui.Product

interface AddView {

    // инициализация тулбара
    fun initToolbar(title: String)

    // получить данные инпута
    fun getInputProduct(): Product

    // очистить поля
    fun clearFields()

    // выбрать изображение
    fun chooseImage()

    // проверка на null все поля
    fun checkForNull()

    // ставить категории продуктов
    fun setCategories(categories: MutableList<String>)
}