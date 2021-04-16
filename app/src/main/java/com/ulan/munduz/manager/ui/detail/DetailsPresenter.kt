package com.ulan.munduz.manager.ui.detail

import android.net.Uri
import com.ulan.app.munduz.ui.Product
import com.ulan.munduz.manager.data.models.Picture

interface DetailsPresenter {

    // инициализируем категории
    fun initCategory()

    // настройки тулбара
    fun setToolbar()

    // показать инфо о продукте
    fun showProduct(product: Product)

    // получить URL изображения
    fun getPictureUrl(filePath: Uri?): Picture

    // клик на обновить продукт
    fun updateButtonClicked()

    // клик на удаление продукта
    fun deleteButtonClicked(product: Product)

    // выбрать изображение
    fun chooseImageButtonClicked()

    // найти категорию по позиции
    fun findCategoryPosition(category: String): Int

    // открепить View
    fun detachView()
}