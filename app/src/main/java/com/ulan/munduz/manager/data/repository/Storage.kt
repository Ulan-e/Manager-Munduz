package com.ulan.munduz.manager.data.repository

import android.net.Uri
import com.ulan.munduz.manager.data.models.Picture
import com.ulan.munduz.manager.data.models.SliderImage

interface Storage {

    fun insertImage(filePath: Uri): Picture

    fun insertSliderImage(filePath: Uri)

    fun getInsertedSliderImage(): SliderImage

    fun removeImage(picture: Picture)
}