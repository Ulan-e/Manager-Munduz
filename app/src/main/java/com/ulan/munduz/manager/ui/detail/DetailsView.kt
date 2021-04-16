package com.ulan.munduz.manager.ui.detail

import com.ulan.app.munduz.ui.Product

interface DetailsView {

    // инициализация тулбара
    fun initToolbar(title: String)

    // вставить категории продуктов
    fun setCategories(categories: MutableList<String>)

    // вставить продукт
    fun setProduct(product: Product?)

    // получить данные с инпута
    fun getInputProduct():Product

    // выбрать изображение
    fun chooseImage()

    // проверка на null
    fun checkForNull()
}