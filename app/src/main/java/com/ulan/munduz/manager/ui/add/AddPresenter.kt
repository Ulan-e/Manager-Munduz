package com.ulan.munduz.manager.ui.add

import android.net.Uri
import com.ulan.app.munduz.ui.Product
import com.ulan.munduz.manager.data.models.Picture

interface AddPresenter{

    // настройки тулбара
    fun setToolbar()

    // инициализации категорий товаров
    fun initCategory()

    // вставить продукт
    fun insert(product: Product)

    // получаем URL изображения
    fun getPictureUrl(filePath: Uri?): Picture

    // выбрать изображение
    fun chooseImageButtonClicked()

    // клик на добавить продукт
    fun addButtonClicked()

    // открепить View
    fun detachView()
}