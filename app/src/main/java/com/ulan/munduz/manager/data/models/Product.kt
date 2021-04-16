package com.ulan.app.munduz.ui

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.ulan.munduz.manager.data.models.Picture
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Product(
    var id: String = "",
    var category: String = "",
    var name: String = "",
    var desc: String = "",
    var cost: Int = -1,
    var priceFor: String = "",
    var isVisible: Boolean = false,
    var recommend: Boolean = false,
    var picture: Picture = Picture(
        "url"
    ),
    var date:  Long = -1)
    : Parcelable{

    override fun equals(other: Any?): Boolean {
        if(this === other)
            return true
        if(other !is Product)
            return false
        else{
            if(this.id == other.id)
                return true
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun hashCode(): Int {
        return Objects.hash(this.id)
    }
}