package com.example.week6.domain.repository

import com.example.week6.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    val homeProducts: Flow<List<Product>>
    val buyProducts: Flow<List<Product>>
    suspend fun saveHomeProducts(products: List<Product>)
    suspend fun saveBuyProducts(products: List<Product>)
}
