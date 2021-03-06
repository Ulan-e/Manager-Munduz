package com.ulan.munduz.manager.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Message(
    var email: String = "",
    var subject: String = "",
    var body: String ="",
    var time: String = ""
): Parcelable