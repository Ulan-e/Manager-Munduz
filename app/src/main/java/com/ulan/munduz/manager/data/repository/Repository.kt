package com.ulan.munduz.manager.data.repository

import com.ulan.app.munduz.ui.Product
import com.ulan.munduz.manager.listeners.OrdersListCallback
import com.ulan.munduz.manager.listeners.ProductsListCallback
import com.ulan.munduz.manager.data.models.SliderImage

interface Repository{

    // встависть продукт
    fun insertProduct(product: Product)

    // обновить продукт
    fun updateProduct(product: Product)

    // получаем продукт
    fun getProducts(callback: ProductsListCallback)

    // удаляем продукт
    fun removeProduct(key: String)

    // получение заказов
    fun getOrders(callback: OrdersListCallback)

    // получение категорий продуктов
    fun getCategories() : MutableList<String>

    // вставить фото рекламы
    fun insertSliderImage(name: String, sliderImage: SliderImage)

    // получить фото рекламы
    fun getSliderImage(queryName: String) : SliderImage
}