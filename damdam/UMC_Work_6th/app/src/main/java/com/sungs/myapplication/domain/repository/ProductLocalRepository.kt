package com.sungs.myapplication.domain.repository

import com.sungs.myapplication.data.ProductData
import kotlinx.coroutines.flow.Flow


interface ProductLocalRepository {

    // Home
    fun getHomeProducts(): Flow<List<ProductData>>
    suspend fun saveHomeProducts(products: List<ProductData>)

    // Shop
    fun getShopProducts(): Flow<List<ProductData>>
    suspend fun saveShopProducts(products: List<ProductData>)

    // Favorites
    fun getFavorites(): Flow<Set<String>>
    suspend fun toggleFavorite(productName: String)
}
