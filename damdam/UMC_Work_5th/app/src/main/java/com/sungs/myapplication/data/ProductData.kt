package com.sungs.myapplication.data

data class ProductData(
    val name: String,
    val category: String,
    val price: String,
    val imageResId: Int,
    var isFavorite: Boolean = false
)