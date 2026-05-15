package com.sungs.myapplication.data.repository

import com.sungs.myapplication.data.model.ProductData
import com.sungs.myapplication.data.local.ProductDataStore
import com.sungs.myapplication.domain.repository.ProductLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductLocalRepositoryImpl @Inject constructor(
    private val productDataStore: ProductDataStore
) : ProductLocalRepository {

    override fun getHomeProducts(): Flow<List<ProductData>> =
        productDataStore.getHomeProducts()

    override suspend fun saveHomeProducts(products: List<ProductData>) {
        productDataStore.saveHomeProducts(products)
    }

    override fun getShopProducts(): Flow<List<ProductData>> =
        productDataStore.getShopProducts()

    override suspend fun saveShopProducts(products: List<ProductData>) {
        productDataStore.saveShopProducts(products)
    }

    override fun getFavorites(): Flow<Set<String>> =
        productDataStore.getFavorites()

    override suspend fun toggleFavorite(productName: String) {
        productDataStore.toggleFavorite(productName)
    }
}
