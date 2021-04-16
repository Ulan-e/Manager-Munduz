package com.ulan.munduz.manager.data.models

data class Order(
    var id : String = "",
    var body: String = "",
    var phoneNumber: Int = -1,
    var time: Long = 0L
)