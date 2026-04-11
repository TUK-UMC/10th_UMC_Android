package com.example.week4

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val description: String,
    val imageRes: Int,
    val subTitle: String = "",
    val category: String = "",
    val colorsCount: String = "",
    var isFavorite: Boolean = false
)