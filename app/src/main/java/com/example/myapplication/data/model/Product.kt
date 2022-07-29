package com.example.myapplication.data.model

data class Product(
    val cargoCode:String = "",
    val shtrichcode: String = "",
    val remainder: Double,
    val name:String = "",
    val prise: Double = 0.0,
    var alcoholContent: String = "",
    var alcoholicBeverages: Boolean = false
) {
}